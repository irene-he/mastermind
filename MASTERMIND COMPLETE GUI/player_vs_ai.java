package mastermind;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class player_vs_ai {
	static int count;
	static String name;
	public static void main(String[] args) {
		//gui for gameboard
		JFrame main = new JFrame("Singleplayer");
		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.BLACK);
		JLabel nameInstructions = new JLabel("Enter your name");	
		EndScreen.styleJLabel(nameInstructions);
		nameInstructions.setFont(new Font("Monospaced", Font.BOLD, 30));
		JButton enterName = new JButton("ENTER");
		enterName.setFont(new Font("Monospaced",Font.BOLD, 20));
		enterName.setBackground(Color.DARK_GRAY);
		enterName.setForeground(Color.WHITE);
		enterName.setFocusPainted(false);

		JTextField nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(200, 50));
		namePanel.add(nameInstructions);
		namePanel.add(nameTextField);
		namePanel.add(enterName);
		main.add(namePanel);
		main.setSize(900, 700);
		main.setVisible(true);

		enterName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameTextField.getText().toString().length() > 0) {
					name=nameTextField.getText().toString();
					main.setVisible(false);
					main.dispose();
					refreshFrame();
				}
					else {
						JOptionPane.showMessageDialog(null, "Please enter a valid name.");
					}
				}
			});

		}

		public static void refreshFrame (){
			String codeColours ="RBGPOY";
			String secretCode = randomCode(codeColours);
			count=1;
			
			JFrame main = new JFrame("Singleplayer");
			main.getContentPane().setLayout(new BoxLayout(main.getContentPane(),BoxLayout.Y_AXIS));

			JPanel top = new JPanel();
			top.setLayout(new GridLayout(0, 2));
			top.setPreferredSize(new Dimension(900,500));
			top.setBackground(Color.BLACK);

			JPanel bottom = new JPanel();
			bottom.setLayout(new GridLayout(4,1));
			bottom.setBackground(Color.GRAY);

			JLabel titles1 = new JLabel("Your Guesses");
			EndScreen.styleJLabel(titles1);
			titles1.setFont(new Font("Monospaced", Font.BOLD, 25));
			top.add(titles1);
			JLabel titles2 = new JLabel("Black and White Pegs");
			EndScreen.styleJLabel(titles2);
			titles2.setFont(new Font("Monospaced", Font.BOLD, 25));
			top.add(titles2);


			JLabel instructions = new JLabel("Guess the 4 colour code [COLOURS: R, G, B, Y, O, P]");	
			EndScreen.styleJLabel(instructions);
			JButton enterCode = new JButton("ENTER");
			enterCode.setFont(new Font("Monospaced",Font.BOLD, 20));
			enterCode.setBackground(Color.DARK_GRAY);
			enterCode.setForeground(Color.WHITE);
			enterCode.setFocusPainted(false);

			JTextField codeTextField = new JTextField();
			codeTextField.setPreferredSize(new Dimension(200,50));
			codeTextField.setHorizontalAlignment(JLabel.CENTER);
			bottom.add(instructions);
			bottom.add(codeTextField);
			bottom.add(enterCode);


			main.add(top);
			main.add(bottom);
			main.setSize(900, 700);
			main.setVisible(true);

			//action listener for 'enter' button
			enterCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (player_vs_player.validCode(codeColours, codeTextField.getText().toString())==true) {
						String answer=codeTextField.getText().toString().toUpperCase();

						if(secretCode.equalsIgnoreCase(answer)) {
							JOptionPane.showMessageDialog(null, "You Won!");
							main.setVisible(false);
							main.dispose();
							new EndScreen(name, count);
						}

						else {
							JLabel newGuess = new JLabel ("Round "+count+": "+answer);
							EndScreen.styleJLabel(newGuess);
							top.add(newGuess);
							JLabel newBW = new JLabel (player_vs_player.blackAndWhite(answer,secretCode));
							EndScreen.styleJLabel(newBW);
							top.add(newBW);

							count++;
							if(count==11) {
								JOptionPane.showMessageDialog(null, "You Lost! The code was "+secretCode);
								main.setVisible(false);
								main.dispose();
								new EndScreen(name, 10);
							}
							main.revalidate();
							main.repaint();
						}
					} 
					else {
						JOptionPane.showMessageDialog(null, "Please enter a valid guess.");
					}
				}
			});


		}

	public static String randomCode(String codeColours) {
		int index;
		StringBuilder newGuess = new StringBuilder();
		for(int i=0;i<4;i++) {
			index = (int)(Math.random() *5);
			newGuess.append(Character.toString(codeColours.charAt(index)));
		}
		String code = newGuess.toString();
		return code;
	}
}