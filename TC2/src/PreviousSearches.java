import java.util.Hashtable;

public class PreviousSearches
{
	private static PreviousSearches Instance = null;
	private Hashtable<String,String> Results;
	
	private PreviousSearches()
	{
		Results = new Hashtable<String,String>();
	}
	
	public static PreviousSearches getInstance()
	{
		if (Instance == null)
			Instance = new PreviousSearches();
		return Instance;
	}
	
	public Hashtable<String,String> getResults()
	{
		return Results;
	}
	
	public void addResult(String pWord,String pWebsite)
	{
		Results.put(pWord, pWebsite);
	}
}
