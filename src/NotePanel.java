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
import javax.swing.SwingConstants;

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
   private JScrollPane noteScroll;
   
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
      noteScroll= new JScrollPane(noteArea);
      noteScroll.setSize(100, 100);
      notePanel.add(noteLabel);
      notePanel.add(noteScroll);
      
      buttonPanel= new JPanel();
      generalCheck=new JCheckBox("General");
      buttonPanel.add(generalCheck);
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(namePanel);
      add(notePanel);
      add(buttonPanel);
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
      noteArea.setText(note);
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
         JOptionPane.showMessageDialog(getParent(), "Wrong input",
               "Error", JOptionPane.ERROR_MESSAGE);
      Note newNote=new Note(nameField.getText(), noteArea.getText(),date);
      if(getGeneral()) newNote.toGeneral();
      NoteList nl=adapter.getAllNotes();
      nl.addNote(newNote);
      adapter.saveNotes(nl);
   }
   
   //we want to be able to use this panel in more places
   public void toSmallerSize()
   {
      nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
      nameField.setColumns(15);
      noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
      noteArea.setColumns(17);
      noteArea.setRows(8);
   }
   
   //testing the panel
   public static void main(String[] args)
   {
      JFrame frame=new JFrame("Kalendar");
      Container c = frame.getContentPane();
      NotePanel p=new NotePanel();
      p.toSmallerSize();
      c.add(p);
      frame.pack();
      frame.setVisible(true);
   }
}