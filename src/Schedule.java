
public class Schedule
{
	
	private Task Tasks[][] = new Task[7][/*Insert employee list size*/];;

	public Schedule() {
		
	}
	
	public void setTask(int day, int employeeIndex, Task task) {
		Tasks[day][employeeIndex] = task;
	}
	public void removeTask(int day, int employeeIndex) {
		Tasks[day][employeeIndex] = new Task();
	}

	public Task getTask(int day, int employeeIndex) {
		return Tasks[day][employeeIndex];
	}
	
	public boolean hasTask(int day, int employeeIndex) {
		return !(Tasks[day][employeeIndex] == null);
	}
	
	
}
