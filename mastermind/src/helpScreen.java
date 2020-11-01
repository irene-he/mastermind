package mastermind.src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class helpScreen extends JFrame{
	JPanel mainPanel;
	helpScreen(){
		//create frame
		super("Help");
		setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);

		
		//Instructions text
		JLabel text = new JLabel("HOW TO PLAY MASTERMIND");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Monospaced",Font.BOLD, 30));
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextArea instructions = new JTextArea("Mastermind is a code-breaking game for two parties. The codemaker creates a code of 4 colours from 6 possible colours. The codebreaker tries to guess that code within 10 tries. Key pegs, white and black, are given for each guess. White means that the guess has a correct colour in the wrong placement. Black means the correct colour is in the correct spot. The codebreaker must try to break the code.");
		instructions.setForeground(Color.WHITE);
		instructions.setBackground(Color.BLACK);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setFont(new Font("Monospaced",Font.PLAIN, 20));

		
		//back button
		JButton back = new JButton("Back");
		StartScreen.style(back);
		back.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new StartScreen();
			}
		});
		back.setPreferredSize(new Dimension(100,30));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.BLACK);
		panel.add(Box.createRigidArea(new Dimension(50,10)));
		panel.add(instructions);
		panel.add(Box.createRigidArea(new Dimension(50,10)));
		
		add(Box.createRigidArea(new Dimension(700,50)));
		add(text);
		add(Box.createRigidArea(new Dimension(700,10)));
		add(panel);
		add(back);
		add(Box.createRigidArea(new Dimension(700,50)));
		
		
		setSize(700, 500);
		setVisible(true);
	}
	
	public static void main(String[]args) {
		helpScreen help = new helpScreen();
	}
}