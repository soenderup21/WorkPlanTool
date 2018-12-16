import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AnalysisAdapter {
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * A constructor which initializes the MyFileIO (read and write from/to files) and sets the name of the file.
    * @param fileName sets the value of fileName to what is given in the parameters.
    */
    public AnalysisAdapter(String fileName)
      {
         mfio=new MyFileIO();
         this.fileName= fileName;
      }
    
    /**
     * A method to read the list of analysis from the binary file.
     * @return an ArrayList which contains all of the Analysis.
     */
    public AnalysisList getAllAnalysis()
      {
         AnalysisList analyses = new AnalysisList();
            try
            {
               analyses = (AnalysisList)mfio.readObjectFromFile(fileName);
            }
            catch (ClassNotFoundException e)
            {
               System.out.println("Class not found");
            }
            catch (IOException e)
            {
               System.out.println("Not working");
               e.printStackTrace();

            }
         return analyses;
      }

      /**
       * A method which writes the data in the parameters to file.
       * @param analyses which contains the list of analysis is being written to the file analysis.bin
       */
      public void saveAnalyses(AnalysisList analyses)
      {
         try
         {
            mfio.writeToFile(fileName, analyses);
         }
         catch (FileNotFoundException e)
         {
            System.out.println("File not found");
         }
         catch (IOException e)
         {
            System.out.println("IO Error writing to file");
         }
      }
      public void saveAnalysis(Analysis analysis)
      {
         
         try
         {
            AnalysisList temp = new AnalysisList();
            temp = getAllAnalysis();
            temp.addAnalysis(analysis);
            mfio.writeToFile(fileName, temp);
         }
         catch (FileNotFoundException e)
            {
               System.out.println("File not found");
            }
            catch (IOException e)
            {
               System.out.println("IO Error writing to file");
            }
      }
      public void deleteAnalysis(Analysis analysis)
      {
         
         try
         {
            AnalysisList temp = new AnalysisList();
            temp = getAllAnalysis();
            temp.removeAnalysis(analysis);
            mfio.writeToFile(fileName, temp);
         }
         catch (FileNotFoundException e)
            {
               System.out.println("File not found");
            }
            catch (IOException e)
            {
               System.out.println("IO Error writing to file");
            }
      }
      
      public static void main(String[] args)
      {
         Analysis a1 = new Analysis("meat");
         Analysis a2 = new Analysis("milk");
         Analysis a3 = new Analysis("corn");
         Analysis a4 = new Analysis("seeds");
         Analysis a5 = new Analysis("oat");
         Analysis a6 = new Analysis("food");
         Analysis a7 = new Analysis("feed");
         Analysis a8 = new Analysis("cereals");
         Analysis a9 = new Analysis("cereals2");
         Analysis a10 = new Analysis("cereals3");
         Analysis a11 = new Analysis("cereals4");
         Analysis a12 = new Analysis("cereals5");
         Analysis a13 = new Analysis("cereals6");
         Analysis a14 = new Analysis("cereals7");
         Analysis a15 = new Analysis("bla");
         
         AnalysisList analysisList = new AnalysisList();
         analysisList.addAnalysis(a1);
         analysisList.addAnalysis(a2);
         analysisList.addAnalysis(a3);
         analysisList.addAnalysis(a4);
         analysisList.addAnalysis(a5);
         analysisList.addAnalysis(a6);
         analysisList.addAnalysis(a7);
         analysisList.addAnalysis(a8);
         analysisList.addAnalysis(a9);
         analysisList.addAnalysis(a10);
         analysisList.addAnalysis(a11);
         analysisList.addAnalysis(a12);
         analysisList.addAnalysis(a13);
         analysisList.addAnalysis(a14);
         analysisList.addAnalysis(a15);
         
         AnalysisAdapter analysisAdapter = new AnalysisAdapter("analysis.bin");
         analysisAdapter.saveAnalyses(analysisList);
      }
}
