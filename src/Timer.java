	/**
	 * A class that converts a difference between 
	 * System.nanoTime values into an easily readable string
	 */
public class Timer
{
	
	/**
	 * Constructor
	 */
	public Timer()
	{
	}
	
	/**
	 * Generates the string from the duration
	 * @param duration difference between System.nanoTime values
	 * @return the generated string
	 */
	public static String calculate(long duration)
	{
		int milliseconds = (int) duration/1000000;
		long seconds = duration/1000000000;
		int hrs = 0;
		int mins = 0;
		if(seconds > 3600)
		{
			hrs = (int) seconds / 3600;
			seconds = seconds%3600;
		}
		if(seconds > 60)
		{
			mins = (int)seconds/60;
			seconds = seconds%60;
		}
		
		String lastLine = "Total time: ";
		if(hrs > 0)
		{
			lastLine = lastLine + hrs + " hours ";
		}
		if(mins > 0)
		{
			lastLine = lastLine + mins + " minutes ";
		}
		if(seconds > 0)
		{
			lastLine = lastLine + seconds + " seconds ";
		}
		if(milliseconds > 0)
		{
			lastLine = lastLine + milliseconds + " milliseconds";
		}
		
		return lastLine;
	}
}
