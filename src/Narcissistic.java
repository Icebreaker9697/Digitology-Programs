import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Narcissistic
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File f = new File("Number Lists/NarcissisticNumbers.txt");
		PrintWriter out = new PrintWriter(f);
		
		long start = System.nanoTime();
		findNumbers(out, 30);
		long end = System.nanoTime();
		long duration = end - start;
		Timer.calculate(duration);
		String lastLine = Timer.calculate(duration);
		
		out.println("");
		out.println(lastLine);
		out.close();	
	}
	
	/**
	 * Generates a list of Narcissistic numbers.
	 * It starts the calculations as using ints, and then moves up to longs
	 * and then finally up to BigIntegers
	 * It also returns what number it currently is at, every million numbers
	 * @param out where the list is written to
	 */
	public static void findNumbers(PrintWriter out, int target)
	{
		//keeps track of how many have been found
		int found = 0;
		
		//keeps track of the current number
		int curInt = 0;
		
		while(curInt != -2147483648)
		{
			if(isNarcissisticInt(curInt))
			{
				out.println(curInt);
				out.flush();
				found++;			
			}
			if(curInt%1000000 == 0)
			{
				System.out.println(curInt);
			}
			if(found == target)
			{
				return;
			}
			
			curInt = curInt + 1;
		}
		
//		rollover number to a long
		long curLong = 2147483648L;

		
		
		while(curLong != -9223372036854775808L)
		{
			if(isNarcissisticLong(curLong))
			{
				out.println(curLong);
				out.flush();
				
			}
			if(curLong%1000000 == 0)
			{
				System.out.println(curLong);
			}
			if(found == target)
			{
				return;
			}
			
			curLong = curLong + 1;		
		}
		
		//convert over to BigInteger
		BigInteger bigCur = new BigInteger( "9223372036854775808");

		
		while(true)
		{
			if(isNarcissisticBig(bigCur))
			{
				out.println(bigCur.toString());
				out.flush();
				
			}
			if(bigCur.mod(new BigInteger("1000000")).equals(BigInteger.ZERO))
			{
				System.out.println(bigCur.toString());
			}
			if(found == target)
			{
				return;
			}
			
			bigCur = bigCur.add(BigInteger.ONE);	
		}
	}
	
	/**
	 * Checks if a base10 number as an int is narcissistic
	 * (A number is narcissistic if the sum of (every digit raised to the power of
	 * the total number of digits in the number) equal the number itself)
	 * @param num the number to be checked
	 * @return whether or not it is narcissistic
	 */
	public static boolean isNarcissisticInt(int num)
	{
		if(num == 0)
		{
			return false;
		}
		
		String numString = "" + num;
		
		//find the power to which to raise each digit when finding the total sum
		int power = numString.length();
		
		//number in which the sum will be stored
		long testNumber = 0;
		
		//iterates over each digit in the number
		for(int i = 0; i < numString.length(); i++)
		{
			int base = Integer.parseInt("" + numString.charAt(i));
			int tmp = (int) Math.pow(base, power);
			
			testNumber = testNumber + tmp;
			
			//if the sum ever get bigger than the number
			//(or if it got so big that the int turned negative)
			//then its not narcissistic
			if(testNumber > num || testNumber < 0)
			{
				return false;
			}
		}
		
		if(testNumber == num)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if a base10 number as a long is narcissistic
	 * (A number is narcissistic if the sum of (every digit raised to the power of
	 * the total number of digits in the number) equal the number itself)
	 * @param num the number to be checked
	 * @return whether or not it is narcissistic
	 */
	public static boolean isNarcissisticLong(long num)
	{		
		String numString = "" + num;
		
		int power = numString.length();
		
		long testNumber = 0L;
		
		for(int i = 0; i < numString.length(); i++)
		{
			int base = Integer.parseInt("" + numString.charAt(i));
			long tmp = pow(base, power);
			
			testNumber = testNumber + tmp;
			
			if(testNumber > num || testNumber < 0)
			{
				return false;
			}
		}
		
		if(testNumber == num)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a base10 number as a BigInteger is narcissistic
	 * (A number is narcissistic if the sum of (every digit raised to the power of
	 * the total number of digits in the number) equal the number itself)
	 * @param num the number to be checked
	 * @return whether or not it is narcissistic
	 */
	public static boolean isNarcissisticBig(BigInteger num)
	{
		String numString = num.toString();
		
		int power = numString.length();
		
		BigInteger testNumber =	BigInteger.ZERO;
		
		for(int i = 0; i < numString.length(); i++)
		{
			int base = Integer.parseInt("" + numString.charAt(i));
			
			BigInteger tmpBase = new BigInteger("" + base);
			
			BigInteger tmp = tmpBase.pow(power);
			
			testNumber = testNumber.add(tmp);
			
			if(testNumber.compareTo(num) == 1)
			{
				return false;
			}
		}
		
		if(testNumber.equals(num))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * This is a simple power method, which was written, because the java Math.pow
	 * was cutting off some digits at the end of huge calculations, and that
	 * was messing up the whole program
	 */
	private static long pow(int base, int power)
	{
		long result = base;
		for(int i = 0; i < power - 1; i++)
		{
			result = result*base;
		}
		
		return result;
	}
}
