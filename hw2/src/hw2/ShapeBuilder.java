package hw2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
	
public class ShapeBuilder {
		public class Builder extends JComponent{
			private static final long serialVersionUID = 1L;
	
			
			public void paintComponent(Graphics g){	
				// Casts g as a Graphics2D object, allows us to draw/fill
				// with shape objects
				Random rand = new Random();
				super.paintComponent(g);
				setBackground(Color.WHITE);
				Graphics2D g2 = (Graphics2D)g;
				
				// Create a shape and color it
				//Rectangle2D.Double r = new Rectangle2D.Double(50, 50, 100, 100);
				
				int rand_shape = rand.nextInt(5);
				int rand_n = rand.nextInt(75)+25;
				int rand_n2 = rand.nextInt(75)+25;
				int rand_r = rand.nextInt(255);
				int rand_g = rand.nextInt(255);
				int rand_b = rand.nextInt(255);
				int rand_x = rand.nextInt(1920);
				int rand_y = rand.nextInt(1080);
				g2.setColor(new Color(rand_r,rand_g,rand_b));
				
				switch (rand_shape) {
				case 1: g2.drawRect(rand_x, rand_y, rand_n, rand_n2);
						g2.fillRect(rand_x, rand_y, rand_n, rand_n2);
						break;
				case 2: g2.drawOval(rand_x, rand_y, rand_n, rand_n2);
						g2.drawOval(rand_x, rand_y, rand_n, rand_n2);
						break;
				case 3: g2.drawRect(rand_x, rand_y, rand_n, rand_n);
						g2.fillRect(rand_x, rand_y, rand_n, rand_n);
						break;
				case 4: int[] xPoints = {rand_x, rand_x+40, rand_x+150};
						int[] yPoints = {rand_y, rand_y+200,rand_y+200};
						Polygon tri = new Polygon(xPoints, yPoints, 3);
						g2.fillPolygon(tri);
						break;
				}
				
				
				// g2.draw() defines the outline made by the shape
				
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
	//			int[] xPoints = {50, 40, 150};
	//			int[] yPoints = {50, 200, 200};
	//			Polygon tri = new Polygon(xPoints, yPoints, 3);
	//			g2.setColor(Color.RED);
				
	
				
			}
			
	
		
		public static void main(String[] args) {
			SwingUtilities.invokeLater(() -> {
	            //Builder shape = new Builder();
	            JFrame frame = new JFrame("Shape Builder 9002");
	            frame.setSize(1920, 1080);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setVisible(true);
	            
	        });
	    }
	}
}
		
		



