
public class AnalysisDetails
{
   private boolean preference;
   private boolean trained;
   private Analysis analysis;
   private MyDate lastDate;
   
   public AnalysisDetails(Analysis analysis)
   {
      this.analysis = analysis;
      trained = true;
      preference = false;
      lastDate = MyDate.getCurrentDate();
   }
   public void setLastDate(MyDate date)
   {
      lastDate = date.copy();
   }
}
