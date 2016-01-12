package ie.gmit.sw;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FontStyles {

	public FontStyles() {
		super();
		
	}
	
	public void createrandomFont(JLabel label, JPanel panel) {
		int a = (int) (Math.random() * 255 - 0);
		int b = (int) (Math.random() * 255 - 0);
		int c = (int) (Math.random() * 255 - 0);
		label.setForeground(new java.awt.Color(a, b, c));
		panel.add(label);

	}
	
}
