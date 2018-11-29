
public class Task
{
	private EmployeeList employees;
	private Analysis analysis;
	private MyDate date;
	
	public Task() {
		
	}
	
	public Task(MyDate date, Analysis analysis) {
		this.date = date;
		this.analysis = analysis;
	}
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	public void addEmployees(EmployeeList employees) {
		for (int i = 0; i < employees.getAllEmployees().size(); i++)
		{
			this.employees.add(employees.getAllEmployees().get(i));
		}
	}
	/*
	public boolean equals(Task task) {
		if (this.employees.equals(task.employees))
		{
			if (this.analysis.equals(task.analysis))
			{
				if (this.date.equals(task.date))
				{
					return true;
				}
			}
		}
	}
	*/
	
	public String toString() {
		return date.toString() + "idkidkidkidk";
	}
	
	
	
}
