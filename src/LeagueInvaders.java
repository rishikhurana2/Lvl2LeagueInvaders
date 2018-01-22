import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame gameFrame;
	GamePanel gamePanel;
	public LeagueInvaders() {
		gameFrame = new JFrame();
		gamePanel = new GamePanel();
	}
	static int width = 500;
	static int height = 800;
	public static void main(String[] args) {
		LeagueInvaders main = new LeagueInvaders();
		main.setup();
	}
	public void setup() {
		gameFrame.add(gamePanel);
		gameFrame.setPreferredSize(new Dimension(width, height));
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setSize(width, height);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.startGame();
		gameFrame.addKeyListener(gamePanel);
	}
}
