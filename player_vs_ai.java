package mastermind.src;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class player_vs_ai {
    static Random num = new Random();
    static ai_vs_person list = new ai_vs_person();
    public static void main(String[] args) {
        ArrayList<Integer> guesses = new ArrayList<>();
        ai_vs_person.createAllGuesses(guesses);
        System.out.println(guesses.get(0));
        Scanner myScanner = new Scanner(System.in);
        String secretCode = randomCode(guesses);
        System.out.println(secretCode);
        for(int guess=1; guess<=10; guess++) {
            System.out.println("enter you guess:");
            String input = myScanner.nextLine();
            String answer = ai_vs_person.compare(input, secretCode);
            System.out.println(answer);
            if (answer.equals("40")) {
                System.out.println("You Won!");
                break;
            }
            if (guess==10){
                System.out.println("You Lost");
            }   
        } 
    }
    public static String randomCode(ArrayList<Integer> guesses) {
        int index = (int)(Math.random() *1296)+0;
        String code = Integer.toString(guesses.get(index));
        return code;
    }

}