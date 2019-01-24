// Judy Carmody
// COSC 1430.701
// Project #7
// December 8, 2014
//

import java.util.Scanner;
public class CountLettersInLine
{
    public static void main(String[] args)
    	{
		int sum = 0;
		int i;
		double percent = 0;

        	Scanner keyboard = new Scanner(System.in);
		System.out.println("How many numbers will you enter?");
		int input= keyboard.nextInt();
       	 
		System.out.println("Enter " + input + " one per line.");
		int [] inputA = new int[input];
        
		for(i = 0 ; i < inputA.length; i++)
	        {
			inputA[i] = keyboard.nextInt();
		}

		for (i = 0; i < inputA.length; i++)
		{
			sum += inputA[i];
		}

		System.out.println("The sum of the integers entered is: " + sum);
		System.out.println("The numbers are:");

		for (i = 0; i < inputA.length; i++)
		{
			percent = inputA[i] * 100 / sum;
			System.out.println(inputA[i] + " which is "+ percent + "% of the sum.");	
		}
	}
}

