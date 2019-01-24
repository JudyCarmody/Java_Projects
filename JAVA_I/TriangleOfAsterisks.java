// Judy Carmody
// COSC 1430.701
// Project 4
// October 31, 2014
// Enter a whole number and get an output of a triangle of asterisks.

import java.util.Scanner;
public class TriangleOfAsterisks
{
	public static void main(String[] args)
	{
		System.out.println("Enter a whole number from 1 to 50");
		System.out.println("and I will print out a triangle of asterisks.");
		int count, a, b, c;
		Scanner keyboard = new Scanner(System.in);
		count = keyboard.nextInt();
		
		System.out.println();
		
		for (a = 1; a <= count; a++)
		{
			for (b = 1; b <= a; b++)
			{
				System.out.print("*");
			}
			for (c = count; c >= a; c--)
			{
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		
		for (a = 1; a <= count; a++)
		{
			for (b = (count - 1); b >= a; b--)
			{
				System.out.print("*");
			}
			for (c = 1; c <= a; c++)
			{
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
}
