import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.concurrent.*;

public class GamePanelLeft extends JPanel {
	
	
	GamePanelLeft() {
			setBackground(Color.GRAY);
	}
	
	public void paint(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g; // casts Graphics to Graphics2D
		g.setColor(Color.WHITE); // sets the paint color to black
		
		g.drawString("INSERT INSTRUCTIONS HERE", 30, 30);
		// Draws the cartesian plane
		int count=1;
		for (int y = 265 ; y <= 625; y +=40) {
			g.drawString(Integer.toString(count), 95, y);
			count++;
		}
		
		g.drawString("GUESSES", 120, 230);
		for (int x = 120; x <= 240; x += 40) {
			for (int y = 600; y >= 240; y -= 40) {
				g.drawRect(x, y, 40, 40);
			}
		}
		
		g.drawString("HINTS", 320, 230);
		for (int x = 320; x <= 440; x += 40) {
			for (int y = 600; y >= 240; y -= 40) {
				g.drawRect(x, y, 40, 40);
			}
		}
	}


	public static void main(String[] args) throws InterruptedException {
		GamePanelLeft gamePanelLeft = new GamePanelLeft();
	}

}
