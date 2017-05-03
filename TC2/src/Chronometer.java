
public class Chronometer implements Runnable
{
	private int Time;
	private boolean Active;
	
	@Override
	public void run()
	{
		Active = true;
		while (Active)
		{
			Time += 1;
		}
	}
	
	public int getTime()
	{
		Active = false;
		return Time;
	}
}
