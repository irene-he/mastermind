package mastermind.src;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import java.util.Collections;

public class ai_vs_person {

    public static void main(String[] args) {
        ArrayList<Integer> allGuesses = new ArrayList<>();
        createAllGuesses(allGuesses);
        ArrayList<String> allResponses = new ArrayList<>();
        createAllResponses(allResponses);
        String guess="1122";
        System.out.println(converter(guess));
        Scanner myScanner = new Scanner(System.in);
        String response=myScanner.next();
        ArrayList<Integer> allGuessesCopy = new ArrayList<>(allGuesses);
        if (response.equals("40")) {
            System.out.println("AI Wins");
        }
        else {
            for (int i=1; i<=5; i++){
                guess=guess(allGuesses, allResponses, allGuessesCopy, guess, response);
                System.out.println(converter(guess));
                response = myScanner.next();
                if (response.equals("40")) {
                    System.out.println("AI Wins");
                    break;
                }
            }
        }
    }
    
    public static String converter(String guess){
        StringBuilder newGuess = new StringBuilder(guess);
        for (int i=0; i<4; i++) {
            if(guess.charAt(i)=='1')
                newGuess.setCharAt(i, 'R');
            if(guess.charAt(i)=='2')
                newGuess.setCharAt(i, 'G');
            if(guess.charAt(i)=='3')
                newGuess.setCharAt(i, 'B');
            if(guess.charAt(i)=='4')
                newGuess.setCharAt(i, 'P');
            if(guess.charAt(i)=='5')
                newGuess.setCharAt(i, 'O');
            if(guess.charAt(i)=='6')
                newGuess.setCharAt(i, 'Y');
        }
        String code = newGuess.toString();
        return code;
    }

    static void createAllGuesses(ArrayList<Integer> allGuesses) {
        int[] colours = {1,2,3,4,5,6};
        int length = 4;
        createAllGuesses2(colours, allGuesses, "", colours.length, length);
    }
    // The main recursive method
    // to print all possible
    // strings of length k
    static void createAllGuesses2(int[] colours, ArrayList<Integer> allGuesses, String temp, int coloursLength, int length) {
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
            createAllGuesses2(colours, allGuesses , newTemp, coloursLength, length - 1);
        }
    }
    public static void createAllResponses(ArrayList<String> allResponses) {
        for (int blacks = 0; blacks <= 4; blacks++) {
			for (int whites = 0; whites <= 4; whites++) {
                int sum = whites + blacks;
				if (sum <= 4 && !(blacks == 3 && whites == 1)) { //since "3 blacks, 1 white) is not a possible response
					allResponses.add(Integer.toString(blacks)+Integer.toString(whites));
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
        int blacks=0, whites=0;
        for (int i=0; i<4; i++) {
            if(code.charAt(i)==guess.charAt(i)){
                blacks++;
            }
            else {
                wrongCode.add(Character.toString(code.charAt(i)));
                wrongGuess.add(Character.toString(guess.charAt(i)));
            }
        }
        for (int i = wrongCode.size()-1; i >=0; i--) {
            if (wrongCode.contains(wrongGuess.get(i))){
                whites++;
                wrongCode.remove(wrongGuess.get(i));
            }
        }
        blackAndWhitePegs=Integer.toString(blacks)+Integer.toString(whites);
        return blackAndWhitePegs;
    }
    public static ArrayList<Integer> allPossibleGuesses(ArrayList<Integer> allGuesses, String lastGuess, String response) {
        ArrayList<Integer> possibleGuesses = new ArrayList<>();
        possibleGuesses.clear();
        for(int i=0; i<allGuesses.size()-1; i++){
            if(compare(Integer.toString(allGuesses.get(i)), lastGuess).equals(response))
                possibleGuesses.add(allGuesses.get(i));
        }
        return possibleGuesses;
    }
    public static void removePossibleGuesses(ArrayList<Integer> possibleGuesses, String lastGuess, String response) {
        for (int i = possibleGuesses.size()-1; i >=0; i--) {
            if (!compare(Integer.toString(possibleGuesses.get(i)),lastGuess).equals(response))
                possibleGuesses.remove(i);
        }    
    }
    //copied code:
    public static String guess(ArrayList<Integer> allGuesses, ArrayList<String> allResponses, ArrayList<Integer> possibleGuesses, String lastGuess, String response) {
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
		ArrayList<Integer> consistentBestGuesses = new ArrayList<>(allPossibleGuesses(bestGuesses, lastGuess, response));
		if(!consistentBestGuesses.isEmpty()) {
            bestGuesses.clear();
			bestGuesses=new ArrayList<Integer>(consistentBestGuesses);
        }
        int index = bestGuesses.indexOf(Collections.min(bestGuesses));
        String guess=Integer.toString(bestGuesses.get(index));
        return guess;
	}
}
