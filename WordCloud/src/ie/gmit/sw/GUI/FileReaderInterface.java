package ie.gmit.sw.GUI;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public interface FileReaderInterface {


	abstract ArrayList<String> getStopWords() throws IOException;
	
	abstract ArrayList<String> readTextFromGUI(String text) throws IOException;
	
	abstract void createrandomFont(JLabel label, JPanel panel);
	
	abstract void scannerInput();
	
	
	
}