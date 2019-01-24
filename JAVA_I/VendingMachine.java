// Judy Carmody
// COSC 1430.701
// Project 2
// Due: October 4, 2014
// Vending Machine: input a number from 25 to 100 in increments of 5 and change is calculated out of one dollar.  

import java.util.Scanner;

public class VendingMachine
{
	public static void main(String[] args)
	{
		int amount, originalAmount, changeAmount,
			quarters, dimes, nickels;

		System.out.println("Enter price of item.");
		System.out.println("Prices range from 25 cents to one dollar\n" +
		"in 5-cent increments.");
		Scanner keyboard = new Scanner(System.in);
		amount = keyboard.nextInt();
		originalAmount = 100;
		changeAmount = 100 - amount;

		quarters = changeAmount / 25;
		changeAmount = changeAmount % 25;
		dimes = changeAmount / 10;
		changeAmount = changeAmount % 10;
		nickels = changeAmount / 5;
		
		System.out.println("Cost of item bought: " + amount);
		System.out.println("Paid with $1.00\n" +
		"Your change:");
		System.out.println(quarters + " quarters");
		System.out.println(dimes + " dimes");
		System.out.println(nickels + " nickels");
	}
}
