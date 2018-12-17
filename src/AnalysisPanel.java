import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
   
   public AnalysisPanel(int n)
   {
      super();
      
      adapter=new EmployeeFileAdapter();
      list=(EmployeeList)adapter.getEmployeeList();
      cbanalysis=new ArrayList<JCheckBox>();
      
      employee=list.get(n);
      eanalysis=employee.getAllAnalyses();
      cbanalysis.clear();
      
      for(int i=0;i<eanalysis.size();i++)
      {
         cbanalysis.add(new JCheckBox(eanalysis.get(i).getName(),false));
         add(cbanalysis.get(i));
      }
      
      setBorder(BorderFactory.createEmptyBorder());
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   }
   
   public void setSelected(Analysis[] a)
   {
      if(a!=null)
      for(int i=0;i<a.length;i++)
      {
         for(int j=0;j<cbanalysis.size();j++)
            if(a[i].getName().equals(cbanalysis.get(j).getText())) cbanalysis.get(j).setSelected(true);
      }
   }
   
   public boolean nothingSelected()
   {
      int count=0;
      for(int i=0;i<cbanalysis.size();i++)
         if(cbanalysis.get(i).isSelected()) count++;
      if(count==0) return true;
      else return false;
   }
   
   public String[] getAllPickedString()
   {
      ArrayList<String> s= new ArrayList<String>();
      for(int i=0;i<cbanalysis.size();i++)
         if(cbanalysis.get(i).isSelected()) s.add(cbanalysis.get(i).getText());
      String[] fin=new String[s.size()];
      
      return s.toArray(fin);
   }
}