import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
   private NoteAdapter adapter;
   private JPanel namePanel;
   private JLabel nameLabel;
   private JTextField nameField;
   
   private JPanel notePanel;
   private JLabel noteLabel;
   private JTextArea noteArea;
   
   private JPanel buttonPanel;
   private JButton createButton;
   private JCheckBox availabilityCheck;
   
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
      
      Listener listen = new Listener();
      buttonPanel= new JPanel();
      availabilityCheck=new JCheckBox("Availability");
      createButton =new JButton("Save");
      availabilityCheck.addActionListener(listen);
      createButton.addActionListener(listen);
      buttonPanel.add(availabilityCheck);
      buttonPanel.add(createButton);
      
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
      nameField.setText(note);
   }
   
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==createButton)
         {
            JOptionPane jp=new JOptionPane();
            if(nameField.getText()=="" || noteArea.getText()=="")
            {
               /* JOptionPane.showMessageDialog(null, "One of the fields is empty", "Error", JOptionPane.WARNING_MESSAGE);
               dispose(); */
            }
            Note n=new Note(nameField.getText(), noteArea.getText());
            if(availabilityCheck.isSelected()) n.toAvailable();
            NoteList nl=adapter.getAllNotes();
            nl.addNote(n);
            adapter.saveNotes(nl);
            
            nameField.setText("");
            noteArea.setText("");
            availabilityCheck.setSelected(false);
            
            nl=(NoteList)adapter.getAllNotes();
            System.out.println(nl);
         }
      }
   }
   
 /*  public static void main(String[] args)
   {
      NoteGUI n=new NoteGUI();
      JFrame frame=new JFrame("Kalendar");
      Container c = frame.getContentPane();
      c.add(n);
      frame.pack();
      frame.setVisible(true);
   }*/
}
