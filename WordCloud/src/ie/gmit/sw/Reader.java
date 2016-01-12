package ie.gmit.sw;

import java.util.ArrayList;

public abstract class Reader implements FileReaderInterface{


	public Reader() {
		super();

	}

	abstract void setDictionaryFileName(String filename);
	abstract String getDictionaryFileName();
	
	abstract void setStopWordsFileName(String filename);
	abstract String getStopFileName();
	
	public abstract void splitWords(String[] str, ArrayList<String> list, String outW);


	
}
