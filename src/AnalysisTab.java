import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AnalysisTab extends JPanel
{	
	
   private String[] colors;
   private AnalysisAdapter adapter;
   private JPanel anPanel;
   private MyListSelectionListener listListener;
   
   private JList allAnalysisArea;
   private DefaultListModel<String> listModel;
   
   private JScrollPane allAnalysisScrollPane;
   private JLabel addAnalysisLabel;
   private JLabel deleteAnalysisLabel;
   private JLabel employeeLabel;
   
   private JList allEmployeesField;
   private JScrollPane allEmployeesScrollPane;
   
   
   private JTextField addAnalysisField;
   private JTextField deleteAnalysisField;
   private JButton addAnalysis;
   private JButton deleteAnalysis;
   private JPanel leftPanel;
   private JPanel rightPanel;
   
   public AnalysisTab()
   {
	   super();
	   adapter = new AnalysisAdapter("analysis.bin");
	   listModel = new DefaultListModel<String>();
	   listListener = new MyListSelectionListener();
	   
	   anPanel = new JPanel();
	   
	    allAnalysisArea = new JList<String>(listModel);
		AnalysisList al = adapter.getAllAnalysis();
		for (int i = 0; i < al.size(); i++)
		{
			listModel.addElement(al.getAnalysis(i).toString());
		}
		
	   allAnalysisArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   allAnalysisArea.addListSelectionListener(listListener);
	   allAnalysisArea.setVisibleRowCount(15); 
	   allAnalysisArea.setPreferredSize(new Dimension(250, 300));
	   
	   allAnalysisScrollPane = new JScrollPane(allAnalysisArea);
	   allAnalysisScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	   
	   addAnalysisLabel = new JLabel("Add analysis:");
	   addAnalysisLabel.setPreferredSize(new Dimension(90, 40));
	   addAnalysisField = new JTextField(15);
	   addAnalysisField.setEditable(true);
	   
	   deleteAnalysisLabel = new JLabel("Delete analysis:");
	   deleteAnalysisLabel.setPreferredSize(new Dimension(90, 40));
	   deleteAnalysisField = new JTextField(15);
	   deleteAnalysisField.setEditable(true);
	   
	   addAnalysis = new JButton("Add");
	   addAnalysis.addActionListener(new TypeListener());
	   deleteAnalysis = new JButton("Delete");
	   deleteAnalysis.addActionListener(new TypeListener());
	   
	   //FIX THIS 
	   employeeLabel = new JLabel("Employees which can do that analysis:");
	   employeeLabel.setPreferredSize(new Dimension(90, 40));
	   allEmployeesField  = new JList();
	   allEmployeesScrollPane = new JScrollPane();
	   
	   
	   
	   leftPanel = new JPanel();
	   leftPanel.setPreferredSize(new Dimension(350, 500));
	   rightPanel = new JPanel();
	   rightPanel.setPreferredSize(new Dimension(350, 500));
	   
	   leftPanel.add(addAnalysisLabel);
	   leftPanel.add(addAnalysisField);
	   leftPanel.add(addAnalysis);
	   leftPanel.add(deleteAnalysisLabel);
	   leftPanel.add(deleteAnalysisField);
	   leftPanel.add(deleteAnalysis);
	   leftPanel.add(allAnalysisScrollPane);
	  
	   rightPanel.add(employeeLabel);
	   rightPanel.add(allEmployeesScrollPane);
	   
	   anPanel.add(leftPanel);
	   anPanel.add(rightPanel);
	   
	   add(anPanel);
   }
   
   private class TypeListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   if(e.getSource() == addAnalysis)
		   {
			   String analysisName = addAnalysisField.getText();
			   Analysis newAnalysis = new Analysis(analysisName);
			   adapter.saveAnalysis(newAnalysis);
		   }
		   else if(e.getSource() == deleteAnalysis) {
			   AnalysisList temp = new AnalysisList();
			   String anName = deleteAnalysisField.getText();
			   for(int i = 0; i < temp.size(); i++) {
				   if(anName.equals(temp.getAnalysis(i))) {
					   temp.removeAnalysis(i);
				   }else {
					   
				   }
			   }
		   }
	   }
   }
   private class MyListSelectionListener implements ListSelectionListener 
   {
      public void valueChanged(ListSelectionEvent e) 
      {
         if (e.getSource() == allAnalysisArea)
         {
            if (allAnalysisArea.getSelectedValue() instanceof AnalysisList)
            {
            	AnalysisList temp = (AnalysisList)allAnalysisArea.getSelectedValue();
//                firstNameField.setText(temp.getFirstName());
//                lastNameField.setText(temp.getLastName());
            }
         }
      }
   }
}

