// Judy Carmody
// COSC 1430.701
// Project # 1
// Due: Sept. 18, 2014  23:59

import java.util.Scanner;

public class Calculate
{
	public static void main(String[] args)
	{
		System.out.println("Hello out there.");
		System.out.println("I will give you the sum, product, difference, and quotient of two whole numbers.");
		System.out.println("Enter two whole numbers on a line:");

		int n1, n2;

		Scanner keyboard = new Scanner(System.in);
		n1 = keyboard.nextInt();
		n2 = keyboard.nextInt();

		System.out.println("The sum:");
		System.out.println(n1 + n2);
		System.out.println("The product:");
		System.out.println(n1 * n2);
		System.out.println("The difference:");
		System.out.println(n1 - n2);
		System.out.println("The quotient:");
		System.out.println(n1 / n2);
	}
}
