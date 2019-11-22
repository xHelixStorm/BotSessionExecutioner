package commands;

import java.io.File;

import util.Files;
import util.STATIC;

/**
 * Display all sessions and their status
 * @author xHelixStorm
 *
 */

public class Session {
	public static void runSession() {
		//iterate through all saved sessions from the ini file
		for(final var session : STATIC.getSessions()) {
			var status = "";
			//confirm that a temporary directory exists and should be used. Else display that the status is unknown
			if(session.useTempDirectory() && new File(session.getTempDirectory()).exists()) {
				var fileName = session.getTempDirectory()+session.getTempFileName();
				//confirm that the file exists
				if(new File(fileName).exists()) {
					//read the file content and set the status
					var content = Files.readFile(fileName);
					if(content.equals("0"))
						status = "NOT RUNNING";
					else if(content.equals("1"))
						status = "RUNNING";
					else
						status = "UNKNOWN";
				}
				else
					status = "NOT RUNNING";
			}
			else
				status = "UNKNOWN";
			
			//display the status on the screen
			System.out.println("["+session.getSessionName()+"] STATUS ["+status+"]");
		}
	}
}
