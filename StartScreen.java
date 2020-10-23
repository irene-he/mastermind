import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame{
	JLabel name;
	JButton help, btnExit, multiplayer, singleplayer;

	StartScreen(){
		//create frame
		JFrame frame = new JFrame("Start");
		Container c =frame.getContentPane();
		c.setBackground(Color.BLACK);

		//display "Mastermind"
		name = new JLabel("<html>MASTERMIND</html>", SwingConstants.CENTER);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Monospaced", Font.BOLD, 70));
		name.setBounds(0, 0, 700, 250);

		//singleplayer button
		singleplayer = new JButton("SINGLEPLAYER");
		style2(singleplayer);
		singleplayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				//new Singleplayer();
			}
		});
		singleplayer.setBounds(200, 200, 300, 70);

		//multiplayer button
		multiplayer = new JButton("MULTIPLAYER");
		style2(multiplayer);
		multiplayer.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				//new Multiplayer();

			}
		});
		multiplayer.setBounds(200, 270, 300, 70);

		//help button
		help = new JButton("Help");
		style(help);
		help.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				new helpScreen();

			}
		});
		help.setBounds(150, 400, 100, 30);

		//exit button
		btnExit = new JButton("Exit");
		style(btnExit);
		btnExit.addActionListener(e -> System.exit(0));
		btnExit.setBounds(450, 400, 100, 30);

		//add components
		frame.add(name);
		frame.add(help);
		frame.add(btnExit);
		frame.add(multiplayer);
		frame.add(singleplayer);

		//frame
		frame.setLayout(null);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	public static void style(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 20));
		x.setBackground(new Color(128,128,128));
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
	}
	
	public static void style2(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 35));
		x.setBackground(Color.BLACK);
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
	}

	public static void main(String[]args) {
		StartScreen start = new StartScreen();
	}
}
