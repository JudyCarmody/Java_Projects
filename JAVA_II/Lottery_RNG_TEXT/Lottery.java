/*
 Carmody, Judy
 COSC 2430.001
 Project # 6
 Due: April 29, 2015
*/

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Lottery
{
	int[] randomNum = new int[5];
	int[] userNum = new int[5];
	int u = 0;
	int r = 0;

	public static void main(String[] args)
	{
		Lottery play = new Lottery();
		play.getUser();
		play.getRandomNum();
		play.printResult();
	}
	
	public int[] getUser()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter five numbers.");
		
		for(u = 0; u < userNum.length; u++)
		{
			userNum[u] = input.nextInt();
		}
		
		return (userNum);
	}
	
	public int[] getRandomNum()
	{
		Random rand = new Random();
		
		for(r = 0; r < randomNum.length; r++)
		{
			randomNum[r] = rand.nextInt(9);
		}
		
		return (randomNum);
	}
	
	public void printResult()
	{	
		System.out.println("\n");
	
		System.out.print("Player: ");
		for (int printUserArray: userNum)
		{
			System.out.print(printUserArray + " ");
		}		
		
		System.out.println("\n");
		
		System.out.print("Random: ");
		for (int printRandomArray: randomNum)
		{
			System.out.print(printRandomArray + " ");
		}
		
		int count = 0;
		
		for(int j = 0; j < randomNum.length; j++)
		{ 
			if(userNum[j] == randomNum[j])
			{
				count++;
			}
		}
		
		System.out.println("\n");
		System.out.print("Number of matches: " + count + "\n");
		
		prizes(count);
	}
	
	public void prizes(int count)
	{
		if (count == 0)
		{
			System.out.println(count + " matches = sorry no prize.");
		}	
	
		if(count == 1)
		{
			System.out.println(count + " match = $2");
		}
		
		if(count == 2)
		{
			System.out.println(count + " matches = $5");
		}
		
		if(count == 3)
		{
			System.out.println(count + " matches = $10");
		}
		
		if(count == 4)
		{
			System.out.println(count + " matches = $5,000");
		}
		
		if(count == 5)
		{
			System.out.println(count + " matches = $50,000");
		}
	}
}