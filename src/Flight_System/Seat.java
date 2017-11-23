package Flight_System;

/**
 * @author Ludwig Gustafsson
 *
 */
//This class is used as a list in the Reservation class
//And is also used Menu and Plane class
public class Seat
{
	private int id;
	Customer customer;

	public Seat(int id, Customer customer)
	{
		this.customer = customer;
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Seat " + id + " reserved by " + customer;
	}

}
