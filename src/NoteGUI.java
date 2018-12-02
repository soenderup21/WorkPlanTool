import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NoteGUI extends JFrame
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
   private JPanel mainPanel;
   
   public NoteGUI()
   {
      super("Note");
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
      
      mainPanel= new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      mainPanel.add(namePanel);
      mainPanel.add(notePanel);
      mainPanel.add(buttonPanel);
      
      add(mainPanel);
      setSize(350, 350);
      setVisible(true);
      setResizable(false);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
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
            NoteList nl=new NoteList();
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
   
   public static void main(String[] args)
   {
      NoteGUI n=new NoteGUI();
   }
}
