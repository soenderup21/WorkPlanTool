import java.io.Serializable;

/**
 * 
 * @author Draluca
 * Holds the details about each employee's ability to perform an analysis
 */
public class AnalysisDetails implements Serializable
{
   private Analysis analysis;
   private boolean trained;
   private boolean preference;
   private MyDate lastPerformed;
   
   /**
    * 
    * @param analysis sets the analysis one employee is able to perform
    */
   
   public AnalysisDetails(Analysis analysis)
   {
      this.analysis = analysis;
      trained = true;
      preference = false;
      lastPerformed = MyDate.getCurrentDate();
   }
   
   /**
    * 
    * @return analysis
    */
   public Analysis getAnalysis()
   {
      return analysis;
   }
   
   /**
    * 
    * @return true if the analysis is a preference of the employee and false otherwise
    */
   public boolean isPreference()
   {
      return preference;
   }
   
   /**
    * calls the checkTrained method
    * @return true of the employee is trained or false otherwise
    */
   public boolean isTrained() 
   {
      checkTrained();
      return trained;
   }
   
   /**
    * 
    * @return the last time the employee has performed the analysis
    */
   public MyDate getLastDate()
   {
      return lastPerformed;
   }
   
   /**
    * When an employee is assigned for an analysis, the lastPerformed date is changed to the current 
    * date
    */
   
   public void performed()
   {
      lastPerformed = MyDate.getCurrentDate();
   }
   /**
    * 
    * @param date is the date the employee has last performed the analysis
    * lastDate gets the value in date
    */
   public void setLastDate(MyDate date)
   {
      lastPerformed = date.copy();
   }
   
   /**
    * is called when the employee finishes training
    * trained gets the value of true and the lastPerformed gets the current day
    */
   public void setTrained()
   {
      lastPerformed = MyDate.getCurrentDate();
      this.trained = true;
   }
   
   /**
    * @param b is a boolean that specifies if the analysis is a preference or not
    * the analysis is set as prefered or not prefered, depending on the value of the parameter
    */
   public void setPreference(boolean b)
   {
      preference = b;
   }
   
   /**
    * checks if the last time the employee has performed the analysis was more than one year ago and changes
    * the trained variable to false if so
    */
   public void checkTrained()
   {
      MyDate today = MyDate.getCurrentDate();
      if(today.getYear() - lastPerformed.getYear() > 1)
         trained = false;
      else if(today.getYear() - lastPerformed.getYear() == 1)
      {
         if(today.getMonth() > lastPerformed.getMonth())
            trained = false;
         else if(today.getMonth() == lastPerformed.getMonth())
         {
            if(today.getDay() > lastPerformed.getDay())
               trained = false;
         }
      }
   }
   
   public boolean equals(Object obj)
   {
      if(!(obj instanceof AnalysisDetails))
         return false;
      AnalysisDetails other = (AnalysisDetails) obj;
      return other.preference == preference && other.trained == trained && 
            other.analysis.equals(analysis) && other.lastPerformed.equals(lastPerformed);
   }
   
}
