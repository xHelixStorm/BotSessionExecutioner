package commands;

/**
 * Display all available commands
 * @author xHelixStorm
 *
 */

public class Commands {
	public static void runCommands() {
		System.out.println("These are all available commands:\n"
			+ "about:     Show details of the session executioner\n"
			+ "create:	  Create a new session\n"
			+ "copy: 	  Copy an existing session (in development)\n"
			+ "edit:	  Edit all options of the existing session (in development)\n"
			+ "session:   Inspect available sessions and display the current status\n"
			+ "run: 	   Run a Bot session\n"
			+ "terminate: Terminate a Bot session");
	}
}
