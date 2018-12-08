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
      this.initials = initials;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void addAnalysis(AnalysisDetails analysis)
   {
      analyses.add(analysis);
      sortAnalysisByPreference();
   }
   
   public boolean equalsInitials(String initials)
   {
      return this.initials.equals(initials);
   }
   
   public void setNote(Note note)
   {
      this.note = note;
   }
   
   public void sortAnalysisByPreference()
   {
      AnalysisDetails aux;
      int count = 0;
      for(int i = 0; i < analyses.size(); ++i)
      {
         if(analyses.get(i).getPreference())
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
   
   public ArrayList<AnalysisDetails> getAllAnalyses()
   {
      return analyses;
   }
}