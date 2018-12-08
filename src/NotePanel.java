import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class NotePanel extends JPanel
{
   private MyDate temp;
   private NoteAdapter adapter;
   private JPanel namePanel;
   private JLabel nameLabel;
   private JTextField nameField;
   
   private JPanel notePanel;
   private JLabel noteLabel;
   private JTextArea noteArea;
   
   private JPanel buttonPanel;
   private JCheckBox generalCheck;
   
   public NotePanel()
   {
      super();
      adapter=new NoteAdapter("notes.bin");
      
      namePanel=new JPanel();
      nameLabel=new JLabel("Name: ");
      nameField=new JTextField(15);
      namePanel.add(nameLabel);
      namePanel.add(nameField);
      
      notePanel=new JPanel();
      noteLabel=new JLabel("Note: ");
      noteArea=new JTextArea(12, 25);
      notePanel.add(noteLabel);
      notePanel.add(noteArea);
      
      buttonPanel= new JPanel();
      generalCheck=new JCheckBox("General");
      buttonPanel.add(generalCheck);
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(namePanel);
      add(notePanel);
      add(buttonPanel);
      setMaximumSize(new Dimension(400, 300));
   }
   
   
   public String getName()
   {
      String s="";
      s=nameField.getText();
      return s;
   }
   public void setName(String name)
   {
      nameField.setText(name);
   }
   
   public String getNote()
   {
      String s="";
      s=noteArea.getText();
      return s;
   }
   public void setNote(String note)
   {
      nameField.setText(note);
   }
   
   public void setGeneral(boolean t)
   {
      generalCheck.setSelected(t);
   }
   public boolean getGeneral()
   {
      if(generalCheck.isSelected()) return true;
      else return false;
   }
   
   public void setEnabled(boolean t)
   {
      if(t)
      {
         noteArea.setEditable(false);
         generalCheck.setEnabled(false);
      }
      else
      {
         noteArea.setEditable(true);
         generalCheck.setEnabled(true);
      }
   }
   
   public void saveNote(MyDate date)
   {
      if(nameField.getText()=="" || noteArea.getText()=="")
         System.out.println("How to write the error?");
      Note newNote=new Note(nameField.getText(), noteArea.getText(),date);
      if(getGeneral()) newNote.toGeneral();
      NoteList nl=adapter.getAllNotes();
      nl.addNote(newNote);
      adapter.saveNotes(nl);
   }
   
   public static void main(String[] args)
   {
      JFrame frame=new JFrame("Kalendar");
      Container c = frame.getContentPane();
      c.add(new NotePanel());
      frame.pack();
      frame.setVisible(true);
   }
}