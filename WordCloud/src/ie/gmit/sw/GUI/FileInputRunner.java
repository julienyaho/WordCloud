package ie.gmit.sw.GUI;

import java.io.File;

import javax.swing.JOptionPane;

public class FileInputRunner {

	private static String stopWordFileName;
	private static String length,lengthStr;
	
	public static void main(String[] args) {
		
		Reader reader = new FileReaderImp();
		
		String workingDir = System.getProperty("user.dir");		
		File workingDirFile = new File(workingDir); 
		
		//===============================
		int i = 0;
	    while (i == 0) {
	    	lengthStr = JOptionPane.showInputDialog("Enter Stop Words File Name: "); //Line 3
	    	stopWordFileName = lengthStr;
			File testfile = new File(workingDirFile, stopWordFileName);
			
			
	        if(testfile.exists() && !lengthStr.isEmpty() || lengthStr == null){
	            System.out.println("You entered right File Names...");
	            reader.setStopWordsFileName(stopWordFileName);
	            ReadFileGUIDesign.createFrame();
	            //JOptionPane.showMessageDialog(null, reader.getStopFileName(),"Rectangle",JOptionPane.INFORMATION_MESSAGE); //Line 10
				//System.exit(0);
				
				//TestRunnerGUI gui = new TestRunnerGUI();
				
				
	            i = 1;
	        } else{
	        	
	        	 	JOptionPane.showMessageDialog(null, "Values dont match","Rectangle",JOptionPane.INFORMATION_MESSAGE); 
	        	 	//System.exit(0);
	        }
	        	
	    }
		//===============================
		
		
		
		
		
		
		
				
	
	}
}
