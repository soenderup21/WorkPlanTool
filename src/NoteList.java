import java.util.ArrayList;

/**
 * 
 * @author Draluca
 *Has an array list with all the notes related to an employee/ a day/ a whatever
 */
public class NoteList
{
   private ArrayList<Note> notes;
   
   /**
    * Empty contructor
    */
   public NoteList()
   {
      notes = new ArrayList<Note>();
   }
   
   /**
    * adds a note
    * @param note is added at the end of the list
    */
   public void addNote(Note note)
   {
      notes.add(note);
   }
   
   /**
    * 
    * @param index the index of the note to be changed
    * @param note the note that will replace the previous one
    */
   public void editNote(int index, Note note)
   {
      notes.set(index, note);
   }
   
   /**
    * deletes a note with a specified index
    * @param index specifies the index of the note to be deleted
    */
   public void removeNote(int index)
   {
      notes.remove(index);
   }
   
   /**
    * deletes a certain note from the list
    * @param note indicates the note to be deleted
    */
   public void removeNote(Note note)
   {
      notes.remove(note);
   }
   
   /**
    * 
    * @param note is the note whose index will be returned
    * @return the index of a given note
    */
   public int getIndex(Note note)
   {
      for(int i = 0; i < notes.size(); ++i)
      {
         if(notes.get(i).equals(note))
            return i;
      }
      return -1;
   }
   
   /**
    * 
    * @return the arrayList that holds the notes
    */
   public ArrayList<Note> getAllNotes()
   {
      return notes;
   }
}
