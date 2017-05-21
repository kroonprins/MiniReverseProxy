package server;

import org.eclipse.jetty.util.ssl.SslContextFactory;

import configuration.model.HttpsConfiguration;
import configuration.model.StoreConfiguration;
import lombok.NonNull;

public class SslContextFactoryBuilder {

	public static SslContextFactory build(@NonNull HttpsConfiguration httpsConfiguration) {
		SslContextFactory sslContextFactory = new SslContextFactory();

		StoreConfiguration keyStore = httpsConfiguration.getKeyStore();
		if (keyStore != null) {
			sslContextFactory.setKeyStorePath(keyStore.getPath());
			sslContextFactory.setKeyStorePassword(keyStore.getPassword());
			sslContextFactory.setKeyStoreType(keyStore.getType());
		}

		StoreConfiguration trustStore = httpsConfiguration.getTrustStore();
		if (keyStore != null) {
			sslContextFactory.setTrustStorePath(trustStore.getPath());
			sslContextFactory.setTrustStorePassword(trustStore.getPassword());
			sslContextFactory.setTrustStoreType(trustStore.getType());
		}

		if (httpsConfiguration != null) {
			sslContextFactory.setCertAlias(httpsConfiguration.getAlias());
		}

		return sslContextFactory;
	}
}
