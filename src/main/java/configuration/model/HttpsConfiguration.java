package configuration.model;

import lombok.Value;

@Value
public class HttpsConfiguration {

	private StoreConfiguration keyStore;
	private StoreConfiguration trustStore;
	private String alias;
}
