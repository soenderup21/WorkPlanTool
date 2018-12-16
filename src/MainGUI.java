import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame
{
   private JTabbedPane tabPane;
   private TabListener tabListen;
   
   private EmployeeGUI employeeTab;
   private NoteTab noteTab;
   private AnalysisTab analysisTab;
   private ScheduleTable schedulePanel;
   
   public MainGUI()
   {
      super("Work Plan Tool v.1.0");

      tabListen = new TabListener();
      
      analysisTab=new AnalysisTab();
      employeeTab=new EmployeeGUI();
      noteTab=new NoteTab();
      schedulePanel=new ScheduleTable();
      
      tabPane=new JTabbedPane();
      tabPane.addChangeListener(tabListen);
      tabPane.addTab("Schedule Planner", schedulePanel);
      tabPane.addTab("Employees", employeeTab);
      tabPane.addTab("Notes", noteTab);
      tabPane.addTab("Analysis", analysisTab);
      
      add(tabPane);
      setSize(1000, 700);
      setVisible(true);
      setResizable(false);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }
   
   private class TabListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==0)
         {
            //fill this out
         }
         
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==1)
         {
            //update this
         }
         
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==2)
         {
            noteTab.updateNotesList(false);
         } 
 
         if(((JTabbedPane)e.getSource()).getSelectedIndex()==3)
         {
            analysisTab.updateAnalysisList();
         }
      }
   }
   
   public static void main(String[] args)
   {
      MainGUI mg= new MainGUI();
      
      EmployeeFileAdapter adapter = new EmployeeFileAdapter();
      Employee e1 = new Employee("Raluca Petrovici", "RPE");
      Employee e2 = new Employee("Karla Jajic", "KJ");
      Employee e3 = new Employee("Aleksandra Aleksandrova", "AAL");
      Employee e4 = new Employee("Christian Stewart Soenderup", "CSS");

      Employee e8 = new Employee("Christian Stewart Soenderup", "CSSo");
      Employee e5 = new Employee("Kiril Iliev", "KI");
      Employee e6 = new Employee("Valera Rusu", "VR");
      Employee e7 = new Employee("David Franko", "DAFR");
      Employee e9 = new Employee("David Franko", "DAF");
      Employee e10 = new Employee("David Franko", "DAR");
      Employee e11 = new Employee("David Franko", "AFR");
      Employee e12 = new Employee("David Franko", "DhAFR");
      Employee e13 = new Employee("David Franko", "DAgFR");
      Employee e14 = new Employee("David Franko", "DAFhzR");
      Employee e15 = new Employee("David Franko", "DAdFR");
      Employee e16 = new Employee("David Franko", "DzhAFR");
      Employee e17 = new Employee("David Franko", "DAFyR");
      Employee e18 = new Employee("David Franko", "DAFrR");
      Employee e19 = new Employee("David Franko", "DAFRu");
      Employee e20 = new Employee("David Franko", "DAaFR");
      Employee e21 = new Employee("David Franko", "DAbFR");
      
      Analysis a1 = new Analysis("meat");
      Analysis a2 = new Analysis("milk");
      Analysis a3 = new Analysis("corn");
      Analysis a4 = new Analysis("seeds");
      Analysis a5 = new Analysis("oat");
      Analysis a6 = new Analysis("food");
      Analysis a7 = new Analysis("feed");
      Analysis a8 = new Analysis("cereals");
      Analysis a9 = new Analysis("cereals2");
      Analysis a10 = new Analysis("cereals3");
      Analysis a11 = new Analysis("cereals4");
      Analysis a12 = new Analysis("cereals5");
      Analysis a13 = new Analysis("cereals6");
      Analysis a14 = new Analysis("cereals7");
      Analysis a15 = new Analysis("bla");
      
      e1.addAnalysis(a1);
      e1.addAnalysis(a2);
      e1.addAnalysis(a8);
      e1.addAnalysis(a9);
      e1.addAnalysis(a10);
      e1.addAnalysis(a11);
      e1.addAnalysis(a12);
      e1.addAnalysis(a13);
      e1.addAnalysis(a3);
      e1.addAnalysis(a4);
      e1.addAnalysis(a5);
      e1.addAnalysis(a6);
      e1.addAnalysis(a7);
      e1.addAnalysis(a14);
      e1.addAnalysis(a15);
      
      e1.setNote(new Note("Name", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new MyDate(1, 1, 2000)));
      
      e2.addAnalysis(a2);
      e2.addAnalysis(a8);
      e2.addAnalysis(a3);
      e3.addAnalysis(a3);
      e4.addAnalysis(a4);
      e5.addAnalysis(a5);
      e5.addAnalysis(a8);
      e6.addAnalysis(a6);
      e6.addAnalysis(a7);
      e6.addAnalysis(a8);
      e7.addAnalysis(a7);
      
      EmployeeList empList = new EmployeeList();
      empList.add(e1);
      empList.add(e2);
      empList.add(e3);
      empList.add(e4);
      empList.add(e5);
      empList.add(e6);
      empList.add(e7);
      empList.add(e8);
      empList.add(e9);
      empList.add(e10);
      empList.add(e11);
      empList.add(e12);
      empList.add(e13);
      empList.add(e14);
      empList.add(e15);
      empList.add(e16);
      empList.add(e17);
      empList.add(e18);
      empList.add(e19);
      empList.add(e20);
      empList.add(e21);
      
      AnalysisList analysisList = new AnalysisList();
      analysisList.addAnalysis(a1);
      analysisList.addAnalysis(a2);
      analysisList.addAnalysis(a3);
      analysisList.addAnalysis(a4);
      analysisList.addAnalysis(a5);
      analysisList.addAnalysis(a6);
      analysisList.addAnalysis(a7);
      analysisList.addAnalysis(a8);
      analysisList.addAnalysis(a9);
      analysisList.addAnalysis(a10);
      analysisList.addAnalysis(a11);
      analysisList.addAnalysis(a12);
      analysisList.addAnalysis(a13);
      analysisList.addAnalysis(a14);
      analysisList.addAnalysis(a15);
      
      AnalysisAdapter analysisAdapter = new AnalysisAdapter("analysis.bin");
      analysisAdapter.saveAnalyses(analysisList);
      adapter.saveEmployeeList(empList);
   }
   
}
