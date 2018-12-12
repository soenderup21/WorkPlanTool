import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AnalysisTab extends JPanel
{
	JLabel nameLabel, analysisLabel, titleLabel;
	JTextField nameField;
	JList list;
	JPanel containerPanel;
	
	public AnalysisTab() {
		
		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

		
		nameLabel = new JLabel("Name:");
		analysisLabel = new JLabel("Analysis:");
		titleLabel = new JLabel("Edit Analysis:");
		
		nameField = new JTextField(10);
		
		
		AnalysisAdapter AA = new AnalysisAdapter("Analysis");
		AnalysisList AL = AA.getAllAnalysis();
		Analysis[] AnalysisArray = new Analysis[AL.size()];
		for (int i = 0; i < AnalysisArray.length; i++)
		{
			AnalysisArray[i] = AL.getAnalysis(i);
		}
	
		
		list = new JList(AnalysisArray);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		containerPanel = new JPanel();
		
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e)
			{
				AA.saveAnalysis(AL);
				int i;
				try
				{
					i = list.getSelectedIndex();		
				}
				catch (Exception e2)
				{
					System.out.println("value not selected: " + e2);
					return;
				}
				if (i != -1)
				{
					nameField.setText(AnalysisArray[i].getName());
				}
				
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				int i;
				try
				{
					i = list.getSelectedIndex();		
				}
				catch (Exception e2)
				{
					System.out.println("value not selected: " + e2);
				}
				
			}
		});
	
	}
}

