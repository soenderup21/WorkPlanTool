import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author raluca
 *has the information about each employee - name, initials, the analyses they are able to perform and
 *some additional notes
 */
public class Employee implements Serializable
{
   private String name;
   private String initials;
   private ArrayList<AnalysisDetails> analyses;
   private Note note;
   
   public Employee(String name, String initials)
   {
      this.name = name;
      this.initials = initials;
      analyses = new ArrayList<AnalysisDetails>();
      note = null;
   }
   
   public String getIntials()
   {
      return initials;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setInitials(String initials)
   {
      this.initials = initials.toUpperCase();
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void addAnalysis(Analysis analysis)
   {
      int i;
      for(i = 0; i < analyses.size(); ++i)
      {
         if(analyses.get(i).getAnalysis().equals(analysis))
            break;
      }
      if(i == analyses.size())
         analyses.add(new AnalysisDetails(analysis));
   }
   
   public int getNumberOfAnalyses()
   {
      return analyses.size();
   }
   
   public Note getNote()
   {
      return note;
   }
   
   public boolean equalsInitials(String initials)
   {
      return this.initials.equalsIgnoreCase(initials);
   }
   
   public void setNote(Note note)
   {
      this.note = note;
   }
   
   public void setPrefered(Analysis analysis)
   {
      for(int i = 0; i < analyses.size(); ++i)
      {
         if(analyses.get(i).getAnalysis().equals(analysis))
         {
            analyses.get(i).setPreference();
            break;
         }
      }
   }
   
   public void setNotPrefered(Analysis analysis)
   {
      for(int i = 0; i < analyses.size(); ++i)
      {
         if(analyses.get(i).getAnalysis().equals(analysis))
         {
            analyses.get(i).setPreference();
            break;
         }
      }
   }
   public void sortAnalysisByPreference()
   {
      AnalysisDetails aux;
      int count = 0;
      for(int i = 0; i < analyses.size(); ++i)
      {
         if(analyses.get(i).isPreference())
         {
            if(count < i)
            {
               aux = analyses.get(count);
               analyses.set(count, analyses.get(i));
               analyses.set(i, aux);
            }
            ++count;
         }
      }
   }
   
   public ArrayList<Analysis> getAllAnalyses()
   {
      ArrayList<Analysis> aux = new ArrayList<Analysis>();
      sortAnalysisByPreference();
      for(int i = 0; i < analyses.size(); ++i)
         aux.add(analyses.get(i).getAnalysis());
      return aux;
   }
   
   public ArrayList<AnalysisDetails> getAllAnalysesDetails()
   {
      ArrayList<AnalysisDetails> aux = new ArrayList<AnalysisDetails>();
      sortAnalysisByPreference();
      for(int i = 0; i < analyses.size(); ++i)
         aux.add(analyses.get(i));
      return aux;
   }
   
   public String displayAnalysis()
   {
      sortAnalysisByPreference();
      String str = "";
      
      for(int i = 0; i < analyses.size(); ++i)
      {
         str += (analyses.get(i).getAnalysis().toString() + "\n");
      }
      return str;
   }
   
   public String displayDetails()
   {
      sortAnalysisByPreference();
      String str = displayAnalysis();
      if(note != null)
      {
         str += note.toString();
      }
      else str += "No notes";
      return str;
   }
}