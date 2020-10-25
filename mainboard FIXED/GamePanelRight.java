import java.awt.*;
import javax.swing.*;

public class GamePanelRight extends JPanel{

	JButton red, yellow, blue, green, magenta, orange, black, white;
	JButton submit, clear;

	GamePanelRight(){

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		red = new JButton();
		red.setBackground(Color.red);
		style4(red);

		yellow = new JButton();
		yellow.setBackground(Color.YELLOW);
		style4(yellow);

		blue = new JButton();
		blue.setBackground(Color.BLUE);
		style4(blue);

		green = new JButton();
		green.setBackground(Color.GREEN);
		style4(green);

		magenta = new JButton();
		magenta.setBackground(Color.MAGENTA);
		style4(magenta);

		orange = new JButton();
		orange.setBackground(Color.ORANGE);
		style4(orange);

		black = new JButton();
		black.setBackground(Color.BLACK);
		style4(black);

		white = new JButton();
		white.setBackground(Color.WHITE);
		style4(white);

		clear = new JButton("CLEAR");
		style3(clear);
		red.setPreferredSize(new Dimension(160,40));
		submit = new JButton("SUBMIT");
		style3(submit);
		red.setPreferredSize(new Dimension(160,40));

		// add buttons
		add(red);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(yellow);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(blue);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(green);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(magenta);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(orange);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(black);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(white);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(clear);
		add(Box.createRigidArea(new Dimension(40,10)));
		add(submit);
	}

	public static void style3(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 20));
		x.setBackground(new Color(128,128,128));
		x.setOpaque(true);
		x.setBorderPainted(false);
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
		x.setAlignmentX(CENTER_ALIGNMENT);
	}

	public static void style4(JButton x) {
		x.setOpaque(true);
		x.setPreferredSize(new Dimension(40,40));
		x.setAlignmentX(CENTER_ALIGNMENT);
	}
}
