package server;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import configuration.model.ProxyServer;
import configuration.model.ProxyTarget;

public class JettyProxyServer {

	private Server server;

	public JettyProxyServer(ProxyServer proxyServer) {
		this.server = new Server();

		ServerConnector http;
		if (proxyServer.getHttps() == null) {
			HttpConfiguration httpConfig = new HttpConfiguration();
			HttpConnectionFactory connectionFactory = new HttpConnectionFactory(httpConfig);
			http = new ServerConnector(server, connectionFactory);
		} else {
			SslContextFactory sslContextFactory = SslContextFactoryBuilder.build(proxyServer.getHttps());
			http = new ServerConnector(server, sslContextFactory);
		}

		http.setPort(proxyServer.getPort());
		server.addConnector(http);

		HandlerCollection handlers = new HandlerCollection();
		server.setHandler(handlers);
		ServletContextHandler context = new ServletContextHandler(handlers, "/", ServletContextHandler.SESSIONS);

		for (ProxyTarget proxyTarget : proxyServer.getTargets()) {
			String path = proxyTarget.getPath();
			HttpTransparent proxy = new HttpTransparent(proxyTarget.getHttps());
			ServletHolder holderPortalProxy = new ServletHolder(proxy);
			holderPortalProxy.setInitParameter("proxyTo", proxyTarget.getHost());
			holderPortalProxy.setInitParameter("prefix", proxyTarget.getPath());
			holderPortalProxy.setAsyncSupported(true);

			String servletPath;
			if ("/".equals(path)) {
				servletPath = "/*";
			} else if (!path.endsWith("*")) {
				servletPath = path + "/*";
			} else {
				servletPath = path;
			}
			context.addServlet(holderPortalProxy, servletPath);

			System.out.println("Forwarding path " + servletPath + " on port " + proxyServer.getPort() + " to host "
					+ proxyTarget.getHost());
		}
	}

	public void start() throws Exception {
		server.start();
	}

	public void join() throws InterruptedException {
		server.join();
	}

}
