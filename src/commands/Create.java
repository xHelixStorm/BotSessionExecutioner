package commands;

import java.util.HashMap;
import java.util.Scanner;

import core.IniReader;
import util.Session;
import util.STATIC;

/**
 * Create a new session basing on question and answer
 * @author xHelixStorm
 *
 */

public class Create {
	public static void runCreate() {
		System.out.println("To interrupt the creation process, type 'exit' on every occasion!");
		
		//create scanner object to read text input from console
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		//create a map to collect all options
		HashMap<String, String> options = new HashMap<String, String>();
		
		//session name
		System.out.println("Please give your new session a name");
		do {
			System.out.print(">");
			var sessionName = scan.next();
			if(sessionName.equals("exit"))
				return;
			else
				options.put("sessionName", sessionName);
			
		} while(options.get("sessionName") == null);
		
		//jar file name
		System.out.println("Please insert the name of the callable jar file");
		do {
			System.out.print(">");
			var jarName = scan.nextLine();
			if(jarName.equals("exit"))
				return;
			else if(jarName.length() > 4 && jarName.endsWith(".jar"))
				options.put("jarName", jarName);
			else
				System.out.println("Please write a valid jar file name which ends with '.jar'!");
		} while(options.get("jarName") == null);
		
		//application path
		System.out.println("Please insert the path to the directory where the jar file resides");
		do {
			System.out.print(">");
			var path = scan.nextLine();
			if(path.equals("exit"))
				return;
			else
				options.put("path", (path.endsWith("/") ? path : path+"/"));
			
		} while(options.get("path") == null);
		
		//allow the use of parameters
		System.out.println("Do you wish to execute this application with self defined parameters? (Y/N)");
		do {
			System.out.print(">");
			var useParameters = scan.nextLine();
			if(useParameters.equals("exit"))
				return;
			else if(useParameters.equalsIgnoreCase("y"))
				options.put("useParameters", "true");
			else if(useParameters.equalsIgnoreCase("n"))
				options.put("useParameters", "false");
			else
				System.out.println("Please write either Y or N! ");
					
		} while(options.get("useParameters") == null);
		
		//parameters
		if(options.get("useParameters").equals("true")) {
			System.out.println("Please define your parameters. Each parameter should be separated by a blank space");
			do {
				System.out.print(">");
				var parameters = scan.nextLine();
				if(parameters.equals("exit"))
					return;
				else
					options.put("parameters", parameters);
			} while(options.get("parameters") == null);
		}
		else
			options.put("parameters", "");
		
		//allow the use of a temp directory
		System.out.println("Does your application use a temporary directory to check, if the application is already running? (Y/N)");
		do {
			System.out.print(">");
			var useTempDirectory = scan.nextLine();
			if(useTempDirectory.equals("exit"))
				return;
			else if(useTempDirectory.equalsIgnoreCase("y"))
				options.put("useTempDirectory", "true");
			else if(useTempDirectory.equalsIgnoreCase("n"))
				options.put("useTempDirectory", "false");
			else
				System.out.println("Please write either Y or N!");
		} while(options.get("useTempDirectory") == null);
		
		if(options.get("useTempDirectory").equals("true")) {
			//temp directory
			System.out.println("Please write down the path to the application's temporary directory");
			do {
				System.out.print(">");
				var tempDirectory = scan.nextLine();
				if(tempDirectory.equals("exit"))
					return;
				else
					options.put("tempDirectory", (tempDirectory.endsWith("/") ? tempDirectory : tempDirectory+"/"));
			} while(options.get("tempDirectory") == null);
			
			//temp file name
			System.out.println("Please write the name of the temporary file which resides inside the temporary directory");
			do {
				System.out.print(">");
				var tempFileName = scan.nextLine();
				if(tempFileName.equals("exit"))
					return;
				else if(tempFileName.length() > 4)
					options.put("tempFileName", tempFileName);
				else
					System.out.println("Please register a file name with a proper file terminator!");
			} while(options.get("tempFileName") == null);
		}
		else {
			options.put("tempDirectory", "");
			options.put("tempFileName", "");
		}
		
		//Save the new session into cache
		STATIC.addSession(new Session(
			options.get("jarName"),
			options.get("path"),
			options.get("sessionName"),
			Boolean.parseBoolean(options.get("useParameters")),
			options.get("parameters"),
			Boolean.parseBoolean(options.get("useTempDirectory")),
			options.get("tempDirectory"),
			options.get("tempFileName")
		));
		
		//create a new section in the ini file
		IniReader.createSection(options);
	}
}
