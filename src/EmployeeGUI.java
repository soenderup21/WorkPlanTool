import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EmployeeGUI extends JPanel
{
   private JPanel searchArea;
   private JPanel specifySearch;
   private JPanel leftPart;
   private JPanel mainContentPanel;
   
   private EmployeeFileAdapter adapter;
   
   private JTextField findEmployee;
   private JButton find;
   
   private JLabel searchBy;
   
   private JRadioButton initialsSearch;
   private JRadioButton nameSearch;
   private JRadioButton analysisSearch;
   
   private ButtonGroup searchGroup;
   
   private JButton getAllEmployees;

   private DefaultTableModel dtm;
   private String[] columnNames;
   private JTable employeesTable;
   private JScrollPane employeesScrollPane;
   private ListSelectionModel listSelectionModel;
   
   private JPanel emptyPanel;

   private JPanel employeeDataPanel;
   private JPanel header;
   private JLabel headerText;
   private JPanel analysesDetails;
   private JLabel analysesText;
   private JList<String> analysesList;
   private DefaultListModel<String> listModel;
   private JLabel noteHeader;
   private JPanel notePanel;
   private JTextArea noteText;
   private JScrollPane analysisScrollPane;
   private JScrollPane noteScrollPane;
   
   private JButton removeEmployee;
   private JButton editEmployee;
   private JPanel buttonsPanel;
   
   private JButton addEmployee;
   
   private JButton addEmployeeButton;
   
   private MyButtonListener click;
   
   private MyListCellRenderer renderer;
   
   public EmployeeGUI()
   {
      click = new MyButtonListener();
      
      adapter = new EmployeeFileAdapter();
      
      searchArea = new JPanel();
      searchArea.setPreferredSize(new Dimension(300, 150));
      
      specifySearch = new JPanel();
      leftPart = new JPanel();
      leftPart.setPreferredSize(new Dimension(500, 700));
      
      find = new JButton("Find");
      find.addActionListener(click);
      findEmployee = new JTextField(15);
      
      searchArea.add(findEmployee);
      searchArea.add(find);
      
      searchBy = new JLabel("Search by");
      
      initialsSearch = new JRadioButton("Initials", true);
      nameSearch = new JRadioButton("Name");
      analysisSearch = new JRadioButton("Analysis");
      
      searchGroup = new ButtonGroup();
      searchGroup.add(initialsSearch);
      searchGroup.add(nameSearch);
      searchGroup.add(analysisSearch);
      
      specifySearch.setPreferredSize(new Dimension(100, 150));
      specifySearch.add(searchBy);
      specifySearch.add(initialsSearch);
      specifySearch.add(nameSearch);
      specifySearch.add(analysisSearch);
      
      getAllEmployees = new JButton("Get all employees");
      getAllEmployees.addActionListener(click);
      
      searchArea.add(getAllEmployees);
      
      mainContentPanel = new JPanel();
      
      columnNames = new String[2];
      columnNames[0] = "Initials";
      columnNames[1] = "Name";
      
      dtm = new DefaultTableModel(columnNames, 0);
      employeesTable = new JTable(dtm);
      employeesTable.getTableHeader().setReorderingAllowed(false);
      employeesTable.getTableHeader().setResizingAllowed(false);
      employeesTable.setRowHeight(20);
      employeesTable.setPreferredScrollableViewportSize(new Dimension(400, employeesTable.getRowHeight()*20));
      
      listSelectionModel = employeesTable.getSelectionModel();
      listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listSelectionModel.addListSelectionListener(new TableSelectionListener());
      employeesTable.setSelectionModel(listSelectionModel);
      
      
      
      
      EmployeeList employees = adapter.getEmployeeList();
      Object[][] data = new Object[employees.size()][2];
      for(int i = 0; i < employees.size(); ++i)
      {
         data[i][0] = employees.get(i).getIntials();
         data[i][1] = employees.get(i).getName();
      }
      dtm = new DefaultTableModel(data, columnNames);
      employeesTable.setModel(dtm);
      
      employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
      employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
      
      
      
      employeesScrollPane = new JScrollPane(employeesTable);
      employeesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      employeeDataPanel = new JPanel();
      employeeDataPanel.setPreferredSize(new Dimension(350, 470));
      employeeDataPanel.setBackground(new Color(255, 255, 255));
      employeeDataPanel.setLayout(new BoxLayout(employeeDataPanel, BoxLayout.Y_AXIS));
      
      header = new JPanel();
      headerText = new JLabel("Select employee");
      
      header.setPreferredSize(new Dimension(350, 30));
      header.add(headerText);
      header.setBackground(new Color(255, 255, 255));
      
      analysesText = new JLabel("");
      analysesText.setPreferredSize(new Dimension(350, 20));
      
      listModel = new DefaultListModel<String>();
      analysesList = new JList<String>(listModel);
      analysesList.setBackground(new Color(255, 255, 255));
      analysisScrollPane = new JScrollPane(analysesList);
      analysisScrollPane.setPreferredSize(new Dimension(350, 180));
      analysesDetails = new JPanel();
      analysesDetails.setPreferredSize(new Dimension(350, 220));
      analysesDetails.setBackground(new Color(255, 255, 255));
      analysesDetails.add(analysesText);
      analysesDetails.add(analysisScrollPane);

      analysisScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      renderer = new MyListCellRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
      analysesList.setCellRenderer(renderer);
      
      noteHeader = new JLabel();
      noteHeader.setPreferredSize(new Dimension(350, 25));
      noteText = new JTextArea();
//      noteText.setMinimumSize(new Dimension(340, 60));
//      noteText.setMaximumSize(new Dimension(340, 60));
      noteText.setLineWrap(true);
      notePanel = new JPanel();
      
      notePanel.setBackground(new Color(255, 255, 255));
      notePanel.setPreferredSize(new Dimension(350, 26));
      noteScrollPane = new JScrollPane(noteText);
//      noteScrollPane.setMaximumSize(new Dimension(350, 90));
      noteScrollPane.setPreferredSize(new Dimension(350, 150));
      noteScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      notePanel.add(noteHeader);
      //notePanel.add(noteScrollPane);
      
      removeEmployee = new JButton("Delete employee");
      removeEmployee.addActionListener(click);
      editEmployee = new JButton("Edit employee");
      editEmployee.addActionListener(click);
      removeEmployee.setEnabled(false);
      editEmployee.setEnabled(false);
      
      buttonsPanel = new JPanel();
      buttonsPanel.setBackground(new Color(255, 255, 255));
      buttonsPanel.setPreferredSize(new Dimension(350, 35));
      buttonsPanel.add(removeEmployee);
      buttonsPanel.add(editEmployee);
      
      addEmployee = new JButton("Add new employee");
      addEmployee.addActionListener(click);
      
      employeeDataPanel.add(header);
      employeeDataPanel.add(analysesDetails);
      employeeDataPanel.add(notePanel);
      employeeDataPanel.add(noteScrollPane);
      employeeDataPanel.add(buttonsPanel);
      
      emptyPanel = new JPanel();
      emptyPanel.setPreferredSize(new Dimension(500, 70));
      
      mainContentPanel.setPreferredSize(new Dimension(500, 900));
      mainContentPanel.add(emptyPanel);
      mainContentPanel.add(employeeDataPanel);
      mainContentPanel.add(addEmployee);
      
      leftPart.add(searchArea);
      leftPart.add(specifySearch);
      leftPart.add(employeesScrollPane);
      
//      addNewEmployee = new JPanel();
//      addNewEmployee.setPreferredSize(new Dimension(400, 200));
//      addNewEmployee.setBorder(new TitledBorder("Add employee"));
//      
//      initialsText = new JLabel("Initials: ");
//      initials = new JTextField();
//      initials.setPreferredSize(new Dimension(200, 30));
//      
//      nameText = new JLabel("Name: ");
//      name = new JTextField();
//      name.setPreferredSize(new Dimension(200, 30));
      
      addEmployeeButton = new JButton("Add emoployee");
      addEmployeeButton.addActionListener(click);
     
      setLayout(new BorderLayout());
      
      add(leftPart, BorderLayout.WEST);
      add(mainContentPanel, BorderLayout.EAST);
      setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, new Color(81, 112, 160)));
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         if(e.getSource() == find)
         {
             EmployeeList employees = adapter.getEmployeeList();
             if(initialsSearch.isSelected())
             {
                Employee found = employees.getEmployee(findEmployee.getText());
                if(found == null)
                   JOptionPane.showMessageDialog(null, "Seems that we can\'t find any such employee", "Ups...", JOptionPane.ERROR_MESSAGE);
                else {                  
                   Object[][] data = new Object[1][2];
                   data[0][0] = found.getIntials();
                   data[0][1] = found.getName();
                   dtm = new DefaultTableModel(data, columnNames);
                   employeesTable.setModel(dtm);
                   employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                   employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
                   
                }
             }
             else if(nameSearch.isSelected())
             {
                ArrayList<Employee> sameNames = new ArrayList<Employee>();
                for(int i = 0; i < employees.size(); ++i)
                {
                   if(employees.get(i).getName().equalsIgnoreCase(findEmployee.getText()))
                      sameNames.add(employees.get(i));
                }
                if(sameNames.size() == 0)
                   JOptionPane.showMessageDialog(null, "Seems that we can\'t find any such employee", "Ups...", JOptionPane.ERROR_MESSAGE);
                else {
                
                Object[][] data = new Object[sameNames.size()][2];
                for(int i = 0; i < sameNames.size(); ++i)
                {
                   data[i][0] = sameNames.get(i).getIntials();
                   data[i][1] = sameNames.get(i).getName();
                }
                dtm = new DefaultTableModel(data, columnNames);
                employeesTable.setModel(dtm);
                employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);

                }
             }
             else if(analysisSearch.isSelected())
             {
//                ArrayList<Employee> employeeArrayList = employees.getAllEmployees();
                ArrayList<Analysis> analyses;
                ArrayList<Employee> toDisplay = new ArrayList<Employee>();
                for(int i = 0; i < employees.size(); ++i)
                {
                   analyses = employees.get(i).getAllAnalyses();
                   for(int j = 0; j < analyses.size(); ++j)
                   {
                      if(analyses.get(j).toString().equals(findEmployee.getText()))
                      {
                         toDisplay.add(employees.get(i));
                      }
                   }
                }
                if(toDisplay.size() == 0)
                   JOptionPane.showMessageDialog(null, "Seems that we can\'t find any such employee", "Ups...", JOptionPane.ERROR_MESSAGE);
                else {
                Object[][] data = new Object[toDisplay.size()][4];
                for(int i = 0; i < toDisplay.size(); ++i)
                {
                   data[i][0] = toDisplay.get(i).getIntials();
                   data[i][1] = toDisplay.get(i).getName();
                }
                dtm = new DefaultTableModel(data, columnNames);
                employeesTable.setModel(dtm);
                employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
                }
             }
         }
        
         else if(e.getSource() == getAllEmployees)
         {
            EmployeeList employees = adapter.getEmployeeList();
            Object[][] data = new Object[employees.size()][2];
            for(int i = 0; i < employees.size(); ++i)
            {
               data[i][0] = employees.get(i).getIntials();
               data[i][1] = employees.get(i).getName();
            }
            dtm = new DefaultTableModel(data, columnNames);

            employeesTable.setModel(dtm);
            employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            headerText.setText("Select employee");
            listModel.clear();
            analysesText.setText("");
            noteText.setText("");
            noteHeader.setText("");
         }
         else if(e.getSource() == removeEmployee)
         {
            EmployeeList employees = adapter.getEmployeeList();
            int index = employeesTable.getSelectedRow();
            if(index > 0)
            {
               String initials = (String) dtm.getValueAt(index, 0);
               Employee found = employees.getEmployee(initials);
               int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you " +
            "want to remove " + found.getName() + " from the list?");
               if(confirm == JOptionPane.YES_OPTION)
               {
                  employees.removeEmployee(found);
                  adapter.saveEmployeeList(employees);
                  
               }
               Object[][] data = new Object[employees.size()][2];
               for(int i = 0; i < employees.size(); ++i)
               {
                  data[i][0] = employees.get(i).getIntials();
                  data[i][1] = employees.get(i).getName();
               }
               dtm = new DefaultTableModel(data, columnNames);
               employeesTable.setModel(dtm);
               
               employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
               employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
               headerText.setText("Select employee");
               listModel.clear();
               analysesText.setText("");
               noteText.setText("");
               noteHeader.setText("");
            }
            
         }
         else if(e.getSource() == addEmployee)
         {
            AddEmployee ae = new AddEmployee();
            ae.addWindowListener(new WindowAdapter()
            {
               public void windowClosed(WindowEvent e)
               {
                     EmployeeList employees = adapter.getEmployeeList();
                     
                     Object[][] data = new Object[employees.size()][2];
                     for(int i = 0; i < employees.size(); ++i)
                     {
                        data[i][0] = employees.get(i).getIntials();
                        data[i][1] = employees.get(i).getName();
                     }
                     dtm = new DefaultTableModel(data, columnNames);
                     employeesTable.setModel(dtm);
                     employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                     employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
                     
                     headerText.setText(employees.get(employees.size()-1).getName() + "(" +
                           employees.get(employees.size()-1).getIntials() + ")");
                  
                     listModel.clear();
                     for(int i = 0; i < employees.get(employees.size()-1).getNumberOfAnalyses(); ++i)
                     {
                           listModel.addElement(employees.get(employees.size()-1).getAnalysis(i).getAnalysis().toString());
                     }
                     if(employees.get(employees.size()-1).getNote() != null)
                     {
                        noteText.setText(employees.get(employees.size()-1).getNote().getNote());
                        noteHeader.setText(employees.get(employees.size()-1).getNote().getName());
                     }
                     else 
                     {
                        noteHeader.setText("No notes");
                        noteText.setText("");
                     }
                        
               }
            });
         }
         else if(e.getSource() == editEmployee)
         {
            EmployeeList employees = adapter.getEmployeeList();
            int index = employeesTable.getSelectedRow();
            String initials = (String) dtm.getValueAt(index, 0);
            Employee found = employees.getEmployee(initials);
            EditEmployee ee = new EditEmployee(employees.getIndexOfEmployee(found));
            ee.addWindowListener(new WindowAdapter()
            {
               public void windowClosed(WindowEvent e)
               {
                     EmployeeList employees = adapter.getEmployeeList();
                     
                     Object[][] data = new Object[employees.size()][2];
                     for(int i = 0; i < employees.size(); ++i)
                     {
                        data[i][0] = employees.get(i).getIntials();
                        data[i][1] = employees.get(i).getName();
                     }
                     dtm = new DefaultTableModel(data, columnNames);
                     employeesTable.setModel(dtm);
                     employeesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                     employeesTable.getColumnModel().getColumn(1).setPreferredWidth(300);
                     
                     headerText.setText(employees.get(index).getName() + "(" +
                           employees.get(index).getIntials() + ")");
                     listModel.clear();
                     for(int i = 0; i < employees.get(index).getNumberOfAnalyses(); ++i)
                     {
                        if(!employees.get(index).getAnalysis(i).isTrained())
                        {
                           String str = employees.get(index).getAnalysis(i).getAnalysis().toString();
                           listModel.addElement(str);
                        }
                        else if(employees.get(index).getAnalysis(i).isPreference())
                        {
                           String str = employees.get(index).getAnalysis(i).getAnalysis().toString();
                           listModel.addElement(str);
                        }
                        else 
                           listModel.addElement(employees.get(index).getAnalysis(i).getAnalysis().toString());
                     }
                     if(employees.get(index).getNote() != null)
                     {
                        noteText.setText(employees.get(index).getNote().getNote());
                        noteHeader.setText(employees.get(index).getNote().getName());
                     }
                     else 
                     {
                        noteHeader.setText("No notes");
                        noteText.setText("");
                     }
               }
            });
         }
      }
   }
   
   private class TableSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         analysesText.setText("Analyses:");
         EmployeeList employees = adapter.getEmployeeList();
         int rowIndex = employeesTable.getSelectedRow();
         if(rowIndex >= 0) {
            removeEmployee.setEnabled(true);
            editEmployee.setEnabled(true);
            String initials = (String) dtm.getValueAt(rowIndex, 0);
            Employee found = employees.getEmployee(initials);
            headerText.setText(found.getName() + "(" +
                  found.getIntials() + ")");
            listModel.clear();
            ArrayList<AnalysisDetails> analyses = found.getAllAnalysesDetails();
            for(int i = 0; i < analyses.size(); ++i)
            {
               if(!analyses.get(i).isTrained())
               {
                  listModel.addElement(analyses.get(i).getAnalysis().toString());
               }
               else if(analyses.get(i).isPreference())
               {
                  listModel.addElement(analyses.get(i).getAnalysis().toString());
               }
               else 
                  listModel.addElement(analyses.get(i).getAnalysis().toString());
            }
            if(found.getNote() != null)
            {
               noteText.setText(found.getNote().getNote());
               noteHeader.setText(found.getNote().getName());
            }
               
            else {
               noteText.setText("");
               noteHeader.setText("No notes");
            }
                         
         }
         else {
            removeEmployee.setEnabled(false);
            editEmployee.setEnabled(false);
         }
      }
   }
   private class MyListCellRenderer extends DefaultListCellRenderer
   {
      public Component getListCellRendererComponent(JList<?> list,
            Object value,
            int ind,
            boolean isSelected,
            boolean cellHasFocus)
      {
         super.getListCellRendererComponent( list, value, ind,
               isSelected, cellHasFocus );
         EmployeeList employeeList = adapter.getEmployeeList();
         
        // String analysisName = (String) value;
         int k = employeesTable.getSelectedRow();
         if(k >= 0)
         {
            String initials = (String) dtm.getValueAt(k, 0);
            Employee found = employeeList.getEmployee(initials);
            if(!found.getAnalysis(ind).isTrained())
            {
               setBackground(new Color(255, 63, 0));
               setForeground(new Color(255, 255, 255));
            }
            if(found.getAnalysis(ind).isPreference())
            {
               setBackground(new Color(84, 255, 0));
            }
         }
         return this;
      }
   }

}