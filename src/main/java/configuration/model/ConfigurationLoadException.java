package configuration.model;

public class ConfigurationLoadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationLoadException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
