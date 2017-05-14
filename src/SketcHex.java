import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SketcHex extends JPanel implements ActionListener, KeyListener {
	Timer gameSpeed;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	static int currentState = 0;
	static int casualtyCount;
	Hecker flynn = new Hecker(250, 700, 30, 60);
	Horde arnold = new Horde(20, 500, 30, 60);
	ObjectManager megahead = new ObjectManager();

	public SketcHex() {
		gameSpeed = new Timer(1000 / 120, this);
		// font = new Font("Arial", Font.PLAIN, 48);
		// Font funFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 30);
		casualtyCount = 0;
		megahead.addObject(flynn);
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

	public void startGame() {
		gameSpeed.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		flynn.update();
		arnold.update();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics a) {
		a.setColor(Color.blue);
		a.fillRect(0, 0, 1000, 1000);
	}

	void drawGameState(Graphics b) {
		b.setColor(Color.GREEN);
		b.fillRect(0, 0, 1000, 1000);
		flynn.draw(b);
		arnold.draw(b);

	}

	void drawEndState(Graphics c) {
		c.setColor(Color.red);
		c.fillRect(0, 0, 1000, 1000);
		/**
		 * the below showScore method/variable/code does not work. This message
		 * has been printed below every instance where something related to it
		 * is mentioned.
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
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			flynn.transpey = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			flynn.transpex = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			flynn.transpex = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			flynn.transpey = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			System.out.println("pew pew");
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
			System.out.println(currentState);
		}

		if (e.getKeyCode() == KeyEvent.VK_1) {
			String a = JOptionPane.showInputDialog("Please enter the additional score amount below.");
			int b = Integer.parseInt(a);
			megahead.cheatScore(b);
			System.out.println(megahead.getScore());
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
