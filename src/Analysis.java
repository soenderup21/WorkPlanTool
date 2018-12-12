import java.awt.Color;
import java.awt.color.*;
import java.util.Random;

/**
 * 
 * @author Draluca
 *Contains the names of the analyses the company has to do
 */
public class Analysis
{
   private String name;
   private Color color;
   
   
   /**
    * Contructor for Analysis
    * @param name sets the name of the analysis
    */
   public Analysis(String name)
   {
   	Random rnd = new Random();
      this.name = name;
      this.color = new Color(rnd.nextInt(255-100)+100,rnd.nextInt(255-100)+100,rnd.nextInt(255-100)+100);
   }
   
   /**
    * gets color of analysis
    * @return color of type Color in java.util.Color
    */
   public Color getColor() {
   	return this.color;
   }
   /**
    * sets the Color of the variable color   
    * @param color
    */
   public void setColor(Color color) {
   	this.color = color;
   }
   
   
   /**
    * 
    * @param name sets the name of the analysis
    */
   public void setName(String name)
   {
      this.name = name;
   }
   
   /**
    * 
    * @return the name of the analysis
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * returns the name of the analysis
    */
   public String toString()
   {
      return name;
   }
   
   /**
    * compares if 2 analyses are equal
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Analysis))
         return false;
      return name.equals(((Analysis)obj).name);
   }
}
