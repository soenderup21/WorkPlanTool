
public class TaskTest
{
   public static void main(String[] args)
   {
      AnalysisAdapter aa=new AnalysisAdapter("analysis.bin");
      AnalysisList al=(AnalysisList)aa.getAllAnalysis();
      
      for(int i=0;i<al.size();i++)
         System.out.println(al.getAnalysis(i).getName());
      System.out.println("\n\n");
      
      EmployeeFileAdapter ea=new EmployeeFileAdapter();
      EmployeeList el=(EmployeeList)ea.getEmployeeList();
      
      for(int i=0;i<el.size();i++)
         System.out.println(el.get(i).getIntials());
      System.out.println("\n\n");
      
      MyDate d=MyDate.getCurrentDate();
      
      Task t1=new Task(el.get(0), d);
      System.out.println(t1);
   }
}
