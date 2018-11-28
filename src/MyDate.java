
public class MyDate
{
	private int
		day,
		month,
		year;
	
	/**
	 * Constructor Class
	 * @param day
	 * @param month
	 * @param year
	 */
	public MyDate(int day, int month, int year) {
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
		return this;
	}
	/**
	 * @return returns a string that describes the date in written form
	 */
	public String toString() {
		return day + "/" + month + "/" + year;
	}
	
	
}
