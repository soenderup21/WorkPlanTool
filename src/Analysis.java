import java.io.Serializable;

/**
 * 
 * @author Draluca
 *Contains the names of the analyses the company has to do
 */
public class Analysis implements Serializable
{
   private String name;
   
   /**
    * 
    * @param name sets the name of the analysis
    */
   public Analysis(String name)
   {
      this.name = name;
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
