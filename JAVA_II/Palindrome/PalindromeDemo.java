/*
 Carmody, Judy
 COSC 2430.001
 Project # 2
 Due: Feb. 18, 2015
 Class: PalindromeDemo -- Checks a string to find whether or not it is a palindrome
*/

import java.util.Scanner;

public class PalindromeDemo
{
	private String strInput;
	private String lowerString;
	private String noSpacesStr;
	private String yn;
	Scanner keyboard = new Scanner(System.in);
	public char [] charArray = new char[80];

	public static void main(String[] args)
	{
		PalindromeDemo input = new PalindromeDemo();
		input.getInput();
	}

	public void getInput()
	{
		yn = "y";
		do
		{
			System.out.println("Enter a string.");
			strInput = keyboard.nextLine();
			lowerString = strInput.toLowerCase();
			noSpacesStr = lowerString.replaceAll("[^a-zA-Z]","");
			charArray = noSpacesStr.toCharArray();

			if (charArray.length >= 81)
			{
				System.out.println("Maximum number of characters is 80.   Characters entered: " + charArray.length);
				System.out.println("Try again.");
				strInput = keyboard.nextLine();
			}
		
			else
			{
				Palindrome check = new Palindrome();
				check.checkPal(charArray);
			}

			System.out.println("Would you like to check another string?  (y/n)");
			yn = keyboard.nextLine();
		
		}while(yn.equalsIgnoreCase("y"));
	}

	public static void palResult(boolean isPal)
	{
		if (isPal == true)
		{
			System.out.println("YES, this is a palindrome!");
			System.out.println();
		}
			
		else
		{
			System.out.println("NO, this is not a palindrome!");
			System.out.println();
		}
	}
}
