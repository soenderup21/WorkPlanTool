
public class Task
{
	private EmployeeList employees;
	private Analysis analysis;
	private MyDate date;
	
	/**
	 * constructor that allows for an empty tast
	 */
	public Task() {
		
	}
	/**
	 * Constructor that initializes a Task
	 * @param date
	 * @param analysis
	 */
	public Task(MyDate date, Analysis analysis) {
		this.date = date;
		this.analysis = analysis;
	}
	/**
	 * add employee to employees
	 * @param employee
	 */
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	/**
	 * adds a multiple of employee to employee
	 * @param employees
	 */
	public void addEmployees(EmployeeList employees) {
		for (int i = 0; i < employees.getAllEmployees().size(); i++)
		{
			this.employees.add(employees.getAllEmployees().get(i));
		}
	}
	/**
	 * Gets Analysis
	 * @return
	 */
	public Analysis getAnalysis(){
		return this.analysis;
	}
	/**
	 * sets Analysis
	 * @param analysis
	 */
	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}
	/**
	 * gets Date for task
	 * @return
	 */
	public MyDate getDate(){
		return this.date;
	}
	/**
	 * sets date
	 * @param date
	 */
	public void setDate(MyDate date) {
		this.date = date;
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
		return date.toString(); //only date is displayed 
	}
}
