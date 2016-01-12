package ie.gmit.sw.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * This class is responsible for designing the GUI frame and also extracting duplicate and
 * unique values from the user text and text file. Duplicate words and unique words are then added to separate
 * arrayLists. Only unique words are added to the cloud word tags to display the most occuring words.
 * We also check for the occurrence/ frequency of unique words and add them to the map. 
 * 
 */
//GUI Program - ReadFileGUIDesign class
public class ReadFileGUIDesign extends JFrame {

	static FileReaderImp readfileImpl = new FileReaderImp();

	private static ArrayList<String> DictionaryList;
	private static ArrayList<String> stopWordsSet;
	private static String[] uniqueWords;

	private static ArrayList<String> duplicatevalues = new ArrayList<String>();
	private static ArrayList<String> uniquevalues = new ArrayList<String>();
	private static Map<String, Integer> OccurrenceMap = new HashMap<>();

	private static final long serialVersionUID = 333L;
	private static JTextArea textArea, filenameTextArea;
	private static SearchButtonHandler searchHandler;

	public static void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Word Cloud");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);
				panel.setBackground(Color.green);

				textArea = new JTextArea(25, 70);
				textArea.setWrapStyleWord(true);
				textArea.setEditable(true);
				textArea.setFont(Font.getFont(Font.SANS_SERIF));

				JScrollPane scroller = new JScrollPane(textArea);
				scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

				filenameTextArea = new JTextArea(10, 10);
				filenameTextArea.setWrapStyleWord(true);
				filenameTextArea.setEditable(true);
				filenameTextArea.setFont(Font.getFont(Font.SANS_SERIF));

				JPanel inputpanel = new JPanel();
				inputpanel.setLayout(new FlowLayout());
				inputpanel.setBackground(Color.green);

				JButton button = new JButton("Generate Word Cloud");
				searchHandler = new SearchButtonHandler();
				button.addActionListener(searchHandler);
				button.setForeground(Color.blue);

				DefaultCaret caret = (DefaultCaret) textArea.getCaret();
				caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				panel.add(scroller);

				inputpanel.add(button);
				panel.add(inputpanel);
				frame.getContentPane().add(BorderLayout.CENTER, panel);
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				frame.setResizable(false);

			}
		});
	}

	// Nested class that binds the Generate Cloud Word button
	private static class SearchButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
		        if((textArea.getText().trim().length() == 0)){
		        	
		        	JOptionPane.showMessageDialog(null, "Please enter text","Rectangle",JOptionPane.INFORMATION_MESSAGE);   	
		           
		        } else{
		        	 
		        	readfileImpl.setWordTextFromGUI(textArea.getText());

					try {
						createUI();
					} catch (IOException e1) {

					System.out.println("Program encontered an error...Please re-run");
				}
		           
		        }
		    }			
	}

	public static void createUI() throws IOException {

		//reading string content from GUI and splitting the words and adding to new Dictionary arrayList
		DictionaryList = readfileImpl.readTextFromGUI(readfileImpl.getWordTextFromGUI());
		//populating the stop words arrayList with contents from text file
		stopWordsSet = readfileImpl.getStopWords();

		//looping over the Dictionary arrayList and checking against the stopwords arrayList to see if 
		//there any common words and adding those words to the duplicate arrayList - otherwise add them
		//as unique values to the uniqueuniqueWordsalues arrayList
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
		uniqueWords = new String[uniquevalues.size()];
		uniqueWords = uniquevalues.toArray(uniqueWords);

		// checking the occurence of words in the map
		Set<Map.Entry<String, Integer>> entrySet = OccurrenceMap.entrySet();
		System.out.println("Words" + "\t\t" + "# of Occurances");
		for (Map.Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "\t\t" + entry.getValue());
		}
		
		//create a GUI frame
		JFrame frame = new JFrame("Word Cloud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		Cloud cloud = new Cloud();
		Random random = new Random();

		for (String s : uniqueWords) {
			// System.out.println(s);
			for (int i = random.nextInt(50); i > 0; i--) {
				cloud.addTag(s);
			}
		}

		System.out.println("==================");
		System.out.println("Duplicates..." + duplicatevalues.size());
		System.out.println("Uniques..." + uniqueWords.length);

		System.out.println("Original Dict Words..." + DictionaryList.size());
		System.out.println("Original Stop Words..." + stopWordsSet.size());
		System.out.println("===================");

		for (Tag tag : cloud.tags()) {
			final JLabel label = new JLabel(tag.getName());
			label.setOpaque(false);
			label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 20));

			// create random font for each word tag
			readfileImpl.createrandomFont(label, panel);
		}
		frame.add(panel);
		frame.setSize(800, 600);
		frame.setVisible(true);

	}

}