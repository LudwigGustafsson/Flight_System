package Flight_System;

/**
 * @author Ludwig Gustafsson
 *
 */
//This is the customer class and is used by the Plane, Seat and Menu class. 
public class Customer
{
	private String firstName;
	private String lastName;

	public Customer(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return firstName + " " + lastName;
	}

}
