import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Narcissistic
{
	private static int found;
	public static void main(String[] args) throws FileNotFoundException
	{
		File f = new File("Number Lists/Base10NarcissisticNumbers.txt");
		PrintWriter out = new PrintWriter(f);
		
		long start = System.nanoTime();
		findNumbers(out, 30);
		long end = System.nanoTime();
		long duration = end - start;
		String lastLine = Utility.calculateTime(duration);
		
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
		found = 0;
		
		//keeps track of the current number
		int curInt = 0;
		
		//keeps track of the current million
		long lastOrder = 0;
		
		while(curInt >= 0)
		{
			curInt = nextInt(curInt, out);
			
			if((curInt/10000000)*10000000 != lastOrder)
			{
				lastOrder = (curInt/10000000)*10000000;
				System.out.println((curInt/10000000)*10000000);
			}
			if(found == target)
			{
				return;
			}
		}
		
//		rollover number to a long
		long curLong = 2100000000L;
		
		while(curLong > 0)
		{
			curLong = nextLong(curLong, out);
			
			if((curLong/100000000)*100000000 != lastOrder)
			{
				lastOrder = (curLong/100000000)*100000000;
				System.out.println((curLong/100000000)*100000000);
			}
			if(found == target)
			{
				return;
			}	
		}
		
		//convert over to BigInteger
		BigInteger bigCur = new BigInteger("9220000000000000000");
		
		while(true)
		{
			bigCur = nextBig(bigCur, out);
			
			if(!(bigCur.divide(new BigInteger("" + 10000000000L))).multiply(new BigInteger("" + 10000000000L)).equals(new BigInteger("" + lastOrder)))
			{
				lastOrder = (curLong/10000000000L)*10000000000L;
				System.out.println((curLong/10000000000L)*10000000000L);
			}
			if(found == target)
			{
				return;
			}	
		}
	}
	
	public static int nextInt(int num, PrintWriter out)
	{
		int res = 0;
		int tmp = 0;
		String numString = "" + num;
		int power = numString.length();
		
		for(int i = 0; i < numString.length() - 1; i++)
		{
			int base = Integer.parseInt(numString.substring(i, i+1));
			tmp = tmp + (int) Utility.pow(base, power);
			
			if(tmp > num && i == 0)
			{
				String first = "" + 1;
				
				String zeros = "";
				for(int in = 0; in < numString.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = Integer.parseInt(full);
				break;
			}
			else if(tmp > num && i == 1)
			{
				String first = "" + (Integer.parseInt("" + numString.charAt(0)) + 1);
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = Integer.parseInt(full);
				break;
			}
			else if(tmp > num)
			{
				String firstHalf = "" + (Integer.parseInt(numString.substring(0,i)) + 1);
				
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = firstHalf + zeros;
				
				res = Integer.parseInt(full);
				break;
			}
		}
		
		if(res == 0)
		{
			int base = Integer.parseInt(numString.substring(numString.length() -1, numString.length()));
			tmp = tmp + (int) Utility.pow(base, power);
			if(tmp == num && num != 0)
			{
				out.println(num);
				out.flush();
				found++;
			}
			res = num + 1;
		}
		
		return res;
	}
	
	public static long nextLong(long num, PrintWriter out)
	{
		long res = 0;
		long tmp = 0;
		String numString = "" + num;
		int power = numString.length();
		
		for(int i = 0; i < numString.length() - 1; i++)
		{
			int base = Integer.parseInt(numString.substring(i, i+1));
			tmp = tmp + Utility.pow(base, power);
			
			if(tmp > num && i == 0)
			{
				String first = "" + 1;
				
				String zeros = "";
				for(int in = 0; in < numString.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = Long.parseLong(full);
				break;
			}
			else if(tmp > num && i == 1)
			{
				String first = "" + (Long.parseLong("" + numString.charAt(0)) + 1);
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = Long.parseLong(full);
				break;
			}
			else if(tmp > num)
			{
				String firstHalf = "" + (Long.parseLong(numString.substring(0,i)) + 1);
				
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = firstHalf + zeros;
				
				res = Long.parseLong(full);
				break;
			}
		}
		
		if(res == 0)
		{
			int base = Integer.parseInt(numString.substring(numString.length() -1, numString.length()));
			tmp = tmp + Utility.pow(base, power);
			if(tmp == num && num != 0)
			{
				out.println(num);
				out.flush();
				found++;
			}
			res = num + 1;
		}
		
		return res;
	}
	
	public static BigInteger nextBig(BigInteger num, PrintWriter out)
	{
		BigInteger res = BigInteger.ZERO;
		BigInteger tmp = BigInteger.ZERO;
		String numString = num.toString();
		int power = numString.length();
		
		for(int i = 0; i < numString.length() - 1; i++)
		{
			int base = Integer.parseInt(numString.substring(i, i+1));
			BigInteger bigBase = new BigInteger("" + base);
			tmp = tmp.add(bigBase.pow(power));
			
			if(tmp.compareTo(num) == 1 && i == 0)
			{
				String first = "" + 1;
				
				String zeros = "";
				for(int in = 0; in < numString.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = new BigInteger(full);
				break;
			}
			else if(tmp.compareTo(num) == 1 && i == 1)
			{
				String first = "" + (new BigInteger("" + numString.charAt(0))).add(BigInteger.ONE);
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = first + zeros;
				
				res = new BigInteger(full);
				break;
			}
			else if(tmp.compareTo(num) == 1)
			{
				String firstHalf = "" + (new BigInteger(numString.substring(0,i))).add(BigInteger.ONE);
				
				String zeros = "";
				String end = numString.substring(i, numString.length());
				for(int in = 0; in < end.length(); in++)
				{
					zeros = zeros + "0";
				}
				
				String full = firstHalf + zeros;
				
				res = new BigInteger(full);
				break;
			}
		}
		
		if(res.equals(BigInteger.ZERO))
		{
			int base = Integer.parseInt(numString.substring(numString.length() -1, numString.length()));
			BigInteger bigBase = new BigInteger("" + base);
			tmp = tmp.add(bigBase.pow(power));
			if(tmp.equals(num) && !num.equals(BigInteger.ZERO))
			{
				out.println(num);
				out.flush();
				found++;
			}
			res = num.add(BigInteger.ONE);
		}
		
		return res;
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
			long tmp = Utility.pow(base, power);
			
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
}
