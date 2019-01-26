/* 
 Carmody, Judy
 COSC 3420.001
 Project # 1
 DUE: January 31, 2018
 	Get a date from user
	Print day before, day after, and day of week
	
CalendarDate.java
	gets input from CalDateDemo.java
	splits the string and places the pieces into an array
	Calendar is set using these pieces.
	Prints input, day before, day after, and day of week.
*/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarDate extends GregorianCalendar{
	GregorianCalendar gregCal = (GregorianCalendar) GregorianCalendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
	DateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
	public int month, day, year;
	String dateStr;
	boolean valid;
	
	// user input and date is valid.
	public CalendarDate(String dateStr){
		String[] strArr = dateStr.split("/");
		int[] dateFieldArr = new int[3];
		dateFieldArr[0] = Integer.parseInt(strArr[0]);
		dateFieldArr[1] = Integer.parseInt(strArr[1]);
		dateFieldArr[2] = Integer.parseInt(strArr[2]);

		month = dateFieldArr[0]; day = dateFieldArr[1]; year = dateFieldArr[2];
		gregCal.set(GregorianCalendar.MONTH, month-1);
		gregCal.set(GregorianCalendar.DATE, day);
		gregCal.set(GregorianCalendar.YEAR, year);
	}
	
	// invalid date
	public CalendarDate(){
		month = 1; day = 1;	year = 2012;
		gregCal.set(GregorianCalendar.MONTH, month-1);
		gregCal.set(GregorianCalendar.DATE, day);
		gregCal.set(GregorianCalendar.YEAR, year);
	}
	
	// prints date user input
	public String getCurrentDay(){
		String c = new String("Date input: " + dateFormat.format(gregCal.getTime()));
		System.out.println(c);
		return c;
	}
	
	// prints previous day
	public String getPrevDay(){
		gregCal.add(GregorianCalendar.DATE, -1);
		String p = new String("Previous Day: " + dateFormat.format(gregCal.getTime()));
		System.out.println(p); return p;
	}
	
	// prints next day
	public String getNextDay(){
		gregCal.add(GregorianCalendar.DATE, 2);
		String n = new String("Next Day: " + dateFormat.format(gregCal.getTime()));
		System.out.println(n);
		gregCal.add(GregorianCalendar.DATE, -1); return n;
	}
	
	// prints day of the week
	public String getDayOfWeek(){
		String w = new String("Day of the Week: " + dayFormat.format(gregCal.getTime()));
		System.out.println(w); return w;
	}
	
	// checks if date is valid.
	// if date is not valid, the date is set to January 1, 2012
	public boolean isValid(){
			int daysTotal = gregCal.getMaximum(DAY_OF_MONTH);
			int monthsTotal = 12;
			if(day <= daysTotal && month <= 12){
				valid = true;
				getCurrentDay();
				getPrevDay();
				getNextDay();
				getDayOfWeek();
			}
			else{
				System.out.println("Invalid Date.\n\nDate set to: January 1, 2012");
				CalendarDate cdEx = new CalendarDate();
				cdEx.getPrevDay();
				cdEx.getNextDay();
				cdEx.getDayOfWeek();
			}
		return valid;
	}
}