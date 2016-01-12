package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Console Program
public interface FileReaderInterface {

	abstract ArrayList<String> getDictionaryWords() throws IOException;

	abstract ArrayList<String> getStopWords() throws IOException;
	
	abstract ArrayList<String> readTextFromGUI(String text) throws IOException;
	
	abstract void scannerInput();
	
	
}