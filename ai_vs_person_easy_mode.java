package mastermind.src;

import java.util.Scanner;

public class ai_vs_person_easy_mode {
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
	    String guess = randomCode(codeColours);
        Scanner myScanner = new Scanner(System.in);
        //System.out.println(secretCode);
        for(int i=1; i<=10; i++) {
            System.out.println(guess);
            System.out.println("Black Pegs: ");
            String blacks = myScanner.nextLine();
            System.out.println("White Pegs: ");
            String whites = myScanner.nextLine();
            String answer = blacks.concat(whites);
            if (answer.equals("40")) {
                System.out.println("AI Won!");
                break;
            }
            if (i==10){
                System.out.println("AI Lost");
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
