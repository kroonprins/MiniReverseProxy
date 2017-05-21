package main;

import java.util.ArrayList;
import java.util.List;

import configuration.model.Configuration;
import configuration.model.ProxyServer;
import configuration.reader.ConfigurationReader;
import configuration.reader.yaml.YamlConfigurationReader;
import server.JettyProxyServer;

public class MiniReverseProxy {

	public static void main(String[] args) throws Exception {

		String configFile = "test.yml";
		if (args.length > 0) {
			configFile = args[0];
		} else {
			System.out.println("WARNING: configuration file not specified. Starting the sample test.yml file.");
		}

		ConfigurationReader configurationReader = YamlConfigurationReader.builder().location(configFile).build();
		Configuration configuration = configurationReader.load();

		List<JettyProxyServer> jettyProxyServers = new ArrayList<>();
		for (ProxyServer proxyServer : configuration.getProxies()) {
			JettyProxyServer jettyProxyServer = new JettyProxyServer(proxyServer);
			jettyProxyServers.add(jettyProxyServer);
			jettyProxyServer.start();
		}

		for (JettyProxyServer jettyProxyServer : jettyProxyServers) {
			jettyProxyServer.join();
		}
	}
}
