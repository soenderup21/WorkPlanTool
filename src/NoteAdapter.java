import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is made to connect GUI with classes
 * @author Karla Jajic
 * @version 1.0
 */
public class NoteAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * This constructor is used to initialize file that will be used 
    * @param fileName The parameter of type String which contains the name of the file 
    */
   public NoteAdapter(String fileName)
   {
      mfio=new MyFileIO();
      this.fileName=fileName;
   }
   
   /**
    * This method is used to read the file and create a NoteList object from gathered information
    * @return The method returns NoteList object
    */
   public NoteList getAllNotes()
   {
      NoteList notes = new NoteList();
         try
         {
            notes=(NoteList)mfio.readObjectFromFile(fileName);
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
      return notes;
   }
   
   /**
    * This method is used to find only those notes which are general
    * @return The method returns an object of type NoteList which consists of 
    * only those notes that are general
    */
   public NoteList generalNotes()
   {
      NoteList all = getAllNotes();
      NoteList gen= new NoteList();
      for(int i=0;i<all.size();i++)
         if(!all.getNote(i).isGeneral()) gen.addNote(all.getNote(i));
      return gen;
   }
   
   /**
    * This method is used to save list of notes to the file
    * @param notes The parameter of type NoteList which contains the list of notes
    */
   public void saveNotes(NoteList notes)
   {
      try
      {
         mfio.writeToFile(fileName, notes);
      }
      catch (IOException e)
      {
         System.out.println("Error writing file");
      }
   }
   
  /* public static void main(String[] args)
   {
      NoteAdapter adapter=new NoteAdapter("notes.bin");
      Note n1=new Note("prva","jbdj",null);
      Note n2=new Note("druga","jbdj",new MyDate(1,1,2018));
      NoteList nl=new NoteList();
      nl.addNote(n1);
      nl.addNote(n2);
      System.out.println(n1.equals(n2));
      
      adapter.saveNotes(nl);
      NoteList n= adapter.getAllNotes();
      System.out.println(n);
   } */
}

