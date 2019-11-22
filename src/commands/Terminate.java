package commands;

import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;

import util.Files;
import util.STATIC;

/**
 * Class to terminate a running session
 * @author xHelixStorm
 *
 */

public class Terminate {
	public static void runTerminate(String param) {
		//run help if no parameter has been passed
		if(param.length() > 0) {
			//shut down all registered sessions
			if(param.equals("all")) {
				//iterate through all registered sessions
				for(var session : STATIC.getSessions()) {
					//be sure that the operating system is linux, else interrupt the start up
					if(SystemUtils.IS_OS_LINUX) {
						try {
							//terminate the screen process
							Process proc = Runtime.getRuntime().exec("screen -X -S "+session.getSessionName()+" quit");
							proc.waitFor();
							//overwrite the temp file to not running
							if(session.useTempDirectory() && session.getTempDirectory() != null) {
								Files.createFile(session.getTempDirectory()+session.getTempFileName(), "0");
							}
							System.out.println(session.getSessionName()+" terminated!");
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("Operating system is not supported! Command interrupted!");
						return;
					}
				}
			}
			//shut down the session mentioned in the parameter
			else {
				//search for the session inside the list
				var session = STATIC.getSessions().parallelStream().filter(f -> f.getSessionName().equals(param)).findAny().orElse(null);
				if(session != null) {
					//be sure that the operating system is linux, else interrupt the start up
					if(SystemUtils.IS_OS_LINUX) {
						try {
							//terminate the screen process
							Process proc = Runtime.getRuntime().exec("screen -X -S "+session.getSessionName()+" quit");
							proc.waitFor();
							//overwrite the temp file to not running
							if(session.useTempDirectory() && session.getTempDirectory() != null) {
								Files.createFile(session.getTempDirectory()+session.getTempFileName(), "0");
							}
							System.out.println(session.getSessionName()+" terminated!");
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("Operating system is not supported! Command interrupted!");
					}
				}
				else {
					System.out.println("Session name doesn't exist! Please check with the 'session' command, if the name has been typed correctly!");
				}
			}
		}
		else
			System.out.println("To use this command, please type 'terminate all' to terminate all sessions or type the name of the session you wish to terminate. You can find all available session names with the 'session' command!");
	}
}
