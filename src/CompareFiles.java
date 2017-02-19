import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CompareFiles
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File oldf = new File("Number Lists/base old.txt");
		File newf = new File("Number Lists/Base10PolyDivisibleNumbers.txt");
		
		Scanner olds = new Scanner(oldf);
		Scanner news = new Scanner(newf);
		int line = 0;
		
		while(olds.hasNextLine() && news.hasNextLine())
		{
			String oldLine = olds.nextLine();
			String newLine = news.nextLine();
			line = line + 1;
			
			if(!oldLine.equals(newLine))
			{
				System.out.println("First Difference on line " + line + " = OLD: " + oldLine + "     NEW: " + newLine);
				break;
			}
		}
		olds.close();
		news.close();
	}
}
