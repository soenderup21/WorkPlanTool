import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.swing.text.NumberFormatter;

public class ScheduleTable extends JPanel
{
   private Listener listen;
   private TableSelectionListener tableListen;
   private EmployeeFileAdapter eAdapter;
   private NoteAdapter nAdapter;
   private WeekAdapter adapter;
   private Week weeks;
   private TaskList tasks;
   private MyDate monday;
   
   private JTable schedule;
   private String[] dayOfTheWeek;
   private DefaultTableModel dtm;
   private ListSelectionModel listSelectionModel;
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
   private HeaderPanel week;
   private JButton forward;
   
   private JPanel headerPanel;
   private JPanel tablePanel;
   private JPanel centralPanel;
   
   private JPanel footerPanel;
   private JPanel schedulePanel;
   
   
   public ScheduleTable()
   {
      listen= new Listener();
      tableListen=new TableSelectionListener();
      this.monday=MyDate.getMondayOfWeek(MyDate.getCurrentDate().getWeek());
      
      eAdapter=new EmployeeFileAdapter();
      nAdapter=new NoteAdapter("notes.bin");
      
      //see if there was already made schedule, if not make a new TaskList
      adapter=new WeekAdapter("week.bin");
      weeks=adapter.getAllTasks();
      tasks=new TaskList();
      if(weeks.isThereAWeek(monday.getWeek()))
         tasks=weeks.getTaskListByWeek(monday.getWeek());
      
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
      schedule.setPreferredScrollableViewportSize(new Dimension(706, schedule.getRowHeight()*24));
      
      schedule.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
             int row = schedule.rowAtPoint(evt.getPoint());
             int col = schedule.columnAtPoint(evt.getPoint());
             if (row >= 0 && col > 0) {
                if(analysisP.countComponents()!=0)
                {
                   analysisP.remove(analysisScroll);
                   analysisP.remove(doneButton);
                }
                updateAnalysisPanel();
                sidePanelEnabled(true);
             }
         }
     }); 
      
      
      //adding table listener so we can keep track of users selections
      listSelectionModel = schedule.getSelectionModel();
      listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listSelectionModel.addListSelectionListener(tableListen);
      schedule.setSelectionModel(listSelectionModel);
      
      //adding table to the scrollPane
      scheduleScroll = new JScrollPane(schedule);
      scheduleScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      updateTable(monday);
      
      //setting the header
      backward=new JButton("<");
      backward.addActionListener(listen);
      week=new HeaderPanel(monday);
      forward=new JButton(">");
      forward.addActionListener(listen);
      
      headerPanel=new JPanel();
      headerPanel.setBorder(BorderFactory.createEmptyBorder());
      headerPanel.setLayout(new BorderLayout());
      headerPanel.add(backward, BorderLayout.WEST);
      headerPanel.add(week, BorderLayout.CENTER);
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
      //updateNotes(monday);
      noteScroll = new JScrollPane(notesArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      seeNotesPanel.add(noteScroll);
      
      noteTabPane= new JTabbedPane();
      noteTabPane.addTab("Add", addNotePanel);
      noteTabPane.addTab("See", seeNotesPanel);
      updateNotes(monday);
      
      analysisP=new JPanel();
      
      searchPanel=new JPanel();
      searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
      searchPanel.setBackground(Color.DARK_GRAY);
      searchPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(81, 112, 160)));
      searchField= new JTextField(10);
      searchField.setBorder(BorderFactory.createEmptyBorder());
      searchField.setToolTipText("Enter the number of the week");
      searchButton=new JButton("Search"); //add image
      searchButton.setBorder(BorderFactory.createEmptyBorder());
      searchButton.addActionListener(listen);
      searchPanel.add(searchField);
      searchPanel.add(searchButton);
      
      sidePanel=new JPanel();
      sidePanel.setLayout(new BorderLayout());
      sidePanel.add(searchPanel, BorderLayout.NORTH);
      sidePanel.add(analysisP, BorderLayout.CENTER);
      sidePanel.add(noteTabPane, BorderLayout.SOUTH);
      sidePanel.setBackground(Color.DARK_GRAY);
      sidePanel.setPreferredSize(new Dimension(264, 698));
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
      this.monday=MyDate.getMondayOfWeek(week.getMonday().getWeek()+1);
      week.updateHeader(monday);
      updateNotes(monday);
      updateTable(monday);
   }
   
   public void updateBackward()
   {
      this.monday=MyDate.getMondayOfWeek(week.getMonday().getWeek()-1);
      week.updateHeader(monday);
      updateNotes(monday);
      updateTable(monday);
   }
   
   public void updateTable(MyDate m)
   {
      EmployeeList employees=eAdapter.getEmployeeList();
      Object[][] data= new Object[employees.size()][6];
      
      weeks=adapter.getAllTasks();
      tasks=new TaskList();
      if(weeks.isThereAWeek(monday.getWeek()))
         tasks=weeks.getTaskListByWeek(monday.getWeek());
      
      for(int i=0;i<employees.size();i++)
      {
         for(int j=0;j<6;j++)
         {
            if(j==0) data[i][j]=employees.get(i).getIntials(); //setting first column
            else
            {
               if(tasks.getTask(employees.get(i), monday.getDayOfWeek(j-1))!=null)
                  data[i][j]=tasks.getTask(employees.get(i), monday.getDayOfWeek(j-1)).getAnalysisInString();
            }
         }
      }
      
      DefaultTableModel tableModel = new DefaultTableModel(data, dayOfTheWeek) {
         public boolean isCellEditable(int row, int column) {
             return false;
         }
      };
      
      schedule.setModel(tableModel);
      listSelectionModel = schedule.getSelectionModel();
      listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listSelectionModel.addListSelectionListener(tableListen);
      schedule.setSelectionModel(listSelectionModel);
      
      DefaultTableCellRenderer r = new DefaultTableCellRenderer();
      r.setToolTipText("Click on right panel to refresh!");
      for(int i=0;i<6;i++)
         schedule.getColumnModel().getColumn(i).setCellRenderer(r);
   }
   
   
   public void sidePanelEnabled(boolean b)
   {
      if(b)
      {
         noteP.setEnabled(false);
         saveNoteButton.setVisible(true);
         //doneButton.setVisible(true);
      }
      else
      {
         noteP.setEnabled(true);
         saveNoteButton.setVisible(false);
        // doneButton.setVisible(false);
      }
   }
   
   public void updateAnalysisPanel()
   {
      int r=schedule.getSelectedRow();
      int c=schedule.getSelectedColumn();
      
      EmployeeList list=eAdapter.getEmployeeList();
      
      if(analysisP.countComponents()!=0)
      {
         analysisP.remove(analysisScroll);
         analysisP.remove(doneButton);
      }
      
      analysisP.setLayout(new BorderLayout());
      analysisPanel=new AnalysisPanel(r);
      
      if(tasks.getTask(list.get(r), monday.getDayOfWeek(c-1))!=null)
         analysisPanel.setSelected(tasks.getTask(list.get(r), monday.getDayOfWeek(c-1)).getAnalysis());
      
      analysisScroll=new JScrollPane(analysisPanel);
      analysisScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      doneButton= new JButton("DONE");
      doneButton.addActionListener(listen);
      
      analysisP.add(analysisScroll, BorderLayout.CENTER);
      analysisP.add(doneButton, BorderLayout.SOUTH);
   }
   
   public void updateNotes(MyDate m)
   {
      String s="";
      NoteList allNotes=nAdapter.getAllNotes();
      for(int i=0;i<allNotes.size();i++)
         if(allNotes.getNote(i).getDate().getWeek()==m.getWeek())
         {
            s+=allNotes.getNote(i).getName().toUpperCase()+"\n"+
                  allNotes.getNote(i).getDate().toStringForCalendar()+"\n"+
                  allNotes.getNote(i).getNote()+"\n\n";
         }
      notesArea.setText(s);
   }
   
   public void setTableValue(String[] a)
   {
      
      int r=schedule.getSelectedRow();
      int c=schedule.getSelectedColumn();
      
      EmployeeList employees=(EmployeeList)eAdapter.getEmployeeList();
      
      Task t=new Task(employees.get(r), monday.getDayOfWeek(c-1));
      
      System.out.println(monday.getDayOfWeek(c-1));
      
      if(!analysisPanel.nothingSelected())
      {
         String s=a[0];
         if(a.length!=1)
            for(int i=1;i<a.length;i++)
               s+=", "+a[i];
         String fin=s;
         
         if(r!=-1 && c!=-1)
            schedule.getModel().setValueAt(fin, r, c);
         
         ArrayList<Analysis> an=employees.get(r).getAllAnalyses();
         ArrayList<Analysis> na=new ArrayList<Analysis>();
         for(int i=0;i<a.length;i++)
            for(int j=0;j<an.size();j++)
               if(a[i].equals(an.get(j).getName())) na.add((Analysis)an.get(j));
         
         t.addAnalysisArray(na);
      }
      else schedule.getModel().setValueAt(" ", r, c);
     
      if(analysisP.countComponents()!=0)
      {
         analysisP.remove(analysisScroll);
         analysisP.remove(doneButton);
      }
      
      tasks.addTask(t);
      weeks.addTaskList(tasks);
      adapter.saveTasks(weeks);
      
      Week nw=adapter.getAllTasks();
      System.out.println(nw.size());
      TaskList tl=nw.getList(0);
      System.out.println(tl.getTaskCount());
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
         
         if(e.getSource()==searchButton)
         {
            try 
            {
                int n = Integer.parseInt(searchField.getText()); 
                updateTable(MyDate.getMondayOfWeek(n));
                searchField.setText("");
            }
            catch (NumberFormatException e1) 
            { 
                   JOptionPane.showMessageDialog(getParent(), "Wrong input!",
                      "Error", JOptionPane.ERROR_MESSAGE);
                   searchField.setText("");
                   return;
          }
         }
         
         if(e.getSource()==doneButton)
         {
            
            int choice = JOptionPane.showConfirmDialog(getParent(), "Do you want to save the changes?"); 
            if(choice==JOptionPane.YES_OPTION) 
            {
               String[] list= analysisPanel.getAllPickedString();
               setTableValue(list);
               sidePanelEnabled(false);
            }
            else if(choice==JOptionPane.NO_OPTION)
            {
               updateAnalysisPanel();
            }
            else if(choice==JOptionPane.CANCEL_OPTION)
            {
               updateAnalysisPanel();
            }
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
            updateNotes(monday);
         }
      }
   } 
   
   private class TableSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         if(analysisP.countComponents()!=0)
         {
            analysisP.remove(analysisScroll);
            analysisP.remove(doneButton);
         }
         int c=schedule.getSelectedColumn();
         int r=schedule.getSelectedRow();
         if(c>0 && r>-1)
         {
            updateAnalysisPanel();
         }
         sidePanelEnabled(true);
         System.out.println(schedule.getSelectedRow());
      }
   }
}