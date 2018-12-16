import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author raluca
 *has an array list of the company's all employees
 */
public class EmployeeList implements Serializable
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
   public void add(Employee emp)
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
   
   public Employee get(int i)
   {
      return employees.get(i);
   }
   
   public int size()
   {
      return employees.size();
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
         if(employees.get(i).getIntials().equalsIgnoreCase(initials))
            return employees.get(i);
      }
      return null;
   }
   /**
    * 
    * @param analysis is the analysis that should be searched for
    * @return all the employees that were able to perform a specific analysis at one point
    */
   public ArrayList<Employee> getEmployeesWithAnalysis(Analysis analysis)
   {
      ArrayList<Employee> empList = new ArrayList<Employee>();
      ArrayList<AnalysisDetails> analysisList = null; 
      for(int i = 0; i < employees.size(); ++i)
      {
         analysisList = employees.get(i).getAllAnalysesDetails();
         for(int j = 0; j < analysisList.size(); ++j)
         {
            if(analysisList.get(j).getAnalysis().equals(analysis))
            {
               if(analysisList.get(j).isPreference())
                  empList.add(0, employees.get(i));
               else empList.add(employees.get(i));
            }
         }
      }
      return empList;
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
