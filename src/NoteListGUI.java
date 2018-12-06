import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class NoteListGUI extends JFrame
{
   private NoteAdapter adapter;
   private JPanel mainPanel;
   
   private JPanel allNotesPanel;
   private JTextArea allNotesArea;
   private JScrollPane allNotesScrollPane;
   private JComboBox<Note> noteBox;
   
   private JButton getButton;
   private JPanel buttonPanel;
   private JButton removeButton;
   
   private NotePanel noteP;
   
   public NoteListGUI()
   {
      super("Notes");
      adapter= new NoteAdapter("notes.bin");
      Listener listen = new Listener();
      
      allNotesPanel=new JPanel();
      allNotesArea=new JTextArea(10, 20);
      allNotesArea.setEditable(false);
      allNotesScrollPane=new JScrollPane(allNotesArea);
      allNotesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      allNotesPanel.add(allNotesScrollPane);
      updateAllNotesArea();
      
      noteBox=new JComboBox<Note>();
      noteBox.addActionListener(listen);
      allNotesPanel.add(noteBox);
      
      buttonPanel=new JPanel();
      getButton=new JButton("Get");
      getButton.addActionListener(listen);
      removeButton=new JButton("Remove");
      removeButton.addActionListener(listen);
      buttonPanel.add(getButton);
      buttonPanel.add(removeButton);
      allNotesPanel.add(buttonPanel);
     
      noteP=new NotePanel();
      
      mainPanel= new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
      mainPanel.add(allNotesPanel);
      mainPanel.add(noteP);
      
      add(mainPanel);
      setSize(1000, 650);
      setVisible(true);
      setResizable(false);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }
   
   /*private void setNoteBox()
   {
      NoteList notes = adapter.getAllNotes();
      for(int i = 0; i<notes.size(); i++)
      {
         noteBox.addItem(notes.getNote(i));
      }
   }*/
   
   private void updateNoteBox()
   {
      int currentIndex = noteBox.getSelectedIndex();
      
      noteBox.removeAllItems();
      
      NoteList notes = adapter.getAllNotes();
      for(int i = 0; i<notes.size(); i++)
      {
         noteBox.addItem(notes.getNote(i));
      }

      if(currentIndex==-1 && noteBox.getItemCount()>0)
      {
         noteBox.setSelectedIndex(0);
      }
      else
      {
         noteBox.setSelectedIndex(currentIndex);
      }
   }
   
   private void updateAllNotesArea()
   {
      NoteList n=adapter.getAllNotes();
      allNotesArea.setText(n.toString());
      System.out.println(n.toString());
   }
   
   private class Listener implements ActionListener
   {  
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==noteBox)
         {
            if(noteBox.getSelectedItem() instanceof Note)
            {
               Note t=(Note)noteBox.getSelectedItem();
               nameField.setText(t.getName());
               noteArea.setText(t.getNote());
            }
         }
         
         if(e.getSource()==removeButton)
         {
            NoteList n=adapter.getAllNotes();
            for(int i=0;i<n.size();i++)
            {
               if(nameField.getText().equals(n.getNote(i).getName()))
               {
                  n.removeNote(i);
                  nameField.setText("");
                 adapter.saveNotes(n);
                 updateAllNotesArea();
               }
            }
         }
         
         if(e.getSource()==getButton)
         {
            updateAllNotesArea();
            NoteList n=adapter.getAllNotes();
            Note[] notes=new Note[n.size()];
            notes=n.getAllNotes();
            /*for(int i=0;i<notes.length;i++)
               if(allNotesArea.getSelectedText()==notes[i].getName())
               {
                  nameField.setText(notes[i].getName());
                  noteArea.setText(notes[i].getNote());
               }*/
         }
      }
   }
   
   public static void main(String[] args)
   {
      NoteListGUI n= new NoteListGUI();
   }
   
}
