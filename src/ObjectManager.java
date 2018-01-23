import java.awt.Color;
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
	boolean alienMadeIt = false;
	public ObjectManager(Rocketship r) {
		this.r = r;
		projectile =  new ArrayList<Projectile>();
		alien = new ArrayList<Alien>();
	}
	int getNewTimer() {
		if(score == 25) {
			enemySpawnTime = 900;
		}
		if(score == 50) {
			enemySpawnTime = 800;
		}
		if(score == 75) {
			enemySpawnTime = 700;
		}
		if(score == 100) {
			enemySpawnTime = 600;
		}
		if(score == 125) {
			enemySpawnTime = 500;
		}
		if(score == 150) {
			enemySpawnTime = 400;
		}
		if(score == 175) {
			enemySpawnTime = 300;
		}
		if(score == 200) {
			enemySpawnTime = 200;
		}
		if(score > 350) {
			enemySpawnTime = (int) (enemySpawnTime* 0.001);
		}
		return enemySpawnTime;
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
		int spawnTimer = getNewTimer();
        if(System.currentTimeMillis() - enemyTimer >= spawnTimer){
        		addAlien(new Alien(new Random().nextInt(LeagueInvaders.width - 50), 0, 50, 50));
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
		for(Alien a: alien) {
			if(a.y > 800) {
				alienMadeIt = true;
			}
		}
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
