package xyz.cp74.utils;

import java.io.File;
import java.util.Map;
import org.tinylog.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * 
 * YamlUtils
 * 
 * Read and Write YAML files.
 * 
 * @author Christian Paul
 *
 */
public class YamlUtils {


    /**
     * Read YAML file with @param filename and @return map
     */
    public static Map<String, Object> readMap(String filename) {
		Map<String, Object> o = null;
        YAMLFactory yamlFac = new YAMLFactory();
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
            File f = new File(filename);
			o = mapper.readValue(f,  new TypeReference<Map<String, Object>>(){});
		} catch (Exception e) { Logger.error(e, "file not readable"); }
		return o;
    }

	/**
	 * Write @param object to YAML file with @param filename
	 * @return File
	 */
	public static File write(Object object, String filename) {
		YAMLFactory yamlFac = new YAMLFactory();
		yamlFac.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
		yamlFac.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
		yamlFac.disable(YAMLGenerator.Feature.SPLIT_LINES);
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
			File f = new File(filename);
			mapper.writeValue(f, object);
			return f;
		} catch (Exception e) {Logger.error(e, "file not writable"); }
		return null;
	}
    
	/**
	 * Write @param object to YAML file to string
	 * @return String
	 */
	public static String write(Object object) {
		YAMLFactory yamlFac = new YAMLFactory();
		yamlFac.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
		yamlFac.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
		yamlFac.disable(YAMLGenerator.Feature.SPLIT_LINES);
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {Logger.error(e, "yaml not writable"); }
		return null;
	}

	/**
     * Read YAML file with @param filename and create object of @param clazz
     * @return Object
     */
	public static Object read(String filename, Class<?> clazz) {
		Object o = null;
        YAMLFactory yamlFac = new YAMLFactory();
		ObjectMapper mapper = new ObjectMapper(yamlFac);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			File f = new File(filename);
			o = mapper.readValue(f, clazz);
		} catch (Exception e) { Logger.error(e, "file not readable"); }
		return o;
	}

	/**
     * Read YAML @param content from string and create object of @param clazz
     * @return Object
     */
	public static Object readString(String content, Class<?> clazz) {
		Object o = null;
        YAMLFactory yamlFac = new YAMLFactory();
		ObjectMapper mapper = new ObjectMapper(yamlFac);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			o = mapper.readValue(content, clazz);
		} catch (Exception e) { Logger.error(e, "file not readable"); }
		return o;
	}
}
