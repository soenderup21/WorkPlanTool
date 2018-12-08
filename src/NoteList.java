import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to manage notes
 * @author Karla Jajic
 * @version 2.0
 */
public class NoteList implements Serializable
{
  private ArrayList<Note> notes;
  
  /**
   * This constructor is used to create new NoteList object. It initializes the 
   * array list of notes that is used through the class
   */
  public NoteList()
  {
     notes=new ArrayList<Note>();
  }
  
  /**
   * This method is used to add specific note to the list of notes if the list
   * does not contain the same note
   * @param note This is the note that we want to save to the list
   */
  public void addNote(Note note)
  {
     boolean t=false;
     if(notes.size()==0) notes.add(note);
     for(int i=0;i<notes.size();i++)
        if(note.equals(notes.get(i))) t=true;
     if(!t) notes.add(note);
  }
  
  /**
   * This method is used to change the text of the already existing note
   * @param index This parameter is used to find specific note
   * @param newText This parameter is the text that should be changed 
   */
  public void editNote (int index, String newText )
  {
     if(index<notes.size())
     {
        notes.get(index).setNote(newText);
     }
  }
  
  /**
   * This method is used to remove the specific note
   * @param index This parameter is used to find specific note
   */
  public void removeNote(int index)
  {
     if(index<notes.size())
     {
        notes.remove(index);
     }
  }
  
  /**
   * This method is used to find out the position of the specific note
   * @param text The parameter is used to compare the message of the wanted note
   * @return The method returns the number which represents the position of the note
   * in the list of notes
   */
  public int getIndex(String text)
  {
     int n=0;
     for(int i=0;i<notes.size();i++)
        if(notes.get(i).getNote().equals(text)) n=i;
     return n;
  }
  
  /**
   * This method is used to get specific note from the list
   * @param index The parameter is an integer used to define the position of note in list
   * @return The method returns a searched object of type Note
   */
  public Note getNote(int index)
  {
     Note n =null;
     if(index<notes.size())
     {
       n= notes.get(index);
     }
     return n;
  }
  
  /**
   * This method is used to get the number of notes in the list
   * @return The method returns an integer which says how many notes there are
   */
  public int size()
  {
     return notes.size();
  }
  
  /**
   * This method goes through the list of notes and saves them in the array
   * @return The method returns the array made of all existing notes
   */
  public Note[] getAllNotes()
  {
     Note[] n= new Note[notes.size()];
     int j=0;
     for(int i=0;i<notes.size();i++)
     {
        n[j]=notes.get(i);
        j++;
     }
     return n;
  }
  
  /**
   * This method is used to present the names of the notes that are in the list 
   * in the type of text
   * @return This method returns an object of type String which carries names of
   * all notes in the note list
   */
  public String toString()
  {
    String s="";
     for(int i=0;i<notes.size();i++)
        if(notes.get(i).isGeneral()) s+=notes.get(i).getName()+"  (general)"+"\n";
        else 
           {
              if(notes.get(i).getDate()!=null)
              s+=notes.get(i).getName()+"("+notes.get(i).getDate()+")"+"\n";
              else s+=notes.get(i).getName()+"\n";
           }
     return s;
  }
  
}
