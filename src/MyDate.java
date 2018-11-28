import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate
{
	private int day, month, year;
	
	public MyDate(int day, int month, int year) 
	{
		setDate(day, month, year);
	}
	public void setDate(int day, int month, int year) 
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public static MyDate getCurrentDate() 
	{
	   int d, m, y;
      Date date = new Date();
      
	   DateFormat dateFormatDay = new SimpleDateFormat("dd");
      String currentDay = dateFormatDay.format(date);
      d = Integer.parseInt(currentDay);
      
      DateFormat dateFormatMonth = new SimpleDateFormat("MM");
      currentDay = dateFormatMonth.format(date);
      m = Integer.parseInt(currentDay);
      
      DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
      currentDay = dateFormatYear.format(date);
      y = Integer.parseInt(currentDay);
      
      MyDate currentDate = new MyDate(d, m, y);
      return currentDate;
	}
	public boolean isLater(MyDate isLater) 
	{
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
	public MyDate copy() 
	{
		return this;
	}
	
	public String toString() {
		return day + "/" + month + "/" + year;
	}
	
	
}
