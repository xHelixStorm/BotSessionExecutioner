package util;

import java.util.ArrayList;

/**
 * Class to set a static version and to save all sessions
 * @author xHelixStorm
 *
 */

public class STATIC {
	private static final String VERSION = "2.0.4";
	private static ArrayList<Session> sessions = new ArrayList<Session>();
	
	/**
	 * Retrieve current version number
	 * @return
	 */
	
	public static String getVersion() {
		return VERSION;
	}
	
	/**
	 * Save all retrieved sessions from Ressources.class
	 * @param _sessions
	 */
	
	public static void setSessions(ArrayList<Session> _sessions) {
		sessions = _sessions;
	}
	
	/**
	 * Add a new session to save
	 * @param _session
	 */
	
	public static void addSession(Session _session) {
		sessions.add(_session);
	}
	
	/**
	 * Retrieve all saved sessions
	 * @return
	 */
	
	public static ArrayList<Session> getSessions() {
		return sessions;
	}
}
