package Flight_System;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ludwig Gustafsson
 *
 */
public class Plane
{
	ArrayList<Reservation> reservation = new ArrayList<Reservation>();
	ArrayList<Customer> customer = new ArrayList<Customer>();
	private Point cords;
	private Point goal;
	private String planeName;
	private double profit;

	/**
	 * Constructor for plane
	 * @param <String>
	 */
	public Plane(String planeName)
	{
		this.profit = 0.0;
		this.cords = new Point(1056, 178); //Sets the starting position for the plane to Arlanda.
		this.planeName = planeName;
		Random rnd = new Random();
		this.goal = new Point(rnd.nextInt(1920), rnd.nextInt(1080)); //Gives the plane a random goal within the resolution.
		init(); //Initialise the plane with preset values to save time.
	}

	//Just a bunch of setters and getters
	public double getTotalPrice()
	{
		return profit;
	}

	public void setTotalPrice(double totalPrice)
	{
		this.profit = totalPrice;
	}

	public Point getCords()
	{
		return cords;
	}

	public String getPlaneName()
	{
		return planeName;
	}

	public void setCords(Point cords)
	{
		this.cords = cords;
	}

	public void setGoal(Point goal)
	{
		this.goal = goal;
	}

	public void setPlaneName(String planeName)
	{
		this.planeName = planeName;
	}

	public Point getGoal()
	{
		return goal;
	}

	/**
	 * This procedure initialise the plane
	 * @param <void>
	 */
	public void init()
	{
		Reservation firstClass = new Reservation(Constants.FIRSTCLASSPRICE, "First class"); //Creates 2 price classes 
		Reservation economyClass = new Reservation(Constants.ECONOMYCLASSPRICE, "Economy class");
		firstClass.mat.add(new Food("None", 0));
		firstClass.mat.add(new Food("Meat 1", 170));
		firstClass.mat.add(new Food("Meat 2", 170));
		firstClass.mat.add(new Food("Fish 1", 180));
		firstClass.mat.add(new Food("Vegeterian 1", 180));
		economyClass.mat.add(new Food("None", 0));
		economyClass.mat.add(new Food("Meat 1", 110));
		economyClass.mat.add(new Food("Meat 2", 120));
		economyClass.mat.add(new Food("Fish 1", 130));
		economyClass.mat.add(new Food("Vegeterian 1", 130));
		reservation.add(firstClass); //Adds the classes to the reservation
		reservation.add(economyClass);
		customer.add(new Customer("Anna", "Petterson")); //Just a bunch of random names that can be used later to add to the plane.
		customer.add(new Customer("Peter", "Andersson"));
		customer.add(new Customer("Sanna", "Johnsson"));
		customer.add(new Customer("Ingemar", "Stream"));
		customer.add(new Customer("Sofia", "Zetterlund"));
		customer.add(new Customer("Jonas", "Lund"));
		customer.add(new Customer("Jasmine", "Gustafsson"));
		customer.add(new Customer("Mohammed", "Lee"));
		customer.add(new Customer("Josefine", "Goransdotter"));
		customer.add(new Customer("Petter", "Svensson"));
	}

	/**
	 * This procedure moves to plane to it's goal
	 * @throws InterruptedException
	 */
	public void pilot() throws InterruptedException
	{
		while (cords.x != goal.x || cords.y != goal.y)
		{
			if (cords.x < goal.x)
			{
				cords.x++;
			}
			else if (cords.x > goal.x)
			{
				cords.x--;
			}
			if (cords.y < goal.y)
			{
				cords.y++;
			}
			else if (cords.y > goal.y)
			{
				cords.y--;
			}
			Thread.sleep(Constants.SPEED);
			//System.out.println("Plane "+ this.id +" x,y = " +this.cords.x +"," + this.cords.y);
		}
	}

	/**
	 * This procedure handles the booking and the flying of the plane
	 * @throws InterruptedException
	 */
	public void fly() throws InterruptedException
	{
		System.out.println(planeName + " Starting booking");
		Random rnd = new Random();
		int choice = rnd.nextInt(reservation.size());
		Reservation tmpReserv;
		Food tmpFood;
		int modifier = 1;
		int i = 0;
		tmpReserv = reservation.get(i);
		int seatsLeft = Constants.SEATSFOREVERYCLASS * this.reservation.size();
		while (true)
		{
			choice = rnd.nextInt(tmpReserv.mat.size());
			tmpFood = tmpReserv.mat.get(choice);
			profit += 0.3 * (tmpReserv.getPrice() + tmpFood.getPrice());
			choice = rnd.nextInt(customer.size());
			tmpReserv.seats.add(new Seat(tmpReserv.seats.size() + modifier, customer.get(choice)));
			seatsLeft--;
			tmpReserv.setSeatsLeft(tmpReserv.getSeatsLeft() - 1);
			if (tmpReserv.getSeatsLeft() == 0)
			{
				if (seatsLeft == 0)
					break;
				i++;
				modifier += Constants.SEATSFOREVERYCLASS;
				tmpReserv = reservation.get(i);
			}
		}
		System.out.println(planeName + " Ending booking");
		System.out.println(planeName + " Profit = " + profit + " kr");
		Thread.sleep(400);
		System.out.println(planeName + " Starting lift off");
		Thread.sleep(1000);
		System.out.println(planeName + " Lift off complete");
		System.out.println(planeName + " Now flying to destination");
		pilot(); //Moves the plane
		System.out.println(planeName + " Landing sequence started");
		Thread.sleep(2000);
		System.out.println(planeName + " Landing sequence ended");
		Thread.sleep(100);
		System.out.println(planeName + " Flight is now complete");
	}

	/**
	 * This procedure prints out all the seats for all the classes.
	 */
	public void print()
	{
		int i = 0;
		while (i < reservation.size())
		{
			reservation.get(i).printSub();
			i++;
		}
	}
}
