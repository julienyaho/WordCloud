package ie.gmit.sw;

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

//Console Program - ReadFile
public class ReadFile extends Reader {

	private static Scanner console = new Scanner(System.in);
	
	private static String dictionaryFileName;
	private static String stopWordFileName;
	
	private static BufferedReader in = null;
	private static FileReader fr = null;
	private static String[] words = null;
	
	private static String str;
	private static String outWords;
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
	public ReadFile(String dictionaryFileName, String stopWordFileName) {
		
		setDictionaryFileName(dictionaryFileName);
		setStopWordsFileName(stopWordFileName);
	}
	
	public String getWordText() {
		return wordText;
	}

	public void setWordText(String wordText) {
		this.wordText = wordText;
	}

	void setDictionaryFileName(String filename) {
		ReadFile.dictionaryFileName = filename;
	}

	String getDictionaryFileName() {
	
		return dictionaryFileName;
	}

	void setStopWordsFileName(String filename) {
		ReadFile.stopWordFileName = filename;
	}

	String getStopFileName() {
	
		return stopWordFileName;
	}
	
	
	public ArrayList<String> getDictionaryWords() throws IOException
	{
		ArrayList<String> DictionaryList = new ArrayList<String>();
		
		try {
			
			fr = new FileReader("crisis.txt");
			in = new BufferedReader(fr);
			
			while ((str = in.readLine()) != null) {
				words = str.split(" ");
				
				splitWords(words, DictionaryList,outWords );
			}
						
		} catch (FileNotFoundException e) {
			 System.out.println("Files not found.");
		} catch (IOException e) {
			System.out.println("Problem with reading files.");
		} 
		in.close();
		fr.close();
		
		return DictionaryList;
		
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
			frStopWord = new FileReader("stopwords.txt");
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
	
	public ArrayList<String> readTextFromGUI(String text) throws IOException {
		
		ArrayList<String> guiTextWords = new ArrayList<String>();
		
		text = this.getWordText();
		lines = text.split(" ");
		splitWords(lines, guiTextWords,guiTextOutWords );
			
		return guiTextWords;
	}

	//getting file names using scanner input
	public void scannerInput() {
		
		String workingDir = System.getProperty("user.dir");		
		File workingDirFile = new File(workingDir); 
	
		int i = 0;
	    while (i == 0) {
	    	System.out.println("Please, Enter Your Word Dictionary File Name...");
			dictionaryFileName = console.nextLine();
			File testfile = new File(workingDirFile, dictionaryFileName);
			
			System.out.println("Please, Enter Your Stop Words File Name...");
			stopWordFileName = console.nextLine();
			File testfile2 = new File(workingDirFile, stopWordFileName);
			
	        if(testfile.exists() && testfile2.exists() ){
	            System.out.println("You entered right File Names...");
	            setDictionaryFileName(dictionaryFileName);
	            setStopWordsFileName(stopWordFileName);
	            
	            System.out.println(getDictionaryFileName());
				System.out.println(getStopFileName());
	            i = 1;
	        } else
	            System.out.println("You entered the wrong File Names... \nPlease try again...");
	    }
	    		 
	}
	
	public static void main(String[] args) {
		
		//ReadFile me = new ReadFile();
		//me.scannerInput() ;
	}


}//class

