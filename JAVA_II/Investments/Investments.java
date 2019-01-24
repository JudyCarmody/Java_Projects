// Carmody, Judy
// COSC 2430.001
// Project # 1
// Due: Feb. 4, 2015
// Calculates yearly interest and outputs the future value at 5, 10, and 20 years.

import java.util.Scanner;
public class Investments
{

	private int year, inputInvest;
	private double futureValue, calcInterest, calcPow, calcInvest, inputInterest, futureRound;

	public static void main(String[] args)
	{
		Investments calcInterest = new Investments();
		calcInterest.input();		
		calcInterest.calc();
	}

	public void input()
	{
	
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter how much will be invested");
		inputInvest = keyboard.nextInt();	

		System.out.println("Enter interest rate in decimal form");
		inputInterest = keyboard.nextDouble();
	}

	public void calc()
	{
		calcInvest = inputInvest;
		calcInterest = 1 + inputInterest;

		do
		{
			for (year=1; year<5; year++)
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcInvest * calcPow;
			}	

			for (year=5; year<6; year++)
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcPow * calcInvest;
				futureRound = Math.round(futureValue*100.0)/100.0;
				System.out.println("Your investment after " + year + " years: " + "$" + futureRound);
			}

			for (year=6; year<10; year++)
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcInvest * calcPow;
			}

			for (year=10; year<11; year++) 
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcPow * calcInvest;
				futureRound = Math.round(futureValue*100.0)/100.0;
				System.out.println("Your investment after " + year + " years: " + "$" + futureRound);
			}

			for (year=11; year<20; year++)
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcInvest * calcPow;
			}

			for (year=20; year<21; year++)
			{
				calcPow = Math.pow(calcInterest,year);
				futureValue = calcInvest * calcPow;
				futureRound = Math.round(futureValue*100.0)/100.0;
				System.out.println("Your investment after " + year + " years: " + "$" + futureRound);
			}

		} while (year < 21);
	}
}
