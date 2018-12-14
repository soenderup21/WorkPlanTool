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
   
   private EmployeeList list;
   private Employee employee;
   private ArrayList<Analysis> eanalysis;
   private ArrayList<JCheckBox> cbanalysis;
   
   public AnalysisPanel()
   {
      super();
      adapter=new EmployeeFileAdapter();
      list=(EmployeeList)adapter.getEmployeeList();
      cbanalysis=new ArrayList<JCheckBox>();
      
      setEmployee(0);
      setCheckBoxes();
      
      setMinimumSize(new Dimension(100, 100));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   }
   
   
   public void setEmployee(int i)
   {
      employee=list.get(i);
      eanalysis=employee.getAllAnalyses();
      cbanalysis=new ArrayList<JCheckBox>();
   }
   
   
   public void setCheckBoxes()
   {
      for(int i=0;i<eanalysis.size();i++)
      {
         cbanalysis.add(new JCheckBox(eanalysis.get(i).getName()));
         add(cbanalysis.get(i));
      }
   }
   
   public String[] getAllPickedString()
   {
      ArrayList<String> s= new ArrayList<String>();
      for(int i=0;i<cbanalysis.size();i++)
         if(cbanalysis.get(i).isSelected()) s.add(cbanalysis.get(i).getText());
      String[] fin=new String[s.size()];
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