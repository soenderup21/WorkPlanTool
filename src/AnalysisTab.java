import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AnalysisTab extends JPanel
{  
   
   private String[] colors;
   private AnalysisAdapter adapter;
   private EmployeeFileAdapter emAdapter;
   private JPanel anPanel;
   private EmployeeSelectionListener employeeListener;
   
   private JList allAnalysisArea;
   private DefaultListModel<Analysis> listModel;
   
   private JScrollPane allAnalysisScrollPane;
   private JLabel addAnalysisLabel;
   private JLabel employeeLabel;
   
   private JList allEmployeesField;
   private JScrollPane allEmployeesScrollPane;
   private DefaultListModel<String> listModel1;
   
   private JTextField addAnalysisField;
   private JButton addAnalysis;
   private JButton deleteAnalysis;
   private JPanel leftPanel;
   private JPanel rightPanel;
   
   public AnalysisTab()
   {
      super();
      emAdapter = new EmployeeFileAdapter();
      adapter = new AnalysisAdapter("analysis.bin");
      listModel = new DefaultListModel<Analysis>();
      listModel1 = new DefaultListModel<String>();
      employeeListener = new EmployeeSelectionListener();
      anPanel = new JPanel();
      
       allAnalysisArea = new JList<Analysis>(listModel);
      AnalysisList al = adapter.getAllAnalysis();
      for (int i = 0; i < al.size(); i++)
      {
         listModel.addElement(al.getAnalysis(i));
      }
      
      allAnalysisArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      allAnalysisArea.addListSelectionListener(employeeListener);
      allAnalysisArea.setVisibleRowCount(15); 
      allAnalysisArea.setPreferredSize(new Dimension(250, 1500));
      
      allAnalysisScrollPane = new JScrollPane(allAnalysisArea);
      allAnalysisScrollPane.setPreferredSize(new Dimension(280, 280));
      allAnalysisScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      addAnalysisLabel = new JLabel("Add analysis:");
      addAnalysisLabel.setPreferredSize(new Dimension(90, 40));
      addAnalysisField = new JTextField(15);
      addAnalysisField.setEditable(true);
       
      addAnalysis = new JButton("Add");
      addAnalysis.addActionListener(new TypeListener());
      deleteAnalysis = new JButton("Delete");
      deleteAnalysis.addActionListener(new TypeListener());
      
      employeeLabel = new JLabel("People who can perform selected analysis:");
      employeeLabel.setPreferredSize(new Dimension(150, 40));
      employeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
      allEmployeesField = new JList<String>(listModel1);
      allEmployeesField.setVisibleRowCount(15); 
      allEmployeesField.setMinimumSize(new Dimension(400, 1500));
      allEmployeesScrollPane = new JScrollPane(allEmployeesField);
      allEmployeesScrollPane.setPreferredSize(new Dimension(280, 280));
      allEmployeesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

      leftPanel = new JPanel();
      leftPanel.setPreferredSize(new Dimension(330, 500));
      rightPanel = new JPanel();
      rightPanel.setPreferredSize(new Dimension(390, 500));
      
      leftPanel.add(addAnalysisLabel);
      leftPanel.add(addAnalysisField);
      leftPanel.add(addAnalysis);
      leftPanel.add(allAnalysisScrollPane);
      leftPanel.add(deleteAnalysis);
     
      rightPanel.add(employeeLabel);
      rightPanel.add(allEmployeesScrollPane);
      
      anPanel.add(leftPanel);
      anPanel.add(rightPanel);
      
      //centering panels 
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      
      c.fill = GridBagConstraints.CENTER;
      gridbag.setConstraints(this, c);
      setLayout(gridbag);
      add(anPanel);
      setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, new Color(81, 112, 160)));
   }
   
   private class TypeListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == addAnalysis)
         {
            if(addAnalysisField.getText().equals("")) 
            {
               String st = "Write the name of the analysis!";
               JOptionPane.showMessageDialog(null, st);
            }
            else 
            {
            String analysisName = addAnalysisField.getText();
            Analysis newAnalysis = new Analysis(analysisName);
            AnalysisList al=adapter.getAllAnalysis();
           
            al.addAnalysis(newAnalysis);
            adapter.saveAnalyses(al);
            
            AnalysisList a=adapter.getAllAnalysis();
            for(int i=0;i<a.size();i++)
               System.out.println(a.getAnalysis(i).getName());
            
            updateAnalysisList();
            }
            
            listModel1.clear();
            addAnalysisField.setText("");
         }
         else if(e.getSource() == deleteAnalysis) {
            
            
               int choice = JOptionPane.showConfirmDialog(null,
                     "Confirm to delete the analysis?", "delete",
                     JOptionPane.YES_NO_OPTION);

               if (choice == JOptionPane.YES_OPTION)
               {
                  if(allAnalysisArea.isSelectionEmpty() == true) {
                     String st = "Select an analysis first!";
                     JOptionPane.showMessageDialog(null, st);
                  }else
                  {
                  
                     AnalysisList al=adapter.getAllAnalysis();
                     for(int i=0;i<al.size();i++)
                        if(allAnalysisArea.getSelectedIndex()==i) al.removeAnalysis(i);
                     
                     adapter.saveAnalyses(al);
                        updateAnalysisList();
                  }
               }
               listModel1.clear();
         }
         
      }
   }
   

   public void updateAnalysisList()
   {
      listModel.clear();
      AnalysisList al = adapter.getAllAnalysis();
      for(int i = 0; i<al.size(); i++)
      {
         listModel.addElement(al.getAnalysis(i));
      }
   }
   
   public void getAllEmployeesForSelectedAnalysis() 
   {
      EmployeeList el = emAdapter.getEmployeeList();
      Analysis ann = (Analysis)(allAnalysisArea.getSelectedValue()); 
      for (int i = 0; i < el.size(); i++)
      {
         Employee e=el.get(i);
         ArrayList analOfEmployee = e.getAllAnalyses();
            for(int j = 0; j < analOfEmployee.size(); j++) {
               if(analOfEmployee.contains(ann)) {
                  listModel1.addElement((String)e.getName());
               }
            }
      }
   }
   
//   private class MyListSelectionListener implements ListSelectionListener 
//   {
//      public void valueChanged(ListSelectionEvent e) 
//      {
//         if (e.getSource() == allAnalysisArea)
//         {
//            if (allAnalysisArea.getSelectedValue() instanceof AnalysisList)
//            {
//             Analysis temp = (Analysis)allAnalysisArea.getSelectedValue();
//             
//            }
//         }
//      }
//   }
   
      
   private class EmployeeSelectionListener implements ListSelectionListener{
   public void valueChanged(ListSelectionEvent e) 
   {
      if(e.getSource() == allAnalysisArea)
      {
         listModel1.clear();
               EmployeeList emplList = emAdapter.getEmployeeList();
               if(!emplList.equals(null))
               {
                  for(int i = 0; i < emplList.size(); i++)
                  {
                     Employee singleEmployee = emplList.get(i);
                     ArrayList<Analysis> analOfEmployee = singleEmployee.getAllAnalyses();
                        for(int j = 0; j < analOfEmployee.size(); j++)
                        {
                           Analysis temp = analOfEmployee.get(j);
                           if(temp.equals((Analysis)allAnalysisArea.getSelectedValue()))
                              listModel1.addElement(singleEmployee.getName());
                        }
                        if(listModel1.getSize()==0) listModel1.addElement("No employee is trained");
                  }
                }
      
   }
   }
   }
   
   public static void main(String[] args)
   {
      AnalysisAdapter adapter= new AnalysisAdapter("analysis.bin");
      AnalysisList a=adapter.getAllAnalysis();
      for(int i=0;i<a.size();i++)
         System.out.println(a.getAnalysis(i).getName());
   }

}
