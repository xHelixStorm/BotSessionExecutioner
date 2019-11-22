package core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.ini4j.Ini;

public class IniReader {
	private static Ini readConfig() {
		try {
			return new Ini(new File("config.ini"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isIniEmpty() {
		Ini ini = readConfig();
		return ini.isEmpty();
	}
	
	public static Set<String> getAllSessions() {
		Ini ini = readConfig();
		return ini.keySet();
	}
	
	public static Object[] getWholeSession(String session) {
		Ini ini = readConfig();
		Ini.Section section = ini.get(session);
		Object [] options = new Object[8];
		options[0] = section.get("jarName");
		options[1] = section.get("path");
		options[2] = section.get("sessionName");
		options[3] = section.get("useParameters", boolean.class);
		options[4] = section.get("parameters");
		options[5] = section.get("useTempDirectory", boolean.class);
		options[6] = section.get("tempDirectory");
		options[7] = section.get("tempFileName");
		return options;
	}
	
	public static String getJarName() {
		Ini ini = readConfig();
		return ini.get("Executioner", "JarName");
	}
	public static boolean getTakeNames() {
		Ini ini = readConfig();
		return ini.get("Executioner", "TakeNames", boolean.class);
	}
	public static boolean getTakeParams() {
		Ini ini = readConfig();
		return ini.get("Executioner", "TakeParams", boolean.class);
	}
	public static boolean getTakePaths() {
		Ini ini = readConfig();
		return ini.get("Executioner", "TakePaths", boolean.class);
	}
	public static boolean getPassNames() {
		Ini ini = readConfig();
		return ini.get("Executioner", "PassNames", boolean.class);
	}
	public static List<String> getTokens() {
		Ini ini = readConfig();
		Ini.Section section = ini.get("Tokens");
		return section.getAll("Token");
	}
	public static List<String> getNames() {
		Ini ini = readConfig();
		Ini.Section section = ini.get("Names");
		return section.getAll("Name");
	}
	public static List<String> getParams() {
		Ini ini = readConfig();
		Ini.Section section = ini.get("Params");
		return section.getAll("Param");
	}
	public static List<String> getPaths() {
		Ini ini = readConfig();
		Ini.Section section = ini.get("Paths");
		return section.getAll("Path");
	}
}
