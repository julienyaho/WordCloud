package ie.gmit.sw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ie.gmit.sw.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SimpleWordCloud extends JPanel{

	private static final long serialVersionUID = 1L;
	public static BufferedImage img;
	private static int fontsize;
	private TestRunner test = new  TestRunner();
	
	public static int getFontsize(int font) {
		return fontsize;
	}
	
	
	
	
	 public SimpleWordCloud() {
		
		 img = new BufferedImage(1200, 650, BufferedImage.TYPE_INT_ARGB);
		 List<String> myWords = test.myWords(); 
		 Random rnd = new Random();
         Font font = getFont();
         Graphics2D g2d = img.createGraphics();
         g2d.setColor(Color.WHITE);
         g2d.fillRect(0, 0, 1200, 650);

         List<Rectangle2D> used = new ArrayList<>(25);
         
         for (String strword : myWords) {
             int size = rnd.nextInt(37) + 11;
             Font drawFont = font.deriveFont((float) size);
             FontMetrics fm = g2d.getFontMetrics(drawFont);
             Rectangle2D bounds = fm.getStringBounds(strword, g2d);
             System.out.println("Positing " + strword);
             do {
                 int xPos = rnd.nextInt(1200 - (int)bounds.getWidth());
                 int yPos = rnd.nextInt(650 - (int)bounds.getHeight());

                 bounds.setFrame(xPos, yPos, bounds.getWidth(), bounds.getHeight());
             } while (collision(used, bounds));
             used.add(bounds);
             g2d.setFont(drawFont);
             g2d.setColor(Color.BLACK);
             g2d.drawString(strword, (float)bounds.getX(), (float)bounds.getY() + fm.getAscent());
             g2d.setColor(Color.RED);
             g2d.draw(bounds);
         }
         g2d.dispose();
		 
		
	}
	protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
         g2d.drawImage(img, 0, 0, this);
         g2d.dispose();
     }
	
	/*

	public void drawWords(String word, int fontSize) throws IOException {
	
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(word);
		
		

	}
	*/

	// font array[]
	// picking(fontsize)
	//
	public static void Write() throws IOException {

		//ImageIO.write(img, "png", new File("image.png"));
		System.out.println("Image saved as image.png");

	}
	public static Font getRandomFont(int fontSize) {
		Font fonts = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);

		return fonts;
	}

	//public static void dispose() {
		//g2d.dispose();
	//}
     public Dimension getPreferredSize() {
         return new Dimension(1200, 650);
     }

    
     protected static boolean collision(List<Rectangle2D> used, Rectangle2D bounds) {
         boolean collides = false;
         for (Rectangle2D check : used) {
             if (bounds.intersects(check)) {
                 collides = true;
                 break;
             }
         }
         return collides;
     }
}
