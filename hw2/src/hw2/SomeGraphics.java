package hw2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;

import javax.swing.JComponent;


public class SomeGraphics extends JComponent{


	public void paintComponent(Graphics g){	
		// Casts g as a Graphics2D object, allows us to draw/fill
		// with shape objects
		Graphics2D g2 = (Graphics2D)g;
		
		// Create a shape and color it
		Rectangle2D.Double r = new Rectangle2D.Double(50, 50, 100, 100);
		
		// setColor tells g2 what to color everything until called again
		// takes either an enum case (Color.SOMETHING) 
		// or a custom color (new Color(r, g, b))
		g2.setColor(Color.YELLOW);
		
		// g2.fill() fills the area defined by the shape
		g2.fill(r);
		g2.setColor(new Color(60, 100, 220));
		
		// g2.draw() defines the outline made by the shape
		g2.draw(r);
		
		// Shape input (int x, int y, int width, int height)
		// (x,y) is the top left corner
		
		// Create another shape and color it differently
		Ellipse2D.Double c = new Ellipse2D.Double(200, 200, 300, 100);
		g2.setColor(Color.BLUE);
		// g2.fill(c);
		g2.draw(c);
		
		// drawRect, drawOval, fillRect, and fillOval can draw without
		// creating shape objects
		//g2.drawRect(150, 50, 100, 100);
		//g2.drawOval(100, 100, 75, 75);
		
		// Draw a triangle by defining the points, and creating a Polygon
		int[] xPoints = {50, 40, 150};
		int[] yPoints = {50, 200, 200};
		Polygon tri = new Polygon(xPoints, yPoints, 3);
		g2.setColor(Color.RED);
		
		// Draw polygons with fillPolygon instead of fill
		g2.fillPolygon(tri);
		//g2.draw(tri);
		
		// Draw two semicircles
		Arc2D.Double leftHalf = 
				new Arc2D.Double(250, 250, 100, 100, 240, 180, Arc2D.PIE);
							//   x ,   y,   w,   h, start, arclength, fill
		g2.setColor(Color.BLUE);
		g2.fill(leftHalf);
		//g2.draw(leftHalf);
		
		Arc2D.Double rightHalf = 
				new Arc2D.Double(250, 250, 100, 100, 60, 180, Arc2D.PIE);
		g2.setColor(Color.MAGENTA);
		g2.fill(rightHalf);
		//g2.draw(rightHalf);
		
	}
	

	private static final long serialVersionUID = 1L;

}
