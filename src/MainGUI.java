import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MainGUI extends JFrame
{
   private JTabbedPane tabPane;
   private Listener listen;
   
   private JPanel employeeTab;
   private NoteTab noteTab;
   private AnalysisTab analysisTab;
   
   private JLabel testLabel;
   private JTabbedPane noteTabPane;
   private NotePanel noteP;
   private JButton saveNoteButton;
   private JPanel addNotePanel;
   private JPanel seeNotesPanel;
   private JTextArea notesArea;
   private JScrollPane noteScroll;
   
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
      
      testLabel=new JLabel("     test      ");
      
      noteP = new NotePanel();
      noteP.toSmallerSize();
      addNotePanel = new JPanel();
      addNotePanel.add(noteP);
      saveNoteButton=new JButton("Save");
      addNotePanel.add(saveNoteButton);
      
      seeNotesPanel=new JPanel();
      notesArea= new JTextArea(15,22);
      notesArea.setEditable(false);
      noteScroll = new JScrollPane(notesArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      seeNotesPanel.add(noteScroll);
      
      noteTabPane= new JTabbedPane();
      noteTabPane.addTab("Add", addNotePanel);
      noteTabPane.addTab("See", seeNotesPanel);
      
      sidePanel=new JPanel();
      sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
      sidePanel.setMaximumSize(new Dimension(350, 700));
      sidePanel.setMinimumSize(new Dimension(350, 700));
      sidePanel.add(testLabel);
      sidePanel.add(noteTabPane);
      sidePanel.setBackground(Color.RED);
      
      footerPanel=new JPanel();
      
      schedulePanel=new JPanel();
      schedulePanel.setBackground(Color.BLACK);
      schedulePanel.setLayout(new BorderLayout());
      schedulePanel.add(centralPanel, BorderLayout.CENTER);
      schedulePanel.add(sidePanel, BorderLayout.EAST);
      schedulePanel.add(footerPanel, BorderLayout.SOUTH);
      
      analysisTab=new AnalysisTab();
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
