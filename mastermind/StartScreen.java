
/*=================================================================
Codebreaker
Cathleen Yan and Irene He
November 2020
Java, 1.8
=================================================================
Problem Definition 	
	Required to design a game called codebreaker with file handling and GUI.
	There are 4 possible gamemodes (player vs player, player vs AI, AI vs player easy, and AI vs player hard)
	For GUI there is also a startscreen, endscreen, and helpscreen.
	File handling reads a text file with all scores from previous games and displays the 
	top scores and usernames in the endscreen.
Input 
	Depending on the gamemode, the inputs can be the player's name(s), the black and white pegs for a guess, 
	or a guess of 4 colours.
Output (All displayed with graphics)
	- Displays the gameboard with the guesses on the right and black/white pegs on the left
	- At the end of the game, it displays the winner and a results screen
	- At the bottom of the gameboard, there are instructions displayed and a textbox for user input
Process 
	- In StartScreen, players can choose their gamemode or the help option 
	- In easy mode and hardmode, the player thinks of a code and the AI tries to guess it. 
	  The AI outputs it guess and the player enters the black and white pegs for that guess.
	  This repeats until 10 rounds are reached or the code is guessed. Easy mode randomly generates guesses.
	  While hardmode uses Knuth's algorithm to guess correctly within 6 tries.
	- In player vs AI, the player tries to guess a randomly generated code. The player 
	  inputs guesses, while the AI returns the black and white pegs until 10 rounds or the player 
	  guesses correctly
	- In player vs player, 2 local players can verse each other. The first player enters their code,
	  and the second player tries to guess it. The black and white pegs are returned.
=================================================================
 */

package mastermind; //creates mastermind package for sharing of methods in different classes
import java.awt.*; //imports GUI functions
import java.awt.event.*; //imports GUI functions
import javax.swing.*; //imports Java Swing

public class StartScreen extends JFrame{ 
	/*
	 * This class creates a starting screen that extends the JFrame class, where the user can choose their gamemode.
	 */
	
	JLabel name; //Declare JLabel called 'name' that will store the game's name
	JButton help, btnExit, hardmode, easymode, multiplayer, guess; //Declares JButtons used on the main menu screen
	JPanel mainPanel; //Declares the main panel used in the start screen
	
	/**LoginForm constructor: 
	 * This constructor creates a start screen frame that the user
	 * can choose their gamemode in. They can also exit or go to a help screen
	 * List of Local Variables: 
	 * - 'mainPanel' is is a panel that holds the components (type JPanel) 
	 * - 'hardmode' is a JButton object that the user can click to enter hardmode (type JButton) 
	 * - 'easymode' is a JButton object that the user can click to enter easymode (type JButton) 
	 * - 'multiplayer' is a JButton object that the user can click to enter multiplayer mode (type JButton) 
	 * - 'guess' is a JButton object that the user can click to enter singleplayer guessing mode (type JButton) 
	 * - 'btnExit' is a JButton object that the user can click to close the window (type JButton) 
	 * - 'help' is a JButton object that the user can click to enter the help screen (type JButton) 
	 * 
	 * @param none
	 * 
	 */
	StartScreen(){
		//create frame and set it's properties (layout, colour, etc.)
		super("Start");
		setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);

		//Create a game name label to display "Mastermind" and set it's properties
		name = new JLabel("MASTERMIND");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Monospaced", Font.BOLD, 70));
		name.setAlignmentX(CENTER_ALIGNMENT);

		//hardmode button
		hardmode = new JButton("AI HARDMODE");
		style2(hardmode);
		hardmode.addActionListener(new ActionListener() { 


			/**actionPerformed method: 
			 * This procedural method launches the hardmode game once
			 * the hardmode button is clicked by the user. If the action event (clicking hardmode
			 * button) occurs, the start screen frame will
			 * disappear, while the game frame appears as it creates a new instance of the ai_vs_person class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				ai_vs_person.main(null);
			}
		});
		hardmode.setPreferredSize(new Dimension(200,70));

		//multiplayer button
		multiplayer = new JButton("MULTIPLAYER");
		style2(multiplayer);
		multiplayer.addActionListener(new ActionListener() {

			/**actionPerformed method: 
			 * This procedural method launches the multiplayer guessing mode once
			 * the multiplayer button is clicked by the user. If the action event (clicking multiplayer
			 * button) occurs, the start screen frame will
			 * disappear, while the game frame appears as it creates a new instance of the player_vs_player class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				player_vs_player.main(null);

			}
		});
		multiplayer.setPreferredSize(new Dimension(200,70));

		//easymode button
		easymode = new JButton("AI EASYMODE");
		style2(easymode);
		easymode.addActionListener(new ActionListener() {


			/**actionPerformed method: 
			 * This procedural method launches the easy mode game once
			 * the easymode button is clicked by the user. If the action event (clicking easymode
			 * button) occurs, the start screen frame will
			 * disappear, while the game frame appears as it creates a new instance of the ai_vs_person_easy_mode class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				ai_vs_person_easy_mode.main(null);
			}
		});
		easymode.setPreferredSize(new Dimension(200,70));

		//guess button
		guess = new JButton("SINGLEPLAYER");
		style2(guess);
		guess.addActionListener(new ActionListener() {
			
			/**actionPerformed method: 
			 * This procedural method launches the singleplayer guessing mode once
			 * the singleplayer button is clicked by the user. If the action event (clicking singleplayer
			 * button) occurs, the start screen frame will
			 * disappear, while the game frame appears as it creates a new instance of the player_vs_ai class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				player_vs_ai.main(null);
			}
		});
		guess.setPreferredSize(new Dimension(200,70));

		//help button
		help = new JButton("Help");
		style(help);
		help.addActionListener(new ActionListener() {

			/**actionPerformed method: 
			 * This procedural method launches the help screen once
			 * the help button is clicked by the user. If the action event (clicking help
			 * button) occurs, the start screen frame will
			 * disappear, while the help frame appears as it creates a new instance of the helpScreen class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new helpScreen();

			}
		});
		help.setPreferredSize(new Dimension(100,30));

		//exit button
		btnExit = new JButton("Exit");
		style(btnExit);
		btnExit.addActionListener(e -> System.exit(0)); //If this button is clicked, the frame is closed
		btnExit.setPreferredSize(new Dimension(100,30));


		//add components and create a visually appealing start screen GUI
		add(Box.createRigidArea(new Dimension(700,50)));
		add(name);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(guess);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(easymode);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(hardmode);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(multiplayer);
		add(Box.createRigidArea(new Dimension(700,50)));
		add(help);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(btnExit);
		add(Box.createRigidArea(new Dimension(700,50)));

		//set frame size and let it be visible to the player
		setSize(900, 700);
		setVisible(true);
	}

	/**style method:
	 * This procedural method accepts a JButton and then stylizes it.
	 * It changes the colours, font, size, and alignment of the button.
	 * 
	 * List of Variables: 
	 * - 'x' is the button that will be altered (type JButton)
	 * 
	 * @param JButton x
	 * @return void
	 * 
	 */
	public static void style(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 20));
		x.setBackground(new Color(128,128,128));
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
		x.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	/**style2 method:
	 * This procedural method accepts a JButton and then stylizes it. This method gives
	 * a different style than the "style" method. 
	 * It changes the colours, font, size, and alignment of the button.
	 * 
	 * List of Variables: 
	 * - 'x' is the button that will be altered (type JButton)
	 * 
	 * @param JButton x
	 * @return void
	 * 
	 */
	public static void style2(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 35));
		x.setBackground(Color.BLACK);
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
		x.setAlignmentX(CENTER_ALIGNMENT);
	}

	/**main method: 
	 * This procedural method is called automatically and is used to
	 * organize the calling of other methods defined in the class. Creates a new
	 * object of type StartScreen called 'start'.
	 * 
	 * List of Local Variables 
	 * - 'start' is a new instance of the StartScreen constructor (type StartScreen)
	 * 
	 * @param args <type String>
	 * @return void
	 */
	public static void main(String[]args) {
		StartScreen start = new StartScreen();
	} 
} //end class

