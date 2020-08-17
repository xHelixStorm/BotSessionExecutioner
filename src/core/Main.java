package core;

import java.util.Scanner;

import commands.About;
import commands.Commands;
import commands.Create;
import commands.Run;
import commands.Session;
import commands.Terminate;
import util.Ressources;
import util.STATIC;

/**
 * Application to start multiple sessions of the same jar files
 * @author xHelixStorm
 *
 */

public class Main {
	@SuppressWarnings("resource")
	public static void main(String [] args) {
		Ressources.loadRessources();
		if(STATIC.getSessions().size() > 0) {
			System.out.println("DiscordBotSessionExecutioner v"+STATIC.getVersion()+" is now running. Type 'commands' to display all available commands!");
			Scanner scan = new Scanner(System.in);
			var param = "";
			do {
				System.out.print(">");
				param = scan.nextLine();
				
				if(param.equals("exit"))
					break;
				else if(param.equals("commands"))
					Commands.runCommands();
				else if(param.equals("about"))
					About.runAbout();
				else if(param.equals("create"))
					Create.runCreate();
				else if(param.equals("session"))
					Session.runSession();
				else if(param.startsWith("run"))
					Run.runRun(param.replaceFirst("run", "").trim());
				else if(param.startsWith("terminate")) 
					Terminate.runTerminate(param.replaceFirst("terminate", "").trim().split(" "));
				else
					System.out.println("Invalid command!");
			} while(true);
			System.out.println("BotSessionExecutioner terminated!");
		}
		else {
			System.out.println("Application terminated because there are no registerd sessions!");
		}
	}
}
