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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditEmployee extends JFrame
{
   private int index;
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
   private MyChangeListener changeListener;
   
   private AnalysisAdapter analysisAdapter;
   private AnalysisList analyses;
   private EmployeeFileAdapter employeeAdapter;
   private EmployeeList employeeList;
   
   private JPanel listsPanel;
   
   private JPanel southPanel;
   private JButton save;
   private MyButtonListener buttonListener;
   
   
   public EditEmployee(int index)
   {
      super("Edit employee");
      
      this.index = index;
      employeeAdapter = new EmployeeFileAdapter();
      employeeList = employeeAdapter.getEmployeeList();
      setLayout(new BorderLayout());
      
      enteringDataPanel = new JPanel();
      enteringDataPanel.setLayout(new BoxLayout(enteringDataPanel, BoxLayout.Y_AXIS));
      
      initialsPanel = new JPanel();
      initialsText = new JLabel("Initials: ");
      initials = new JTextField(employeeList.get(index).getIntials());
      initials.setPreferredSize(new Dimension(200, 25));
      
      initialsPanel.add(initialsText);
      initialsPanel.add(initials);
//      initialsPanel.setMaximumSize(new Dimension(350, 30));
//      initialsPanel.setMinimumSize(new Dimension(350, 30));
      
      namePanel = new JPanel();
      nameText = new JLabel("Name:   ");
      name = new JTextField(employeeList.get(index).getName());
      name.setPreferredSize(new Dimension(200, 25));
      
      namePanel.add(nameText);
      namePanel.add(name);
//      namePanel.setMaximumSize(new Dimension(550, 30));
//      namePanel.setMinimumSize(new Dimension(350, 30));
      
      enteringDataPanel.setMaximumSize(new Dimension(600, 70));
      enteringDataPanel.setMinimumSize(new Dimension(600, 70));
      
      enteringDataPanel.add(initialsPanel);
      enteringDataPanel.add(namePanel);
      
      analysesText = new JLabel("Edit analyses: ");
      
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
      
      
      
      for(int i = 0; i < employeeList.get(index).getNumberOfAnalyses(); ++i)
      {
         listModel2.addElement(employeeList.get(index).getAnalysis(i).getAnalysis().toString());
      }
      
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
      
      changeListener = new MyChangeListener();
      
      prefered = new JCheckBox("prefered");
      prefered.addChangeListener(changeListener);
      
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
         if(e.getSource() == save)
         {
            employeeList = employeeAdapter.getEmployeeList();
            if(name.getText().equals("") || initials.getText().equals(""))
               JOptionPane.showMessageDialog(getParent(), "Must add the initials and the name", 
                     "Ups...", JOptionPane.INFORMATION_MESSAGE);
            else {
               int i;
               for(i = 0; i < employeeList.size(); ++i)
               {
                  if(employeeList.get(i).getIntials().equalsIgnoreCase(initials.getText()) &&
                        !(employeeList.get(i).equals(employeeList.get(index))))
                  {
                     JOptionPane.showMessageDialog(getParent(), "Initials already assigned to employee " + 
                           employeeList.get(i).getName() + ".", "Error", JOptionPane.ERROR_MESSAGE);
                     break;
                  }
               }
            
               if(i == employeeList.size())
               {
                  employeeList.get(index).setInitials(initials.getText());
                  employeeList.get(index).setName(name.getText());
                  
                  employeeList.get(index).sortAnalysisByPreference();
                  employeeAdapter.saveEmployeeList(employeeList);
//                  employeeList = employeeAdapter.getEmployeeList();
//                  for(int j = 0; j < employeeList.size(); ++j)
//                  {
//                     System.out.println(employeeList.get(j).getIntials());
//                  }
                  for(int j = 0; j < employeeList.get(index).getNumberOfAnalyses(); ++j)
                  {
                     System.out.println(employeeList.get(index).getAnalysis(j).getAnalysis());
                  }
                  dispose();
//                  setVisible(false);
               }
            }
            
         }
         else if(e.getSource() == removeAnalysis)
         {
            if (!selectedAnalyses.isSelectionEmpty())
            {               
               employeeList = employeeAdapter.getEmployeeList();
               employeeList.get(index).removeAnalysis(selectedAnalyses.getSelectedIndex());
               listModel2.removeElementAt(selectedAnalyses.getSelectedIndex());
               employeeAdapter.saveEmployeeList(employeeList);
            }
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
            employeeList = employeeAdapter.getEmployeeList();
            if (!analysisList.isSelectionEmpty())
            {
               int indexA = analysisList.getSelectedIndex();
               int i;
               for(i = 0; i < listModel2.getSize(); ++i)
               {
                  if(listModel2.getElementAt(i).equals(analyses.getAnalysis(indexA).toString()))
                     break;
               }
               if(i == listModel2.getSize())
               {
                  employeeList.get(index).addAnalysis(new Analysis(listModel1.getElementAt(indexA).toString()));
                  employeeAdapter.saveEmployeeList(employeeList);
                  listModel2.addElement(analyses.getAnalysis(indexA).toString());
               }
               
               //ADAUGA SI IN LISTA
            }
         }
         if(list.equals(selectedAnalyses))
         {
            if (!selectedAnalyses.isSelectionEmpty())
            {
               employeeList = employeeAdapter.getEmployeeList();
               if(employeeList.get(index).getAnalysis(selectedAnalyses.getSelectedIndex()).isPreference())
               {
                  prefered.setSelected(true);
//                  employeeAdapter.saveEmployeeList(employeeList);
               }
               else
                  prefered.setSelected(false);
//                  employeeAdapter.saveEmployeeList(employeeList);
            }
         }
         
      }
   }
   
   private class MyChangeListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         if(!selectedAnalyses.isSelectionEmpty())
         {
            employeeList = employeeAdapter.getEmployeeList();
            if(prefered.isSelected())
            {
               employeeList.get(index).getAnalysis(selectedAnalyses.getSelectedIndex()).setPreference(true);
               employeeAdapter.saveEmployeeList(employeeList);
            }
            else {
               employeeList.get(index).getAnalysis(selectedAnalyses.getSelectedIndex()).setPreference(false);
               employeeAdapter.saveEmployeeList(employeeList);
            }
         }
      }
   }
}
