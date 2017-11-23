package Flight_System;

/**
 * @author Ludwig Gustafsson
 *
 */
//This is the Food class and a list of it is created in the Reservation class.
public class Food
{
	private String name = null;
	private int price;

	public Food(String name, int price)
	{
		this.name = name;
		this.price = price;
	}

	public int getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return name + "	" + price + " kr";
	}

}
