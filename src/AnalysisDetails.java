public class AnalysisDetails
{
   private Analysis analysis;
   private boolean trained;
   private boolean preference;
   private MyDate lastPerformed;
   
   public AnalysisDetails(Analysis analysis)
   {
      this.analysis = analysis;
      trained = true;
      preference = false;
      lastPerformed = MyDate.getCurrentDate();
   }
   
   public Analysis getAnalysis()
   {
      return analysis;
   }
   
   public boolean isPreference()
   {
      return preference;
   }
   
   public boolean isTrained() 
   {
      checkTrained();
      return trained;
   }
   
   public MyDate getLastDate()
   {
      return lastPerformed;
   }
   
   public void setLastDate(MyDate date)
   {
      lastPerformed = date.copy();
   }
   
   public void setTrained()
   {
      this.trained = true;
   }
   
   public void setPreference(boolean preference)
   {
      this.preference = preference;
   }
   
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