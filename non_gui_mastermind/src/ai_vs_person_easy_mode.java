package mastermind.src;

public class ai_vs_person_easy_mode {
    /**
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class.
	 * It also prints out the AI's guesses and prompts the user to enter responses.
     * 
	 * List of Local Variables
	 * codeColours - stores the 6 possible colours that the user can guess <type String>
     * guess - stores the randomly generated code of 4 colours returned by randomCode(codeColours) <type String>
     * answer - stores the input from the user returned by ai_vs_person.validNumber(); <type String>
     * 
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        String codeColours ="RBGPOY";
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
                //score passed in is 11 and in EndScreen's method if the score passed in is 11 it'll ignore that name and score
                new EndScreen("AI", 11);
            }
        } 
    }
    /**
	 * This functional method creates a randomly generated code of 4 colours from codeColours
	 * 
	 * List of Local Variables
	 * index - randomly generated number from 0 to 5 <type int>
     * newGuess - creates the code of 4 colours by appending the character at 'index' one by one until 4 chracters have been appended <type StringBuilder>
     * code - stores the randomly generated code; 
     * 
     * @param codeColours - stores the 6 possible colours that the user can guess <type String>
     * @return code - the randomly generated code of colours <type String>
     */
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
