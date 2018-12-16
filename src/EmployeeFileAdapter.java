import java.io.IOException;

public class EmployeeFileAdapter
{
   private String fileName;
   private MyFileIO employeeFile;
   
   public EmployeeFileAdapter()
   {
      fileName = "employees.bin";
      employeeFile = new MyFileIO();
   }
   
   public EmployeeList getEmployeeList()
   {
      EmployeeList el=null;
      try
      {
         el=(EmployeeList) employeeFile.readObjectFromFile(fileName);
      }
      catch (ClassNotFoundException | IOException e)
      {
         System.out.println("Problem with reading the file");
      }
      
      return el;
   }
   public void saveEmployeeList(EmployeeList list)
   {
      try
      {
         employeeFile.writeToFile(fileName, list);
      }
      catch (IOException e)
      {
         System.out.println("Problem with writing in the file");
      }
   }
   
  public static void main(String[] args)
   {
      EmployeeFileAdapter adapter = new EmployeeFileAdapter();
      Employee e1=new Employee("Karla Jajic","JK");
      
      Employee e2=new Employee("Oriana Jajic","OK");
      
      EmployeeList el=new EmployeeList();
      el.add(e1);
      el.add(e2);
      adapter.saveEmployeeList(el);
      
      EmployeeList l=adapter.getEmployeeList();
      for(int i=0;i<l.size();i++)
         System.out.println(l.get(i).getIntials());
   }
}
