package configuration.model;

import lombok.Value;

@Value
public class StoreConfiguration {

	private String path;
	private String password;
	private String type;

}
