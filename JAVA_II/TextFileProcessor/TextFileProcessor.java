/*
 Carmody, Judy
 COSC 2430.001
 Project # 5
 Due: April 15, 2015
 Gets input from a file, modifies the input, and creates a new file with the modified input.
*/

import java.io.*;

public class TextFileProcessor
{
	public String line;
	private String noSpaces;
	private String lowerCase;
	private char[] lineArray;
	private BufferedReader inputStream;
	private PrintWriter outputStream;

	public void writeToFile(String inputFile, String outputFile)
	{
		try
		{
			inputStream = new BufferedReader(new FileReader(inputFile));
			outputStream = new PrintWriter(new FileOutputStream(outputFile));

			line = inputStream.readLine();
			while(line != null)
			{
				lowerCase = line.toLowerCase();
				noSpaces = lowerCase.replaceAll("\\s+"," ");
				lineArray = noSpaces.toCharArray();

				for (int i = 0; i < lineArray.length -1; i++)
				{
					lineArray[0] = Character.toUpperCase(lineArray[0]);
					
					if (lineArray[i] == '.' || lineArray[i] == '!' || lineArray[i] == '?' && lineArray[i+1] == ' ')
					{	
						lineArray[i+2] = Character.toUpperCase(lineArray[i+2]);			
					}	
				}	

				String str = new String (lineArray);
				String [] strA = str.split("(?=[.!])");
				strA =  str.split("(?<=[.!])");

				for (String strNew : strA)
				{
					String strNewTrim = strNew.trim();
					outputStream.write(strNewTrim + "\r\n");
				}

				line = inputStream.readLine();
				inputStream.close();
			}

			System.out.println("File " + outputFile + " created.");
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("File " + inputFile + " not found. \nProgram ending.");
			System.exit(0);
		}
		
		catch(IOException e)
		{
			System.out.println("Error reading from file " + inputFile + ". \nProgram ending.");
			System.exit(0);
		}

		finally
		{
			outputStream.close();
		}
	}
}
