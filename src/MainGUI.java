import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainGUI extends JFrame
{
   private JTabbedPane tabPane;
   private Listener listen;
   
   private JPanel employeeTab;
   private NoteTab noteTab;
   private JPanel analysisTab;
   
   private JLabel testLabel;
   private NotePanel noteP;
   private JPanel notePanel;
   private JPanel analysisPanel;
   private JPanel sidePanel;
   
   private JButton backward;
   private JLabel weekLabel;
   private JButton forward;
   
   private JPanel headerPanel;
   private JPanel tablePanel;
   private JPanel centralPanel;
   
   private JPanel footerPanel;
   
   private JPanel schedulePanel;
   
   
   public MainGUI()
   {
      super("Work Plan Tool v1.0");
      //added colors so its easier to see stuff, remove later
      listen=new Listener();
      
      backward=new JButton("<");
      backward.addActionListener(listen);
      weekLabel=new JLabel("---NUMBER OF THE WEEK---");
      forward=new JButton(">");
      forward.addActionListener(listen);
      
      headerPanel=new JPanel();
      headerPanel.setBackground(Color.LIGHT_GRAY);
      headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
      headerPanel.add(backward);
      headerPanel.add(weekLabel);
      headerPanel.add(forward);
      
      tablePanel=new JPanel();
      tablePanel.setBackground(Color.DARK_GRAY);
      
      centralPanel=new JPanel();
      centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
      centralPanel.add(headerPanel);
      centralPanel.add(tablePanel);
      
      testLabel=new JLabel("test");
      sidePanel=new JPanel();
      sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
      sidePanel.add(testLabel);
      sidePanel.setBackground(Color.RED);
      
      footerPanel=new JPanel();
      
      schedulePanel=new JPanel();
      schedulePanel.setBackground(Color.BLACK);
      schedulePanel.setLayout(new BorderLayout());
      schedulePanel.add(centralPanel, BorderLayout.CENTER);
      schedulePanel.add(sidePanel, BorderLayout.EAST);
      schedulePanel.add(footerPanel, BorderLayout.SOUTH);
      
      analysisTab=new JPanel();
      employeeTab=new JPanel();
      noteTab=new NoteTab();
      
      tabPane=new JTabbedPane();
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
   
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
      }
   }
   
   public static void main(String[] args)
   {
      MainGUI mg=new MainGUI();
   }
}
