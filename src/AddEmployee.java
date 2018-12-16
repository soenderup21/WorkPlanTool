import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddEmployee extends JFrame
{
   private JPanel enteringDataPanel;
   
   private JPanel initialsPanel;
   private JLabel initialsText;
   private JTextField initials;
   
   private JPanel namePanel;
   private JLabel nameText;
   private JTextField name;
   
   private JPanel containerPanel;
   
   private JLabel analysesText;
   private DefaultListModel<String> listModel1;
   private JList<String> analysisList;
   private JScrollPane analysisListScrollPane;
   private DefaultListCellRenderer renderer1;
   private MyListSelectionListener selectionListener;

   private DefaultListModel<String> listModel2;
   private JList<String> selectedAnalyses;
   private JScrollPane selectedAnalysisListScrollPane;
   private DefaultListCellRenderer renderer2;
   
   private JButton removeAnalysis;
   private JCheckBox prefered;
   
   private AnalysisAdapter analysisAdapter;
   private AnalysisList analyses;
   private EmployeeFileAdapter employeeAdapter;
   private EmployeeList employeeList;
   
   private JPanel listsPanel;
   
   private JPanel southPanel;
   private JButton save;
   private MyButtonListener buttonListener;
   
   
   public AddEmployee()
   {
      super("Add employee");
      
      setLayout(new BorderLayout());
      
      enteringDataPanel = new JPanel();
      enteringDataPanel.setLayout(new BoxLayout(enteringDataPanel, BoxLayout.Y_AXIS));
      
      initialsPanel = new JPanel();
      initialsText = new JLabel("Initials: ");
      initials = new JTextField();
      initials.setPreferredSize(new Dimension(200, 25));
      
      initialsPanel.add(initialsText);
      initialsPanel.add(initials);
//      initialsPanel.setMaximumSize(new Dimension(350, 30));
//      initialsPanel.setMinimumSize(new Dimension(350, 30));
      
      namePanel = new JPanel();
      nameText = new JLabel("Name:   ");
      name = new JTextField();
      name.setPreferredSize(new Dimension(200, 25));
      
      namePanel.add(nameText);
      namePanel.add(name);
//      namePanel.setMaximumSize(new Dimension(550, 30));
//      namePanel.setMinimumSize(new Dimension(350, 30));
      
      enteringDataPanel.setMaximumSize(new Dimension(600, 70));
      enteringDataPanel.setMinimumSize(new Dimension(600, 70));
      
      enteringDataPanel.add(initialsPanel);
      enteringDataPanel.add(namePanel);
      
      analysesText = new JLabel("Choose analyses: ");
      
      listModel1 = new DefaultListModel<String>();
      
      analysisAdapter = new AnalysisAdapter("analysis.bin");
      analyses = analysisAdapter.getAllAnalysis();
      
      for(int i = 0; i < analyses.size(); ++i)
      {
         listModel1.addElement(analyses.getAnalysis(i).toString());
      }
      analysisList = new JList<String>(listModel1);
      analysisList.setMaximumSize(new Dimension(200, 200));
      analysisList.setMinimumSize(new Dimension(200, 200));
      
      selectionListener = new MyListSelectionListener();
      analysisList.addListSelectionListener(selectionListener);
      
      renderer1 = (DefaultListCellRenderer) analysisList.getCellRenderer();
      renderer1.setHorizontalAlignment(JLabel.CENTER);
      
      analysisListScrollPane = new JScrollPane(analysisList);
      analysisListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      analysisListScrollPane.setPreferredSize(new Dimension(200, 200));

      listModel2 = new DefaultListModel<String>();
      selectedAnalyses = new JList<String>(listModel2);
      selectedAnalyses.addListSelectionListener(selectionListener);
      
      selectedAnalysisListScrollPane = new JScrollPane(selectedAnalyses);
      selectedAnalysisListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      selectedAnalysisListScrollPane.setPreferredSize(new Dimension(200, 200));
      
      renderer2 = (DefaultListCellRenderer) selectedAnalyses.getCellRenderer();
      renderer2.setHorizontalAlignment(JLabel.CENTER);
      
      listsPanel = new JPanel();
      listsPanel.add(analysisListScrollPane);
      listsPanel.add(selectedAnalysisListScrollPane);
      
      listsPanel.setMaximumSize(new Dimension(500, 210));
      listsPanel.setMinimumSize(new Dimension(500, 210));
      
      containerPanel = new JPanel();
      containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
      
      removeAnalysis = new JButton("Remove");
      
      prefered = new JCheckBox("prefered");
      
      containerPanel.add(analysesText);
      containerPanel.add(listsPanel);
      containerPanel.add(prefered);
      containerPanel.add(removeAnalysis);
      
      containerPanel.setMaximumSize(new Dimension(500, 210));
      containerPanel.setMinimumSize(new Dimension(500, 210));
      
      southPanel = new JPanel();
      
      save = new JButton("Save");
      buttonListener = new MyButtonListener();
      save.addActionListener(buttonListener);
      save.setPreferredSize(new Dimension(100,  30));
      southPanel.add(save);
      
      removeAnalysis.addActionListener(buttonListener);
      
      
      add(enteringDataPanel, BorderLayout.NORTH);
      add(containerPanel, BorderLayout.CENTER);
      add(southPanel, BorderLayout.SOUTH);
      
      
      setResizable(false);
      setSize(700, 500);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
   }
   
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         employeeAdapter = new EmployeeFileAdapter();
         employeeList = employeeAdapter.getEmployeeList();
         if(e.getSource() == save)
         {
            int i;
            int n = employeeList.size();
            if(name.getText().equals("") || initials.getText().equals(""))
               JOptionPane.showMessageDialog(getParent(), "Must add the initials and the name", 
                     "Ups...", JOptionPane.INFORMATION_MESSAGE);
            else {
               for(i = 0; i < n; ++i)
               {
                  if(employeeList.get(i).getIntials().equalsIgnoreCase(initials.getText()))
                  {
                     JOptionPane.showMessageDialog(getParent(), "Initials already assigned to employee " + 
                           employeeList.get(i).getName() + ".", "Error", JOptionPane.ERROR_MESSAGE);
                     break;
                  }
               }
               if(i == n)
               {
                  employeeList.add(new Employee(name.getText(), initials.getText()));
                  for(int j = 0; j < listModel2.getSize(); ++j)
                  {
                     employeeList.get(n).addAnalysis(new Analysis(listModel2.get(j)));
                  }
                  employeeAdapter.saveEmployeeList(employeeList);
                  employeeList = employeeAdapter.getEmployeeList();
                  for(int j = 0; j < employeeList.size(); ++j)
                  {
                     System.out.println(employeeList.get(j).getIntials());
                  }
                  dispose();
//                  setVisible(false);
               }
            }
            
         }
         else if(e.getSource() == removeAnalysis)
         {
            if (!selectedAnalyses.isSelectionEmpty())
               listModel2.removeElementAt(selectedAnalyses.getSelectedIndex());
         }
      }
   }
   
   
   
   private class MyListSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         JList list = (JList) e.getSource();
         if(list.equals(analysisList))
         {
            if (!analysisList.isSelectionEmpty())
            {
               int index = analysisList.getSelectedIndex();
               int i;
               for(i = 0; i < listModel2.getSize(); ++i)
               {
                  if(listModel2.getElementAt(i).equals(analyses.getAnalysis(index).toString()))
                     break;
               }
               if(i == listModel2.getSize())
                  listModel2.addElement(analyses.getAnalysis(index).toString());
            }
         }
         
      }
   }
   
   public static void main(String[] args)
   {
      AddEmployee ae = new AddEmployee();
   }  
}