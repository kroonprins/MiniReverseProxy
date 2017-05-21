package configuration.reader.yaml;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import configuration.model.Configuration;
import configuration.model.ConfigurationLoadException;
import configuration.reader.ConfigurationReader;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class YamlConfigurationReader implements ConfigurationReader {

	private static final ObjectMapper JACKSON_YAML_MAPPER = new ObjectMapper(new YAMLFactory());

	@NonNull
	private String location;

	@Override
	public Configuration load() {
		try {
			return JACKSON_YAML_MAPPER.readValue(new File(location), Configuration.class);
		} catch (IOException exc) {
			throw new ConfigurationLoadException("Could not load yml file " + location, exc);
		}
	}

}
