package configuration.model;

import java.util.Set;

import lombok.NonNull;
import lombok.Value;

@Value
public class ProxyServer {

	@NonNull
	private Integer port;
	@NonNull
	private Set<ProxyTarget> targets;
	private HttpsConfiguration https;

}
