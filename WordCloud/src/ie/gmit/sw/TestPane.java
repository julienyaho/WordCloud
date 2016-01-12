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

import javax.swing.JPanel;

public class TestPane extends JPanel{

	 private BufferedImage  img;
	
	 public TestPane() {
		 img = new BufferedImage(1200, 650, BufferedImage.TYPE_INT_ARGB);
         try {
        BufferedReader br = new BufferedReader(new FileReader(new File("Words.txt")));
             List<String> words = new ArrayList<>(25); 
             String text = null;
             System.out.println("Read");
             while ((text = br.readLine()) != null) {
                 words.add(text);
             }
             System.out.println("Loaded " + words.size());

             Collections.sort(words);

             Random rnd = new Random();
             Font font = getFont();
             Graphics2D g2d = img.createGraphics();
             g2d.setColor(Color.WHITE);
             g2d.fillRect(0, 0, 1200, 650);

             List<Rectangle2D> used = new ArrayList<>(25);
             for (String word : words) {
                 int size = rnd.nextInt(37) + 11;
                 
                 Font drawFont = font.deriveFont((float) size);
                 
                 FontMetrics fm = g2d.getFontMetrics(drawFont);
                 Rectangle2D bounds = fm.getStringBounds(word, g2d);
                 System.out.println("Positing " + word);
                 do {
                     int xPos = rnd.nextInt(1200 - (int)bounds.getWidth());
                     int yPos = rnd.nextInt(650 - (int)bounds.getHeight());

                     bounds.setFrame(xPos, yPos, bounds.getWidth(), bounds.getHeight());
                 } while (collision(used, bounds));
                 used.add(bounds);
                 g2d.setFont(drawFont);
                 g2d.setColor(Color.BLACK);
                 g2d.drawString(word, (float)bounds.getX(), (float)bounds.getY() + fm.getAscent());
                 g2d.setColor(Color.RED);
                 g2d.draw(bounds);
             }
             g2d.dispose();
         } catch (IOException exp) {
             exp.printStackTrace();
         }
     }


     @Override
     public Dimension getPreferredSize() {
         return new Dimension(1200, 650);
     }

     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
         g2d.drawImage(img, 0, 0, this);
         g2d.dispose();
     }

     protected boolean collision(List<Rectangle2D> used, Rectangle2D bounds) {
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
