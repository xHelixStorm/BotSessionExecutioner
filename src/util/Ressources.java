package util;

import java.util.ArrayList;

import commands.Create;
import core.IniReader;

public class Ressources {
	public static void loadRessources() {
		//verify if the ini is empty
		if(IniReader.isIniEmpty()) {
			//create the very first session if it's empty
			System.out.println("First application start detected! Running session creation wizard now!");
			Create.runCreate();
		}
		else {
			//collect all previously saved sessions
			ArrayList<Session> sessions = new ArrayList<Session>();
			IniReader.getAllSessions().stream().forEach(session -> {
				//Retrieve all options of the found session and add it to array
				Object [] options = IniReader.getWholeSession(session);
				sessions.add(new Session((String)options[0], (String)options[1], (String)options[2], (boolean)options[3], (String)options[4], (boolean)options[5], (String)options[6], (boolean)options[7], (String)options[8], (String)options[9], (boolean)options[10], (String)options[11]));
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
