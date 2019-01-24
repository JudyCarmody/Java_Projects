// Judy Carmody
// COSC 1430.701
// Project #5
// November 14, 2014
//

import java.util.Scanner;
public class GradesGraph
{
	public int gradeA, gradeB, gradeC, gradeD, gradeF, count;
	public double total;
	public double Apercent, Bpercent, Cpercent, Dpercent, Fpercent;

	public static void main(String[] args)
	{	
		GradesGraph grade = new GradesGraph();
		grade.inputNumOfGrades();
		grade.getTotal();
		grade.percent();
		grade.drawBar();
		grade.DrawGraph();
	}

	public void inputNumOfGrades()
	{
		Scanner keyboard = new Scanner(System.in);		
		System.out.println("Number of A's: ");
		gradeA = keyboard.nextInt();

		System.out.println("Number of B's: ");
		gradeB = keyboard.nextInt();

		System.out.println("Number of C's: ");
		gradeC = keyboard.nextInt();

		System.out.println("Number of D's: ");
		gradeD = keyboard.nextInt();

		System.out.println("Number of F's: ");
		gradeF = keyboard.nextInt();

		System.out.println();	
	}

	public void getTotal()
	{
		total = gradeA + gradeB + gradeC + gradeD + gradeF;
	}

	public void percent()
	{		
		Apercent = Math.round(gradeA / total*100);
		Bpercent = Math.round(gradeB / total*100);
		Cpercent = Math.round(gradeC / total*100);
		Dpercent = Math.round(gradeD / total*100);
		Fpercent = Math.round(gradeF / total*100);	
	}

	public void drawBar()
	{
		System.out.print("0   10   20   30   40   50   60   70   80   90   100%\n");
		System.out.print("|   |    |    |    |    |    |    |    |    |    |\n");
		System.out.print("**************************************************\n");
	}

	public void DrawGraph()
	{	
		for(int i = 0 ; i< Apercent/2; i++)
		System.out.print("*");
		System.out.print(" A\n");

		for (int j = 0; j< Bpercent/2; j++)
		System.out.print("*");
		System.out.print(" B\n");

		for(int k = 0 ; k< Cpercent/2; k++)
		System.out.print("*");
		System.out.print(" C\n");

		for (int l = 0; l< Dpercent/2; l++)
		System.out.print("*");
		System.out.print(" D\n");

		for(int m = 0 ; m< Fpercent/2; m++)
		System.out.print("*");
		System.out.print(" F\n");

		System.out.println();
	}
}
