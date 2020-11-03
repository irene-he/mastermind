package mastermind;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class player_vs_player{
	static final int ROUNDS=10;
	static int count;
	static String name1, name2, secretCode, guess, codeColours ="RBGPOY";;
	static boolean nameBoolean=true;

	public static void main(String[]args) {
		JFrame main = new JFrame("Multiplayer");
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(),BoxLayout.Y_AXIS));
		getNames(main);
	}

	public static void playerVsPlayer(JFrame main) {
		main.getContentPane().removeAll();
		count=1;

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(0, 2));
		top.setPreferredSize(new Dimension(900,500));
		top.setBackground(Color.BLACK);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(4,1));
		bottom.setBackground(Color.GRAY);

		JLabel titles1 = new JLabel(name2+"'s Guesses");
		EndScreen.styleJLabel(titles1);
		titles1.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles1);
		JLabel titles2 = new JLabel("Black and White Pegs");
		EndScreen.styleJLabel(titles2);
		titles2.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles2);


		JLabel instructions = new JLabel(name2+", guess the 4 colour code [COLOURS: R, G, B, Y, O, P]");	
		EndScreen.styleJLabel(instructions);
		JButton enterCode = new JButton("ENTER");
		StartScreen.style(enterCode);
		enterCode.setBackground(Color.DARK_GRAY);

		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200,50));
		codeTextField.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(instructions);
		bottom.add(codeTextField);
		bottom.add(enterCode);


		main.add(top);
		main.add(bottom);
		main.revalidate();
		main.repaint();

		//action listener for 'enter' button
		enterCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validCode(codeColours, codeTextField.getText().toString())==true) {
					guess=codeTextField.getText().toString().toUpperCase();
					if(checkCode(guess,secretCode)==true) {
						JOptionPane.showMessageDialog(null, name2+" wins!");
						main.setVisible(false);
						main.dispose();
						new EndScreen(name2, count);
					}
					else {
						JLabel newGuess = new JLabel ("Round "+count+": "+guess);
						EndScreen.styleJLabel(newGuess);
						top.add(newGuess);
						JLabel newBW = new JLabel (blackAndWhite(guess, secretCode));
						EndScreen.styleJLabel(newBW);
						top.add(newBW);

						count++;
						if(count==11) {
							JOptionPane.showMessageDialog(null, name2+" loses! The code was "+secretCode);
							main.setVisible(false);
							main.dispose();
							new EndScreen(name2, 10);
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


	public static void enterCode (JFrame main){
		main.getContentPane().removeAll();
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		JLabel instructions = new JLabel(name1+" Please hide the screen from "+name2+" and enter your code");	
		EndScreen.styleJLabel(instructions);
		instructions.setFont(new Font("Monospaced", Font.BOLD, 30));
		JButton enter = new JButton("ENTER");
		StartScreen.style(enter);
		enter.setBackground(Color.DARK_GRAY);


		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200, 50));
		panel.add(instructions);
		panel.add(codeTextField);
		panel.add(enter);
		main.add(panel);
		main.revalidate();
		main.repaint();

		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validCode(codeColours, codeTextField.getText().toString())==true) {
					secretCode=codeTextField.getText().toString();
					playerVsPlayer(main);

				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid code.");
				}
			}
		});

	}

	public static void getNames(JFrame main) {
		JPanel namePanelTop = new JPanel();
		namePanelTop.setBackground(Color.BLACK);
		JPanel namePanelBottom = new JPanel();
		namePanelBottom.setBackground(Color.BLACK);
		JLabel nameInstructionsTop = new JLabel("CODEMAKER: Enter your name");	
		EndScreen.styleJLabel(nameInstructionsTop);
		nameInstructionsTop.setFont(new Font("Monospaced", Font.BOLD, 30));
		JLabel nameInstructionsBottom = new JLabel("CODEBREAKER: Enter your name");	
		EndScreen.styleJLabel(nameInstructionsBottom);
		nameInstructionsBottom.setFont(new Font("Monospaced", Font.BOLD, 30));

		JButton enterName = new JButton("ENTER");
		StartScreen.style(enterName);
		enterName.setBackground(Color.DARK_GRAY);
		enterName.setPreferredSize(new Dimension(900,300));

		JTextField nameTextFieldTop = new JTextField();
		nameTextFieldTop.setPreferredSize(new Dimension(200, 50));
		JTextField nameTextFieldBottom = new JTextField();
		nameTextFieldBottom.setPreferredSize(new Dimension(200, 50));
		namePanelTop.add(nameInstructionsTop);
		namePanelTop.add(nameTextFieldTop);
		main.add(namePanelTop);
		namePanelBottom.add(nameInstructionsBottom);
		namePanelBottom.add(nameTextFieldBottom);

		main.add(namePanelBottom);
		main.add(enterName);
		main.setSize(900, 700);
		main.setVisible(true);

		enterName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameTextFieldTop.getText().toString().length() > 0 && nameTextFieldBottom.getText().toString().length() > 0) {
					name1=nameTextFieldTop.getText().toString();
					name2=nameTextFieldBottom.getText().toString();
					enterCode(main);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid name.");
				}
			}
		});

	}


	public static boolean checkCode(String guess, String code) {
		for(int i=0;i<4;i++) {
			if(Character.toLowerCase(guess.charAt(i))!=Character.toLowerCase(code.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validCode(String codeColours, String code) {
		ArrayList<Character> codeColoursList = new ArrayList<Character>();
		if(code.length()==4) {
			for(int i=0;i<6;i++) 
				codeColoursList.add(codeColours.charAt(i));
			for(int i=0;i<4;i++) {
				if(!codeColoursList.contains(Character.toUpperCase(code.charAt(i))))
					return false;
			}
			return true;
		}
		else
			return false;
	}

	public static String blackAndWhite(String guess, String code) {
		String blackAndWhitePegs="";
		ArrayList<String> wrongGuess = new ArrayList<String>();
		ArrayList<String> wrongCode = new ArrayList<String>();
		wrongGuess.clear();
		wrongCode.clear();

		int black=0, white=0;

		//black pegs
		for(int i=0;i<4;i++) {
			if(Character.toLowerCase(guess.charAt(i))==Character.toLowerCase(code.charAt(i))) {
				black++;
			}
			else {
				wrongCode.add(Character.toString(code.charAt(i)).toLowerCase());
				wrongGuess.add(Character.toString(guess.charAt(i)).toLowerCase());
			}
		}

		//white pegs
		for(int i=0;i<wrongGuess.size();i++) {
			for(int k=0;k<wrongCode.size();k++) {
				if(wrongCode.get(k).equalsIgnoreCase(wrongGuess.get(i))) {
					white++;
					wrongCode.remove(wrongGuess.get(i));
				}
			}

		}

		//convert to string
		for(int i=0;i<black;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("B");
		}
		for(int i=0;i<white;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("W");
		}

		return blackAndWhitePegs;
	}

}
