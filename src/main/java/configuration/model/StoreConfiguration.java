package configuration.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class StoreConfiguration {

	@NonNull
	private String path;
	@NonNull
	private String password;
	private String type;

}
