import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable
{
	private int
		day,
		month,
		year;
	
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
	
	public void setDay(int day)
	{
	   this.day=day;
	}
	
	public void setMonth(int month)
	{
	   this.month=month;
	}
	
	public void setYear(int year)
	{
	   this.year=year;
	}
	
	
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
		return day + "/" + month + "/" + year;
	}
	
	
}
