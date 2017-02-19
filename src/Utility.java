	/**
	 * A class that converts a difference between 
	 * System.nanoTime values into an easily readable string
	 */
public class Utility
{
	
	/**
	 * Constructor
	 */
	public Utility()
	{
	}
	
	/**
	 * Generates the string from the duration
	 * @param duration difference between System.nanoTime values
	 * @return the generated string
	 */
	public static String calculateTime(long duration)
	{
		int milliseconds = (int) (duration%1000000000)/1000000;
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
	
	/**
	 * This is a simple power method, which was written, because the java Math.pow
	 * was cutting off some digits at the end of huge calculations, and that
	 * was messing up the whole program
	 */
	public static long pow(int base, int power)
	{
		long result = base;
		for(int i = 0; i < power - 1; i++)
		{
			result = result*base;
		}
		
		return result;
	}
}
