package commands;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.SystemUtils;

import util.Files;
import util.STATIC;

/**
 * Class to terminate a running session
 * @author xHelixStorm
 *
 */

public class Terminate {
	public static void runTerminate(String [] params) {
		//run help if no parameter has been passed
		if(params.length > 0) {
			final var param = params[0];
			String message = "";
			for(int i = 1; i<params.length; i++) {
				message += params[i]+" ";
			}
			message = message.trim();
			//shut down all registered sessions
			if(param.equals("all")) {
				//iterate through all registered sessions
				for(var session : STATIC.getSessions()) {
					//verify if a rest termination should be used
					if(!session.getRest()) {
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
					else {
						if(session.getParameters().length() > 0) {
							final var token = session.getParameters().split(" ")[0];
							if(restTermination(token, session.getRestURL(), message)) {
								System.out.println(session.getSessionName()+" terminated!");
							}
							else {
								System.out.println(session.getSessionName()+" couldn't be terminated! Rest error!");
							}
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						else {
							System.out.println(session.getSessionName()+" couldn't be terminated. Token required as param!");
						}
					}
				}
			}
			//shut down the session mentioned in the parameter
			else {
				//search for the session inside the list
				var session = STATIC.getSessions().parallelStream().filter(f -> f.getSessionName().equals(param)).findAny().orElse(null);
				if(session != null) {
					//verify if a rest termination should be used
					if(!session.getRest()) {
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
						if(session.getParameters().length() > 0) {
							final var token = session.getParameters().split(" ")[0];
							if(restTermination(token, session.getRestURL(), message)) {
								System.out.println(session.getSessionName()+" terminated!");
							}
							else {
								System.out.println(session.getSessionName()+" couldn't be terminated! Rest error!");
							}
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						else {
							System.out.println(session.getSessionName()+" couldn't be terminated. Token required as param!");
						}
					}
				}
				else {
					System.out.println("Session name doesn't exist! Please check with the 'session' command, if the name has been typed correctly!");
				}
			}
		}
		else
			System.out.println("To use this command, please type 'terminate all' to terminate all sessions or type the name of the session you wish to terminate. You can find all available session names with the 'session' command! You may also submit a message, if a rest termination is used!");
	}
	
	private static boolean restTermination(String token, String link, String message) {
		try {
			URL url = new URL(link);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String json = "{\"token\": \""+token+"\", \"type\": \"shutdown\""+(message.length() > 0 ? "\"message\": \""+message+"\"" : "")+"}";
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = json.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			con.connect();
			int response = con.getResponseCode();
			if(response >= 200 && response <= 399) {
				return true;
			}
			else {
				return false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
