import java.util.ArrayList;

/**
 * 
 * @author raluca
 *has an array list of the company's all employees
 */
public class EmployeeList
{
   private ArrayList<Employee> employees;
   
   /**
    * Constructor
    */
   public EmployeeList()
   {
      employees = new ArrayList<Employee>();
   }
   
   /**
    * 
    * @param emp the new employee added to the list
    */
   public void addEmployee(Employee emp)
   {
      boolean ok = true;
      for(int i = 0; i < employees.size(); ++i)
      {
         if(employees.get(i).equalsInitials(emp.getIntials()))
            ok = false;
      }
      if(ok == true)
         employees.add(emp);
   }
   
   /**
    * 
    * @param emp to be removed
    */
   public void removeEmployee(Employee emp)
   {
      employees.remove(emp);
   }
   
   /**
    * 
    * @param initials the initials of the employee to be returned
    * @return
    */
   public Employee getEmployee(String initials)
   {
      for(int i = 0; i < employees.size(); ++i)
      {
         if(employees.get(i).getIntials().equals(initials))
            return employees.get(i);
      }
      return null;
   }
   
   /**
    * 
    * @return a list with all the employees
    */
   public ArrayList<Employee> getAllEmployees()
   {
      return employees;
   }
}
