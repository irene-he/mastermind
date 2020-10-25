package mastermind.src;

import javax.swing.*;
import java.awt.*;

public class mainboard2 extends JFrame {
    JFrame f = new JFrame();
    JPanel coloursPanel = new JPanel();
    JButton red, yellow, blue, green, magenta, orange, black, white;
    JButton submit, clear;
    
    mainboard2() {
        super("Game Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Container c = getContentPane();
        c.setBackground(Color.GRAY);
        setSize(700, 500);
        setVisible(true);
        red = new JButton();
        red.setBackground(Color.red);
        red.setOpaque(true);
        red.setBorderPainted(false);
        red.setBounds(40, 430, 40, 40);
        yellow = new JButton();
        yellow.setBackground(Color.YELLOW);
        yellow.setOpaque(true);
        yellow.setBorderPainted(false);
        yellow.setBounds(80, 430, 40, 40);
        blue = new JButton();
        blue.setBackground(Color.BLUE);
        blue.setOpaque(true);
        blue.setBorderPainted(false);
        blue.setBounds(120, 430, 40, 40);
        green = new JButton();
        green.setBackground(Color.GREEN);
        green.setOpaque(true);
        green.setBorderPainted(false);
        green.setBounds(160, 430, 40, 40);
        magenta = new JButton();
        magenta.setBackground(Color.MAGENTA);
        magenta.setOpaque(true);
        magenta.setBorderPainted(false);
        magenta.setBounds(200, 430, 40, 40);
        orange = new JButton();
        orange.setBackground(Color.ORANGE);
        orange.setOpaque(true);
        orange.setBorderPainted(false);
        orange.setBounds(240, 430, 40, 40);
        black = new JButton();
        black.setBackground(Color.BLACK);
        black.setOpaque(true);
        black.setBorderPainted(false);
        black.setBounds(360, 430, 40, 40);
        white = new JButton();
        white.setBackground(Color.WHITE);
        white.setOpaque(true);
        white.setBorderPainted(false);
        white.setBounds(400, 430, 40, 40);
        clear = new JButton("CLEAR");
        style3(clear);
        clear.setBounds(520, 160, 160, 40);
        submit = new JButton("SUBMIT");
        style3(submit);
        submit.setBounds(520, 280, 160, 40);

        coloursPanel.setLayout(new GridLayout(1, 6));
        coloursPanel.setPreferredSize(new Dimension(240, 40));

        // add buttons
        add(red);
        add(yellow);
        add(blue);
        add(green);
        add(magenta);
        add(orange);
        add(black);
        add(white);
        add(clear);
        add(submit);
    }
    
    public static void style3(JButton x) {
		x.setFont(new Font("Monospaced",Font.BOLD, 20));
        x.setBackground(new Color(128,128,128));
        x.setOpaque(true);
        x.setBorderPainted(false);
		x.setForeground(Color.WHITE);
		x.setFocusPainted(false);
    }
    
    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g; // casts Graphics to Graphics2D
        g.setColor(Color.BLACK); // sets the paint color to black

        // Draws the cartesian plane
        for (int x = 80; x <= 200; x += 40) {
            for (int y = 400; y >= 40; y -= 40) {
                g.drawRect(x, y, 40, 40);
            }
        }
        for (int x = 320; x <= 440; x += 40) {
            for (int y = 400; y >= 40; y -= 40) {
                g.drawRect(x, y, 40, 40);
            }
        }
    }

    public static void main(String[] args) {
        mainboard2 frame = new mainboard2();
    }
}
