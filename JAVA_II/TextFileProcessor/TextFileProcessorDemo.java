/*
 Carmody, Judy
 COSC 2430.001
 Project # 5
 Due: April 15, 2015
Gets input from a file, modifies the input, and creates a new file with the modified input.
*/

import java.util.Scanner;

public class TextFileProcessorDemo
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter the file name you want to open. ");
		String inputFile = keyboard.nextLine();

		System.out.println();

		System.out.println("Enter the file name you want to save to. ");
		String outputFile = keyboard.nextLine();

		System.out.println();
		TextFileProcessor getFile = new TextFileProcessor();
		getFile.writeToFile(inputFile, outputFile);
	}
}
