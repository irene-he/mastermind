package mastermind;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class player_vs_player{
	static int count; //Declares an int called count that stores the round number
	static String name1, name2, secretCode, guess, codeColours ="RBGPOY"; //Declares the strings used within the game

	/**main method: 
	 * This procedural method is called automatically and is used to
	 * organize the calling of other methods defined in the class. This method creates a JFrame 
	 * and called the getNames method to begin the game.
	 * 
	 * List of Local Variables 
	 * - 'main' is JFrame that holds the components of the game 
	 * 
	 * @param args <type String>
	 * @return void
	 */
	public static void main(String[]args) {
		JFrame main = new JFrame("Multiplayer");
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(),BoxLayout.Y_AXIS));
		getNames(main);
	}

	/**main method: 
	 * This procedural method is used to begin the player vs. player game after getting the secretCode
	 * and the names of the players. It has a top panel which displays the game board (guesses, black and white pegs, round) 
	 * and a bottom panel which accepts user input and displays the instructions.
	 * 
	 * List of Local Variables 
	 * - 'main' is JFrame that holds the components of the game 
	 * - 'top' is the top JPanel that holds the game board
	 * - 'bottom' is the bottom JPanel that allows user input
	 * - 'titles1' is the JLabel for the Guesses
	 * - 'titles2' is the JLabel for the black and white pegs
	 * - 'instructions' is the JLabel that gives the player instructions
	 * - 'enterCode' is the JButton to enter the input from the JTextField
	 * - 'codeTextField'is the JTextField that allows the user to input the guesses 
	 * 
	 * @param JFrame main
	 * @return void
	 */
	public static void playerVsPlayer(JFrame main) {
		//resets data
		main.getContentPane().removeAll();
		count=1;
		
		//Top panel and it's properties
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(0, 2));
		top.setPreferredSize(new Dimension(900,500));
		top.setBackground(Color.BLACK);

		//Bottom panel and it's properties
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(4,1));
		bottom.setBackground(Color.GRAY);

		//Title for the guesses column 
		JLabel titles1 = new JLabel(name2+"'s Guesses");
		EndScreen.styleJLabel(titles1);
		titles1.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles1);
		
		//Title for the black and white pegs column
		JLabel titles2 = new JLabel("Black and White Pegs");
		EndScreen.styleJLabel(titles2);
		titles2.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles2);

		//Instructions label 
		JLabel instructions = new JLabel(name2+", guess the 4 colour code [COLOURS: R, G, B, Y, O, P]");	
		EndScreen.styleJLabel(instructions);
		
		//Button to enter user input
		JButton enterCode = new JButton("ENTER");
		StartScreen.style(enterCode);
		enterCode.setBackground(Color.DARK_GRAY);

		//Text field for user input
		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200,50));
		codeTextField.setHorizontalAlignment(JLabel.CENTER);
		
		//add components to bottom panel
		bottom.add(instructions);
		bottom.add(codeTextField);
		bottom.add(enterCode);

		//add components to main frame
		main.add(top);
		main.add(bottom);
		main.revalidate();
		main.repaint();

		//action listener for 'enter' button
		enterCode.addActionListener(new ActionListener() {
			/**actionPerformed method: 
			 * This procedural method launches repaints the game board with the user inputs once it is clicked and
			 * also increments the round. If the input is invalid it prompts the user to enter it again. If the
			 * rounds are over or someone wins, it calls the results screen.
			 * 
			 * Variables
			 * - newGuess: This is a JLabel that gets added to the top frame with 
			 * the users input
			 * - newBW: This is a JLabel that gets added to the top frame with the black and white pegs
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				if (validCode(codeColours, codeTextField.getText().toString())==true) { //checks if the input is valid
					guess=codeTextField.getText().toString().toUpperCase();
					if(checkCode(guess,secretCode)==true) { //if the guess is correct
						JOptionPane.showMessageDialog(null, name2+" wins!"); //a dialog tells the user they win
						main.setVisible(false); //removes game frame
						main.dispose();
						new EndScreen(name2, count); //creates a new instance of EndScreen
					}
					else { //if the guess is not correct
						//JLabel that displays the round and guess
						JLabel newGuess = new JLabel ("Round "+count+": "+guess);
						EndScreen.styleJLabel(newGuess);
						top.add(newGuess);
						
						//JLabel that displays the black and white pegs
						JLabel newBW = new JLabel (blackAndWhite(guess, secretCode));
						EndScreen.styleJLabel(newBW);
						top.add(newBW);

						count++;
						if(count==11) { //if the round is equal to 11, the game ends
							JOptionPane.showMessageDialog(null, name2+" loses! The code was "+secretCode); //player loses
							main.setVisible(false);//close game frame
							main.dispose();
							new EndScreen(name2, 10); //Creates new instance of EndScreen
						}
						//repaints the frame after the components are updated
						main.revalidate();
						main.repaint();
					}
				}
				else { //if the input is invalid, a dialog prompts the user to enter it again
					JOptionPane.showMessageDialog(null, "Please enter a valid guess.");
				}
			}
		});
	}

	/**enterCode method: 
	 * This procedural method prompts player 1 to enter the secretCode, with the screen hidden from player 2
	 * 
	 * Variables
	 * - 'panel' - this is the main panel that holds all the components
	 * - 'instructions' - this is the JLabel that tells the user what to do
	 * - 'codeTextField' - the is the JTextField that allows the user to enter their code
	 * @param JFrame main - the game frame
	 * @return void
	 * 
	 */
	public static void enterCode (JFrame main){
		//reset frame
		main.getContentPane().removeAll();
		
		//Declare panel that holds the components
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		//instructions label for player
		JLabel instructions = new JLabel(name1+" Please hide the screen from "+name2+" and enter your code");	
		EndScreen.styleJLabel(instructions);
		instructions.setFont(new Font("Monospaced", Font.BOLD, 30));
		
		//Button that submits user input
		JButton enter = new JButton("ENTER");
		StartScreen.style(enter);
		enter.setBackground(Color.DARK_GRAY);

		//Textfield for recieving user input
		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200, 50));
		
		//add components to panel
		panel.add(instructions);
		panel.add(codeTextField);
		panel.add(enter);
		
		//add components to main frame
		main.add(panel);
		main.revalidate();
		main.repaint();
	
		/**actionPerformed method: 
		 * This procedural method is called when the enter button is clicked. It accepts the input from
		 * the JTextField called codeTextField. Then is calls the playerVsPlayer method to begin the game.
		 * 
		 * @param e - instance of the ActionEvent class that listens for the button click
		 * @return void
		 * 
		 */
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validCode(codeColours, codeTextField.getText().toString())==true) { //checks if code is valid
					secretCode=codeTextField.getText().toString(); //sets secretCode to the input
					playerVsPlayer(main); //calls the method to begin the game

				}
				else { //if the code is not valid, the user is prompted to enter it again.
					JOptionPane.showMessageDialog(null, "Please enter a valid code.");
				}
			}
		});

	}

	/**getNames method: 
	 * This procedural displays 2 text fields that allows both players to enter their names. 
	 * 
	 * Variables
	 * - 'namePanelTop' - top JPanel
	 * - 'namePanelBottom' - bottom JPanel
	 * - 'nameInstructionsTop' - JLabel instructions for player 1
	 * - 'nameInstructionsBottom' - JLabel instructions for player 2
	 * - 'enterName' - JButton to submit both inputs
	 * - 'nameTextFieldTop' - JTextField for player 1's name
	 * - 'nameTextFieldBottom' - JTextField for player 2's name
	 * @param JFrame main: game frame
	 * @return void
	 * 
	 */
	public static void getNames(JFrame main) {
		//Declare and instantiate top panel
		JPanel namePanelTop = new JPanel();
		namePanelTop.setBackground(Color.BLACK);
		
		//Declare and instantiate bottom panel
		JPanel namePanelBottom = new JPanel();
		namePanelBottom.setBackground(Color.BLACK);
		
		//Declare and instantiate instructions for top panel
		JLabel nameInstructionsTop = new JLabel("CODEMAKER: Enter your name");	
		EndScreen.styleJLabel(nameInstructionsTop);
		nameInstructionsTop.setFont(new Font("Monospaced", Font.BOLD, 30));
		
		//Declare and instantiate instructions for bottom panel
		JLabel nameInstructionsBottom = new JLabel("CODEBREAKER: Enter your name");	
		EndScreen.styleJLabel(nameInstructionsBottom);
		nameInstructionsBottom.setFont(new Font("Monospaced", Font.BOLD, 30));

		//Declare and instantiate button to submit user input
		JButton enterName = new JButton("ENTER");
		StartScreen.style(enterName);
		enterName.setBackground(Color.DARK_GRAY);
		enterName.setPreferredSize(new Dimension(900,300));
		
		//Declare and instantiate top textfield to receive user input
		JTextField nameTextFieldTop = new JTextField();
		nameTextFieldTop.setPreferredSize(new Dimension(200, 50));
		
		//Declare and instantiate bottom textfield to receive user input
		JTextField nameTextFieldBottom = new JTextField();
		nameTextFieldBottom.setPreferredSize(new Dimension(200, 50));
		
		//add components to top panel
		namePanelTop.add(nameInstructionsTop);
		namePanelTop.add(nameTextFieldTop);
		main.add(namePanelTop);
		
		//add components to bottom panel
		namePanelBottom.add(nameInstructionsBottom);
		namePanelBottom.add(nameTextFieldBottom);
		main.add(namePanelBottom);
		main.add(enterName);
		
		//Show frame to user
		main.setSize(900, 700);
		main.setVisible(true);

		/**actionPerformed method: 
		 * This procedural method is called when the enter button is clicked. It accepts both of the player's names
		 * It then calls the enterCode method to get the secretCode.
		 * 
		 * @param e - instance of the ActionEvent class that listens for the button click
		 * @return void
		 * 
		 */
		enterName.addActionListener(new ActionListener() { //performs method if the button is clicked
			public void actionPerformed(ActionEvent e) { 
				if (nameTextFieldTop.getText().toString().length() > 0 && nameTextFieldBottom.getText().toString().length() > 0) { //Checks if names are valid
					name1=nameTextFieldTop.getText().toString(); //sets name1 as the input from the top textfield
					name2=nameTextFieldBottom.getText().toString(); //sets name2 as the input from the bottom textfield
					enterCode(main); //calls the enterCode method to get the secretCode
				}
				else { //If names are not valid, the user is prompted to enter a valid name
					JOptionPane.showMessageDialog(null, "Please enter a valid name.");
				}
			}
		});

	}

	/**checkCode method: 
	 * This functional method is used check if the guess is correct.	 
	 *
	 * @param String guess, String code
	 * @return Boolean: it returns whether or not the guess is correct
	 */
	public static boolean checkCode(String guess, String code) {
		//compares the colours in code with guess
		for(int i=0;i<4;i++) {
			if(Character.toLowerCase(guess.charAt(i))!=Character.toLowerCase(code.charAt(i))) {
				return false; //if the character does not match, it returns false
			}
		}
		return true;//returns true if all colours match
	}
	/**validCode method: 
	 * This functional method is used check if the code that the user entered is valid.
	 * This means that the code should have 4 characters that are from the 6 letters/colours required. 
	 * 
	 * List of Local Variables 
	 * - 'codeColourList' the 6 possible colours get added to this list (type ArrayList<Character>)
	 *
	 * @param String codeColours: the string contains the 6 colours, String code: the user's input code/guess
	 * @return Boolean: it returns whether or not the code is valid
	 */
	public static boolean validCode(String codeColours, String code) {
		ArrayList<Character> codeColoursList = new ArrayList<Character>(); //Declare and instantiate codeColourList
		
		
		if(code.length()==4) {//checks if the length is 4 characters
			//add 6 colours to codeColoursList
			for(int i=0;i<6;i++) 
				codeColoursList.add(codeColours.charAt(i));
			//checks if the guess matches the colours from codeColoursList
			for(int i=0;i<4;i++) {
				if(!codeColoursList.contains(Character.toUpperCase(code.charAt(i))))//if they don't match, it returns false
					return false;
			}
			return true;
		}
		else //returns false if the code is not 4 characters
			return false;
	}
	/**blackAndWhitePegs method: 
	 * This functional method is used to calculate the number of black and white pegs for the guess.
	 * It checks if the character at index i are equal for both the code and guess for black pegs.
	 * For white pegs, it checks if the colours (that do not have black pegs) in guess are also found 
	 * somewhere in the code. Then it converts these to a string.
	 * 
	 * List of Local Variables 
	 * - 'blackAndWhitePegs' this String stores the string version of the black and white pegs
	 * - 'black' this integer keeps track of the number of black pegs
	 * - 'white' this integer keeps track of the number of white pegs
	 * - 'wrongGuess' this ArrayList<String> stores the colours that do not have black pegs from the guess
	 * - 'wrongCode' this ArrayList<String> stores the colours that do not have black pegs from the code
	 * 
	 * @param String guess: the users guess, String code: the secret code
	 * @return String: it returns the string version of the number of black and white pegs
	 */
	public static String blackAndWhite(String guess, String code) {
		//Declare and instantiate variables
		String blackAndWhitePegs="";
		ArrayList<String> wrongGuess = new ArrayList<String>();
		ArrayList<String> wrongCode = new ArrayList<String>();
		int black=0, white=0;
		
		//clears the arraylists from previous rounds
		wrongGuess.clear();
		wrongCode.clear();

		//Calculate black pegs
		for(int i=0;i<4;i++) {
			if(Character.toLowerCase(guess.charAt(i))==Character.toLowerCase(code.charAt(i))) {
				black++;
			}
			else {
				wrongCode.add(Character.toString(code.charAt(i)).toLowerCase());
				wrongGuess.add(Character.toString(guess.charAt(i)).toLowerCase());
			}
		}

		//Calculate white pegs
		for(int i=0;i<wrongGuess.size();i++) {
			if(wrongCode.contains(wrongGuess.get(i))) {
				white++;
				wrongCode.remove(wrongGuess.get(i));
			}
		}



		//Convert to string
		for(int i=0;i<black;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("B");
		}
		for(int i=0;i<white;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("W");
		}

		return blackAndWhitePegs;
	}

}
