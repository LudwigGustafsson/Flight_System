package Flight_System;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author Ludwig Gustafsson
 *
 */
/**
 * This is a class I have created to replace the poor inbuilt java scanner
 */
public class BetterScanner
{
	Scanner sc = new Scanner(System.in);
	/**
	 * This function takes in a lower and upper limit of allowed values
	 * and returns a accepted value.
	 * @param <int>
	 * @return int
	 */
	public int betterScanInt(int lowerLimit, int upperLimit)
	{
		int choice;
		while (true)
		{
			try
			{
				sc = new Scanner(System.in);
				choice = sc.nextInt();
				if (choice < lowerLimit || choice > upperLimit)
				{
					System.err.println("Invalid choice!");
					continue;
				}
				break;
			}
			catch (InputMismatchException e1)
			{
				System.err.println("Invalid choice!");
				continue;
			}
		}
		return choice;
	}
	public int betterScanInt()
	{
		int choice;
		while (true)
		{
			try
			{
				sc = new Scanner(System.in);
				choice = sc.nextInt();
				break;
			}
			catch (InputMismatchException e1)
			{
				System.err.println("Invalid choice!");
				continue;
			}
		}
		return choice;
	}
	/**
	 * This function is a improvement of java's inbuilt scanner and will only accept a correct string.
	 * @return string
	 */
	public String betterScanString()
	{
		String choice;
		while (true)
		{
			try
			{
				 sc = new Scanner(System.in);
				choice = sc.next();
				break;
			}
			catch (InputMismatchException e1)
			{
				System.err.println("Invalid choice!");
				continue;
			}
		}
		return choice;
	}
	public void close()
	{
		sc = new Scanner(System.in);
		sc.close();
	}
}