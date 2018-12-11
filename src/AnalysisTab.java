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
		
		list = new JList(/*inseert data here plz!!!*/ );
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		containerPanel = new JPanel();
		
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			public void chnagedUpdate(DocumentEvent e) {
				//save analysis here text:
				
				
			}
		});
		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				//Do fancy label stuff
				
			}
		});
	
	}
}

