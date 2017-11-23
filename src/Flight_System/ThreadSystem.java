package Flight_System;
/**
 * @author Ludwig Gustafsson
 *
 */
//This where the threads are created and used.
public class ThreadSystem implements Runnable
{
	private Thread t = null;
	private Plane plane;
	private String threadName;
	/**
	 * It takes in a plane and sets it's name to the same as the plane.
	 * 
	 * @param <Plane>
	 */
	ThreadSystem(Plane plane)
	{
		this.plane = plane;
		this.threadName = plane.getPlaneName();
		System.out.println("Creating " + threadName);
	}
	//Calls run after
	public void start()
	{
		if (t == null)
		{
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			plane.fly();
		}
		catch (InterruptedException e)
		{
			System.out.println("Thread " + threadName + " interrupted.");
		}
	}
}