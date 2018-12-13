import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author chris
 *
 */
public class MyDate implements Serializable
{
	private int
		day,
		month,
		year;
	
	/**
	 * Default MyDate Constructor
	 */
	public MyDate() {
		setDate(0,0,0);
	}
	
	/**
	 * Constructor Class
	 * @param day
	 * @param month
	 * @param year
	 */
	public MyDate(int day, int month, int year ) {
		setDate(day, month, year);
	}
	
	/**
	 * SetDate sets the day by the use of 3 integers
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/**
	 * Sets day
	 * @param day
	 */
	public void setDay(int day)
	{
	   this.day=day;
	}
	
	/**
	 * Sets Month
	 * @param month
	 */
	public void setMonth(int month)
	{
	   this.month=month;
	}
	
	/**
	 * setYear
	 * @param year
	 */
	public void setYear(int year)
	{
	   this.year=year;
	}
	
	/**
	 * returns an integer indicating the day so 2 = Tuesday
	 * @return integer
	 */
	public int getDayOfWeekIndex() {
		Calendar cal = getCalendar();
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * Converts a day of week index to a string so 2 = "Tuesday"
	 * @return String: returns "-1" if index is not equal to a case
	 */
	public String getDayOfWeek() {
		switch (getDayOfWeekIndex()) {
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		case 1:
			return "Sunday";
		}
		return "-1";
	}
	
	/**
	 * returns a calendar with the date of this object this object can calculate Week, and day indexes
	 * @return Calendar 
	 */
	private Calendar getCalendar() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date;
		try
		{
			date = df.parse(this.toString());
		}
		catch (ParseException e)
		{
			System.out.println("Invalid date: " + e);
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * gets week
	 * @return int
	 */
	public int getWeek() {
		Calendar cal = getCalendar();
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * returns today
	 * @return MyDate
	 */
	public static MyDate getCurrentDate() {
	   GregorianCalendar cal = new GregorianCalendar();
		return new MyDate(cal.get(GregorianCalendar.DATE)-1,cal.get(GregorianCalendar.MONTH)+1
		      ,cal.get(GregorianCalendar.YEAR));
	}
	
	/**
	 * Gets current year
	 * @return
	 */
	public int getYear() {
		return this.year;
	}
	/**
	 * Gets current Month
	 * @return
	 */
	public int getMonth() {
		return this.month;
	}
	/**
	 * Gets current Day
	 * @return
	 */
	public int getDay() {
		return this.day;
	}
	
	
	/**
	 * returns a boolean that returns true if the inputted date is later than the date of the object itself
	 * @param isLater
	 * @return returns a boolean indicator
	 */
	public boolean isLater(MyDate isLater) {
		if (this.year < isLater.year)
		{
			return true;
		}
		else if (this.month < isLater.month)
		{
			return true;
		}
		else if (this.day < isLater.day)
		{
			return true;
		}
		return false;
	}
	/**
	 * Copies the object
	 * @return returns the object it's called from
	 */
	public MyDate copy() {
		return new MyDate(day, month, year);
	}
	
	public boolean equals(Object obj)
	{
	   if(!(obj instanceof MyDate))
	      return false;
	   MyDate other=(MyDate)obj;
	   return other.day==day && other.month==month && other.year==year;
	}
	
	/**
	 * @return returns a string that describes the date in written form
	 */
	public String toString() {
		return "" + year + month + day;
	}
	
	
}
