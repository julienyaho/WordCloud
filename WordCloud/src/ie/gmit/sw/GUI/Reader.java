package ie.gmit.sw.GUI;

import java.util.ArrayList;

//GUI Program - ReadFile
public abstract class Reader implements FileReaderInterface{


	public Reader() {
		super();

	}

	abstract void setStopWordsFileName(String filename);
	abstract String getStopFileName();
	
	public abstract void splitWords(String[] str, ArrayList<String> list, String outW);


	
}
