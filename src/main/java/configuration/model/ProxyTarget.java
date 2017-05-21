package configuration.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class ProxyTarget {

	@NonNull
	private String path;
	@NonNull
	private String host;
	private HttpsConfiguration https;

}
