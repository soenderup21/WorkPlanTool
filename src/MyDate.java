
public class MyDate
{
	private int
		day,
		month,
		year;
	
	public MyDate(int day, int month, int year) {
		setDate(day, month, year);
	}
	public void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public MyDate getDate() {
		return this;
	}
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
	public MyDate copy() {
		return this;
	}
	
	public String toString() {
		return day + "/" + month + "/" + year;
	}
	
	
}
