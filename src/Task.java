
public class Task
{
	private EmployeeList employees;
	private Analysis analysis;
	private MyDate date;
	
	public Task(MyDate date, Analysis analysis) {
		this.date = date;
		this.analysis = analysis;
	}
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	public void addEmployees(EmployeeList employees) {
		for (int i = 0; i < employees.count; i++)
		{
			this.employees.add(employees.get(i));
		}
	}
	public String toString() {
		return "TO BE DETERMINED";
	}
	
}
