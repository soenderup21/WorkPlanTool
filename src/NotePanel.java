import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class NotePanel extends JPanel
{
   private MyDate temp;
   private JPanel namePanel;
   private JLabel nameLabel;
   private JTextField nameField;
   
   private JPanel notePanel;
   private JLabel noteLabel;
   private JTextArea noteArea;
   
   private JPanel buttonPanel;
   private JCheckBox generalCheck;
   
   public NotePanel()
   {
      super();
      
      namePanel=new JPanel();
      nameLabel=new JLabel("Name: ");
      nameField=new JTextField(15);
      namePanel.add(nameLabel);
      namePanel.add(nameField);
      
      notePanel=new JPanel();
      noteLabel=new JLabel("Note: ");
      noteArea=new JTextArea(12, 25);
      notePanel.add(noteLabel);
      notePanel.add(noteArea);
      
      buttonPanel= new JPanel();
      generalCheck=new JCheckBox("General");
      buttonPanel.add(generalCheck);
      
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(namePanel);
      add(notePanel);
      add(buttonPanel);
      setMaximumSize(new Dimension(400, 300));
   }
   
   
   public String getName()
   {
      String s="";
      s=nameField.getText();
      return s;
   }
   public void setName(String name)
   {
      nameField.setText(name);
   }
   
   public String getNote()
   {
      String s="";
      s=noteArea.getText();
      return s;
   }
   public void setNote(String note)
   {
      nameField.setText(note);
   }
   
   public void setGeneral(boolean t)
   {
      generalCheck.setSelected(t);
   }
   public boolean getGeneral()
   {
      if(generalCheck.isSelected()) return true;
      else return false;
   }
   
   public void setEnabled(boolean t)
   {
      if(t)
      {
         noteArea.setEditable(false);
         generalCheck.setEnabled(false);
      }
      else
      {
         noteArea.setEditable(true);
         generalCheck.setEnabled(true);
      }
   }
   
   /*private void updateBoxes()
   {
      int year = 2017+ yearBox.getSelectedIndex();
      yearBox.removeAllItems();
      for(int i=1;i<=20; i++)
         yearBox.addItem(2017+i);
      if(year==2018 && yearBox.getItemCount()>0)
         yearBox.setSelectedIndex((int)(temp.getYear()-2017));
      else
         yearBox.setSelectedIndex(year);
      
      
      int month=monthBox.getSelectedIndex()+1;
      monthBox.removeAllItems();
      for(int i=1;i<=12; i++)
         monthBox.addItem(getMonthInString(i));
      if(month==0 && monthBox.getItemCount()>0)
         monthBox.setSelectedIndex(temp.getMonth()-1);
      else
         monthBox.setSelectedIndex(month-1);
      
      
      if(dayBox.isEditable()) 
      {
         int day = 1+dayBox.getSelectedIndex();
         dayBox.removeAllItems();
         int[] d= getDaysOfMonth(getSelectedMonth());
         
         for(int i=1;i<=d.length;i++)
            dayBox.addItem(d[i]);

         if(day==-1 && dayBox.getItemCount()>0)
            yearBox.setSelectedIndex(temp.getDay());
         else
            yearBox.setSelectedIndex(day-1);
      }  
   }*/
   
}