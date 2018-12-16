import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class NoteTab extends JPanel
{
   private NoteAdapter adapter;
   private JPanel mainPanel;
   
   private JRadioButton generalButton;
   private JRadioButton allNotesButton;
   private ButtonGroup radioButtons;
   private JPanel radioButtonsPanel;
   
   private JPanel showNotesPanel;
   private JList<Note> noteList;
   private DefaultListModel<Note> listModel;
   private JScrollPane showNotesScrollPane;
   
   private NotePanel noteP;
   private JPanel notePanel;
   
   private JPanel datePanel;
   private JLabel dateLabel;
   private JComboBox<String> monthBox;
   private JComboBox<Integer> dayBox;
   private JComboBox<Integer> yearBox;
   
   private JPanel buttonPanel;
   private JButton removeButton;
   private JButton editNoteButton;
   
   public NoteTab()
   {
      //initializing panel, adapter and adding button listener
      super();
      adapter= new NoteAdapter("notes.bin");
      Listener listen = new Listener();
      ListListener listListen = new ListListener();
      
      //setting radioButtons and adding them to the group and panel
      generalButton=new JRadioButton("General notes");
      generalButton.addActionListener(listen);
      allNotesButton= new JRadioButton("All notes", true);
      allNotesButton.addActionListener(listen);
      radioButtons=new ButtonGroup();
      radioButtons.add(generalButton);
      radioButtons.add(allNotesButton);
      radioButtonsPanel= new JPanel();
      radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.X_AXIS));
      radioButtonsPanel.add(generalButton);
      radioButtonsPanel.add(allNotesButton);
      
      //setting list of notes
      listModel= new DefaultListModel<Note>();
      noteList= new JList<Note>(listModel);
      noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      noteList.addListSelectionListener(listListen);
      noteList.setVisibleRowCount(18); 
      showNotesScrollPane= new JScrollPane(noteList);
      showNotesScrollPane.setPreferredSize(new Dimension(250,300));
      showNotesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      showNotesPanel= new JPanel();
      showNotesPanel.setLayout(new BoxLayout(showNotesPanel, BoxLayout.Y_AXIS));
      showNotesPanel.add(radioButtonsPanel);
      showNotesPanel.add(showNotesScrollPane);
      //updateNotesList(false);
      
      //adding buttons that are needed for saving note
      buttonPanel=new JPanel();
      editNoteButton=new JButton("Save");
      editNoteButton.addActionListener(listen);
      removeButton=new JButton("Remove");
      removeButton.addActionListener(listen);
      buttonPanel.add(editNoteButton);
      buttonPanel.add(removeButton);
      
      //adding panel that allows us to set the date
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
      
      setDate(MyDate.getCurrentDate());
      datePanel.setVisible(false);
     
      //connecting NotePanel with created buttons and comboBoxes
      notePanel=new JPanel();
      noteP=new NotePanel();
      noteP.setEnabled(true);
      notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
      notePanel.add(noteP);
      notePanel.add(datePanel);
      notePanel.add(buttonPanel);
      
      mainPanel= new JPanel();
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.HORIZONTAL;
      mainPanel.setLayout(gridbag);
      
      mainPanel.add(showNotesPanel,c);
      //because we want to make space between two panels
      c.insets=new Insets(0,90,0,0); //padding
      mainPanel.add(notePanel,c);
      
      //setting to the center so we can align whole panel in the center
      c.fill = GridBagConstraints.CENTER;
      gridbag.setConstraints(this, c);
      setLayout(gridbag);
      add(mainPanel);
      setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, new Color(81, 112, 160)));
      setVisible(true);
      
   }
   
   public void updateNotesList(boolean general)
   {
      int currentIndex = noteList.getSelectedIndex();
      
      if(general==false)
      {
         listModel.clear();
         NoteList notes = adapter.getAllNotes();
         for(int i = 0; i<notes.size(); i++)
         {
            listModel.addElement((Note)notes.getNote(i));
         }

         if(currentIndex==-1 && listModel.size()>0)
         {
            noteList.clearSelection();
         }
         else
         {
            noteList.setSelectedIndex(currentIndex);
         }
      }
      else
      {
         listModel.clear();
         NoteList notes = adapter.generalNotes();
         for(int i = 0; i<notes.size(); i++)
         {
            listModel.addElement((Note)notes.getNote(i));
         }

         if(currentIndex==-1 && listModel.size()>0)
         {
            noteList.clearSelection();
         }
         else
         {
            noteList.setSelectedIndex(currentIndex);
         }
      }
   }
   
   private boolean alreadyThere()
   {
      boolean t=false;
      
      NoteList n=adapter.getAllNotes();
      Note[] notes=new Note[n.size()];
      notes=n.getAllNotes();
      
      for(int i=0;i<notes.length;i++)
         if(noteP.getName().equals(notes[i].getName())) t=true;
      
      return t;
   }
   
   //used to set comboBoxes to current date
   private void setDate(MyDate temp)
   {
      
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
         dayBox.setSelectedIndex(temp.getDay()-1);
      
   }
   
   private void updateYearBox()
   {
      int year=yearBox.getSelectedIndex();
      yearBox.removeAllItems();
      
      //because we count from 2016 we need to be careful when using info 
      for(int i=2016;i<2040;i++)
         yearBox.addItem(i);
      if(year==-1 && yearBox.getItemCount()>0)
         yearBox.setSelectedIndex(0);
      else yearBox.setSelectedIndex(year);
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
      else dayBox.setSelectedIndex(day+1);
   }
   
   //used for making a MyDate object when saving created note
   public MyDate saveDate()
   {
      MyDate newDate = MyDate.getCurrentDate();
      int day=dayBox.getSelectedIndex()+1;
      int month= monthBox.getSelectedIndex()+1;
      int year=yearBox.getSelectedIndex()+2016;
      newDate.setDate(day,month, year);
      return newDate;
   }
   
   
 //used to get the number of days in month, takes number of the month and 
   //year as a parameter
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
   
   
   
   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e) 
      {
         if (e.getSource() == noteList)
         {
            if (noteList.getSelectedValue() instanceof Note)
            {
               Note temp = (Note)noteList.getSelectedValue();
               noteP.setName(temp.getName());
            }
         }
      }
   }
   
   private class Listener implements ActionListener
   {  
      public void actionPerformed(ActionEvent e)
      {
         //checking what notes should be in the noteArea
         if(e.getSource()==allNotesButton)
         {
            updateNotesList(false);
         }
         if(e.getSource()==generalButton)
         {
            updateNotesList(true);
         }
         
         //changing the date
         if(e.getSource()==yearBox)
         {
            updateYearBox();
         }
         
         if(e.getSource()==monthBox)
         {
            updateMonthBox();
            updateDayBox(); //updating so that user can't pick the wrong date
         }
         
         if(e.getSource()==removeButton)
         {
            NoteList n=adapter.getAllNotes();
            //we want to check if user is sure in removing the note
            int choice = JOptionPane.showConfirmDialog(getParent(), "Do you want to remove the note?"); 
            if(choice==JOptionPane.YES_OPTION) 
            {
               for(int i=0;i<n.size();i++)
               {
                  if(noteP.getName().equals(n.getNote(i).getName()))
                  {
                     n.removeNote(i);
                     noteP.setName("");
                     adapter.saveNotes(n);
                     updateNotesList(false);
                     allNotesButton.setSelected(true);
                  }
               }
            }
            else if(choice==JOptionPane.NO_OPTION)
            {
               noteP.setName("");
               noteList.clearSelection();
            }
            else if(choice==JOptionPane.CANCEL_OPTION)
            {
               noteP.setName("");
               noteList.clearSelection();
            }
         }
         
         if(e.getSource()==editNoteButton)
         {
            if(generalButton.isSelected()) updateNotesList(true);
            else updateNotesList(false);
            NoteList n=adapter.getAllNotes();
            Note[] notes=new Note[n.size()];
            notes=n.getAllNotes();
            
            if(alreadyThere() && editNoteButton.getText().equals("Save"))
            {
               int choice = JOptionPane.showConfirmDialog(getParent(), "Do you want to edit the note?"); 
               if(choice==JOptionPane.YES_OPTION) 
               {
                  for(int i=0;i<notes.length;i++)
                     if(noteP.getName().equals(notes[i].getName()))
                     {
                        noteP.setEnabled(false);
                        noteP.setNote(notes[i].getNote());
                        if(notes[i].isGeneral()) noteP.setGeneral(true);
                        datePanel.setVisible(true);
                        setDate(notes[i].getDate());
                        editNoteButton.setText("Edit");
                        noteList.clearSelection();
                     }
               }
               else if(choice==JOptionPane.NO_OPTION)
               {
                  noteP.setName("");
                  noteList.clearSelection();
               }
               else if(choice==JOptionPane.CANCEL_OPTION)
               {
                  noteP.setName("");
                  noteList.clearSelection();
               }
            }
            else if(alreadyThere()==false && editNoteButton.getText().equals("Save"))
            {
               noteP.setEnabled(false);
               datePanel.setVisible(true);
               setDate(MyDate.getCurrentDate());
               editNoteButton.setText("New");
               noteList.clearSelection();
            }
            else
            {
             //edits if it's already there, makes new one if there isn't
               if(alreadyThere())
               {
                  for(int i=0;i<notes.length;i++)
                     if(noteP.getName().equals(notes[i].getName()))
                     {
                        n.removeNote(i);
                        Note newN=new Note(noteP.getName(), noteP.getNote(), saveDate());
                        if(noteP.getGeneral()) newN.toGeneral();
                        n.addNote(newN);
                        
                        adapter.saveNotes(n);
                     }
               }
               else noteP.saveNote(saveDate());
               
               //clears up everything
               noteP.setName("");
               noteP.setNote("");
               noteP.setGeneral(false);
               datePanel.setVisible(false);
               
               noteP.setEnabled(true);
               updateNotesList(false);
               allNotesButton.setSelected(true);
               editNoteButton.setText("Save");
               noteList.clearSelection();
            }
            
         }
      }
   }
}
