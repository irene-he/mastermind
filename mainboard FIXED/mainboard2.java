import javax.swing.*;
import java.awt.*;

public class mainboard2 extends JFrame {
   JPanel mainPanel;
    mainboard2() {
        super("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.GRAY);
        setLayout(new BorderLayout());
		this.mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

  
		JPanel lPanel = new GamePanelLeft();
		JPanel rPanel = new GamePanelRight();
		add(lPanel);
		add(rPanel);
        
        setSize(700, 700);
        setVisible(true);
        
    }
   

    public static void main(String[] args) {
        mainboard2 frame = new mainboard2();
    }
}
