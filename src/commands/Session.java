package commands;

import java.io.BufferedReader;
//import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.SystemUtils;

//import util.Files;
import util.STATIC;

/**
 * Display all sessions and their status
 * @author xHelixStorm
 *
 */

public class Session {
	public static void runSession() {
		if(SystemUtils.IS_OS_LINUX) {
			//display all running sessions and save to string
			StringBuilder response = new StringBuilder();
			try {
				Process proc = Runtime.getRuntime().exec("ls -laR /var/run/screen/");
				proc.waitFor();
				BufferedReader out = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String line = null;
				while((line = out.readLine()) != null) {
					response.append(line);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			
			//iterate through all saved sessions from the ini file
			for(final var session : STATIC.getSessions()) {
				var status = "";
				if(response.toString().contains(session.getSessionName())) {
					status = "RUNNING";
				}
				else {
					status = "NOT RUNNING";
				}
				
				//display the status on the screen
				System.out.println("["+session.getSessionName()+"] STATUS ["+status+"]");
			}
		}
		else {
			System.out.println("Operating system is not supported! Command interrupted!");
		}
	}
}
