import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class helpScreen {
	
	helpScreen(){
		//create frame
		JFrame helpFrame = new JFrame("Help Screen");
		Container c =helpFrame.getContentPane();
		c.setBackground(Color.BLACK);
		//helpFrame.setLayout(new BoxLayout(helpFrame, BoxLayout.PAGE_AXIS));
		
		//Instructions text
		JLabel text = new JLabel("<html>HOW TO PLAY MASTERMIND</html>", SwingConstants.CENTER);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Monospaced",Font.BOLD, 20));
		text.setBounds(0,0,700,200);
		
		JLabel instructions = new JLabel("<html>Mastermind is a code-breaking game for two parties. <BR>The codemaker creates a code of 4 colours from 6 possible colours. <BR>The codebreaker tries to guess that code within 10 tries.<BR>Key pegs, white and black, are given for each guess. <BR>White means that the guess has a correct colour in the wrong placement.<BR>Black means the correct colour is in the correct spot. <BR>The coodebreaker must try to break the code. </html>");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Monospaced",Font.BOLD, 12));
		text.setBounds(100,200,700,400);
		
		//exit button
		JButton btnExit = new JButton("Exit");
		StartScreen.style(btnExit);
		btnExit.addActionListener(e -> System.exit(0));
		btnExit.setBounds(450, 400, 100, 30);
		
		//back button
		JButton back = new JButton("Back");
		StartScreen.style(back);
		back.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				helpFrame.setVisible(false);
				helpFrame.dispose();
				new StartScreen();
			}
		});
		back.setBounds(150,400,100,30);
		
		/*JPanels
		JPanel topPanel = new JPanel();
		topPanel.add(text);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(back);
		bottomPanel.add(btnExit);
		*/
		
		//add components
		//helpFrame.add(topPanel);
		//helpFrame.add(bottomPanel);
		helpFrame.add(back);
		helpFrame.add(text);
		helpFrame.add(btnExit);
		//helpFrame.add(instructions);
		
		helpFrame.setLayout(null);
		helpFrame.setSize(700, 500);
		helpFrame.setVisible(true);
	}
	
	public static void main(String[]args) {
		helpScreen help = new helpScreen();
	}
}
