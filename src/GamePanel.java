import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener{
	Timer timer;
	Font titleFont;
	Font tellFont;
	GameObject object;
	Rocketship r;
	Alien a;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	static boolean moveLeft;
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	ObjectManager om;
    public static BufferedImage alienImg;
    public static BufferedImage rocketImg;
    public static BufferedImage bulletImg;
    public static BufferedImage spaceImg;
	public GamePanel() {
		timer = new Timer(1000/60,this);
		titleFont = new Font("Times New Roman", Font.PLAIN,48);
		tellFont = new Font("Arial", Font.PLAIN, 24);
		object = new GameObject(10,10,100,100);
		r = new Rocketship(250,700,50,50);
		a = new Alien(0,0,0,0);
		om = new ObjectManager(r);
		moveLeft = false;
		moveRight = false;
		moveUp = false;
		moveDown = false;
        try {
            alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
            rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
            bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
            spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
        			e.printStackTrace();
        		}
		}
	public void startGame() {
		timer.start();
	}
	public void updateMenuState() {
		ObjectManager.score = 0;
	}
	public void updateGameState() {
		om.update();
		om.manageEnemies();
		om.checkCollision();
		om.purgeObjects();
		if(r.isAlive == false) {
			currentState = END_STATE;
			om.reset();
			r = new Rocketship(250,700,50,50);
			om = new ObjectManager(r);
		}
	}
	public void updateEndState() {
	}
	public void drawMenuState(Graphics m) {
		m.setColor(Color.BLUE);
		m.fillRect(0,0,LeagueInvaders.width,LeagueInvaders.height);
		m.setFont(titleFont);
		m.setColor(Color.BLACK);
		m.drawString("League Invaders", 80, 100);
		m.setFont(tellFont);
		m.drawString("Press Enter to Play", 130, 300);
		m.drawString("Press Space for Instructions", 90, 500);
		
	}
	public void drawGameState(Graphics d) {
		d.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.width,LeagueInvaders.height, null);
		d.setColor(Color.WHITE);
		d.setFont(tellFont);
		d.drawString("Score: " + ObjectManager.score, 370 , 30);
		om.draw(d);
	}
	public void drawEndState(Graphics e) {
		e.setColor(Color.RED);
		e.fillRect(0,0,LeagueInvaders.width,LeagueInvaders.height);
		e.setFont(titleFont);
		e.setColor(Color.BLACK);
		e.drawString("Game Over", 120, 100);
		e.setFont(tellFont);
		e.drawString("You Killed " + ObjectManager.score +  " Enemies", 130, 300);
		e.drawString("Press Enter to Restart", 120, 500);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if(currentState == MENU_STATE){
             updateMenuState();
		}else if(currentState == GAME_STATE){
           	updateGameState();
		}else if(currentState == END_STATE){
    	   		updateEndState();
       }
	}
	 @Override
	 public void paintComponent(Graphics g){
	   if(currentState == MENU_STATE){
               drawMenuState(g);
       }else if(currentState == GAME_STATE){
               drawGameState(g);
       }else if(currentState == END_STATE){
               drawEndState(g);
       }
	 }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Typed");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
			else if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			}
			else if(currentState == GAME_STATE) {
				currentState = END_STATE;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME_STATE) {
			moveLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME_STATE) {
			moveRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && currentState == GAME_STATE) {
			moveUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && currentState == GAME_STATE) {
			moveDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME_STATE) {
			om.addProjectile(new Projectile(r.x + (r.width/2 - 5),r.y,10,10));
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		moveRight = false;
		moveLeft = false;
		moveUp = false;
		moveDown = false;
	}
}
