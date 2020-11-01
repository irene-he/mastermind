package mastermind.src;

import java.util.Scanner;


public class player_vs_ai {
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
        String secretCode = randomCode(codeColours);
        System.out.println(secretCode);
        Scanner myScanner = new Scanner(System.in);
        System.out.println("WELCOME TO THE SINGLE PLAYER GAME MODE OF CODEBREAKER");
        System.out.println("Please enter your name: ");
        String name = myScanner.nextLine();
        System.out.println("Thanks "+name+"!");
        for(int guess=1; guess<=10; guess++) {
            System.out.println();
            System.out.println("Enter guess #"+guess+":");
            String input = myScanner.nextLine();
            boolean match=player_vs_player.validCode(codeColours, input);
		    while (match==false) {
			    System.out.println("Your code is invalid, please enter it again");
                input=myScanner.nextLine();
                match=player_vs_player.validCode(codeColours, input);
            }
            String answer = ai_vs_person.compare(secretCode, input.toUpperCase());
            System.out.println("Black Pegs: "+answer.charAt(0)+"      White Pegs: "+answer.charAt(1));
            if (answer.equals("40")) {
                System.out.println("You Won!");
                new EndScreen(name, guess);
                break;
            }
            if (guess==10){
                System.out.println("You Lost");
                new EndScreen(name, guess);
            }   
        } 
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