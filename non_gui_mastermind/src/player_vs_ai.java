package mastermind.src;

import java.util.Scanner;


public class player_vs_ai {
    /** 
     * This procedural method is called automatically and is used to organize the calling of other methods defined in the class.
     * It also prints out the AI's guesses and prompts the user to enter responses.
     * 
     * List of local variables:
     * codeColours - stores the 6 possible colours that the user can guess <type String>
     * secretCide - stores the randomly generated code of 4 colours returned by randomCode(codeColours) <type String>
     * myScanner - reads inputs from the console <type Scanner>
     * name - user's name <type String>
     * guess - number of guesses taken <type int>
     * input - user's guess <type String>
     * match - determines whether or not the guess the user entered was valid <type boolean>
     * answer - black and white pegs that result when the user's guess is compared to secretCode <type String>
     * 
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
        //uses method from ai_vs_person_easy_mode class to randomly generate a code
        String secretCode = ai_vs_person_easy_mode.randomCode(codeColours);
        Scanner myScanner = new Scanner(System.in);
        //intro
        System.out.println("WELCOME TO THE SINGLE PLAYER GAME MODE OF CODEBREAKER");
        System.out.println("Please enter your name: ");
        String name = myScanner.nextLine();
        System.out.println("Welcome "+name+" you have 10 rounds to guess the code.");
        //prompt user to enter guesses
        for(int guess=1; guess<=10; guess++) {
            System.out.println();
            System.out.println("Round "+guess);
            System.out.println("You may enter the colours R,G,B,P,O,P with duplicates, no spaces, and with 4 colours");
            System.out.println("Enter guess #"+guess+":");
            String input = myScanner.nextLine();
            boolean match=player_vs_player.validCode(codeColours, input);
            //get user to enter guess again if what they entered was not 4 colour characters
            while (match==false) {
			    System.out.println("Your code is invalid, please enter it again");
                input=myScanner.nextLine();
                match=player_vs_player.validCode(codeColours, input);
            }
            //prints out response which is the number of black and white pegs
            String blackAndWhitePegs=player_vs_player.blackAndWhite(input, secretCode);
			//print game
			System.out.println("Round "+guess+"		Guess: "+input+"		Pegs: "+blackAndWhitePegs);
            if (blackAndWhitePegs.equals("bbbb")) {
                System.out.println("The code was deciphered, great job "+name+"!");
                //call the endscreen and pass in the player's name and number of guesses they took
                new EndScreen(name, guess);
                break;
            }
            if (guess==10){
                System.out.println("The code was not deciphered, nice try "+name+".");
                System.out.println("The code was: "+secretCode);
                //score passed in is 11 and in EndScreen's method if the score passed in is 11 it'll ignore that name and score
                new EndScreen(name, 11);
            }   
        } 
    }
}