package mastermind.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ai_vs_person {
    /**
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class.
     * It also prints out the AI's guesses and prompts the user to enter responses.
	 * 
	 * List of Local Variables
	 * allGuesses - stores every possible guess that can be made from the 6 different colours <type ArrayList<Integer>>
     * allReponses - stores every possible response that the user can give <type ArrayList<String>>
     * allGuessesCopy - stores a copy of allGuesses <type ArrayList<Integer>>
     * guess - stores the guess that the AI want to make <type String>
     * counter - counts how many guesses the AI has taken <type int>
     * response - stores the input from the user returned by validNumber(); <type String>
	 *
	 * @param args
	 * @return void
	 */
    public static void main(String[] args) {
        ArrayList<Integer> allGuesses = new ArrayList<>();
        createAllGuesses(allGuesses);
        ArrayList<String> allResponses = new ArrayList<>();
        createAllResponses(allResponses);
        ArrayList<Integer> allGuessesCopy = new ArrayList<>(allGuesses);

        // intro
        System.out.println("Welcome to the 'HARD MODE' of the AI vs Player feature of Code Breaker!");
        System.out.println("The AI will guess your secret code in 6 or less guesses.");

        // print out first guess
        String guess = "1122";
        int counter = 1;
        System.out.println();
        System.out.println("Guess #" + counter + " " + converter(guess));

        // asks user for input
        String response=validNumber();
        if (response.equals("40")) {
            System.out.println("AI Wins");
            new EndScreen("AI", counter);
        }

        // repeat process of printing out guess and asking for input
        else {
            for (int i = 1; i <= 5; i++) {
                counter++;
                guess = guess(allGuesses, allResponses, allGuessesCopy, guess, response);
                System.out.println();
                System.out.println("Guess #" + counter + " " + converter(guess));
                response=validNumber();
                if (response.equals("40")) {
                    System.out.println("AI Wins");
                    new EndScreen("AI", counter);
                    break;
                }
            }
        }
    }
    /**
	 * This procedural method is used to prompt the user to enter the number of black and white pegs in response to the AI's guess.
	 * 
	 * List of Local Variables
	 * myScanner - reads input from the console <type Scanner>
     * blacks - stores the number of black pegs entered by the user <type int>
     * whites - stores the number of white pegs entered by the user <type int>
     * sum - the sum of blacks plus whites <type int>
     * response - stores the concatenation of blacks and whites <type String>
	 *
	 * @param args
	 * @return repsonse - answer given by user <type String>
	 */
    public static String validNumber(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("How many black pegs are there (enter number from 0 to 4):");
        int blacks = myScanner.nextInt();
        while (blacks < 0 || blacks > 4) {
            System.out.println("Your code is invalid, please enter it again");
            blacks = myScanner.nextInt();
        }
        System.out.println("How many white pegs are there (enter number from 0 to "+(4-blacks)+"):");
        int whites = myScanner.nextInt();
        int sum = blacks + whites;
        while (whites<0 || sum > 4 || (blacks == 3 && whites == 1)) {
            System.out.println("Your code is invalid, please enter it again");
            whites = myScanner.nextInt();
            sum = blacks + whites;
        }
        String response = Integer.toString(blacks).concat(Integer.toString(whites));
        return response;
    }
    
    /**
	 * This functional method is used to convert the numbers in the AI's guess to colour letters before displaying the guess in the console. 
	 * 
	 * List of Local Variables
	 * newGuess - converts each number in 'guess' to its specified colour character <type StringBuilder>
     * code - stores the converted version of the guess <type String>
	 *
	 * @param guess the guess the AI wants to make which needs to be converted to colours since although it is of type string, its characters are numbers <type String>
	 * @return code - the converted version of the guess <type String>
	 */
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

    /**
	 * This procedural method is used as a helper method to the recursive method createAllGuesses2(); , it simply passes in variables and nothing else
	 * 
	 * List of Local Variables
	 * colours - a list of colours but they're listed as numbers since a part of Knuth's algorithm is choosing the smallest number in the list of best guesses to make for each turn. <type int[]>
     * length - the number that represents the length the code will be <type int>
	 *
	 * @param allGuesses - empty arraylist to be filled once it's passed into createAllGuesses2 <type ArrayList<Integer>>
	 * @return void
	 */
    public static void createAllGuesses(ArrayList<Integer> allGuesses) { 
        int[] colours = { 1, 2, 3, 4, 5, 6 };
        int length = 4;
        createAllGuesses2(colours, allGuesses, "", colours.length, length);
    }
    /**
     * This procedural method is used to recursively call itself until all possible combinations of length 4 from the list 'colours' are stored in 'allGuesses'
	 * 
	 * List of Local Variables
	 * newTemp - temporarily stores the partially completed combination
     * 
     * @param colours a list of numbers from 1 to 6 that will be used to create combinations <type int[]>
     * @param allGuesses stores the created combinations <type ArrayList<Integer>>
     * @param temp partially created combination
     * @param coloursLength the length of the colours list
     * @param length the length/number of characters left to add to the combination
     * @return void
     */
    public static void createAllGuesses2(int[] colours, ArrayList<Integer> allGuesses, String temp, int coloursLength, int length) {
        //if all 4 characters of the combination have been added to 'temp' add that combination to allGuesses.
        if (length == 0) {
            allGuesses.add(Integer.parseInt(temp));
            //call the method again to create another combination.
            return;
        }
        // One by one add all characters from colours and recursively call for length equals to length-1
        for (int i = 0; i < coloursLength; i++) {
            // Next character added
            String newTemp = temp + colours[i];
            // k is decreased, because
            // we have added a new character
            createAllGuesses2(colours, allGuesses, newTemp, coloursLength, length - 1);
        }
    }
    /**
     * This procedural method is used create all possible responses that the user can give
	 * 
	 * List of Local Variables
	 * blacks - number of black pegs in the response <type int>
     * whites - number of white pegs in the response <type int>
     * 
     * @param allResponses stores the created responses <type ArrayList<String>>
     * @return void
     */
    public static void createAllResponses(ArrayList<String> allResponses) {
        for (int blacks = 0; blacks <= 4; blacks++) {
            for (int whites = 0; whites <= 4; whites++) {
                int sum = whites + blacks;
                //if whites and blacks add up to 4 or less and there isn't 3 blacks pegs and 1 white peg (which is an impossible response), the response is added to allResponses 
                if (sum <= 4 && !(blacks == 3 && whites == 1)) {
                    allResponses.add(Integer.toString(blacks) + Integer.toString(whites));
                }
            }
        }
    }
    /**
     * This functional method is used to compare 2 codes with each other and return the number of black and white pegs as a response
	 * 
	 * List of Local Variables
	 * blackAndWhitePegs - number of black and white pegs in the response <type String>
     * wrongGuess - stores the characters of 'code' that aren't in the correct position <type ArrayList<String>>
     * wrongCode - stores the characters of 'guess' that aren't in the correct position <type ArrayList<String>>
     * blacks - number of colours in correct positions <type int>
     * whites - number of colours that are correct but in wrong positions <type int>
     * 
     * @param allResponses stores the created responses <type ArrayList<String>>
     * @return blackAndWhitePegs - a concatenation of blacks and whites <type String>
     */
    public static String compare(String code, String guess) {
        String blackAndWhitePegs = "";
        ArrayList<String> wrongGuess = new ArrayList<>();
        ArrayList<String> wrongCode = new ArrayList<>();
        wrongGuess.clear();
        wrongCode.clear();
        int blacks = 0, whites = 0;
        for (int i = 0; i < 4; i++) {
            //if the characters at i match, they are in the correct position and blacks is incremented by one.
            if (code.charAt(i) == guess.charAt(i))
                blacks++;
            //otherwise the characters that don't match will be added to their respective arraylists to check if there are any correct colours in wrong positions.
            else {
                wrongCode.add(Character.toString(code.charAt(i)));
                wrongGuess.add(Character.toString(guess.charAt(i)));
            }
        }
        for (int i = wrongCode.size() - 1; i >= 0; i--) {
            //if the arraylist wrongCode contains that colour that's at index i of wrongGuess, whites is incremented by one and that colour is removed.
            if (wrongCode.contains(wrongGuess.get(i))) {
                whites++;
                wrongCode.remove(wrongGuess.get(i));
            }
        }
        blackAndWhitePegs = Integer.toString(blacks) + Integer.toString(whites);
        return blackAndWhitePegs;
    }
    /**
     * This functional method is used to see how many guesses there are in a list of guesses which when compared to the AI's last guess gives the same response as the specified response.
     * Used to find the maxminimum.
     * 
     * @param allGuesses - list with all possible guesses <type ArrayList<String>>
     * @param lastGuess - the last guess the AI made <type String>
     * @param response - the response that the guesses in allGuesses should give when compared to lastGuess
     * @return allPossibleGuesses every single guess in allGuesses that when compared to lastGuess equals 'response' <type ArrayList<Integer>>
     */
    public static ArrayList<Integer> allPossibleGuesses(ArrayList<Integer> allGuesses, String lastGuess, String response) {
        ArrayList<Integer> possibleGuesses = new ArrayList<>();
        possibleGuesses.clear();
        for (int i = 0; i < allGuesses.size() - 1; i++) {
            //if the guess when compared to lastGuess equals to response, that guess will be added to possibleGuesses
            if (compare(Integer.toString(allGuesses.get(i)), lastGuess).equals(response))
                possibleGuesses.add(allGuesses.get(i));
        }
        return possibleGuesses;
    }
    /**
     * Procedural method that removes all impossible guesses from allGuessesCopy
     * @param allGuessesCopy - the list of all the current possible guesses <type ArrayList<Integer>>
     * @param lastGuess - the last guess the AI made <type String>
     * @param response - the response that the use gave <type String>
     * @return void
     */
    public static void removePossibleGuesses(ArrayList<Integer> allGuessesCopy, String lastGuess, String response) {
        for (int i = allGuessesCopy.size() - 1; i >= 0; i--) {
            if (!compare(Integer.toString(allGuessesCopy.get(i)), lastGuess).equals(response))
                allGuessesCopy.remove(i);
        }
    }
    /**
     * List of local variables:
     * bestGuesses - list of all the best guesses that the ai can make on that turn <type ArrayList<Integer>>
     * maxMinimum - the largest possible minimum number of removed guesses from allGuessesCopy, starts off as 0 but gets updated each turn <type int>
     * code - guesses in allGuesses <type Integer>
     * pegs - responses in allResponses <type String>
     * removedCodeSize - the number of guesses in allGuessesCopy which when compared to 'code' equals 'pegs' <type int>
     * minimum - the smaller number between the current value of removedCodesSize and it's previous value, starts off as the highest possible integer value <type int>
     * consistentBestGuesses - list of codes in bestGuesses that when compared to the last guess, gives the same response as the user's response, which means the code is actually a valid code <type ArrayList<Integer>>
     * index - index of the smallest guess in bestGuesses
     * 
     * @param allGuesses - list of all guesses the ai could make <type ArrayList<Integer>>
     * @param allResponses - list of all responses the user could give <type ArrayList<String>>
     * @param allGuessesCopy - list of all the valid guesses the ai could make <type ArrayList<Integer>>
     * @param lastGuess - the last guess the ai made <type String>
     * @param response - the last response the user gave <type String>
     * @return guess - the best guess that the ai could make for this turn <type String>
     */
    public static String guess(ArrayList<Integer> allGuesses, ArrayList<String> allResponses, ArrayList<Integer> allGuessesCopy, String lastGuess, String response) {
        //remove all guesses that we know are no longer valid based on the user's response
        removePossibleGuesses(allGuessesCopy, lastGuess, response);
        ArrayList<Integer> bestGuesses = new ArrayList<>();
        bestGuesses.add(allGuessesCopy.get(0));
        int maxMinimum = 0;
        //take all possible guesses and compare each guess to each possible answer
        for (Integer code : allGuesses) {
            int minimum = Integer.MAX_VALUE;
            for (String pegs : allResponses) {
                int removedCodesSize = allPossibleGuesses(allGuessesCopy, Integer.toString(code), pegs).size();
                //find the peg score with the lowest amount of duplicate resulting scores
                minimum = Math.min(removedCodesSize, minimum);
            }
            //if the minimum of this code is the MAXminimum, the code will be added
            if (minimum == maxMinimum && minimum > 0) {
                bestGuesses.add(code);
            }
            //find the MAXminimum, if a code's minimum is larger than maxMinimum, that code is now one of the best possible guesses
            if (minimum > maxMinimum) {
                maxMinimum = minimum;
                bestGuesses.clear();
                bestGuesses.add(code);
            }
        }
        // Use, if possible, consistent codes, otherwise an "impossible" code may be used
        ArrayList<Integer> consistentBestGuesses = new ArrayList<>(allPossibleGuesses(bestGuesses, lastGuess, response));
        if (!consistentBestGuesses.isEmpty()) {
            bestGuesses.clear();
            bestGuesses = new ArrayList<Integer>(consistentBestGuesses);
        }
        //If there are multiple best guesses, use the one that's smallest 
        int index = bestGuesses.indexOf(Collections.min(bestGuesses));
        String guess = Integer.toString(bestGuesses.get(index));
        return guess;
    }
}
