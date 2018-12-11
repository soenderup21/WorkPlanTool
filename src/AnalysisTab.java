import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AnalysisTab extends JPanel
{	
   private String[] colors;
   private AnalysisAdapter adapter;
   private JPanel anPanel;
   
   private JTextArea allAnalysisArea;
   private JScrollPane allAnalysisScrollPane;
   private JLabel addAnalysisLabel;
   private JLabel deleteAnalysisLabel;
   private JTextField addAnalysisField;
   private JTextField deleteAnalysisField;
   private JButton updateAnalysis;
   private JButton addAnalysis;
   private JButton deleteAnalysis;
   private JComboBox<String> colorsComboBox;
   private JPanel leftPanel;
   private JPanel rightPanel;
   
   public AnalysisTab()
   {
	   super();
	   adapter = new AnalysisAdapter("analysis.bin");
	   colors = new String[] {"red", "pink", "green", "yellow"};
	   
	   anPanel = new JPanel();
	   allAnalysisArea = new JTextArea(350, 350);
	   allAnalysisArea.setEditable(false);
	   
	   allAnalysisScrollPane = new JScrollPane(allAnalysisArea);
	   allAnalysisScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	   
	   addAnalysisLabel = new JLabel("Add analysis:");
	   addAnalysisLabel.setPreferredSize(new Dimension(70, 30));
	   addAnalysisField = new JTextField(15);
	   addAnalysisField.setEditable(true);
	   
	   deleteAnalysisLabel = new JLabel("Delete analysis:");
	   deleteAnalysisLabel.setPreferredSize(new Dimension(70, 30));
	   deleteAnalysisField = new JTextField(15);
	   deleteAnalysisField.setEditable(true);
	   
	   addAnalysis = new JButton("Add");
	   addAnalysis.addActionListener(new TypeListener());
	   deleteAnalysis = new JButton("Delete");
	   deleteAnalysis.addActionListener(new TypeListener());
	   updateAnalysis = new JButton("Update");
	   updateAnalysis.addActionListener(new TypeListener());
	   colorsComboBox = new JComboBox<String>(colors);
	   colorsComboBox.addActionListener(new TypeListener());
	   colorsComboBox.setPreferredSize(new Dimension(200, 30));
	   
	   leftPanel = new JPanel();
	   leftPanel.setPreferredSize(new Dimension(350, 350));
	   rightPanel = new JPanel();
	   rightPanel.setPreferredSize(new Dimension(350, 350));
	   
	   leftPanel.add(addAnalysisLabel);
	   leftPanel.add(addAnalysisField);
	   leftPanel.add(addAnalysis);
	   leftPanel.add(deleteAnalysisLabel);
	   leftPanel.add(deleteAnalysisField);
	   leftPanel.add(deleteAnalysis);
	   leftPanel.add(colorsComboBox);
	   leftPanel.add(updateAnalysis);

	   rightPanel.add(allAnalysisScrollPane);
	   anPanel.add(leftPanel);
	   anPanel.add(rightPanel);
   }
   
   private class TypeListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   if(e.getSource() == addAnalysis)
		   {
			   String analysisName = addAnalysisField.getText();
//			   int numColor = colorsComboBox.getSelectedIndex();
//			   String color = colors[numColor];
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
}

