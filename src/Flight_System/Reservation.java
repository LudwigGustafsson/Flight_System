package Flight_System;

import java.util.ArrayList;

/**
 * @author Ludwig Gustafsson
 *
 */
//This is the class for the different types of reservation. Like for example first and economy class.
public class Reservation
{
	ArrayList<Food> mat;
	private int price;
	private String classType;
	ArrayList<Seat> seats = new ArrayList<Seat>();
	private int SeatsLeft;

	public Reservation(int price, String classType)
	{
		this.SeatsLeft = Constants.SEATSFOREVERYCLASS;
		this.mat = new ArrayList<Food>();
		this.price = price;
		this.classType = classType;
	}

	//Setters and getters
	public int getSeatsLeft()
	{
		return SeatsLeft;
	}

	public void setSeatsLeft(int seatsLeft)
	{
		SeatsLeft = seatsLeft;
	}

	public int getPrice()
	{
		return price;
	}

	/**
	 * This procedure prints the seats for this reservation class
	 * Plane class can call this.
	 */
	public void printSub()
	{
		for (Seat seat : seats)
		{
			System.out.println(seat);
		}
		for (int i = seats.size(); i < Constants.SEATSFOREVERYCLASS; i++)
		{
			System.out.println("Seat " + i + " vacant");
		}
	}

	@Override
	public String toString()
	{
		return classType + "	" + price + " kr";
	}

}
