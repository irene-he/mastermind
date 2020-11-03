/*
 * This class creates a help screen that tells the user how to play codebreaker
 */

package mastermind; //creates mastermind package for sharing of methods in different classes
import java.awt.*; //imports GUI functions
import java.awt.event.*; //imports GUI functions
import javax.swing.*; //imports Java Swing

public class helpScreen extends JFrame{
	JPanel mainPanel, panel; //Declares the main panel used in the help screen and panel within main panel
	JLabel text; //Declares a JLabel called text that will display the title of the page
	JTextArea instructions; //Declares a JTextArea called instructions that displays the instruction text for codebreaker
	JButton back; //Declares a JButton called 'back' that allows the user to go back to the main screen
	
	
	/**helpScreen constructor: 
	 * This constructor creates a help screen frame that tells
	 * the user how to play codebreaker. They can also go back to the main screen.
	 * List of Variables: 
	 * - 'mainPanel' is is a panel that holds the components (type JPanel) 
	 * - 'back' is a JButton object that the user can click to go back to the main screen (type JButton) 
	 * - 'text' is a JLabel object that holds the title text (type JLabel) 
	 * - 'instructions' is a JTextArea that holds the instructions on codebreaker
	 * 
	 * @param none
	 * 
	 */
	helpScreen(){
		//create frame
		super("Help");
		setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);

		
		//Instructions text
		text = new JLabel("HOW TO PLAY MASTERMIND");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Monospaced",Font.BOLD, 30));
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		//instructions text
		instructions = new JTextArea("Mastermind is a code-breaking game for two parties. The codemaker creates a code of 4 colours from 6 possible colours. The codebreaker tries to guess that code within 10 tries. Key pegs, white and black, are given for each guess. White means that the guess has a correct colour in the wrong placement. Black means the correct colour is in the correct spot. The codebreaker must try to break the code.");
		instructions.setForeground(Color.WHITE);
		instructions.setBackground(Color.BLACK);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setFont(new Font("Monospaced",Font.PLAIN, 20));

		
		//back button
		back = new JButton("Back");
		StartScreen.style(back);
		back.addActionListener(new ActionListener() {
			
			/**actionPerformed method: 
			 * This procedural method launches the main screen once
			 * the back button is clicked by the user. If the action event (clicking back
			 * button) occurs, the help frame will
			 * disappear, while the main frame appears as it creates a new instance of the StartScreen class. 
			 *
			 * @param e - instance of the ActionEvent class that listens for the button click
			 * @return void
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new StartScreen();
			}
		});
		back.setPreferredSize(new Dimension(100,30));
		
		//create a panel called 'panel' to hold instructions text
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.BLACK);
		panel.add(Box.createRigidArea(new Dimension(50,10)));
		panel.add(instructions);
		panel.add(Box.createRigidArea(new Dimension(50,10)));
		
		//add components to frame
		add(Box.createRigidArea(new Dimension(700,50)));
		add(text);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(panel);
		add(back);
		add(Box.createRigidArea(new Dimension(700,50)));
		
		//display frame and set frame size
		setSize(700, 500);
		setVisible(true);
	}
	
	/**main method: 
	 * This procedural method is called automatically and is used to
	 * organize the calling of other methods defined in the class. Creates a new
	 * object of type helpScreen called 'help'.
	 * 
	 * List of Local Variables 
	 * - 'help' is a new instance of the helpScreen constructor (type helpScreen)
	 * 
	 * @param args <type String>
	 * @return void
	 */
	public static void main(String[]args) {
		helpScreen help = new helpScreen();
	}
}