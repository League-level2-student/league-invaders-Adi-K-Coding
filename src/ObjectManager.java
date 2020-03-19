import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
private int score=0;
	
ObjectManager(Rocketship rocketShip) {
		rocket = rocketShip;
	}

	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}
		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).update();
		}
	checkCollisions();
	purgeObjects();
rocket.update();
}

	void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).draw(g);
		}
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		for (int j = 0; j < projectiles.size(); j++) {
			if(projectiles.get(j).isActive==false) {
				projectiles.remove(j);
			}
		}
	}
public int getScore() {
	return score;
}
	void checkCollisions() {
		for (int i = 0; i < aliens.size(); i++) {
			for (int j = 0; j < projectiles.size(); j++) {
				if (aliens.get(i).collisionBox.intersects(projectiles.get(j).collisionBox)) {
					aliens.get(i).isActive = false;
					projectiles.get(j).isActive = false;
				score=score+1;
				}
			}
			if (aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
			System.out.println("FALSE");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		addAlien();
	}
}
