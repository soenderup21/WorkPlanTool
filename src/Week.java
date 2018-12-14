import java.io.Serializable;
import java.util.ArrayList;

public class Week implements Serializable
{ 
   private ArrayList<TaskList> lists;
   
   public Week()
   {
      lists=new ArrayList<TaskList>();
   }
   
   public void addTaskList(TaskList tl)
   {
      if(isThereAWeek(tl.getWeek()))
         for(int i=0;i<lists.size();i++)
            if(lists.get(i).getWeek()==tl.getWeek()) lists.set(i, tl);
      lists.add(tl);
   }
   
   public TaskList getList(int n)
   {
      TaskList temp=lists.get(0);
      
      for(int i=0;i<lists.size();i++)
        if(n==i) temp=lists.get(i);
      
      return temp;
   }
   
   public int size()
   {
      return lists.size();
   }
   
   public boolean isThereAWeek(int w)
   {
      for(int i=0;i<lists.size();i++)
         if(lists.get(i).getWeek()==w) return true;
      return false;
   }
}
