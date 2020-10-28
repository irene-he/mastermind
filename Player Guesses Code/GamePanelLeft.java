import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.concurrent.*;

public class GamePanelLeft extends JPanel {

	Guess guessMode;

	GamePanelLeft() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(500, 300));
	}

	public void paint(Graphics g) {
		boolean test = true;
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g; // casts Graphics to Graphics2D
		g.setColor(Color.WHITE); // sets the paint color to black

		// Draws the cartesian plane
		int count=1;
		for (int y = 65 ; y <= 425; y +=40) {
			g.drawString(Integer.toString(count), 95, y);
			count++;
		}

		g.drawString("GUESSES", 120, 30);
		for (int x = 120; x <= 240; x += 40) {
			for (int y = 40; y <= 400; y += 40) {
				if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("r")) {
					g.setColor(Color.RED);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("b")) {
					g.setColor(Color.BLUE);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("g")) {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("y")) {
					g.setColor(Color.YELLOW);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("o")) {
					g.setColor(Color.ORANGE);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else if(guessMode.codeArray[(y-40)/40][(x-120)/40].equalsIgnoreCase("p")) {
					g.setColor(Color.MAGENTA);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);
				}
				else {
					g.drawRect(x, y, 40, 40);
				}
			}
		}

		g.drawString("HINTS", 320, 30);
		for (int x = 320; x <= 440; x += 40) {
			for (int y = 40; y <= 400; y += 40) {

				if(guessMode.bwPegsArray[(y-40)/40][(x-320)/40].equalsIgnoreCase("b")) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 40, 40);
					g.setColor(Color.WHITE);

				}
				else if(guessMode.bwPegsArray[(y-40)/40][(x-320)/40].equalsIgnoreCase("w")) {
					g.fillRect(x, y, 40, 40);
				}
				else {
					g.drawRect(x, y, 40, 40);
				}

			}
		}
	}


	public static void main(String[] args) throws InterruptedException {
		GamePanelLeft gamePanelLeft = new GamePanelLeft();
	}

}
