package mastermind;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;

public class ai_vs_person {
	static int counter=1;
	static String guess;
	public static void main(String[] args) {
		ArrayList<Integer> allGuesses = new ArrayList<>();
		createAllGuesses(allGuesses);
		ArrayList<String> allResponses = new ArrayList<>();
		createAllResponses(allResponses);
		ArrayList<Integer> allGuessesCopy = new ArrayList<>(allGuesses);
		counter=1;


		JFrame main = new JFrame("Hard Mode");
		main.getContentPane().setLayout(new BoxLayout(main.getContentPane(),BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(0, 2));
		top.setPreferredSize(new Dimension(900,300));
		top.setBackground(Color.BLACK);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(4,1));
		bottom.setBackground(Color.GRAY);

		JLabel titles1 = new JLabel("AI Guess");
		EndScreen.styleJLabel(titles1);
		titles1.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles1);
		JLabel titles2 = new JLabel("Black and White Pegs");
		EndScreen.styleJLabel(titles2);
		titles2.setFont(new Font("Monospaced", Font.BOLD, 25));
		top.add(titles2);


		JLabel intro = new JLabel("The AI will guess your secret code in 6 or less guesses.");
		EndScreen.styleJLabel(intro);
		JLabel instructions = new JLabel("How many black and white pegs are there? Black number first, then whites. (E.g. '04' or '22')");	
		EndScreen.styleJLabel(instructions);
		JButton enterCode = new JButton("ENTER");
		enterCode.setFont(new Font("Monospaced",Font.BOLD, 20));
		enterCode.setBackground(Color.DARK_GRAY);
		enterCode.setForeground(Color.WHITE);
		enterCode.setFocusPainted(false);

		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200,50));
		codeTextField.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(intro);
		bottom.add(instructions);
		bottom.add(codeTextField);
		bottom.add(enterCode);

		guess = "1122";
		JLabel firstGuess = new JLabel ("Round "+counter+": "+converter(guess));
		EndScreen.styleJLabel(firstGuess);
		top.add(firstGuess);


		main.add(top);
		main.add(bottom);
		main.setSize(900, 700);
		main.setVisible(true);

		//action listener for 'enter' button
		enterCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ai_vs_person_easy_mode.isInteger(codeTextField.getText().toString())==true && ai_vs_person_easy_mode.isValid(codeTextField.getText().toString())==true) {
					String answer=codeTextField.getText().toString();

					if(answer.equals("40")) {
						JOptionPane.showMessageDialog(null, "AI Won!");
						main.setVisible(false);
						main.dispose();
						new EndScreen("AI", counter);
					}

					else {
						JLabel newBW = new JLabel (answer);
						EndScreen.styleJLabel(newBW);
						top.add(newBW);

						counter++;
						guess = guess(allGuesses, allResponses, allGuessesCopy, guess, answer);
						JLabel newGuess = new JLabel ("Round "+counter+": "+converter(guess));
						EndScreen.styleJLabel(newGuess);
						top.add(newGuess);

						if(counter==11) {
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


	public static String converter(String guess) {
		StringBuilder newGuess = new StringBuilder(guess);
		for (int i = 0; i < 4; i++) {
			if (guess.charAt(i) == '1')
				newGuess.setCharAt(i, 'R');
			if (guess.charAt(i) == '2')
				newGuess.setCharAt(i, 'G');
			if (guess.charAt(i) == '3')
				newGuess.setCharAt(i, 'B');
			if (guess.charAt(i) == '4')
				newGuess.setCharAt(i, 'P');
			if (guess.charAt(i) == '5')
				newGuess.setCharAt(i, 'O');
			if (guess.charAt(i) == '6')
				newGuess.setCharAt(i, 'Y');
		}
		String code = newGuess.toString();
		return code;
	}

	static void createAllGuesses(ArrayList<Integer> allGuesses) {
		int[] colours = { 1, 2, 3, 4, 5, 6 };
		int length = 4;
		createAllGuesses2(colours, allGuesses, "", colours.length, length);
	}

	// The main recursive method
	// to print all possible
	// strings of length k
	static void createAllGuesses2(int[] colours, ArrayList<Integer> allGuesses, String temp, int coloursLength,
			int length) {
		if (length == 0) {
			allGuesses.add(Integer.parseInt(temp));
			return;
		}
		// One by one add all characters
		// from set and recursively
		// call for k equals to k-1
		for (int i = 0; i < coloursLength; i++) {

			// Next character of input added
			String newTemp = temp + colours[i];
			// k is decreased, because
			// we have added a new character
			createAllGuesses2(colours, allGuesses, newTemp, coloursLength, length - 1);
		}
	}

	public static void createAllResponses(ArrayList<String> allResponses) {
		for (int blacks = 0; blacks <= 4; blacks++) {
			for (int whites = 0; whites <= 4; whites++) {
				int sum = whites + blacks;
				if (sum <= 4 && !(blacks == 3 && whites == 1)) { // since "3 blacks, 1 white) is not a possible response
					allResponses.add(Integer.toString(blacks) + Integer.toString(whites));
				}
			}
		}
	}

	public static String compare(String code, String guess) {
		String blackAndWhitePegs = "";
		ArrayList<String> wrongGuess = new ArrayList<>();
		ArrayList<String> wrongCode = new ArrayList<>();
		wrongGuess.clear();
		wrongCode.clear();
		int blacks = 0, whites = 0;
		for (int i = 0; i < 4; i++) {
			if (code.charAt(i) == guess.charAt(i)) {
				blacks++;
			} else {
				wrongCode.add(Character.toString(code.charAt(i)));
				wrongGuess.add(Character.toString(guess.charAt(i)));
			}
		}
		for (int i = wrongCode.size() - 1; i >= 0; i--) {
			if (wrongCode.contains(wrongGuess.get(i))) {
				whites++;
				wrongCode.remove(wrongGuess.get(i));
			}
		}
		blackAndWhitePegs = Integer.toString(blacks) + Integer.toString(whites);
		return blackAndWhitePegs;
	}

	public static ArrayList<Integer> allPossibleGuesses(ArrayList<Integer> allGuesses, String lastGuess,
			String response) {
		ArrayList<Integer> possibleGuesses = new ArrayList<>();
		possibleGuesses.clear();
		for (int i = 0; i < allGuesses.size() - 1; i++) {
			if (compare(Integer.toString(allGuesses.get(i)), lastGuess).equals(response))
				possibleGuesses.add(allGuesses.get(i));
		}
		return possibleGuesses;
	}

	public static void removePossibleGuesses(ArrayList<Integer> possibleGuesses, String lastGuess, String response) {
		for (int i = possibleGuesses.size() - 1; i >= 0; i--) {
			if (!compare(Integer.toString(possibleGuesses.get(i)), lastGuess).equals(response))
				possibleGuesses.remove(i);
		}
	}

	public static String guess(ArrayList<Integer> allGuesses, ArrayList<String> allResponses,
			ArrayList<Integer> possibleGuesses, String lastGuess, String response) {
		removePossibleGuesses(possibleGuesses, lastGuess, response);
		ArrayList<Integer> bestGuesses = new ArrayList<>();
		bestGuesses.add(possibleGuesses.get(0));
		int maxMinimum = 0;
		for (Integer code : allGuesses) {
			int minimum = Integer.MAX_VALUE;
			for (String pegs : allResponses) {
				int removedCodesSize = allPossibleGuesses(possibleGuesses, Integer.toString(code), pegs).size();
				minimum = Math.min(removedCodesSize, minimum);
			}
			if (minimum == maxMinimum && minimum > 0) {
				bestGuesses.add(code);
			}
			if (minimum > maxMinimum) {
				maxMinimum = minimum;
				bestGuesses.clear();
				bestGuesses.add(code);
			}
		}
		// Use, if possible, consistent codes
		ArrayList<Integer> consistentBestGuesses = new ArrayList<>(
				allPossibleGuesses(bestGuesses, lastGuess, response));
		if (!consistentBestGuesses.isEmpty()) {
			bestGuesses.clear();
			bestGuesses = new ArrayList<Integer>(consistentBestGuesses);
		}
		int index = bestGuesses.indexOf(Collections.min(bestGuesses));
		String guess = Integer.toString(bestGuesses.get(index));
		return guess;
	}
}
