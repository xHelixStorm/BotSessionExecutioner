package core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.ini4j.Ini;

/**
 * Class to interact with the ini file
 * @author xHelixStorm
 *
 */

public class IniReader {
	
	/**
	 * Open the ini file
	 * @return
	 */
	
	private static Ini readConfig() {
		try {
			return new Ini(new File("config.ini"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Check if the ini file is empty
	 * @return
	 */
	
	public static boolean isIniEmpty() {
		Ini ini = readConfig();
		return ini.isEmpty();
	}
	
	/**
	 * Retrieve all saved sections (sessions)
	 * @return
	 */
	
	public static Set<String> getAllSessions() {
		Ini ini = readConfig();
		return ini.keySet();
	}
	
	/**
	 * Retrieve all options of the current session
	 * @param session
	 * @return
	 */
	
	public static Object[] getWholeSession(String session) {
		Ini ini = readConfig();
		Ini.Section section = ini.get(session);
		Object [] options = new Object[12];
		options[0] = section.get("jarName");
		options[1] = section.get("path");
		options[2] = section.get("sessionName");
		options[3] = section.get("useParameters", boolean.class);
		options[4] = section.get("parameters");
		options[5] = section.get("useVmParameters", boolean.class);
		options[6] = section.get("vmParameters");
		options[7] = section.get("useTempDirectory", boolean.class);
		options[8] = section.get("tempDirectory");
		options[9] = section.get("tempFileName");
		options[10] = section.get("rest", boolean.class);
		options[11] = section.get("restUrl");
		return options;
	}
	
	/**
	 * Create a new section in the ini file
	 * @param options
	 */
	
	public static void createSection(HashMap<String, String> options) {
		Ini ini = readConfig();
		String sectionName = options.get("sessionName");
		ini.add(sectionName, "sessionName", sectionName);
		ini.add(sectionName, "jarName", options.get("jarName"));
		ini.add(sectionName, "path", options.get("path"));
		ini.add(sectionName, "useParameters", options.get("useParameters"));
		ini.add(sectionName, "parameters", options.get("parameters"));
		ini.add(sectionName, "useVmParameters", options.get("useVmParameters"));
		ini.add(sectionName, "vmParameters", options.get("vmParameters"));
		ini.add(sectionName, "useTempDirectory", options.get("useTempDirectory"));
		ini.add(sectionName, "tempDirectory", options.get("tempDirectory"));
		ini.add(sectionName, "tempFileName", options.get("tempFileName"));
		
		try {
			ini.store();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
