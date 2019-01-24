/* 
Take Home Quiz

*/

import java.util.*;

public class Calculator
{
	private double result;
	private double precision = 0.0001;
	
	public static void main(String[] args)
	{
		Calculator clerk = new Calculator();
		Calculator clerk1 = new Calculator();
		
		try
		{
			System.out.println("Calculator is on.");
			System.out.print("Format of each line: ");
			System.out.println("operator number");
			System.out.println("For example: +3");
			System.out.println("To end, enter the letter e.");
			
			clerk1.doCalculation1();
			clerk1.doCalculation();
			clerk.doCalculation1();
			clerk.doCalculation();
		}
		
		catch(UnknownOpException e)
		{
			clerk.handleUnknownOpException(e);
		}
		
		catch(DivideByZeroException e)
		{
			clerk.handleDivideByZeroException(e);
		}
		
		System.out.println("The final result is: " + clerk.resultValue());
		System.out.println("Calculator program is ending.");
	}
	
	public Calculator()
	{
		result = 0;
	}
	
	public void reset()
	{
		result = 0;
	}
	
	public void setResult(double newResult)
	{
		result = newResult;
	}
	
	public double resultValue()
	{
		return result;
	}
	
	public void doCalculation() throws DivideByZeroException, UnknownOpException
	{
		Scanner keyboard = new Scanner(System.in);
		char nextOp;
		double nextNumber;
		boolean done = false;
		result = 0;
		System.out.println("result = " + result);
		
		while (! done)
		{
			nextOp = (keyboard.next()).charAt(0);
			if ((nextOp == 'e') || (nextOp == 'E'))
			{
				done = true;
			}
			
			else
			{
				nextNumber = keyboard.nextDouble();
				result = evaluate(nextOp, result, nextNumber);
				System.out.println("result " + nextOp + " " + nextNumber + " = " + result);
				System.out.println("updated result = " + result);
			}
		}
	}
	
	public void doCalculation1() throws DivideByZeroException, UnknownOpException
	{
		Scanner keyboard = new Scanner(System.in);
		char nextOp;
		double nextNumber;
		boolean done = false;
		result = 0;
		System.out.println("result = " + result);
		
		while (! done)
		{
			nextOp = (keyboard.next()).charAt(0);
			if ((nextOp == 'e') || (nextOp == 'E'))
			{
				done = true;
			}
			
			else
			{
				nextNumber = keyboard.nextDouble();
				result = evaluate1(nextOp, result, nextNumber);
				System.out.println("result " + nextOp + " " + nextNumber + " = " + result);
				System.out.println("updated result = " + result);
			}
		}
	}
	
	public double evaluate(char op, double n1, double n2) throws DivideByZeroException, UnknownOpException
	{
		double answer;
		switch(op)
		{
			case '+':
			{
				answer = n1 + n2;
				break;
			}
			
			case '-':
			{
				answer = n1 - n2;
				break;
			}
			
			case '*':
			{
				answer = n1 * n2;
				break;
			}
			
			case '/':
			{
				if ((precision < n2) && (n2 < precision))
				{
					throw new DivideByZeroException();
				}
				
				answer = n1/n2;
				break;
			}
			
			default:
			{
				throw new UnknownOpException(op);
			}
		}

		return answer;
	}
	
	public double evaluate1(char op, double n1, double n2) throws DivideByZeroException, UnknownOpException
	{
		double answer;
		switch(op)
		{
			case '+':
			{
				answer = n1 + n2;
				break;
			}
			
			default:
			{
				throw new UnknownOpException(op);
			}
		}
		
		return answer*2;
	}
	
	public void handleDivideByZeroException(DivideByZeroException e)
	{
		System.out.println("Dividing by zero.");
		System.out.println("Program aborted.");
		System.exit(0);
	}
	
	public void handleUnknownOpException(UnknownOpException e)
	{
		System.out.println(e.getMessage());
		System.out.println("Try again from the beginning:");
		
		try
		{
			System.out.print("Format of each line: ");
			System.out.println("operator number");
			System.out.println("For example: +3");
			System.out.println("To end, enter the letter e.");
			doCalculation();
		}
		
		catch(UnknownOpException e2)
		{
			System.out.println(e2.getMessage());
			System.out.println("Try again at some other time.");
			System.out.println("Program ending.");
			System.exit(0);
		}
		
		catch(DivideByZeroException e3)
		{
			handleDivideByZeroException(e3);
		}
	}
}