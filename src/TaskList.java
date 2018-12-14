import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable
{
	private ArrayList<Task> tasks;
	
	public TaskList()
	{
	   tasks=new ArrayList<Task>();
	}
	
	public int getWeek()
	{
	   if(tasks.size()==0)
	      return MyDate.getCurrentDate().getWeek();
	   else return tasks.get(0).getDate().getWeek();
	}
	
	public Task getTask(Employee e, MyDate d)
	{
	   Task t=null;
	   for(int i=0;i<tasks.size();i++)
	      if(tasks.get(i).getEmployee().equals(e)&&tasks.get(i).getDate().equals(d))
	         t=tasks.get(i);
	   return t;
	}
	
	public TaskList getTasks(Employee e)
	{
	   TaskList tl=new TaskList();
      for(int i=0;i<tasks.size();i++)
         if(tasks.get(i).getEmployee().equals(e))
            tl.addTask(tasks.get(i));
      return tl;
	}
	
	public void addTask(Task task)
	{
		tasks.add(task);
	}
	
	public void removeTaskById(int id)
	{
		tasks.remove(id);
	}
	
	public int getTaskCount() 
	{
		return tasks.size();
	}
	
	public String toString()
	{
	   String s="";
	   for(int i=0;i<tasks.size();i++)
	      s+=tasks.get(i)+" \n";
	   return s;
	}
	
	
	
}
