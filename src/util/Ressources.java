package util;

import java.util.ArrayList;

import core.IniReader;

public class Ressources {
	public static void loadRessources() {
		//verify if the ini is empty
		if(IniReader.isIniEmpty()) {
			//create the very first session if it's empty
		}
		else {
			//collect all previously saved sessions
			ArrayList<Session> sessions = new ArrayList<Session>();
			IniReader.getAllSessions().parallelStream().forEach(session -> {
				//Retrieve all options of the found session and add it to array
				Object [] options = IniReader.getWholeSession(session);
				sessions.add(new Session((String)options[0], (String)options[1], (String)options[2], (boolean)options[3], (String)options[4], (boolean)options[5], (String)options[6], (String)options[7]));
			});
			//save retrieved sessions
			if(sessions.size() > 0) {
				STATIC.setSessions(sessions);
				System.out.println("Ressource load successfull!");
			}
			else {
				System.err.println("Ressource load error! Program terminated! Tokens have not been provided!");
				System.exit(1);
			}
		}
	}
}
