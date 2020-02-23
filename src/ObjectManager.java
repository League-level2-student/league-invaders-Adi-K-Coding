import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket = new Rocketship(0, 0, 0, 0);
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();

	ObjectManager(int x, int y, int width, int height) {
	}

	void addProjectile(Projectile p) {
		projectiles.add(new Projectile(0, 0, 0, 0));
	}

	void addAlien(Alien a) {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {

	}
	for (int i = 0; i < aliens.size(); i++) {
		
	}
}
