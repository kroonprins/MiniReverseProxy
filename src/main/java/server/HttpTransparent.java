package server;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.proxy.AsyncProxyServlet;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import configuration.model.HttpsConfiguration;

public class HttpTransparent extends AsyncProxyServlet.Transparent {

	private static final long serialVersionUID = 1L;
	private HttpsConfiguration httpsConfiguration;

	public HttpTransparent() {
		this(null);
	}

	public HttpTransparent(HttpsConfiguration httpsConfiguration) {
		this.httpsConfiguration = httpsConfiguration;
	}

	@Override
	protected void addProxyHeaders(HttpServletRequest clientRequest, Request proxyRequest) {
		// Don't let the proxy add headers
	}

	@Override
	protected HttpClient newHttpClient() {
		HttpClient client;
		if (this.httpsConfiguration != null) {
			SslContextFactory sslContextFactory = SslContextFactoryBuilder.build(this.httpsConfiguration);
			client = new HttpClient(sslContextFactory);
		} else {
			client = super.newHttpClient();
		}
		client.setRequestBufferSize(1000000);
		client.setResponseBufferSize(1000000);
		return client;
	}

}
