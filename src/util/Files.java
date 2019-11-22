package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Files {
	public static void createFile(String name, String content) {
		try {
			PrintWriter pw = new PrintWriter(name, "UTF-8");
			pw.print(content);
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void appendFile(String name, String content) {
		try
		{
		    FileWriter fw = new FileWriter(name, true);
		    fw.write(content);
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public static String readFile(String name) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(name));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				
				if(line != null) {
					sb.append(line);
					line = br.readLine();
				}
				String content = sb.toString();
				return content;
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		return null;
	}
}
