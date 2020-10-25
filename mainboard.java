package mastermind.src;

import javax.swing.*;
import java.awt.*;

public class mainboard extends JFrame {
    JFrame f = new JFrame();
    JPanel coloursPanel = new JPanel();
    JButton red, yellow, blue, green, magenta, orange;
    static CartesianPlane cartesianPlane = new CartesianPlane();;
    
    mainboard() {
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
        yellow = new JButton();
        yellow.setBackground(Color.YELLOW);
        yellow.setOpaque(true);
        yellow.setBorderPainted(false);
        blue = new JButton();
        blue.setBackground(Color.BLUE);
        blue.setOpaque(true);
        blue.setBorderPainted(false);
        green = new JButton();
        green.setBackground(Color.GREEN);
        green.setOpaque(true);
        green.setBorderPainted(false);
        magenta = new JButton();
        magenta.setBackground(Color.MAGENTA);
        magenta.setOpaque(true);
        magenta.setBorderPainted(false);
        orange = new JButton();
        orange.setBackground(Color.ORANGE);
        orange.setOpaque(true);
        orange.setBorderPainted(false);

        coloursPanel.setLayout(new GridLayout(1, 6));
        coloursPanel.setPreferredSize(new Dimension(240, 40));

        // add buttons
        coloursPanel.add(red);
        coloursPanel.add(yellow);
        coloursPanel.add(blue);
        coloursPanel.add(green);
        coloursPanel.add(magenta);
        coloursPanel.add(orange);
        add(coloursPanel);
		add(cartesianPlane, BorderLayout.CENTER);
    }

    public void pegsPanel() {

    }

    public void gameboard() {

    }

    public static void main(String[] args) {
        mainboard frame = new mainboard();
    }
}

class CartesianPlane extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // prints the component

        Graphics2D g2D = (Graphics2D) g; // casts Graphics to Graphics2D
        g.setColor(Color.BLACK); // sets the paint color to black

        // Draws the cartesian plane
        for (int x = 40; x <= 160; x += 40) {
            g.drawString(Integer.toString(x / 40), x + 35, 455);
            for (int y = 400; y >= 40; y -= 40) {
                g.drawRect(x, y, 40, 40);
                g.drawString(Integer.toString(11 - y / 40), 25, y + 3);
            } // end for loop
        } // end paintComponent method
    }
}
