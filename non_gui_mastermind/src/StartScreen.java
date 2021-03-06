package mastermind.src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame{
	JLabel name;
	JButton help, btnExit, hardmode, easymode, multiplayer, guess;
	JPanel mainPanel;
	StartScreen(){
		//create frame
		super("Start");
		setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);

		//display "Mastermind"
		name = new JLabel("MASTERMIND");
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Monospaced", Font.BOLD, 70));
		name.setAlignmentX(CENTER_ALIGNMENT);

		//hardmode button
		hardmode = new JButton("AI HARDMODE");
		style2(hardmode);
		hardmode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				ai_vs_person.main(null);
				//new Singleplayer();
			}
		});
		hardmode.setPreferredSize(new Dimension(200,70));

		//multiplayer button
		multiplayer = new JButton("MULTIPLAYER");
		style2(multiplayer);
		multiplayer.addActionListener(new ActionListener() {


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

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				ai_vs_person_easy_mode.main(null);
				//new Singleplayer();
			}
		});
		easymode.setPreferredSize(new Dimension(200,70));

		//guess button
		guess = new JButton("SINGLEPLAYER");
		style2(guess);
		guess.addActionListener(new ActionListener() {

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
		btnExit.addActionListener(e -> System.exit(0));
		btnExit.setPreferredSize(new Dimension(100,30));

		//instructions
		JLabel instruction = new JLabel();
		instruction.setText("Please head to your IDE console after");
		instruction.setForeground(Color.WHITE);
		instruction.setFont(new Font("Monospaced", Font.BOLD, 20));
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction2 = new JLabel();
		instruction2.setText("choosing an option to play the game!");
		instruction2.setForeground(Color.WHITE);
		instruction2.setFont(new Font("Monospaced", Font.BOLD, 20));
		instruction2.setAlignmentX(CENTER_ALIGNMENT);



		//add components
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
		add(instruction);
		add(instruction2);

		setSize(900, 700);
		setVisible(true);
	}

	public static void style(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 20));
		x.setBackground(new Color(128,128,128));
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
		x.setAlignmentX(CENTER_ALIGNMENT);
	}

	public static void style2(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 35));
		x.setBackground(Color.BLACK);
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
		x.setAlignmentX(CENTER_ALIGNMENT);
	}

	public static void main(String[]args) {
		StartScreen start = new StartScreen();
	}
}