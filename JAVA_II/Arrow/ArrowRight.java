/*
 Carmody, Judy
 COSC 2430.001
 Project # 4
 Due: March 30, 2015
 Draws an arrow pointing either left or right.  The size is determined by the user.
 
 Right facing arrow 
*/

import java.util.Scanner;

public class ArrowRight extends ShapeBasics
{
	Scanner tailLength = new Scanner(System.in);
	Scanner headSize = new Scanner(System.in);
	
	public int length;
	public int width;
	
	public ArrowRight( )
	{
		super( );
		length = 0;
		width = 0;
	}
    
	public ArrowRight(int theOffset, int theLength, int theWidth)
	{
		super(theOffset);
		length = theLength;
		width = theWidth;
	}
    
	public void set(int theLength, int theWidth)
	{
		length = theLength;
		width = theWidth;
	}

	public void setLength(int newLength, int newWidth)
	{
		length = newLength;
		width = newWidth;
	}
	
	public void sizeRight()
	{
		// Tail length
		System.out.println("How long will the tail be? (whole numbers)");
		length = tailLength.nextInt();
	
		// tail must be greater than 3
		if (length < 3)
		{
			System.out.println("Tail Length must be 3 or greater.");
			length = headSize.nextInt();
		}
	
		// Arrowhead size
		System.out.println("How large will the arrowhead be? (odd, whole numbers)");
		width = headSize.nextInt();
		
		// arrowhead must be greater than 3
		if (width < 3)
		{
			System.out.println("Arrow Head must be 3 or greater.");
			width = headSize.nextInt();
		}

		// Checks for even width
		// Allows user to try again.
		if (width % 2 == 0)
		{
			System.out.println("Whole, odd numbers only.  Try again");
			width = headSize.nextInt();
		}
		
		drawHere();
	}
	
	// Draw complete arrow
	public void drawHere()
	{
		drawTop();
		drawTail();
		drawBottom();
	}	

	// Draw top half
	public void drawTop()
	{
		int lineCount = width / 2;
		int position = getOffset() + length;
		int count;
		int emptySpaces = 1;

		skipSpaces(position);
		System.out.println("*");

		for (count = 1; count < lineCount; count++)
		{
			skipSpaces(position);
			System.out.print("*");
			skipSpaces(emptySpaces);
			System.out.println("*");
			emptySpaces = emptySpaces + 2;
		}
	}

	// Draw the tail
	public void drawTail()
	{	
		// the rest of the tail
		int count;
		for (count = 0; count <= length; count ++)
		{
			System.out.print("*");
		}

		// skips spaces (where the arrowhead is)
		int emptyW = (width / 2) * 2 - 1;		
		skipSpaces(emptyW);
		
		// point of arrow
		System.out.print("*");

		System.out.println("");	
	}
	
	
	// Draw bottom half
	public void drawBottom()
	{	
		int lineCount = width / 2;
		int position = getOffset() + length;		
		int count;
		int emptySpaces = (lineCount - 1) * 2 - 1;

		for (count = 1; count < lineCount; count++)
		{
			skipSpaces(position);
			System.out.print("*");
			skipSpaces(emptySpaces);
			System.out.println("*");
			emptySpaces = emptySpaces - 2;

		}

		skipSpaces(position);
		System.out.println("*");
	}

	private static void skipSpaces(int number)
	{
	        for (int count = 0; count < number; count++)
		{
            		System.out.print(" ");
		}
	}
}
