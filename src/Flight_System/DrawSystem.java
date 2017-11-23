package Flight_System;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Ludwig Gustafsson
 *
 */

//This is where graphics is drawn from Menu option 7.
public class DrawSystem
{
	JFrame frame;

	public DrawSystem()
	{
		this.frame = new JFrame();
	}
	/**
	 *  This procedure takes in planes and starts the drawing process.
	 * @param planes
	 */
	public void drawRun (ArrayList<Plane> planes)
	{
		frame.add(new TestPane(planes));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public class TestPane extends JPanel
	{
		private static final long serialVersionUID = 1L;
		private BufferedImage image = null;
		private BufferedImage image2 = null;
		ArrayList<Plane> planes;
		public TestPane(ArrayList<Plane> planes)
		{
			this.planes = planes;
			try //Loads the images used.
			{
				image = ImageIO.read(new File(System.getProperty("user.dir") + File.separator + "images" + File.separator + "earth4.jpg"));
				image2 = ImageIO.read(new File(System.getProperty("user.dir") + File.separator + "images" + File.separator + "plane5.png"));
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

		/**
		 * This sets the size of the screen
		 */
		@Override
		public Dimension getPreferredSize()
		{
			return image == null ? new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT): new Dimension(image.getWidth(), image.getHeight());
		}

		/**
		 * This is the drawing loop
		 * @param <Graphics>
		 */
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.RED);
			g2d.drawImage(image, 0, 0, this); //Draws the world map.
			double rotation;
			int i = 0;
			Plane p;
			int reached = planes.size();
			while (i < planes.size())
			{
				p = planes.get(i);
				if (p.getCords().x == p.getGoal().x && p.getCords().y == p.getGoal().y) //Checks if the plane have reached it's goal.
				{
					reached--;
					if (reached < 1) //If all planes have reached their goals this ends the DrawSystem execution.
					{
						planes.clear();
						g2d.dispose();
						frame.dispose();
					}
					i++;
					continue; //Skips drawing of planes that have already reched goal.
				}
				rotation = -Math.atan2(p.getCords().x - p.getGoal().x, p.getCords().y - p.getGoal().y);
				g2d.rotate(rotation, p.getCords().x, p.getCords().y); //Rotates the plane
				g2d.drawImage(image2, p.getCords().x - 16, p.getCords().y - 16, this); //Draws the plane
				g2d.rotate(-rotation, p.getCords().x, p.getCords().y); //Rotates back.
				g2d.drawLine(p.getCords().x, p.getCords().y, p.getGoal().x, p.getGoal().y); //Draws line from the plane to the goal.
				i++;
			}
			repaint();
			g2d.dispose();
		}
	}
}
