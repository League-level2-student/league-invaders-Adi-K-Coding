import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font otherMenuFont;
	Timer frameDraw;
	Rocketship rocketShip = new Rocketship(250, 700, 50, 50);
	ObjectManager objectManager = new ObjectManager(rocketShip);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer alienSpawn;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		otherMenuFont = new Font("Arial", Font.PLAIN, 15);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}

	}

	void updateMenuState() {

	}

	void updateGameState() {
		objectManager.update();
		if (rocketShip.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 125);
		g.setFont(otherMenuFont);
		g.drawString("Press ENTER to start", 175, 400);
		g.drawString("Press SPACE for instructions", 150, 600);

	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		objectManager.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + objectManager.getScore(), 50, 50);
	}

	void drawEndState(Graphics g) {
		// END STATE FONTS HERE
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 125);
		g.setFont(otherMenuFont);
		g.drawString("You killed "+objectManager.getScore()+" enimies", 165, 400);
		g.drawString("Press ENTER to restart", 170, 600);
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
		rocketShip=new Rocketship(250, 700, 50, 50);
			objectManager=new ObjectManager(rocketShip);
			} else if (currentState == MENU) {
				currentState++;
				startGame();
			} else if (currentState == GAME) {
				alienSpawn.stop();
			} else {
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (rocketShip.y > 0) {
					rocketShip.up();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (rocketShip.y < LeagueInvaders.HEIGHT - rocketShip.height) {
					rocketShip.down();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (rocketShip.x > 0) {
					rocketShip.left();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (rocketShip.x < LeagueInvaders.WIDTH+25- rocketShip.width)
					rocketShip.right();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				objectManager.addProjectile(rocketShip.getProjectile());
			}
		}
		if (currentState == MENU) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				JOptionPane.showMessageDialog(null, "Use the arrow keys to move around,"
						+ " use space to shoot,\n"
						+ "don't let the aliens touch your ship");
			}
		}
	}

	void startGame() {
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
