import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();

	ObjectManager(Rocketship rocketShip) {
	rocket =rocketShip;
	}

	void addProjectile(Projectile p) {
		projectiles.add(new Projectile(0, 0, 0, 0));
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}
		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).update();
			if (aliens.get(j).y < 0) {
				projectiles.get(j).isActive = false;
			}
		}
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
			projectiles.remove(j);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		addAlien();
	}
}
