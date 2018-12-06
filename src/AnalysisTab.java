import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AnalysisTab extends JPanel
{
	JLabel nameLabel, analysisLabel, titleLabel;
	JTextField nameField;
	JButton saveButton;
	JList list;
	
	public AnalysisTab() {
		nameLabel = new JLabel("Name:");
		analysisLabel = new JLabel("Analysis:");
		titleLabel = new JLabel("Edit Analysis:");
		
		nameField = new JTextField(10);
		
		saveButton = new JButton("Save");
		
		list = new JList(/*inseert data here plz!!!*/ );
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		

		
	}
	
	
}
