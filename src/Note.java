import java.io.Serializable;

/**
 * This class is made to save all kinds of specific notes that might be needed
 * @author Karla Jajic
 * @version 2.0
 */
public class Note implements Serializable
{
   private String note;
   private String name;
   private boolean general;
   private MyDate date;
   
   /**
    * The constructor creates an object of type note which by default says that note is not
    * connected with employees availability to work 
    * @param name The parameter of type String which contains the name of the note
    * @param note The parameter of type String which contains the message of the note
    * @param date The parameter of type MyDate which says to what date the note is related,
    * it is null when the note is connected to employee information
    */
   public Note(String name, String note, MyDate date)
   {
      this.note=note;
      this.name=name;
      this.general=false;
      this.date=date;
   }
   
   /**
    * This method is used to change the message of the note
    * @param note The parameter of type String which contains the message of the note
    */
   public void setNote(String note)
   {
      this.note=note;
   }
   
   /**
    * This method is used to get the message of the specific note
    * @return The method returns the object of type String that represents the message of
    * the note
    */
   public String getNote()
   {
      return note;
   }
   
   /**
    * This method is used to change the name of the note
    * @param name The parameter of type String which contains the name of the note
    */
   public void setName(String name)
   {
      this.name=name;
   }
   
   /**
    * This method is used to get the name of the specific note
    * @return The method returns the object of type String that represents the name of
    * the note
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * This method is used to set the date of the specific note
    * @param date The parameter of type MyDate which contains the date of the note
    */
   public void setDate(MyDate date)
   {
      this.date=date;
   }
   
   /**
    * This method is used to get the date of the specific note
    * @return The method returns the object of type MyDate that represents the date related
    * to the note
    */
   public MyDate getDate()
   {
      return date;
   }
   
   /**
    * This method is used to specify the note as general
    */
   public void toGeneral()
   {
      this.general=true;
   }
   
   /**
    * This method is used to see if the note is relevant for whole week, not only one day
    * @return The method returns true if the note is relevant for whole week, false if it
    * is relevant for one specific date
    */
   public boolean isGeneral()
   {
      return general;
   }
   
   /**
    * This method is used to copy object of type Note
    * @return The method returns an object of the type Note which carries the same content
    * as the one which it was copied from 
    */
   public Note copy()
   {
      if(general==false)
      return new Note(name, note, date);
      else
      {
         Note n =new Note(name, note, date);
         n.toGeneral();
         return n;
      }
   }
   
   /**
    * The method is used to check if the object given in parameter is the same as the one
    * that the method is called on. We are not checking the date of the note because some
    * notes don't have dates and it is not that relevant.
    * @param obj The parameter is any Object. We compare the note with this object
    * @return The method returns boolean, true if two objects are the same and false if they
    * are different
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Note))
         return false;
      Note other =(Note)obj;
      return other.note.equals(note)&&other.general==general&&other.name.equals(name);
   }
   
   /**
    * This method is used to present the note in the form of text
    * @return This method returns an object of type String which carries the information about
    * specific note
    */
   public String toString()
   {
      String s="";
      if(general)
         s=name+" ("+date.getWeek()+")";
      else s=name+" ("+date.toStringForCalendar()+")";
      return s;
   }
}
