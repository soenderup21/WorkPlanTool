/**
 * 
 * @author Draluca
 * Holds the details about each employee's ability to perform an analysis
 */
public class AnalysisDetails
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
    * the analysis is set as prefered
    */
   public void setPreference()
   {
      preference = true;
   }
   
   /**
    * the analysis is set as not prefered
    */
   public void notPrefered()
   {
      preference = false;
   }
   
   /**
    * 
    * @return the preference
    */
   public boolean getPreference()
   {
      return preference;
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
   
}
