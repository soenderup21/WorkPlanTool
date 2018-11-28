public class Analysis
{
   private String name;
   public Analysis(String name)
   {
      this.name = name;
   }
   public void setName(String name)
   {
      this.name = name;
   }
   public String getName()
   {
      return name;
   }
   public String toString()
   {
      return name;
   }
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Analysis))
         return false;
      return name.equals(((Analysis)obj).name);
   }
}