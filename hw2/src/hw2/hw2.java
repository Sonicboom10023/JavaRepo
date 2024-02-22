package hw2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class hw2 extends JComponent {        
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Builder 9002");
        JPanel P = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                   super.paintComponent(g);
                Random rand = new Random();
                setBackground(Color.WHITE);
                Graphics2D g2 = (Graphics2D)g;
                int rand_shape = rand.nextInt(4);
                int rand_n = rand.nextInt(75)+25;
                int rand_n2 = rand.nextInt(75)+25;
                int rand_r = rand.nextInt(255);
                int rand_g = rand.nextInt(255);
                int rand_b = rand.nextInt(255);
                int rand_x = rand.nextInt(1920);
                int rand_y = rand.nextInt(1080);
                g2.setColor(new Color(rand_r,rand_g,rand_b));
                switch (rand_shape) {
                case 0: //rect
                		if (rand_y + rand_n2 > 1080) {
                			rand_y = 1080-rand_n2;
                		}
                		if (rand_x + rand_n > 1920) {
                			rand_x = 1920-rand_n;
                		}
                		g2.drawRect(rand_x, rand_y, rand_n, rand_n2);                	
                        g2.fillRect(rand_x, rand_y, rand_n, rand_n2);
                        break;
                case 1: //oval
                		if (rand_y + rand_n > 1080) {
                			rand_y = 1080-rand_n2;
        				}
                		if (rand_x + rand_n > 1920) {
        					rand_x = 1920-rand_n;
        				}
                		g2.drawOval(rand_x, rand_y, rand_n, rand_n2);
                        g2.fillOval(rand_x, rand_y, rand_n, rand_n2);
                        break;
                case 2: //square
                		if (rand_y + rand_n > 1080) {
                		rand_y = 1080-rand_n2;
        				}
        				if (rand_x + rand_n > 1920) {
        					rand_x = 1920-rand_n;
        				}
                		g2.drawRect(rand_x, rand_y, rand_n, rand_n);
                        g2.fillRect(rand_x, rand_y, rand_n, rand_n);
                        break;
                case 3: //triangle
	                	if (rand_x > 1860) {
		        			rand_x = 1860;
		        		}
		        		if (rand_x < 60) {
		        			rand_x = 60;
		        		}
                		int[] xPoints = {rand_x, rand_x+60, rand_x-60};
                		if (rand_y > 880) {
                			rand_y = 880;
                		}
                        int[] yPoints = {rand_y, rand_y+200,rand_y+200};
                        Polygon tri = new Polygon(xPoints, yPoints, 3);
                        g2.fillPolygon(tri);
                        break;
                case 4:
		                if (rand_x > 1870) {
		        			rand_x = 1850;
		        		}
		        		if (rand_x < 50) {
		        			rand_x = 70;
		        		}
	                	int[] x1Points = {rand_x+50, rand_x+25, rand_x-25, rand_x-50,rand_x};
	                	if (rand_y > 980) {
		        			rand_y = 980;
		        		}
		        		if (rand_y < 100) {
		        			rand_y = 100;
		        		}
	                    int[] y1Points = {rand_y-50, rand_y-100,rand_y-100, rand_y-50, rand_y};
	                    Polygon hexa = new Polygon(x1Points, y1Points, 5);
	                    g2.fillPolygon(hexa);
	                    break;
                }
            }
        };
        frame.add(P);
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    
}