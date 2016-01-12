package ie.gmit.sw.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

//GUI Program - ReadFile
public class ReadFile extends Reader {

	private static Scanner console = new Scanner(System.in);
	
	private String stopWordFileName = "stopwords.txt";
	//private String stopWordFileName;
	private static String outStopwords;
	
	private static BufferedReader brStopWord = null;
	private static FileReader frStopWord = null;
	
	private static String[] stopword = null;
	private static String strStopWord;
	
	private static String[] lines = null;
	private static String guiTextOutWords;
	
	private String wordText;
	
	public ReadFile()
	{
		
	}
	public ReadFile(String stopWordFileName) {
		
		setStopWordsFileName(stopWordFileName);
	}
	
	public String getWordTextFromGUI() {
		return wordText;
	}

	public void setWordTextFromGUI(String wordText) {
		this.wordText = wordText;
	}


	void setStopWordsFileName(String filename) {
		this.stopWordFileName = filename;
	}

	String getStopFileName() {
	
		return stopWordFileName;
	}
	
	
public ArrayList<String> readTextFromGUI(String text) throws IOException {
		
		ArrayList<String> guiTextWords = new ArrayList<String>();
		
		text = this.getWordTextFromGUI();
		lines = text.split(" ");
		splitWords(lines, guiTextWords,guiTextOutWords );
			
		return guiTextWords;
	}
	public void splitWords(String[] str, ArrayList<String> list, String outWord)
	{
		for (int i = 0; i < str.length; i++) {
			str[i] = str[i].replaceAll("[^a-zA-Z]", "").toLowerCase().trim();
			str[i].toLowerCase();
			outWord = str[i].toLowerCase().trim();
			list.addAll(Arrays.asList(outWord));
			
		}
		
	}
	
	public ArrayList<String> getStopWords() throws IOException
	{
		ArrayList<String> stopWordsSet = new ArrayList<String>();
		
		try {
			frStopWord = new FileReader(getStopFileName());
			brStopWord = new BufferedReader(frStopWord);
			
			while ((strStopWord = brStopWord.readLine()) != null) {
				stopword = strStopWord.split(" ");
				
				splitWords(stopword, stopWordsSet,outStopwords );
				
			}
		}catch (FileNotFoundException e) {
			 System.out.println("Files not found.");
		}catch (IOException e) {
			System.out.println("Problem with reading files.");
		} 
		brStopWord.close();
		frStopWord.close();
		
		return stopWordsSet;
		
	}
	
	public void createrandomFont(JLabel label, JPanel panel) {
		int a = (int) (Math.random() * 255 - 0);
		int b = (int) (Math.random() * 255 - 0);
		int c = (int) (Math.random() * 255 - 0);
		label.setForeground(new java.awt.Color(a, b, c));
		panel.add(label);
		
	}
	
	

	//getting file names using scanner input
	public void scannerInput() {
		
		String workingDir = System.getProperty("user.dir");		
		File workingDirFile = new File(workingDir); 
	
		int i = 0;
	    while (i == 0) {
			
			System.out.println("Please, Enter Your Stop Words File Name...");
			stopWordFileName = console.nextLine();
			File testfile2 = new File(workingDirFile, stopWordFileName);
			
	        if(testfile2.exists() ){
	            //System.out.println("You entered right File Names...");
	            setStopWordsFileName(stopWordFileName);
	            
	            i = 1;
	        } else
	            System.out.println("You entered the wrong File Names... \nPlease try again...");
	    }
		 
	}

}//class

