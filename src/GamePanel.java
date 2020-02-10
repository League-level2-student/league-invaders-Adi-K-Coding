import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		otherMenuFont = new Font("Arial", Font.PLAIN, 15);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {

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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		rocketShip.draw(g);
	}

	void drawEndState(Graphics g) {
		// END STATE FONTS HERE
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 125);
		g.setFont(otherMenuFont);
		g.drawString("You killed 0 enimenimies", 165, 400);
		g.drawString("Press ENTER to restart", 170, 600);
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
		System.out.println("Action");
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
			} else {
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (rocketShip.y > 0) {
					rocketShip.up();
				}
			} if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (rocketShip.y < LeagueInvaders.HEIGHT - rocketShip.height) {
					rocketShip.down();
				}
			}if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (rocketShip.x > 0) {
					rocketShip.left();
				}
			}if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(rocketShip.x<LeagueInvaders.WIDTH-rocketShip.width)
				rocketShip.right();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

}
