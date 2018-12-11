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
	      catch (IOException e)
	      {
	         System.out.println("Error writing file");
	      }
	   }
	   public void saveAnalysis(Analysis analysis)
	   {
		   
		   try
		   {
			   ArrayList<Analysis> allAnalysis = new ArrayList<>();
			   allAnalysis.add(analysis);
			   mfio.writeToFile(fileName, allAnalysis);
		   }
		   catch (IOException e)
		      {
		         System.out.println("Error writing file");
		      }
	   }

}
