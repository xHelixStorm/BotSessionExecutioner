package commands;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;

import util.Session;
import util.STATIC;

/**
 * Class to launch a session on a linux operating system
 * @author xHelixStorm
 *
 */

public class Run {
	public static void runRun(String param) {
		//run help if no parameter has been passed
		if(param.length() > 0) {
			//start up all registered sessions
			if(param.equals("all")) {
				//iterate through all registered sessions
				for(var session : STATIC.getSessions()) {
					//be sure that the operating system is linux, else interrupt the start up
					if(SystemUtils.IS_OS_LINUX) {
						try {
							//start a screen process in the background of the system
							Process proc = Runtime.getRuntime().exec("screen -dm -S "+session.getSessionName()+" java -jar --enable-preview "+session.getJarName()+" "+(session.useParameters() && session.getParameters() != null ? " "+replaceVariables(session.getParameters(), session) : ""), null, new File("bot/"));
							proc.waitFor();
							System.out.println(session.getSessionName()+" launched!");
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
			//start up the session mentioned in the parameter
			else {
				//search for the session inside the list
				var session = STATIC.getSessions().parallelStream().filter(f -> f.getSessionName().equals(param)).findAny().orElse(null);
				if(session != null) {
					//be sure that the operating system is linux, else interrupt the start up
					if(SystemUtils.IS_OS_LINUX) {
						try {
							//start a screen process in the background of the system
							Process proc = Runtime.getRuntime().exec("screen -dm -S "+session.getSessionName()+" java -jar --enable-preview "+session.getJarName()+" "+(session.useParameters() && session.getParameters() != null ? " "+replaceVariables(session.getParameters(), session) : ""), null, new File("bot/"));
							proc.waitFor();
							System.out.println(session.getSessionName()+" launched!");
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
			System.out.println("To use this command, please type 'run all' to start all sessions or type the name of the session you wish to start. You can find all available session names with the 'session' command!");
	}
	
	/**
	 * Replace all variables by actual parameters
	 * @param parameter
	 * @param session
	 * @return
	 */
	
	private static String replaceVariables(String parameter, final Session session) {
		parameter = parameter.replaceAll("$jarName", session.getJarName());
		parameter = parameter.replaceAll("$path", session.getPath());
		parameter = parameter.replaceAll("$sessionName", session.getSessionName());
		parameter = parameter.replaceAll("$useParameters", ""+session.useParameters());
		parameter = parameter.replaceAll("$useTempDirectory", ""+session.useTempDirectory());
		parameter = parameter.replaceAll("$tempDirectory", session.getTempDirectory());
		parameter = parameter.replaceAll("$tempFileName", session.getTempFileName());
		return parameter;
	}
}
