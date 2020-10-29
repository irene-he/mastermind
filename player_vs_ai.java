package mastermind.src;

import java.util.Scanner;


public class player_vs_ai {
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
	    String secretCode = randomCode(codeColours);
        Scanner myScanner = new Scanner(System.in);
        //System.out.println(secretCode);
        for(int guess=1; guess<=10; guess++) {
            System.out.println("Enter you're guess:");
            String input = myScanner.nextLine();
            String answer = ai_vs_person.compare(input, secretCode);
            System.out.println("Black Pegs: "+answer.charAt(0)+"      White Pegs: "+answer.charAt(1));
            if (answer.equals("40")) {
                System.out.println("You Won!");
                break;
            }
            if (guess==10){
                System.out.println("You Lost");
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