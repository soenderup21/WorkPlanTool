import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class TableGUI extends JTable
{
   private TableSelectionListener tableListen;
   private EmployeeFileAdapter eAdapter;
   
   private WeekAdapter adapter;
   private Week weeks;
   private TaskList tasks;
   private MyDate monday;
   
   private String[] dayOfTheWeek;
   private DefaultTableModel dtm;
   private ListSelectionModel listSelectionModel;
   private JScrollPane scheduleScroll;
   
   
   public TableGUI(MyDate monday)
   {
      super(new DefaultTableModel());
      tableListen=new TableSelectionListener();
      this.monday=MyDate.getMondayOfWeek(MyDate.getCurrentDate().getWeek());
      
      eAdapter=new EmployeeFileAdapter();
      
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
      setModel(dtm);
      getTableHeader().setBackground(new Color(206, 225, 229));
      setGridColor(Color.DARK_GRAY);
      setCellSelectionEnabled(true);
      setRowHeight(25);
      getTableHeader().setReorderingAllowed(false);
      getTableHeader().setResizingAllowed(false);
      setPreferredScrollableViewportSize(new Dimension(706, getRowHeight()*24));
      
      addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
             int row = rowAtPoint(evt.getPoint());
             int col = columnAtPoint(evt.getPoint());
             if (row >= 0 && col > 0) {
                 
             }
         }
     }); 
      
      //adding table listener so we can keep track of users selections
      listSelectionModel = getSelectionModel();
      listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listSelectionModel.addListSelectionListener(tableListen);
      setSelectionModel(listSelectionModel);
      
      updateTable(monday);
   }
   
   public void updateTable(MyDate m)
   {
      EmployeeList employees=eAdapter.getEmployeeList();
      Object[][] data= new Object[employees.size()][6];
      
      for(int i=0;i<employees.size();i++)
      {
         for(int j=0;j<6;j++)
         {
            if(j==0) data[i][j]=employees.get(i).getIntials(); //setting first column
            else if(tasks.getTask(employees.get(i), monday.getDayOfWeek(j))!=null)
               data[i][j]=tasks.getTask(employees.get(i), monday.getDayOfWeek(j)).getAnalysisInString();
            else
            {
               data[i][j]=new Task(employees.get(i), monday.getDayOfWeek(j)).getAnalysisInString();
               tasks.addTask(new Task(employees.get(i), monday.getDayOfWeek(j)));
            }
         }
         
         weeks.addTaskList(tasks);
         adapter.saveTasks(weeks);
      }
      
      DefaultTableModel tableModel = new DefaultTableModel(data, dayOfTheWeek) {
         public boolean isCellEditable(int row, int column) {
             return false;
         }
      };
      
      setModel(tableModel);
      listSelectionModel = getSelectionModel();
      listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listSelectionModel.addListSelectionListener(tableListen);
      setSelectionModel(listSelectionModel);
   }
   
   
   

   private class TableSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         int c=getSelectedColumn();
         //if(c>0)
            
         //System.out.println(getSelectedRow());
      }
   }
}
