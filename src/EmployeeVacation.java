public class EmployeeVacation 
{
   private Employee employeeOnVacation;
   private MyDate firstDate;
   private MyDate lastDate;
   
   public EmployeeVacation(Employee employee, MyDate firstDate, MyDate lastDate) {
      employeeOnVacation = employee;
      this.firstDate = firstDate;
      this.lastDate =  lastDate;
   }
   
   public void setStartDate(MyDate firstDate){
      this.firstDate = firstDate;
   }
   
   public void setLastDate(MyDate lastDate) {
      this.lastDate = lastDate;
   }
   
   public MyDate getFirstDate() {
      return firstDate;
   }
   
   public MyDate getLastDate() {
      return lastDate;
   }
   
   public Employee employeeOnVacation() {
      return employeeOnVacation;
   }
   
   public boolean isInbetween(MyDate currentDate) {
      if(currentDate.isLater(firstDate)) {
         if(!(currentDate.isLater(lastDate))) {
            return true;
         }else if(currentDate.equals(lastDate))
         return true;
      }
         return false;
         
   }

  /* public static void main(String[] args) {
      MyDate first = new MyDate(1, 1, 2000);
      MyDate last = new MyDate(20, 1, 2000);
      Employee aleks = new Employee("AA", "Aleks");
      EmployeeVacation vac = new EmployeeVacation(aleks, first, last);
      MyDate testDate1 = new MyDate(1, 1, 2000);
      MyDate testDate2 = new MyDate(12, 1, 2000);
      MyDate testDate3 = new MyDate(20, 1, 2000);
      MyDate testDate4 = new MyDate(21, 1, 2000);
      MyDate testDate5 = new MyDate(31, 12, 1999);
      
      System.out.println(vac.isInbetween(testDate1));
      System.out.println(vac.isInbetween(testDate2));
      System.out.println(vac.isInbetween(testDate3));
      System.out.println(vac.isInbetween(testDate4));
      System.out.println(vac.isInbetween(testDate5));
   }*/
}
