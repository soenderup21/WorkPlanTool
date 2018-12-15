import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ScheduleTable extends JPanel
{
   private Listener listen;
   private EmployeeFileAdapter eAdapter;
   private NoteAdapter nAdapter;
   private WeekAdapter adapter;
   private Week weeks;
   private TaskList tasks;
   private MyDate monday;
   
   private JTable schedule;
   private String[] dayOfTheWeek;
   private DefaultTableModel dtm;
   private JScrollPane scheduleScroll;
   
   private JTabbedPane noteTabPane;
   private NotePanel noteP;
   private JButton saveNoteButton;
   private JPanel addNotePanel;
   private JPanel seeNotesPanel;
   private JTextArea notesArea;
   private JScrollPane noteScroll;
   
   private JPanel analysisP;
   private AnalysisPanel analysisPanel;
   private JScrollPane analysisScroll;
   private JButton doneButton;
   
   private JTextField searchField;
   private JButton searchButton;
   private JPanel searchPanel;
   
   private JPanel sidePanel;
   
   private JButton backward;
   private JLabel weekLabel;
   private JButton forward;
   
   private JPanel headerPanel;
   private JPanel tablePanel;
   private JPanel centralPanel;
   
   private JPanel footerPanel;
   private JPanel schedulePanel;
   
   
   public ScheduleTable()
   {
      listen= new Listener();
      this.monday=MyDate.getMondayOfWeek(MyDate.getCurrentDate().getWeek());
      
      eAdapter=new EmployeeFileAdapter();
      nAdapter=new NoteAdapter("notes.bin");
      
      //see if there was already made schedule, if not make a new TaskList
      adapter=new WeekAdapter("week.bin");
      weeks=adapter.getAllTasks();
      tasks=new TaskList();
      if(weeks.isThereAWeek(monday.getWeek()))
         for(int i=0;i<weeks.size();i++)
            if(weeks.getList(i).getWeek()==monday.getWeek())
               tasks=weeks.getList(i);
      
      //setting the header of the table
      dayOfTheWeek = new String[6];
      dayOfTheWeek[0]="Employee";
      dayOfTheWeek[1]="Monday \n";
      dayOfTheWeek[2]="Tuesday ";
      dayOfTheWeek[3]="Wednesday ";
      dayOfTheWeek[4]="Thursday ";
      dayOfTheWeek[5]="Friday ";
      
      dtm = new DefaultTableModel(dayOfTheWeek,0);
      schedule = new JTable(dtm);
      schedule.getTableHeader().setBackground(new Color(206, 225, 229));
      schedule.setGridColor(Color.DARK_GRAY);
      schedule.setCellSelectionEnabled(true);
      schedule.setRowHeight(25);
      schedule.getTableHeader().setReorderingAllowed(false);
      schedule.getTableHeader().setResizingAllowed(false);
      schedule.setPreferredScrollableViewportSize(new Dimension(700, schedule.getRowHeight()*24));
      
      //adding mouse listener so we know when we can allow user to write notes and pick analysis
      schedule.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent e){
            sidePanelEnabled(true);
            updateAnalysisPanel();
         }});
      
      //adding table to the scrollPane
      scheduleScroll = new JScrollPane(schedule);
      scheduleScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      updateTable(monday);
      
      //setting the header
      backward=new JButton("<");
      backward.addActionListener(listen);
      weekLabel=new JLabel("WEEK  "+updateHeader(monday));
      weekLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
      weekLabel.setForeground(new Color(44, 62, 91));
      weekLabel.setHorizontalAlignment(SwingConstants.CENTER); //setting text in the center
      forward=new JButton(">");
      forward.addActionListener(listen);
      
      headerPanel=new JPanel();
      headerPanel.setBorder(BorderFactory.createEmptyBorder());
      headerPanel.setBackground(new Color(229, 237, 249));
      headerPanel.setLayout(new BorderLayout());
      headerPanel.add(backward, BorderLayout.WEST);
      headerPanel.add(weekLabel, BorderLayout.CENTER);
      headerPanel.add(forward, BorderLayout.EAST);
      
      tablePanel=new JPanel();
      tablePanel.add(scheduleScroll);
      tablePanel.setBackground(Color.DARK_GRAY);
      
      centralPanel=new JPanel();
      centralPanel.setBackground(Color.DARK_GRAY);
      centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
      centralPanel.add(headerPanel);
      centralPanel.add(tablePanel);
      
      //making note part 
      noteP = new NotePanel();
      noteP.toSmallerSize();
      addNotePanel = new JPanel();
      addNotePanel.setLayout(new BoxLayout(addNotePanel, BoxLayout.Y_AXIS));
      addNotePanel.add(noteP);
      saveNoteButton=new JButton("Save");
      saveNoteButton.addActionListener(listen);
      addNotePanel.add(saveNoteButton);
      
      seeNotesPanel=new JPanel();
      notesArea= new JTextArea(15,22);
      notesArea.setEditable(false);
      updateNotes();
      noteScroll = new JScrollPane(notesArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      seeNotesPanel.add(noteScroll);
      
      noteTabPane= new JTabbedPane();
      noteTabPane.addTab("Add", addNotePanel);
      noteTabPane.addTab("See", seeNotesPanel);
      
      analysisP=new JPanel();
      analysisP.setLayout(new BoxLayout(analysisP, BoxLayout.Y_AXIS));
      analysisPanel=new AnalysisPanel();
      analysisScroll=new JScrollPane(analysisPanel);
      analysisScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      doneButton= new JButton("DONE");
      doneButton.addActionListener(listen);
      analysisP.add(analysisScroll);
      analysisP.add(doneButton);
      
      searchPanel=new JPanel();
      searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
      searchPanel.setBackground(Color.DARK_GRAY);
      searchPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(81, 112, 160)));
      searchField= new JTextField(10);
      searchField.setBorder(BorderFactory.createEmptyBorder());
      searchField.setToolTipText("Enter the number of the week");
      searchButton=new JButton("Search"); //add image
      searchButton.setBorder(BorderFactory.createEmptyBorder());
      searchPanel.add(searchField);
      searchPanel.add(searchButton);
      
      sidePanel=new JPanel();
      sidePanel.setLayout(new BorderLayout());
      sidePanel.add(searchPanel, BorderLayout.NORTH);
      sidePanel.add(analysisP, BorderLayout.CENTER);
      sidePanel.add(noteTabPane, BorderLayout.SOUTH);
      sidePanel.setBackground(Color.DARK_GRAY);
      sidePanelEnabled(false);
      
      footerPanel=new JPanel();
      footerPanel.setPreferredSize(new Dimension(1000, 30));
      footerPanel.setBackground(Color.DARK_GRAY);
      footerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, new Color(81, 112, 160)));
      
      setBackground(Color.BLACK);
      setLayout(new BorderLayout());
      setMaximumSize(new Dimension(990,690));
      add(centralPanel, BorderLayout.CENTER);
      add(sidePanel, BorderLayout.EAST);
      add(footerPanel, BorderLayout.SOUTH);
      
   }
   
   public void updateForward()
   {
      this.monday=MyDate.getMondayOfWeek(monday.getWeek()+1);
      updateTable(monday);
   }
   
   public void updateBackward()
   {
      this.monday=MyDate.getMondayOfWeek(monday.getWeek()-1);
      updateTable(monday);
   }
   
   public String updateHeader(MyDate m)
   {
      String sunday=m.getDayOfWeek(6).toStringForCalendar();
      String week=m.getWeek()+" ("+m.toStringForCalendar()+" - "+sunday+" )";
      return week;
   }
   
   public void updateTable(MyDate m)
   {
      EmployeeList employees=eAdapter.getEmployeeList();
      Object[][] data= new Object[employees.size()][6];
      
      for(int i=0;i<employees.size();i++)
      {
         for(int j=0;j<6;j++)
         {
            if(j==0) data[i][j]=employees.get(i).getIntials();
            TaskList temp=tasks.getTasks(employees.get(i));
            
         }
      }
      
      DefaultTableModel tableModel = new DefaultTableModel(data, dayOfTheWeek) {
         public boolean isCellEditable(int row, int column) {
             return false;
         }
      };


      
      schedule.setModel(tableModel);
   }
   
   
   public void sidePanelEnabled(boolean b)
   {
      if(b)
      {
         noteP.setEnabled(false);
         updateAnalysisPanel();
         saveNoteButton.setVisible(true);
         doneButton.setVisible(true);
      }
      else
      {
         noteP.setEnabled(true);
         analysisPanel.clearChoice();
         saveNoteButton.setVisible(false);
         doneButton.setVisible(false);
      }
   }
   
   public void updateAnalysisPanel()
   {
      int r=schedule.getSelectedRow();
      
      analysisPanel.setCheckBoxes();
   }
   
   public void updateNotes()
   {
      String s="";
      NoteList allNotes=nAdapter.getAllNotes();
      for(int i=0;i<allNotes.size();i++)
         if(allNotes.getNote(i).getDate().getWeek()==monday.getWeek())
         {
            s+=allNotes.getNote(i).getName().toUpperCase()+"\n"+
                  allNotes.getNote(i).getDate().toStringForCalendar()+"\n"+
                  allNotes.getNote(i).getNote()+"\n\n";
         }
      notesArea.setText(s);
   }
   
   public void setTableValue(String[] a)
   {
      String s=a[0];
      if(a.length!=1)
         for(int i=1;i<a.length;i++)
            s+=", "+a[i];
      String fin=s;
      int r=schedule.getSelectedRow();
      int c=schedule.getSelectedColumn();
      if(r!=-1 && c!=-1)
      schedule.getModel().setValueAt(fin, r, c);
      
      EmployeeList employees=(EmployeeList)eAdapter.getEmployeeList();
      //analysisPanel.setEmployee(r);
      
      Task t=new Task(employees.get(r), monday.getDayOfWeek(c-1));
      
      ArrayList<Analysis> an=employees.get(r).getAllAnalyses();
      ArrayList<Analysis> na=new ArrayList<Analysis>();
      for(int i=0;i<a.length;i++)
         for(int j=0;j<an.size();i++)
            if(a[i].equals(an.get(j).getName())) na.add(an.get(j));
      
      for(int i=0;i<na.size();i++)
         t.addAnalysis(na.get(i));
      
      
      
      sidePanelEnabled(false);
   }
   
   
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource()==forward)
         {
            updateForward();
         }
         
         if(e.getSource()==backward)
         {
            updateBackward();
         }
         
         if(e.getSource()==doneButton)
         {
            String[] list= analysisPanel.getAllPickedString();
            setTableValue(list);
         }
         
         if(e.getSource()==saveNoteButton)
         {
            MyDate date=null;
            int c=schedule.getSelectedColumn();
            if(c==0) date=MyDate.getCurrentDate();
            else date=monday.getDayOfWeek(c-1);
            
            noteP.saveNote(date);
            noteP.setName("");
            noteP.setNote("");
            noteP.setGeneral(false);
            updateNotes();
         }
      }
   } 
   
   public static void main(String[] args)
   {
      JFrame frame=new JFrame("Kalendar");
      Container c = frame.getContentPane();
      c.add(new ScheduleTable());
      frame.pack();
      frame.setVisible(true);
      //new ScheduleTable(MyDate.lastMonday()).createImage();
   }
}