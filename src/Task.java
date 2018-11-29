
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
		employees.addEmployee(employee);
	}
	public void addEmployees(EmployeeList employees) {
		for (int i = 0; i < employees.getAllEmployees().size(); i++)
		{
			this.employees.addEmployee(employees.getAllEmployees().get(i));
		}
	}
	public String toString() {
		return date.toString() + "idkidkidkidk";
	}
	
}
