package Flight_System;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Ludwig Gustafsson
 *
 */
/**
 * This is the class for the menu that the flight system uses
 * 
 */
public class Menu
{
	BetterScanner scanner = new BetterScanner();
	ArrayList<ThreadSystem> threads = new ArrayList<ThreadSystem>();
	boolean quit = false;
	int choice = 0;
	StringBuilder sb = new StringBuilder("Plane 0");
	Integer planeCount = 0;
	ArrayList<Plane> planes = new ArrayList<Plane>();

	/**
	 * This procedure prints out the choices to the user 
	 * 
	 */
	public void printInstructions()
	{
		clearConsole();
		System.out.println("Menu");
		System.out.println("1) Add plane");
		System.out.println("2) Remove plane");
		System.out.println("3) Edit plane");
		System.out.println("4) Print passenger list");
		System.out.println("5) Print plane list");
		System.out.println("6) Run plane simulation (no graphics)");
		System.out.println("7) Run plane simulation (with graphics)");
		System.out.println("0) Exit");
		System.out.print("Choice:");
	}

	/**
	 * This procedure clears the screen(Wont work on eclipse) 
	 * 
	 */
	public final void clearConsole()
	{
		try
		{
			final String os = System.getProperty("os.name");
			if (os.contains("Windows"))
			{
				Runtime.getRuntime().exec("cls");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{
			// Handle any exceptions.
		}
	}

	public void printPassengerList(Plane plane)
	{
		plane.print();
	}

	/**
	 * This procedure prints the list of planes
	 */
	public void printPlaneList()
	{
		int i = 1;
		for (Plane plane : planes)
		{
			System.out.println(i + ") " + plane.getPlaneName());
			i++;
		}
	}

	/**
	 * This function takes in a plane and asks the user which
	 *  class to use and returns a reservation in that type
	 * @param <Plane>
	 * @return Reservation
	 */
	public Reservation askForClass(Plane plane)
	{
		System.out.println("Which class?");
		int i = 1;
		for (Reservation reservtmp : plane.reservation)
		{
			System.out.println(i + ") " + reservtmp);
			i++;
		}
		choice = scanner.betterScanInt(1, plane.reservation.size()) - 1;
		return plane.reservation.get(choice);
	}

	/**
	 *This procedure takes in a food list and prints it out
	 * 
	 * @param <ArrayList<Food>>
	 */
	public void printFoods(ArrayList<Food> mat)
	{
		int i = 0;
		System.out.println("Food?");
		for (Food food : mat)
		{
			System.out.println(i + ") " + food);
			i++;
		}
	}

	/**
	 * This procedure lets the user edit plane chosen
	 * 
	 */
	public void editPlane()
	{
		Point p = new Point();
		ArrayList<Food> mat;
		Reservation reserv = null;
		Food tmpFood = new Food(null, 0);
		while (true)
		{
			printPlaneList();
			System.out.println("Which plane do you want to edit?");
			choice = scanner.betterScanInt(1, planes.size()) - 1;
			Plane plane = planes.get(choice);
			System.out.println("What do you want to edit for " + plane.getPlaneName() + "?");
			System.out.println("1) Add customer manually");
			System.out.println("2) Add new food item to list");
			System.out.println("3) Staring coordinates");
			System.out.println("4) Goal coordinates");
			System.out.println("5) Plane name");
			System.out.println("0) Exit edit menu");
			choice = scanner.betterScanInt(0, 5);
			switch (choice)
			{
			case 1:
				reserv = askForClass(plane);
				if (reserv.getSeatsLeft() == 0)
				{
					System.out.println("This class is full");
					continue;
				}
				System.out.print("First name = ");
				String firstName = scanner.betterScanString();
				System.out.print("Last name = ");
				String lastName = scanner.betterScanString();
				Customer customer = new Customer(firstName, lastName);
				plane.customer.add(customer);
				mat = reserv.mat;
				printFoods(mat);
				choice = scanner.betterScanInt(0, reserv.mat.size());
				tmpFood = reserv.mat.get(choice);
				plane.setTotalPrice(plane.getTotalPrice() + tmpFood.getPrice());
				reserv.säte.add(new Seat(Constants.SEATSFOREVERYCLASS - reserv.getSeatsLeft(), customer));
				reserv.setSeatsLeft(reserv.getSeatsLeft() - 1);
				break;
			case 2:
				reserv = askForClass(plane);
				mat = reserv.mat;
				printFoods(mat);
				System.out.print("Name =");
				tmpFood.setName(scanner.betterScanString());
				System.out.print("Price =");
				choice = scanner.betterScanInt(0, Integer.MAX_VALUE);
				tmpFood.setPrice(choice);
				reserv.mat.add(tmpFood);
				break;
			case 3:
				System.out.print("x = ");
				p.x = scanner.betterScanInt(0, Constants.SCREEN_WIDTH);
				System.out.print("y = ");
				p.y = scanner.betterScanInt(0, Constants.SCREEN_HEIGHT);
				plane.setCords(p);
				break;
			case 4:
				System.out.print("x = ");
				p.x = scanner.betterScanInt(0, Constants.SCREEN_WIDTH);
				System.out.print("y = ");
				p.y = scanner.betterScanInt(0, Constants.SCREEN_HEIGHT);
				plane.setGoal(p);
				break;
			case 5:
				System.out.print("Plane name = ");
				plane.setPlaneName(scanner.betterScanString());
				break;
			case 0:
				return;
			}
		}
	}

	/**
	 * This is the where the program starts. User is given choices by text print out
	 * to the screen. User can pick choices with keyboard with values from 0 to 7.
	 */
	public void mainMenu()
	{
		while (quit == false)
		{
			printInstructions();
			switch (choice = scanner.betterScanInt(0, 7))
			{
			case 1: // Add planes
				System.out.println("How many planes?");
				choice = scanner.betterScanInt(0, Integer.MAX_VALUE);
				for (int i = 0; i < choice; i++)
				{
					planeCount++;
					sb.replace(6, 7, planeCount.toString());
					planes.add(new Plane(sb.toString()));
					threads.add(new ThreadSystem(planes.get(planeCount - 1)));
				}
				break;
			case 2: // Remove plane
				printPlaneList();
				System.out.println("Which 1 do you wan't to remove?");
				choice = scanner.betterScanInt(1, planes.size()) - 1;
				planes.remove(choice);
				threads.remove(choice);
				planeCount--;
				break;
			case 3: // Edit item plane
				editPlane();
				break;
			case 4: //Print passenger list
				if (planes.isEmpty())
				{
					System.out.println("There is not planes, so no passenger list can be printed!");
					break;
				}
				printPlaneList();
				System.out.println("Which plane passenger list?");
				choice = scanner.betterScanInt(1, planes.size()) - 1;
				printPassengerList(planes.get(choice));
				break;
			case 5: //Print plane list
				printPlaneList();
				break;
			case 6: //Run plane simulation (no graphics)
				for (ThreadSystem threadSystem : threads)
				{
					threadSystem.start();
				}
				threads.clear();
				planes.clear();
				planeCount = 0;
				break;
			case 7: // Run plane simulation (with graphics)
				for (ThreadSystem threadSystem : threads)
				{
					threadSystem.start();
				}
				DrawSystem drawSystem = new DrawSystem();
				drawSystem.drawRun(planes);
				threads.clear();
				planeCount = 0;
				break;
			case 0: //Exit
				quit = true;
				break;
			default:
				System.out.println("Invaild choice!");
				break;
			}
		}
		scanner.close();
	}
}
