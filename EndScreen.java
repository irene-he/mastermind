package mastermind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EndScreen extends JFrame {
	String[] names;
	int[] scores;
	int hiscore;
	int size;
	JPanel mainPanel;
	
	/* ScoreScreen constructor:
	 * This procedural method is the constructor for score screen
	 * displays the previous hiscores by reading from an input file
	 * 
	 * List of Local Variables
	 * freader - filereader for score.txt <type FileReader>
	 * br - BufferedReader used to read from file <type BufferedReader>
	 * fwriter - filewriter for score.txt <type FileWriter>
	 * bo - BufferedWriter used to write to file <type BufferedWriter>
	 * nameTitleLabel - label for name column <type JLabel>
	 * scoreTitelLabel - label for score column <type JLabel>
	 * nameLabel - label for saved name <type JLabel>
	 * scoreLabel - label fore saved score <type JLabel>
	 * tryAgainBtn - button for starting the game again <type JButton>
	 * quitBtn - button for quitting the game <type JButton>
	 *
	 * @param name - the player name <type String>
	 * 		  score - the last game score <type int>
	 * @return void
	 */
	public EndScreen(String name, int score) {
		setSize(900,700);	// set the dimensions of the ScoreScreen JPanel
		setLayout(new GridLayout(12, 2));			// Set the JPanel layout to GridLayout
		setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		
		// initialize base variables
		names = new String[10];
		scores = new int[10];
		size = 0;
		hiscore = 11;
		
		// set the names and scores array to empty values
		for (byte i = 0; i < 10; i++) {
			names[i] = "";
			scores[i] = 11;
		} // end for loop
		
		try {
			FileReader freader = new FileReader("score.txt");	// Read from file score.txt
			BufferedReader br = new BufferedReader(freader);	// create BufferedReader from file
			String s;
			
			if ((s = br.readLine()) != null) {					// Read first line from file
				hiscore = Integer.parseInt(s);					// set hiscore to first line value
			} // end if statement
			
			while ((s = br.readLine()) != null) {				// Continue to read rest of file
			    names[size] = s;								// assign saved name
			    if ((s = br.readLine()) != null) {				// Check if next line is empty
			    	scores[size] = Integer.parseInt(s);			// assign saved score
			    	
			    	if (scores[size] < hiscore) {				// check if saved score is greater than current hiscore
			    		hiscore = scores[size];					// update hiscore (want the largest score as hiscore)
			    	} // end if statement
			    } // end if statement
			    size++; 										// Increase array size counter
			} // end while loop
			
			br.close();											// close the buffered reader
		} catch (NumberFormatException e) {						// Catch and parsing errors
			scores[size] = 11;									// set to empty value
			size++;												// increase size counter
		} catch (IOException e) {								// catch and io errors
			System.out.println("Unable to read from 'score.txt'"); // notify user of io error
		} // end try catch
		
		names[0] = name;										// set the first element of names array to the current player name
		scores[0] = score;										// set the first element of scores array to the last game score
		if (score < hiscore) {									// check if last game score is greater than hiscore
			hiscore = score;									// udpate hiscore
		} // end if statement
		
		JLabel nameTitleLabel = new JLabel("Name");				// create JLabel
		styleJLabel(nameTitleLabel);	// stylize text
		JLabel scoreTitleLabel = new JLabel("Score");			// create JLabel
		styleJLabel(scoreTitleLabel); 	// stylize text
		add(nameTitleLabel);									// add the JLabel to the ScoreScreen JPanel
		add(scoreTitleLabel);									// add the JLabel to the ScoreScreen JPanel
		
		bubbleSorting(names, scores);							// sort the names and scores array
		
		for (byte i = 0; i < 10; i++) {						// read the array from back to front because of bubble sort
			JLabel nameLabel;
			JLabel scoreLabel;
			if (!names[i].equals("") && scores[i] != 11) {		// Check if array element is not empty
				nameLabel = new JLabel(names[i]);				// create JLabel for entry name
				scoreLabel = new JLabel("" + scores[i]);	    // create JLabel for entry score
			} else {											// else create filler labels
				nameLabel = new JLabel("-");					// create filler JLabel 
				scoreLabel = new JLabel("-");					// create filler JLabel
			} // end if else statement
			
			styleJLabel(nameLabel);	// stylize text
			styleJLabel(scoreLabel);  // stylize text
			add(nameLabel);										// add the JLabel to the ScoreScreen JPanel
			add(scoreLabel);									// add the JLabel to the ScoreScreen JPanel
		} // end for loop
		
		JButton tryAgainBtn = new JButton("Try Again!");// Create try again button
		StartScreen.style2(tryAgainBtn);
		JButton quitBtn = new JButton("Quit");					// create quit button
		StartScreen.style2(quitBtn);
		
		tryAgainBtn.addActionListener(new ActionListener() {	// ActionListener for try again button
			/* actionPerformed method:
			 * This procedural method is used when a button is clicked
			 * 
			 * List of Local Variables
			 * None
			 *
			 * @param e - the event for button click <type ActionEvent>
			 * @return void
			 */
			public void actionPerformed(ActionEvent e) {
				new StartScreen(); // Replay the game
			} // end actionPeformed method
		}); // end ActionListener
		
		quitBtn.addActionListener(new ActionListener() {		// ActionListener for quite button
			/* actionPerformed method:
			 * This procedural method is used when a button is clicked
			 * 
			 * List of Local Variables
			 * None
			 *
			 * @param e - the event for button click <type ActionEvent>
			 * @return void
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);									// quit the game
			} // end actionPerformed method
		}); // end ActionListener
		
		try {
			FileWriter fwriter = new FileWriter("score.txt"); // FileWriter for score.txt
			BufferedWriter bo = new BufferedWriter(fwriter);   // Create a BufferedWriter from file
			
			bo.write(Integer.toString(hiscore));			   // write to file
			bo.newLine();									   // go to next line
				
			for (int i = 0; i < 10; i++) {					   // list array names and scores in ascending ordre
				bo.write(names[i]);							   // write entry name to file
				bo.newLine();								   // go to next line
				bo.write(Integer.toString(scores[i]));		   // write entry score to file
				bo.newLine();								   // go to next line
			} // end for loop
			bo.close();										   // close the buffered writer
		} catch (IOException e) {							   // catch any io errors 
			System.out.println("Unable to write to 'score.txt'"); // notify user of error
		} // end try catch statement
		
		add(tryAgainBtn);									   // add try again button to ScoreScreen JPanel
		add(quitBtn);										   // add quit button to ScoreScreen JPanel
	}
	
	/* bubbleSorting method:
	 * This functional method sorts the scores array in ascending order.
	 * Uses the scores array to position names array elements
	 * 
	 * List of Local Variables
	 * tempStr - used to store temporary name value <type String>
	 * tempNum - used to store temporary score value <type int>
	 *
	 * @param names - the names array <type String[]>
	 * 		  num - the scores array <type int[]>
	 * @return int[]
	 */
	public int[] bubbleSorting (String names[], int num[]) {
		String tempStr;
		int tempNum;
        // Bubble sort
		for (byte i = 0; i < 9; i++) {
			for (byte j = 0; j < 9; j++) {		
				if (num[j] > num[j+1]) {
					tempStr = names[j];
					tempNum = num[j];
					num[j] = num[j+1];
					num[j+1] = tempNum;
					names[j] = names[j+1];		// assigns names array based on scores bubble sort
					names[j+1] = tempStr;
				} // end if statement
			} // end for statement
		} // end for statement
		return num;
	} // end bubbleSorting method
	
	public void styleJLabel (JLabel label) {
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Monospaced", Font.BOLD, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
	}
} // end ScoreScreen class