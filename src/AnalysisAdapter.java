import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is made to connect GUI with classes
 * @author Karla Jajic
 * @version 1.0
 */
public class AnalysisAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * This constructor is used to initialize file that will be used 
    * @param fileName The parameter of type String which contains the name of the file 
    */
   public AnalysisAdapter(String fileName)
   {
      mfio=new MyFileIO();
      this.fileName=fileName;
   }
   
   /**
    * This method is used to read the file and create a NoteList object from gathered information
    * @return The method returns NoteList object
    */
   public AnalysisList getAllAnalysis()
   {
      AnalysisList analysis = new AnalysisList();
         try
         {
            analysis=(AnalysisList)mfio.readObjectFromFile(fileName);
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
      return analysis;
   }
   
   /**
    * This method is used to save list of notes to the file
    * @param notes The parameter of type NoteList which contains the list of notes
    */
   public void saveAnalysis(AnalysisList analysis)
   {
      try
      {
         mfio.writeToFile(fileName, analysis);
      }
      catch (IOException e)
      {
         System.out.println("Error writing file");
      }
   }
}

