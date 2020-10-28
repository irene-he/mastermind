import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Guess extends JFrame{

	JPanel mainPanel;
	String codeColours ="RBGPOY";
	String secretCode = randomCode(codeColours);
	String instructionsText="ROUND 1    Enter a guess of 4 colours: R, B, Y, O, P, G";
	int round=1;
	String blackAndWhitePegs;
	static String[][] codeArray = new String[10][4];
	static String[][] bwPegsArray = new String[10][4];
	Guess(){
		super("Guess");
		instantiateArrays();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel topPanel = new GamePanelLeft();
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30,30));
		bottomPanel.setBackground(Color.BLACK);

		JLabel instructions = new JLabel(instructionsText);

		JTextField codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension(200,50));

		JButton enterCode = new JButton("ENTER");
		enterCode.setFont(new Font("Monospaced",Font.BOLD, 35));
		enterCode.setBackground(Color.BLACK);
		enterCode.setForeground(Color.WHITE);
		enterCode.setFocusPainted(false);
		enterCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validCode(codeColours,codeTextField.getText().toString())==true) {
					blackAndWhitePegs=compare(secretCode, codeTextField.getText().toString());
					if(blackAndWhitePegs.equalsIgnoreCase("bbbb") || round>10){
						remove(topPanel);
						remove(bottomPanel);
					}
					else {
						instructionsText="ROUND "+round+"    Enter a guess of 4 colours: R, B, Y, O, P, G";
						for(int i=0;i<4;i++) {
							codeArray[round][i]=Character.toString(codeTextField.getText().toString().charAt(i));
							bwPegsArray[round][i]=Character.toString(blackAndWhitePegs.charAt(i));
						}
						remove(topPanel);
						remove(bottomPanel);
						add(topPanel);
						add(bottomPanel);
						repaint();
						revalidate();
						
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a valid code");
				}
			}
		});

					bottomPanel.add(instructions);
					bottomPanel.add(codeTextField);
					bottomPanel.add(enterCode);
					bottomPanel.setPreferredSize(new Dimension(700,80));

					add(topPanel);
					add(bottomPanel);

					setSize(700, 700);
					setVisible(true);

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
		
		for(int i=0;i<blacks;i++) {
			blackAndWhitePegs.concat("b");
		}
		for(int i=0;i<whites;i++) {
			blackAndWhitePegs.concat("w");
		}
		return blackAndWhitePegs;
	}

	public static String randomCode(String codeColours) {
		int index;
		String code ="";
		for(int i=0;i<4;i++) {
			index = (int)(Math.random() *5);
			code.concat(Character.toString(codeColours.charAt(index)));

		}
		return code;
	}

	public static boolean validCode(String codeColours, String code) {
		boolean match = false;
		if(code.length()==4) {
			for(int i=0;i<4;i++) {
				for(int j=0;j<6;j++) {
					if(Character.toLowerCase(code.charAt(i))==Character.toLowerCase(codeColours.charAt(j))) {
						match=true;
					}
				}
				if(match==false) {
					return false;
				}
				match=false;
			}
			return true;
		}
		else {
			return false;
		}
	}

	public void instantiateArrays() {
		for(int i=0;i<codeArray.length;i++) {
			for(int k=0;k<codeArray[i].length;k++) {
				codeArray[i][k]="null";
				bwPegsArray[i][k]="null";
			}
		}
	}

	public static void main(String[] args) {
		Guess frame = new Guess();
	}
}


