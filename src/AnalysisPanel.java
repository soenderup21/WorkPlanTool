import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnalysisPanel extends JPanel
{
   private EmployeeFileAdapter adapter;
   private AnalysisAdapter aAdapter;
   
   private EmployeeList list;
   private Employee employee;
   private AnalysisList allAnalysis;
   private ArrayList<Analysis> eanalysis;
   private ArrayList<JCheckBox> cbanalysis;
   
   public AnalysisPanel()
   {
      super();
      
      aAdapter=new AnalysisAdapter("analysis.bin");
      allAnalysis=aAdapter.getAllAnalysis();
      
      adapter=new EmployeeFileAdapter();
      list=(EmployeeList)adapter.getEmployeeList();
      cbanalysis=new ArrayList<JCheckBox>();
      
      getAllAnalysis();
      setCheckBoxes();
      
      setMinimumSize(new Dimension(300, 300));
      
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   }
   
   
   public void setEmployee(int n)
   {
      employee=list.get(n);
      eanalysis=employee.getAllAnalyses();
      cbanalysis.clear();
      
      for(int i=0;i<eanalysis.size();i++)
         cbanalysis.add(new JCheckBox(eanalysis.get(i).getName()));
      
      setCheckBoxes();
   }
   
   public void getAllAnalysis()
   {
      cbanalysis.clear();
      for(int i=0;i<allAnalysis.size();i++)
      {
         cbanalysis.add(new JCheckBox(allAnalysis.getAnalysis(i).getName()));
      }
   }
   
   public void setCheckBoxes()
   {
      for(int i=0;i<cbanalysis.size();i++)
         add(cbanalysis.get(i));
   }
   
   public String[] getAllPickedString()
   {
      ArrayList<String> s= new ArrayList<String>();
      for(int i=0;i<cbanalysis.size();i++)
         if(cbanalysis.get(i).isSelected()) s.add(cbanalysis.get(i).getText());
      String[] fin=new String[s.size()];
      
      for(int i=0;i<cbanalysis.size();i++)
         cbanalysis.get(i).setSelected(false);
      
      return s.toArray(fin);
   }
   
   public void clearChoice()
   {
      removeAll();
   }
   
   public void setEnabled(boolean t)
   {
      if(t)
      {
         setFocusable(false);
      }
      else
      {
         setFocusable(true);
      }
   }
   
   
   //testing the panel
   public static void main(String[] args)
   {
      JFrame frame=new JFrame("Kalendar");
      Container c = frame.getContentPane();
      AnalysisPanel p=new AnalysisPanel();
      c.add(p);
      frame.pack();
      frame.setVisible(true);
   }
}