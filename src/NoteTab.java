import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class NoteTab extends JFrame
{
   private NoteAdapter adapter;
   private JPanel mainPanel;
   
   private JPanel allNotesPanel;
   private JTextArea allNotesArea;
   private JScrollPane allNotesScrollPane;
   
   private NotePanel noteP;
   private JPanel notePanel;
   
   private JPanel datePanel;
   private JLabel dateLabel;
   private JComboBox<String> monthBox;
   private JComboBox<Integer> dayBox;
   private JComboBox<Integer> yearBox;
   
   private JPanel buttonPanel;
   private JButton removeButton;
   private JButton saveNoteButton;
   
   public NoteTab()
   {
      super("Notes");
      adapter= new NoteAdapter("notes.bin");
      Listener listen = new Listener();
      
      allNotesPanel=new JPanel();
      allNotesArea=new JTextArea(19, 30);
      allNotesArea.setEditable(false);
      allNotesScrollPane=new JScrollPane(allNotesArea);
      allNotesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      allNotesPanel.add(allNotesScrollPane);
      updateAllNotesArea();
      
      buttonPanel=new JPanel();
      saveNoteButton=new JButton("Edit");
      saveNoteButton.addActionListener(listen);
      removeButton=new JButton("Remove");
      removeButton.addActionListener(listen);
      buttonPanel.add(saveNoteButton);
      buttonPanel.add(removeButton);
      
      dateLabel=new JLabel("Date:   ");
      datePanel =new JPanel();
      datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
      datePanel.setMaximumSize(new Dimension(316,15));
      datePanel.add(dateLabel);
      
      yearBox=new JComboBox<Integer>();
      yearBox.addActionListener(listen);
      datePanel.add(yearBox);
      
      monthBox= new JComboBox<String>();
      monthBox.addActionListener(listen);
      datePanel.add(monthBox);
      
      dayBox=new JComboBox<Integer>();
      dayBox.addActionListener(listen);
      datePanel.add(dayBox);
     
      notePanel=new JPanel();
      noteP=new NotePanel();
      noteP.setEnabled(true);
      notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
      notePanel.add(noteP);
      notePanel.add(datePanel);
      notePanel.add(buttonPanel);
      
      mainPanel= new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
      mainPanel.add(allNotesPanel);
      mainPanel.add(notePanel);
      mainPanel.setMaximumSize(new Dimension(800, 400));
      
      add(mainPanel); //doesn't work??
      setSize(1000, 700);
      setVisible(true);
      setResizable(false);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }
   
   //used to set comboBoxes to current date
   private void setDate()
   {
      MyDate temp= MyDate.getCurrentDate();
      
      yearBox.removeAllItems();
      for(int i=0;i<20;i++)
         yearBox.addItem(2016+i);
      yearBox.setSelectedIndex((int)(temp.getYear()-2016));
      
      monthBox.removeAllItems();
      for(int i=1;i<=12;i++)
         monthBox.addItem(getMonthInString(i));
      monthBox.setSelectedIndex((int)(temp.getMonth()-1));
      
      dayBox.removeAllItems();
      int numberOfDays=getDaysOfMonth(temp.getMonth(), temp.getYear());
         for(int i=1;i<=numberOfDays;i++)
            dayBox.addItem(i);
         dayBox.setSelectedIndex(temp.getDay());
      
   }
 
   //used to get number of the days of the specific month
   private int getDaysOfMonth(int i, int year)
   {
      int size=0;
      if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12)
         size=31;
      else if(i==2)
      {
         if((year%4==0 && year%100!=0) || year%400==0)
            size=29;
         else size=28;
      }
      else size=30;
      
      return size;
   }
   
   //used to see the name of the month when given integer
   private String getMonthInString(int i)
   {
      if(i==1)
         return "January";
      else if(i==2)
         return "February";
      else if(i==3)
         return "March";
      else if(i==4)
         return "April";
      else if(i==5)
         return "May";
      else if(i==6)
         return "June";
      else if(i==7)
         return "July";
      else if(i==8)
         return "August";
      else if(i==9)
         return "October";
      else if(i==10)
         return "September";
      else if(i==11)
         return "November";
      else if(i==12)
         return "December";
      else return "Error";
   }
   
   private void updateAllNotesArea()
   {
      NoteList n=adapter.getAllNotes();
      allNotesArea.setText(n.toString());
      System.out.println(n.toString());
   }
   
   private void updateYearBox()
   {
      int year=yearBox.getSelectedIndex()+2016;
      yearBox.removeAllItems();
      
      for(int i=0;i<20;i++)
         yearBox.addItem(2016+i);
      if(year==2016 && yearBox.getItemCount()>0)
         yearBox.setSelectedIndex(0);
      else yearBox.setSelectedIndex(year-2016);
   }
   
   private void updateMonthBox()
   {
      int month=monthBox.getSelectedIndex();
      monthBox.removeAllItems();
      
      for(int i=1;i<=12;i++)
         monthBox.addItem(getMonthInString(i));
      if(month==-1 && yearBox.getItemCount()>0)
         monthBox.setSelectedIndex(0);
      else monthBox.setSelectedIndex(month);
   }
   
   private void updateDayBox()
   {
      int day=dayBox.getSelectedIndex();
      dayBox.removeAllItems();
      
      int numberOfDays=getDaysOfMonth(monthBox.getSelectedIndex()+1, yearBox.getSelectedIndex()+2016);
      for(int i=1;i<=numberOfDays;i++)
         dayBox.addItem(i);
      if(day==-1 && yearBox.getItemCount()>0)
         dayBox.setSelectedIndex(0);
      else dayBox.setSelectedIndex(day);
   }
   
   public MyDate saveDate()
   {
      MyDate newDate = MyDate.getCurrentDate();
      int day=dayBox.getSelectedIndex()+1;
      int month= monthBox.getSelectedIndex()+1;
      int year=yearBox.getSelectedIndex()+2016;
      newDate.setDate(day,month, year);
      return newDate;
   }
   
   private class Listener implements ActionListener
   {  
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==yearBox)
         {
            updateYearBox();
         }
         
         if(e.getSource()==monthBox)
         {
            updateMonthBox();
            updateDayBox();
         }
         
         if(e.getSource()==removeButton)
         {
            NoteList n=adapter.getAllNotes();
            for(int i=0;i<n.size();i++)
            {
               if(noteP.getName().equals(n.getNote(i).getName()))
               {
                  n.removeNote(i);
                  noteP.setName("");
                 adapter.saveNotes(n);
                 updateAllNotesArea();
               }
            }
         }
         
         if(e.getSource()==saveNoteButton)
         {
            updateAllNotesArea();
            NoteList n=adapter.getAllNotes();
            Note[] notes=new Note[n.size()];
            notes=n.getAllNotes();
            if(saveNoteButton.getText().equals("Edit"))
            {
               for(int i=0;i<notes.length;i++)
                  if(!noteP.getName().equals(notes[i]))
                  {
                     noteP.setEnabled(false);
                     saveNoteButton.setText("Save");
                     setDate();
                  }
            }
            else 
            {
               if(noteP.getName()=="" || noteP.getNote()=="")
                  System.out.println("How to write the error?");
               Note newNote=new Note(noteP.getName(), noteP.getNote(),saveDate());
               if(noteP.getGeneral()) newNote.toGeneral();
               NoteList nl=adapter.getAllNotes();
               nl.addNote(newNote);
               adapter.saveNotes(nl);
               
               noteP.setName("");
               noteP.setNote("");
               noteP.setGeneral(false);
               
               //kako ocistit nakon spremanja??
               
               noteP.setEnabled(true);
               updateAllNotesArea();
               saveNoteButton.setText("Edit");
            }
         }
      }
   }
   
   public static void main(String[] args)
   {
      NoteTab n= new NoteTab();
   }
   
}
