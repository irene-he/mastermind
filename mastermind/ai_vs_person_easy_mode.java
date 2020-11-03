package mastermind;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class ai_vs_person_easy_mode {
	static int count;
	static String codeColours ="RBGPOY";

	public static void main(String[] args) {
		count=1;

		//Create frame
		JFrame main = new JFrame("Easy Mode");
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(),BoxLayout.Y_AXIS));
		
		//Top panel
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(0, 2));
		top.setPreferredSize(new Dimension(900,500));
		top.setBackground(Color.BLACK);
		
		//Bottom panel
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(4,1));
		bottom.setBackground(Color.GRAY);
		
		//Labels within panels
		JLabel titles1 = new JLabel("AI Guess");
		EndScreen.styleJLabel(titles1);
		titles1.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles1);
		JLabel titles2 = new JLabel("Black and White Pegs");
		EndScreen.styleJLabel(titles2);
		titles2.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles2);


		JLabel instructions = new JLabel("How many black and white pegs are there? Black number first, then whites. (E.g. '04' or '22')");	
		EndScreen.styleJLabel(instructions);
		JButton enterCode = new JButton("ENTER");
		StartScreen.style(enterCode);
		enterCode.setFont(new Font("Monospaced",Font.BOLD, 20));
		enterCode.setBackground(Color.DARK_GRAY);

		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200,50));
		codeTextField.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(instructions);
		bottom.add(codeTextField);
		bottom.add(enterCode);


		String guess = randomCode(codeColours);
		JLabel firstGuess = new JLabel ("Round "+count+": "+guess);
		EndScreen.styleJLabel(firstGuess);
		top.add(firstGuess);


		main.add(top);
		main.add(bottom);
		main.setSize(900, 700);
		main.setVisible(true);

		//action listener for 'enter' button
		enterCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isInteger(codeTextField.getText().toString())==true && isValid(codeTextField.getText().toString())==true) {
					String answer=codeTextField.getText().toString();

					if(answer.equals("40")) {
						JOptionPane.showMessageDialog(null, "AI Won!");
						main.setVisible(false);
						main.dispose();
						new EndScreen("AI", count);
					}

					else {
						JLabel newBW = new JLabel (answer);
						EndScreen.styleJLabel(newBW);
						top.add(newBW);

						count++;
						String guess = randomCode(codeColours);
						JLabel newGuess = new JLabel ("Round "+count+": "+guess);
						EndScreen.styleJLabel(newGuess);
						top.add(newGuess);

						if(count==11) {
							JOptionPane.showMessageDialog(null, "AI Lost!");
							main.setVisible(false);
							main.dispose();
							new EndScreen("AI", 10);
						}
						main.revalidate();
						main.repaint();
					}
				} 
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid number. Black pegs first, then white.");
				}
			}
		});

	}



	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public static boolean isValid(String s) {
		if(s.length()>2 ||s.length()<2) {
			return false;
		}
		else {
			if(Character.getNumericValue(s.charAt(0))<0||Character.getNumericValue(s.charAt(0))>4) {
				if(Character.getNumericValue(s.charAt(0))+Character.getNumericValue(s.charAt(1))>4 || Character.getNumericValue(s.charAt(1))<0  || (Character.getNumericValue(s.charAt(0)) == 3 && Character.getNumericValue(s.charAt(1)) == 1)) {
					return false;
				}
			}
		}
		return true;
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
