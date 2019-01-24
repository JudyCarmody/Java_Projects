/*
 Carmody, Judy
 COSC 2430.001
 Project # 4
 Due: March 30, 2015
 Draws an arrow pointing either left or right.  The size is determined by the user.
*/

import java.util.Scanner;

public class ArrowDemo
{

	Scanner direction = new Scanner(System.in);
	private String leftRight;
	
	public static void main(String[] args)
	{
		ArrowDemo start = new ArrowDemo();
		start.leftOrRight();
	}
	
	public void leftOrRight()
	{
		System.out.println("This program will print out an arrow that points left or right.");
		System.out.println("");
	
	// Will the arrow face left or right?  User choice.
		System.out.println("Will the arrow point left or right?");
		leftRight = direction.nextLine();
		System.out.println("");
		
		// User chooses an arrow pointing right
		if (leftRight.equalsIgnoreCase("right"))
		{
			// Go to ArrowRight.class
			ArrowRight inputRight = new ArrowRight();
			inputRight.sizeRight();
		}
		
		// User chooses an arrow pointing left
		if (leftRight.equalsIgnoreCase("left"))
		{
			// Go to ArrowLeft.class
			ArrowLeft inputLeft = new ArrowLeft();
			inputLeft.sizeLeft();
		}
	}
}
