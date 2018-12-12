
public class Schedule
{
	
	private Task Tasks[][] = new Task[7][/*Insert employee list size*/];;

	public Schedule() {
		
	}
	
	/**
	 * 
	 * @author Lenovo
	 *	
	 */
	public enum Day{
		MONDAY (1),
		TUESDAY (2),
		WEDNESDAY (3),
		THURSDAY (4),
		FRIDAY (5),
		SATURDAY (6),
		SUNDAY (7);
		
		int day;
		Day(int day){
			this.day = day;
		}
	}
	
	public void setTask(Day Day, int employeeIndex, Task task) {
		int day = Day.day;
		Tasks[day][employeeIndex] = task;
	}
	public void removeTask(Day Day, int employeeIndex) {
		int day = Day.day;
		Tasks[day][employeeIndex] = new Task();
	}

	public Task getTask(Day Day, int employeeIndex) {
		int day = Day.day;
		return Tasks[day][employeeIndex];
	}
	
	public boolean hasTask(int day, int employeeIndex) {
		return !(Tasks[day][employeeIndex] == null);
	}
	
	
}
