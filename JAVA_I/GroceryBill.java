// Carmody, Judy
// COSC 1430.701
// Project 6
// Due: November 26, 2014
// Sets prices of items, then get number of items bought and calculates grocery bill.

public class GroceryBill
{
	public static void main(String[] args)
	{
		Purchase bill = new Purchase();
		bill.getName();
		bill.getUnitCost();
		bill.getNumberBought();
		bill.getTotalCost();

		double total = 0;
		int inventory = 5;

		while(inventory>0)
		{
			bill.readInput();
			bill.writeOutput();	
			
			System.out.println("Name: " + bill.getName());
			System.out.println("Unit Cost: " + bill.getUnitCost());
			System.out.println("Number Bought: " + bill.getNumberBought());
			System.out.println("Total cost of this item: " + bill.getTotalCost());

			inventory--;
			total = total + bill.getTotalCost();
		}

		System.out.println("\n Grocery Bill: $" + total);	
	}
}
