import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive;
	Rectangle collisionBox;
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		collisionBox = new Rectangle(x,y,width,height);
	}
	public void update() {
		collisionBox.setBounds(x,y,width, height);
	}
}
class Rocketship extends GameObject{
	int speed = 5;
	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	public void update() {
		super.update();
		if (GamePanel.moveLeft == true) {
			x = x - speed;
		}
		if (GamePanel.moveRight == true) {
			x = x + speed;
		}
		if (GamePanel.moveUp == true) {
			y = y - speed;
		}
		if (GamePanel.moveDown == true) {
			y = y + speed;
		}
	}
	public void draw(Graphics D) {
		  D.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}
}
