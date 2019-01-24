/*
 Carmody, Judy
 COSC 2430.001
 Project # 2
 Due: Feb. 18, 2015
 Class: Palindrome -- Checks a string to find whether or not it is a palindrome
*/

public class Palindrome
{	
	private boolean isPal = true;

	public boolean isPalindrome()
	{
		return isPal;
	}

	public void checkPal(char[] palArray)
	{
		char[] revArray = new char[palArray.length];

		for (int i = 0; i < palArray.length; i++)
		{
			revArray[i] = palArray[palArray.length - 1 - i];
		}

		int index = 0;
		
		do
		{
			for (index = 0; index < palArray.length; index++)
				if (palArray[index] != revArray[index])
				{
					isPal = false;
				}
		}while(index < palArray.length - 1);
		
		PalindromeDemo.palResult(isPal);
	}
}
