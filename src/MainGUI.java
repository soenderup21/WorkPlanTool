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
   
   private JPanel employeeTab;
   private NoteTab noteTab;
   private JPanel analysisTab;
   private ScheduleTable schedulePanel;
   
   public MainGUI()
   {
      super("Work Plan Tool v.1.0");

      tabListen = new TabListener();
      
      analysisTab=new JPanel();
      employeeTab=new JPanel();
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
            //fill this out
         }
      }
   }
   
   public static void main(String[] args)
   {
      MainGUI mg= new MainGUI();
   }
   
}
