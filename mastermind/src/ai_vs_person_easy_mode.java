package mastermind.src;

import java.util.Scanner;

public class ai_vs_person_easy_mode {
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
        Scanner myScanner = new Scanner(System.in);
        for(int i=1; i<=10; i++) {
            String guess = randomCode(codeColours);
            System.out.println("Guess #" + i + " " + guess);
            String answer = ai_vs_person.validNumber();
            if (answer.equals("40")) {
                System.out.println("AI Won!");
                new EndScreen("AI", i);
                break;
            }
            if (i==10){
                System.out.println("AI Lost");
                new EndScreen("AI", i);
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
