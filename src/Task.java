import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable
{
   private Employee employee;
   private ArrayList<Analysis> analysis;
   private MyDate date;
   
   /**
    * Constructor that initializes a Task
    * @param date
    * @param analysis
    */
   public Task(Employee employee, MyDate date) {
      this.date = date;
      this.employee=employee;
      analysis=new ArrayList<Analysis>();
   }
   
   public void setEmployee(Employee employee)
   {
      this.employee=employee;
   }
   
   public Employee getEmployee()
   {
      return employee;
   }
   
   /**
    * add employee to employees
    * @param employee
    */
   public void addAnalysisArray(ArrayList<Analysis> a) 
   {
      for(int i=0;i<a.size();i++)
         analysis.add(a.get(i));
   }
   
   public void addAnalysis(Analysis a)
   {
      if(!a.equals(null))
         analysis.add(a);
   }
   
   /**
    * Gets Analysis
    * @return
    */
   public Analysis[] getAnalysis()
   {
      Analysis[]a=new Analysis[analysis.size()];
      for(int i=0;i<analysis.size();i++)
         a[i]=analysis.get(i);
      return a;
   }
   
   public String getAnalysisInString()
   {
      String s="";
      Analysis[] a=getAnalysis();
      if(a.length!=0)
      {
         s=a[0].getName();
         for(int i=1;i<a.length;i++)
            s+=", "+a[i].getName();
      }
      return s;
   }
   
   /**
    * sets Analysis
    * @param analysis
    */
   public void setAnalysis(Analysis[] a) 
   {
      ArrayList<Analysis> al=new ArrayList<Analysis>();
      for(int i=0;i<a.length;i++)
         al.add(a[i]);
      this.analysis=al;
   }
   
   /**
    * gets Date for task
    * @return
    */
   public MyDate getDate()
   {
      return this.date;
   }
   
   /**
    * sets date
    * @param date
    */
   public void setDate(MyDate date) {
      this.date = date;
   }
   
   public String toString() 
   {
      String s=employee.getName()+", ";
      s+="  "+date.toString();
      return s;
   }
}
