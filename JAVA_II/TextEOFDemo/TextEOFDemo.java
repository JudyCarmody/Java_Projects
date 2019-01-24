import java.io.*;

/*****************************************************************************
*Makes storylines.txt the same as story.txt but with each line numbered.
******************************************************************************/
public class TextEOFDemo
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader inputStream = new BufferedReader(new FileReader("story.txt"));
			PrintWriter outputStream = new PrintWriter(new FileOutputStream("storylines.txt"));
			int count = 0;
			String line = inputStream.readLine();
			while(line !=null)
			{
				count++;
				outputStream.println(count+" "+line);
				line = inputStream.readLine();
			}
			System.out.println(count+" lines written to storylines.txt");

			inputStream.close();
			outputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File story.txt not found");
		}
		catch(IOException e)
		{
			System.out.println("Error reading from file story.txt.");
		}
	}
}