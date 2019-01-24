// Judy Carmody
// COSC 1430.701
// Project #3
// Due: October 20, 2014
// Averages the values the user inputs and finds the highest and lowest number out of the given values.

import java.util.Scanner;

public class LargeSmallAverage
{
	public static void main(String[] args)
	{
		System.out.println("This program will list a");
		System.out.println("minimum number, a maximum number,");
		System.out.println("and the average of non-negative numbers.");

		Scanner keyboard = new Scanner(System.in);
		int counter;
		double totalValue;
	        double maxValue;
	        double minValue;
		double next;
		String answer;

		do
		{
			System.out.println();
			System.out.println("Enter values.");
			System.out.println("When finished, enter a negative number to begin calculation.");
			next = keyboard.nextDouble();
			counter = 0;
			totalValue = 0;
			minValue = Double.MAX_VALUE;
			maxValue = Double.MIN_VALUE;
			boolean getNextValue = true;
			while (getNextValue)
			{
				if (next > 0 )
				{	if (next > maxValue)
                				maxValue = next;
					if (next < minValue)
						minValue = next;
			
					counter++;
					totalValue = totalValue + next;
					next = keyboard.nextDouble();

				}
				else
					getNextValue = false;

			}
			if (counter > 0)
			{
				double average = totalValue / counter;
				System.out.println();
				System.out.println("Minimum: " + minValue);
				System.out.println("Maximum: " + maxValue);
				System.out.println("Average: " + average);
				System.out.println();
			}
		System.out.println("Would you like to go again? yes/no");
		answer = keyboard.next();
		} while (answer.equalsIgnoreCase("yes"));
	}
}
