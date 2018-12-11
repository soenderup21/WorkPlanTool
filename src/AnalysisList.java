import java.util.ArrayList;

/**
 * A class that hold an ArrayList which contains all of the Analysis.
 * @author Aleksandra Angelova
 * @version 1.0
 */
public class AnalysisList 
{
	private ArrayList<Analysis> analysisList;
	
	/**
	 * A no-argument constructor which initializes an ArrayList.
	 */
	public AnalysisList()
	{
		analysisList = new ArrayList<Analysis>();
	}
	
	/**
	 * A method which returns nothing and adds an Object from type Analysis to the ArrayList analysisList.
	 * @param analysis is of type Analysis and is being added to the ArrayList.
	 */
	public void addAnalysis(Analysis analysis) 
	{
		analysisList.add(analysis);
	}
	
	/**
	 * A method which returns nothing and removes the Object given in the parameters from the ArrayList.
	 * @param analysis is of type Analysis and is being removed from the ArrayList analysisList.
	 */
	public void removeAnalysis(Analysis analysis)
	{
		analysisList.remove(analysis);
	}
	
	public void removeAnalysis(int index)
	{
		analysisList.remove(index);
	}
	
	/**
	 * A method which returns an ArrayList with all the Analysis.
	 * @return an ArrayList analysisList.
	 */
	public ArrayList<Analysis> getAllAnalysis()
	{
		return analysisList;
	}
	
	/**
	 * A method which takes the position of the Analysis in the parameters and returns the Analysis on this position
	 * @param index gives the position of the Analysis in the ArrayList analysisList.
	 * @return an Analysis on position index in the ArrayList.
	 */
	public Analysis getAnalysis(int index)
	{
		return analysisList.get(index);
	}
	
	/**
	 * A method which returns the number of Analysis.
	 * @return the size of the ArrayList.
	 */
	public int size()
	{
		return analysisList.size();
	}
	
}
