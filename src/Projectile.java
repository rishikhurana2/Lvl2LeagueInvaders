import java.awt.Graphics;

public class Projectile extends GameObject{
	int speed = 8;
	public Projectile(int x, int y, int width, int height) {
		super(x,y,width,height);
	}
	public void update() {
		super.update();
		y = y - speed;
	}
	public void draw(Graphics d) {
		d.drawImage(GamePanel.bulletImg, x, y, width, height, null);
	}
}
