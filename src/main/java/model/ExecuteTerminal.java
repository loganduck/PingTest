package model;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author LoganDuck
 * @version 1
 */
public class ExecuteTerminal extends Thread {
	private static BufferedReader reader;
	private static String command = "ping -c 100 google.com";

	private static PrintWriter writer;
	private static Scanner scan;
	
	
	public ExecuteTerminal() {
		executeCommand();
	}

	public static void executeCommand() {
		Thread thread = new Thread() {
			public void run() {
				Process process;
				try {
					process = Runtime.getRuntime().exec(command);
					reader = new BufferedReader(new InputStreamReader(process.getInputStream()));					
					scan = new Scanner(reader);
					
					File file = new File(System.getProperty("user.home") + "/Desktop/PingTest.txt");
					writer = new PrintWriter(file);
					while (scan.hasNext()) {
						writer.println(scan.nextLine());
					}
					writer.close();
					scan.close();
					
					Desktop.getDesktop().open(file);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
}