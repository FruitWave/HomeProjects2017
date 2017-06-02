import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SketcHex extends JPanel implements ActionListener, KeyListener {
	Color roomColor;
	Timer gameSpeed;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	static int currentState = 0;
	static int casualtyCount;
	Hecker flynn = new Hecker(250, 700, 30, 60, 10, 100);
	Horde arnold;
	Horde rick;
	ObjectManager megahead;
	Room base;
	Font font;
	Font funFont;
	public int roomsEntered = 0;
	public int hordeAdder = 0;

	public SketcHex() {
		gameSpeed = new Timer(1000 / 120, this);
		// font = new Font("Arial", Font.PLAIN, 48);
		// Font funFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 30);
		casualtyCount = 0;
		font = new Font("Arial", Font.PLAIN, 48);
		funFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 30);

		// try {
		// alienImg =
		// ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
		// rocketImg =
		// ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
		// bulletImg =
		// ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	public void enteredNewRoom(boolean isGoingRight) {
		roomsEntered++;
		int xdisplacement = -1000;
		if (isGoingRight) {
			System.out.println("Going right! WHEEEEEEE!");
		} else {
			xdisplacement = 1000;
		}
		megahead.manageEnemies(xdisplacement);
		addToHorde(hordeAdder);
	}

	public void bulletfirel() {
		System.out.println("pew pew left");
		Bullet bullet = new Bullet(flynn.x, flynn.y + (flynn.height/2), 8, 4, this);
		bullet.leftorrightLEFTisZEROrightISone = 0;
		megahead.addObject(bullet);
	}
	public void bulletfirer() {
		System.out.println("pew pew right");
		Bullet bullet = new Bullet(flynn.x + flynn.width, flynn.y + (flynn.height/2), 8, 4, this);
		bullet.leftorrightLEFTisZEROrightISone = 1;
		megahead.addObject(bullet);
	}

	public void addToHorde(int a) {
		for (int i = 1; i < (a + 1); i++) {
			int randomx = new Random().nextInt((700) + 200);
			int randomy = new Random().nextInt((700) + 200);
			Horde dissolvent = new Horde(randomx, randomy, 30, 60, this);
			megahead.addObject(dissolvent);
			System.out.println("Zombie add count " + i);
		}

	}

	public void startGame() {
		gameSpeed.start();
		arnold = new Horde(20, 500, 30, 60, this);
		rick = new Horde(20, 600, 30, 60, this);
		base = new Room(0, 0, 1000, 1000, this);
		megahead = new ObjectManager(this);
		megahead.addObject(flynn);
		megahead.addObject(arnold);
		megahead.addObject(rick);
		megahead.addObject(base);
		roomColor = Color.green;
	}

	void updateMenuState() {

	}

	void updateGameState() {
		megahead.update();

		if (flynn.isAlive == false) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics a) {
		a.setColor(Color.blue);
		a.fillRect(0, 0, 1000, 1000);
	}

	void drawGameState(Graphics b) {
		b.setColor(roomColor);
		b.fillRect(0, 0, 1000, 1000);
		megahead.draw(b);

	}

	void drawEndState(Graphics c) {
		c.setColor(Color.red);
		c.fillRect(0, 0, 1000, 1000);
		c.setFont(font);
		c.setColor(Color.WHITE);
		c.drawString("GAME OVER", 370, 300);
		c.setColor(Color.BLACK);
		c.drawString("You killed " + casualtyCount + " aliens.", 340, 400);
		c.setFont(funFont);
		c.setColor(Color.WHITE);
		c.drawString("press delete to restart", 355,
				500);/**
						 * the below showScore method/variable/code does not
						 * work. This message has been printed below every
						 * instance where something related to it is mentioned.
						 */
		// megahead.showScore(c);
	}

	public void paintComponent(Graphics delta) {
		if (currentState == MENU_STATE) {
			drawMenuState(delta);
		} else if (currentState == GAME_STATE) {
			drawGameState(delta);
		} else if (currentState == END_STATE) {
			drawEndState(delta);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			flynn.transpey = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			flynn.transpex = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			flynn.transpex = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			flynn.transpey = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			bulletfirel();
		} else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			bulletfirer();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
			System.out.println("The current state is " + currentState + ".");
		}
		if ((e.getKeyCode() == 8) && (currentState >= END_STATE)) {
			casualtyCount = 0;
			currentState = MENU_STATE;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		flynn.transpex = 0;
		flynn.transpey = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		// System.out.println("action performed");
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

}
