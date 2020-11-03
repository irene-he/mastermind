package mastermind.src;

import java.util.Scanner;
import java.util.ArrayList;
public class player_vs_player {
	static Scanner scan = new Scanner(System.in);
	static final int ROUNDS=10;
	public static void main(String[]args) {
		playerVsPlayer();
	}

	public static void playerVsPlayer() {
		boolean match, correct=false;
		String blackAndWhitePegs, code, guess, codeColours="RBGPOY";

		//intro
		System.out.println("Welcome to the 2 player gamemode of Code Breaker!");
		System.out.println("Player 1: Please enter your name");
		String player1=scan.nextLine();
		System.out.println("Thank you, "+player1+"!");
		System.out.println("Player 2: Please enter your name");
		String player2=scan.nextLine();
		System.out.println("Thank you, "+player2+"!");

		//player 1 enters code
		System.out.println(player1+": Please hide the screen from "+player2);
		System.out.println("You may enter the colours R,G,B,P,O,P with duplicates, no spaces, and with 4 colours");
		code=scan.nextLine();
		match=validCode(codeColours, code);
		while (match==false) {
			System.out.println("Your code is invalid, please enter it again");
			code=scan.nextLine();
			match=validCode(codeColours, code);
		}

		//player 2 guesses
		System.out.println("Please pass your device to "+player2);

		System.out.println("Welcome "+player2+" you have 10 rounds to guess the code.");
		for(int i=1;i<=ROUNDS;i++) {
			System.out.println();
			System.out.println("Round "+(i));
			System.out.println("You may enter the colours R,G,B,P,O,P with duplicates, no spaces, and with 4 colours");
			guess=scan.nextLine();
			match=validCode(codeColours, guess);
			while(match==false) {
				System.out.println("Your guess is invalid, please enter it again");
				code=scan.nextLine();
				match=validCode(codeColours, code);
			}
			correct=checkCode(guess, code);
			blackAndWhitePegs=blackAndWhite(guess, code);
			//print game
			System.out.println("Round "+(i)+"		Guess: "+guess+"		Pegs: "+blackAndWhitePegs);
			if(correct==true) {
				System.out.println("The code was deciphered! "+player2+" wins!");
				new EndScreen(player2, i);
				break;
			}
			if(i==10){
				System.out.println("The code was not deciphered! "+player1+" wins!");
				
				new EndScreen(player2, 11);
			}
		}
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
				wrongCode.add(Character.toString(code.charAt(i)));
				wrongGuess.add(Character.toString(guess.charAt(i)));
			}
		}
		
		//white pegs
		for(int i=0;i<wrongCode.size();i++) {
			if(wrongCode.contains(wrongGuess.get(i))) {
				white++;
				wrongCode.remove(wrongGuess.get(i));
			}
		}

		//convert to string
		for(int i=0;i<black;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("b");
		}
		for(int i=0;i<white;i++) {
			blackAndWhitePegs=blackAndWhitePegs.concat("w");
		}

		return blackAndWhitePegs;
	}


}