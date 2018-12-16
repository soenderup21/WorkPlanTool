import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderPanel extends JPanel
{  
   private JLabel weekLabel;
   private MyDate monday;
   
   public HeaderPanel(MyDate monday)
   {
      this.monday=monday;
      
      weekLabel=new JLabel("WEEK  ");
      updateHeader(monday);
      weekLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
      weekLabel.setForeground(new Color(44, 62, 91));
      weekLabel.setHorizontalAlignment(SwingConstants.CENTER); //setting text in the center
      
      add(weekLabel);
      setVisible(true);
      setBackground(new Color(229, 237, 249));
   }

   public void updateHeader(MyDate monday)
   {
      this.monday=monday;
      String sunday=monday.getDayOfWeek(6).toStringForCalendar();
      String week=monday.getWeek()+" ("+monday.toStringForCalendar()+" - "+sunday+" )";
      
      weekLabel.setText("WEEK  "+week);
   }
   
   public MyDate getMonday()
   {
      return monday;
   }
   
   public int getWeek()
   {
      return monday.getWeek();
   }
}
