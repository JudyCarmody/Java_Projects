/*
 Carmody, Judy
 COSC 3420.001
 Project # 1
 DUE: January 31, 2018
	Get a date from user
	Print day before, day after, and day of week

 CalDateDemo.java
	Gets input from user as a string
	Checks format of user input.
	Allows user to try again, if input is wrong.
*/

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class CalDateDemo{	
	private static Scanner input;
	
	public static void main(String[] args){
		CalDateDemo cdd = new CalDateDemo();
		System.out.println("Enter the date in MM/DD/YYYY format: ");
		cdd.getInput();
	}
	
	public void getInput(){
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat dateCheck = new SimpleDateFormat(DATE_FORMAT);
	
		try{
			input = new Scanner(System.in);
			String dateStr = input.nextLine();
			Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
			String dateFormStr = dateCheck.format(date);
		
			CalendarDate cdUser = new CalendarDate(dateStr);
			cdUser.isValid();
		} catch(ParseException e){
			System.out.println("Enter the date in MM/DD/YYYY format" + 
			"(you need to type the forward slashes, too!): ");
			getInput();
		}
	}
}