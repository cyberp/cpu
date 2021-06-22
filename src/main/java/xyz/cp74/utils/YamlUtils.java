package xyz.cp74.utils;

import java.io.File;
import java.util.Map;
import org.tinylog.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * 
 * YamlUtils.
 * Read and Write YAML files.
 * 
 * @author Christian Paul
 *
 */
public class YamlUtils {


    /**
     * Read YAML file as map
     * @param filename
     * @return
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
	 * Write object to YAML file
	 * @param object
	 * @param filename
	 * @return
	 */
	public static File write(Object object, String filename) {
		YAMLFactory yamlFac = new YAMLFactory();
		yamlFac.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
		yamlFac.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
			File f = new File(filename);
			mapper.writeValue(f, object);
			return f;
		} catch (Exception e) {Logger.error(e, "file not writable"); }
		return null;
	}
    
	/**
	 * Write object to YAML String
	 * @param object
	 * @return
	 */
	public static String write(Object object) {
		YAMLFactory yamlFac = new YAMLFactory();
		yamlFac.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
		yamlFac.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {Logger.error(e, "yaml not writable"); }
		return null;
	}

	/**
     * Read YAML file and create object
     * @param filename
     * @param clazz
     * @return
     */
	public static Object read(String filename, Class<?> clazz) {
		Object o = null;
        YAMLFactory yamlFac = new YAMLFactory();
		ObjectMapper mapper = new ObjectMapper(yamlFac);
		try {
			File f = new File(filename);
			o = mapper.readValue(f, clazz);
		} catch (Exception e) { Logger.error(e, "file not readable"); }
		return o;
	}

}