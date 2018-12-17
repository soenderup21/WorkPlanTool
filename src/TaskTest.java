import java.util.ArrayList;

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
      System.out.println("\n\n");
      
      ArrayList<Analysis> anem=el.get(0).getAllAnalyses();
      for(int i=0;i<anem.size();i++)
         System.out.println(anem.get(i).getName());
      System.out.println("\n\n");
      
      ArrayList<Analysis> taskan=new ArrayList<Analysis>();
      taskan.add(anem.get(1));
      taskan.add(anem.get(5));
      
      
      t1.addAnalysisArray(taskan);
      Analysis[] alt=t1.getAnalysis();
      for(int i=0;i<alt.length;i++)
         System.out.println(alt[i].getName());
      System.out.println("\n\n");
      
      taskan.clear();
      Task t2=new Task(el.get(0),d);
      
      anem.clear();
      anem=el.get(0).getAllAnalyses();
      for(int i=0;i<anem.size();i++)
         System.out.println(anem.get(i).getName());
      System.out.println("\n\n");
      
      taskan.add(anem.get(0));
      t2.addAnalysisArray(taskan);
      
      Analysis[] alt2=t2.getAnalysis();
      for(int i=0;i<alt2.length;i++)
         System.out.println(alt2[i].getName());
      System.out.println("\n\n");
      
      TaskList tl=new TaskList();
      tl.addTask(t1);
      tl.addTask(t2);
      
      TaskList empl=tl.getTasks(el.get(0));
      System.out.println(empl.getTaskCount());
      
      Week w=new Week();
      w.addTaskList(tl);
      TaskList tnew=w.getList(0);
      System.out.println(tnew.getTaskCount());
      
      WeekAdapter wa=new WeekAdapter("week.bin");
      wa.saveTasks(w);
      Week wnew=wa.getAllTasks();
      System.out.println(wnew.size());
      System.out.println(wnew.isThereAWeek(51));
   }
}
