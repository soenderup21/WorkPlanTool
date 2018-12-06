import java.util.Calendar;

public class MyDate
{
	private int
		day,
		month,
		year,
		week;
	
	
	public MyDate() {
		setDate(0,0,0,0);
	}
	
	/**
	 * Constructor Class
	 * @param day
	 * @param month
	 * @param year
	 */
	public MyDate(int day, int month, int year, int week ) {
		setDate(day, month, year, week);
	}
	
	/**
	 * SetDate sets the day by the use of 3 integers
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setDate(int day, int month, int year, int week) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.week = week;
	}
	
	public static MyDate getCurrentDate() {
	   Calendar cal = Calendar.getInstance();
		return new MyDate(
		      cal.get(Calendar.DAY_OF_MONTH),
		      cal.get(Calendar.MONTH),
		      cal.get(Calendar.YEAR),
		      cal.get(Calendar.WEEK_OF_YEAR)
		      );
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
		return new MyDate(day, month, year, week);
	}
	/**
	 * @return returns a string that describes the date in written form
	 */
	public String toString() {
		return day + "/" + month + "/" + year;
	}
	
	
}
