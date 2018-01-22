import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
public class ObjectManager {
	ArrayList<Projectile> projectile;
	ArrayList<Alien> alien;
	Rocketship r;
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	static int score = 0;
	public ObjectManager(Rocketship r) {
		this.r = r;
		projectile =  new ArrayList<Projectile>();
		alien = new ArrayList<Alien>();
	}
	int getScore() { 
		return score;
	}
	public void update() {
		r.update();
		for(Projectile p: projectile) {
			p.update();
		}
		for(Alien a: alien) {
			a.update();
		}
	}
	public void draw(Graphics d) {
		r.draw(d);
		for(Projectile p: projectile) {
			p.draw(d);
		}
		for(Alien a: alien) {
			a.draw(d);
		}
	}
	public void addProjectile(Projectile p) {
		projectile.add(p);
	}
	public void addAlien(Alien a) {
		alien.add(a);
	}
	public void manageEnemies(){
        if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
        		addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));
            enemyTimer = System.currentTimeMillis();
        }
	}
	public void checkCollision() {
			for(Projectile p: projectile) {
				for(Alien a: alien) {
					if(p.collisionBox.intersects(a.collisionBox)) {
						a.isAlive = false;
						score++;
						p.isAlive = false;
					}
				}
			}
			for(Alien a: alien) {
				if(r.collisionBox.intersects(a.collisionBox)) {
					r.isAlive = false;
				}
			}
		}	
	public void purgeObjects() {
		for(int i = 0;i < alien.size(); i++) {
			if(!alien.get(i).isAlive) {
				alien.remove(i);
			}
		}
		for(int j = 0;j < projectile.size(); j++) {
			if(!projectile.get(j).isAlive) {
				projectile.remove(j);
			}
		}
	}
	public void reset() {
		alien.clear();
	}
}
