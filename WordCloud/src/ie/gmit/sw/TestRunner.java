package ie.gmit.sw;

import java.awt.EventQueue;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;


public class TestRunner {

	private static ArrayList<String> DictionaryList;
	private static ArrayList<String> stopWordsSet;
	private static String[] words;

	private static ArrayList<String> duplicatevalues = new ArrayList<String>();
	private static ArrayList<String> uniquevalues = new ArrayList<String>();
	private static Map<String, Integer> OccurrenceMap = new HashMap<>();
	public static ArrayList<String> temp = new ArrayList<String>(25);
	public static String mywords;

	private static Reader rf = new ReadFile();
	FontStyles styles = new FontStyles();
	
	public ArrayList<String> myWords()
	{
		temp.add(mywords);
		return temp;
		
	}
	

	@SuppressWarnings("null")
	public void createUI() throws IOException {

		DictionaryList = rf.getDictionaryWords();
		stopWordsSet = rf.getStopWords();

		for (String item : DictionaryList) {
			if (stopWordsSet.contains(item)) {
				duplicatevalues.add(item);
			} else {
				uniquevalues.add(item);
			}
		}
		for (String item : uniquevalues) {

			// Looping over all the unique items in the list and checking to see
			// if the OccurrenceMap has a value
			// mapped to the key - if not, add to the map the value - else check
			// the map and increment the value
			// then add it back to the map
			if (item.length() > 0) {
				if (OccurrenceMap.get(item) == null) {
					OccurrenceMap.put(item, 1);
				} else {
					int value = OccurrenceMap.get(item).intValue();
					value++;
					OccurrenceMap.put(item, value);
				}
			}

		}

		// creating an array of words from a uniquevalues arratList to be added
		// to cloud tags
		words = new String[uniquevalues.size()];
		words = uniquevalues.toArray(words);

		//

		Set<Map.Entry<String, Integer>> entrySet = OccurrenceMap.entrySet();

		System.out.println("Words" + "\t\t" + "# of Occurances");

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entrySet);

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		int counter = 0;
		for (Map.Entry<String, Integer> entry : list) {

			counter++;
			mywords = entry.getKey();
			//temp.add(mywords);
			 myWords();
			
			if (counter == 75) {
				break;
			}
			//System.out.println(entry.getKey() + "\t\t" + entry.getValue());
			//System.out.println(entry.getKey() );

			int fontSize = ComputeFontSize.getFontSize(entry.getValue());
			//System.out.println(fontSize);
			// PaintThing(fontSize,word);
			
			
			System.out.println(mywords);
			SimpleWordCloud.getFontsize(fontSize);
			
		}
		
		SimpleWordCloud.Write();
		//SimpleWordCloud.dispose();

		/*
		 * JFrame frame = new JFrame("Word Cloud");
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); JPanel panel =
		 * new JPanel(); Cloud cloud = new Cloud(); Random random = new
		 * Random();
		 * 
		 * for (String s : words) { //System.out.println(s); for (int i =
		 * random.nextInt(50); i > 0; i--) { cloud.addTag(s); } }
		 */

		/*
		 * for (Tag tag : cloud.tags()) { final JLabel label = new
		 * JLabel(tag.getName()); label.setOpaque(false);
		 * label.setFont(label.getFont().deriveFont((float) tag.getWeight() *
		 * 20));
		 * 
		 * // create random font styles.createrandomFont(label, panel); }
		 * frame.add(panel); frame.setSize(800, 600); frame.setVisible(true);
		 */
	}
	 public TestRunner() {
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                    ex.printStackTrace();
	                }

	                JFrame frame = new JFrame("Testing");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	              
	                frame.add(new SimpleWordCloud());
					
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });

	    }


	public static void main(String[] args) throws IOException {
		new TestRunner();
		TestRunner runner = new TestRunner();
		runner.createUI();
		
		       
		    

		   
	}

}
