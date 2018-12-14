import java.io.FileNotFoundException;
import java.io.IOException;

//these javadocs are wrong
public class TaskListAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * This constructor is used to initialize file that will be used 
    * @param fileName The parameter of type String which contains the name of the file 
    */
   public TaskListAdapter(String fileName)
   {
      mfio=new MyFileIO();
      this.fileName=fileName;
   }
   
   /**
    * This method is used to read the file and create a NoteList object from gathered information
    * @return The method returns NoteList object
    */
   public TaskList getAllTasks()
   {
      TaskList tasks = new TaskList();
         try
         {
            tasks=(TaskList)mfio.readObjectFromFile(fileName);
         }
         catch (ClassNotFoundException e)
         {
            System.out.println("Class not found");
         }
         catch (IOException e)
         {
            System.out.println("This is not working");
            e.printStackTrace();

         }
      return tasks;
   }
   
   /**
    * This method is used to save list of notes to the file
    * @param notes The parameter of type NoteList which contains the list of notes
    */
   public void saveTasks(TaskList tasks)
   {
      try
      {
         mfio.writeToFile(fileName, tasks);
      }
      catch (IOException e)
      {
         System.out.println("Error writing file");
      }
   }
   
 /* public static void main(String[] args)
   {
      TaskListAdapter adapter=new TaskListAdapter("task.bin");
      Employee e1=new Employee("JK","KARLA Jajic");
      Employee e2= new Employee("OJ", "ORIANA Jajic");
      Task n1=new Task(e1, MyDate.getCurrentDate());
      Task n2=new Task(e2, new MyDate(12,12, 2018));
      TaskList tl=new TaskList();
      tl.addTask(n1);
      tl.addTask(n2);
      System.out.println(n1.equals(n2));
      
      adapter.saveTasks(tl);
      TaskList n= adapter.getAllTasks();
      System.out.println(n);
   }*/
}

