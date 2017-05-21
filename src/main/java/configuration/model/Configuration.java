package configuration.model;

import java.util.Set;

import lombok.NonNull;
import lombok.Value;

@Value
public class Configuration {

	@NonNull
	private Set<ProxyServer> proxies;

}
