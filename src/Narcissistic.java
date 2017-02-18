import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Narcissistic
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File f = new File("NarcissisticNumbers.txt");
		PrintWriter out = new PrintWriter(f);
		
		findNumbers(out);
		out.close();
		
	}
	
	public static void findNumbers(PrintWriter out)
	{
		//keeps track of how many have been found
		int found = 0;
		int num = 0;
		
		while(num != -2147483648)
		{
			if(isNarcissistic(num))
			{
				out.println(num);
				out.flush();
				found++;
				
				if(found%10 == 0)
				{
					System.out.println(found);
				}
			}
			if(num%1000000 == 0)
			{
				System.out.println(num);
			}
			num = num + 1;
		}
	}
	
	/**
	 * Checks if a base10 number is narcissistic
	 * (A number is narcissistic if the sum of (every digit raised to the power of
	 * the total number of digits in the number) equal the number itself)
	 * @param num the number to be checked
	 * @return whether or not it is narcissistic
	 */
	public static boolean isNarcissistic(int num)
	{
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
			
			//if the sum ever get bigger than the number, then its not narcissistic
			if(testNumber > num)
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
}
