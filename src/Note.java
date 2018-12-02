import java.io.Serializable;

/**
 * This class is made to save all kinds of specific notes that might be needed
 * @author Karla Jajic
 * @version 1.0
 */
public class Note implements Serializable
{
   private String note;
   private String name;
   private boolean availability;
   
   /**
    * The constructor creates an object of type note which by default says that note is not
    * connected with employees availability to work 
    * @param name The parameter of type String which contains the name of the note
    * @param note The parameter of type String which contains the message of the note
    */
   public Note(String name, String note)
   {
      this.note=note;
      this.name=name;
      this.availability=false;
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
    * This method is used to specify the note as a note which is connected with employees
    * availability to work
    */
   public void toAvailable()
   {
      this.availability=true;
   }
   
   /**
    * This method is used to see if the note is connected to the employees availability
    * to work
    * @return The method returns true if the note is connected to the employees availability
    * to work and false if not 
    */
   public boolean isAvailability()
   {
      return availability;
   }
   
   /**
    * This method is used to copy object of type Note
    * @return The method returns an object of the type Note which carries the same content
    * as the one which it was copied from 
    */
   public Note copy()
   {
      if(availability==false)
      return new Note(name, note);
      else
      {
         Note n =new Note(name, note);
         n.toAvailable();
         return n;
      }
   }
   
   /**
    * The method is used to check if the object given in parameter is the same as the one
    * that the method is called on
    * @param obj The parameter is any Object. We compare the note with this object
    * @return The method returns boolean, true if two objects are the same and false if they
    * are different
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Note))
         return false;
      Note other =(Note)obj;
      return other.note.equals(note)&&other.availability==availability&&other.name.equals(name);
   }
   
   /**
    * This method is used to present the note in the form of text
    * @return This method returns an object of type String which carries the information about
    * specific note
    */
   public String toString()
   {
      if(availability)
         return name+": "+note+"(availability)";
      return name+": "+note;
   }
}
