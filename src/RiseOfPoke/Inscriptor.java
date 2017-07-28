package RiseOfPoke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Inscriptor extends JPanel implements ActionListener, KeyListener {
	Timer gameSpeed;
	Timer levelUp;
	ObjectManager book;
	int lordwidth = 250;
	int lordheight = 250;
	public int level = 1;
	Poker lord = new Poker((PokeRunner.width / 2) - (lordwidth / 2), (PokeRunner.height / 2) - (lordheight / 2),
			lordwidth, lordheight, /* level */200 /* book.level */ /* health */);
	// for some reason, it doesn't like book.level as a parameter for health. It
	// says that there is a Null Pointer Exception--but everything seems to be in
	// order. With a raw integer put in, such as 1 or 2 or 3 or 5, the program runs
	// fine--so this is definitely the problem.
	// I shifted 'level' over to Inscriptor but leave, commented out, any
	// references to the prior 'level' there, for I hope to learn from this.
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	static int currentState = 0;
	static BufferedImage menubackround;
	static BufferedImage endbackround;
	static BufferedImage gamebackround;
	Font calisto;
	String statevar;
	boolean scorestarted = false;

	public Inscriptor() {
		gameSpeed = new Timer(1000 / 120, this);
		levelUp = new Timer(10000, this);
		calisto = new Font("Calisto MT", Font.CENTER_BASELINE, 25);
		try {
			menubackround = ImageIO.read(this.getClass().getResourceAsStream("menuimage.jpg"));
			endbackround = ImageIO.read(this.getClass().getResourceAsStream("endimage.jpg"));
			gamebackround = ImageIO.read(this.getClass().getResourceAsStream("gameimage.jpg"));
			// bulletImg =
			// ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics darkflood) {
		if (currentState == MENU_STATE) {
			drawMenuState(darkflood);
		} else if (currentState == GAME_STATE) {
			drawGameState(darkflood);
		} else if (currentState == END_STATE) {
			drawEndState(darkflood);
		}
		// drawGameState(darkflood);
	}

	private void drawEndState(Graphics i) {
		// TODO Auto-generated method stub
		i.drawImage(endbackround, 0, 0, null);
	}

	private void drawMenuState(Graphics darkflood) {
		// TODO Auto-generated method stub
		darkflood.drawImage(menubackround, 0, 0, null);
	}

	void drawGameState(Graphics b) {
		// b.setColor(Color.darkGray);
		// b.fillRect(0, 0, 1000, 1000);
		b.drawImage(gamebackround, 0, 0, null);
		book.draw(b);
		// show score code
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (currentState < 2)) {
			// if (currentState < 2) {
			// currentState++;
			// }
			currentState++;
			System.out.println("The current state is " + currentState + ".");
		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			fullRestart();
			System.out.println("Restart. The current state is " + currentState + ".");
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			if (currentState == MENU_STATE) {
				statevar = "menu";
			} else if (currentState == GAME_STATE) {
				statevar = "game";
			} else if (currentState == END_STATE) {
				statevar = "end";
			} else {
				statevar = "unknown";
			}
			System.out.println(statevar);
		}
		if (e.getKeyCode() == KeyEvent.VK_T) {
			System.out.println("Life: " + lord.health);
			System.out.println(System.currentTimeMillis());
		}
	}

	private void fullRestart() {
		currentState = MENU_STATE;
		book.score = 0;
		scorestarted = false;
		gameSpeed.restart();
		book.reset();
		// hordeAdder = 0;
		lord.isAlive = true;
		lord.health = level;
		// paused = false;
		startGame();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	void updateMenuState() {
		// TODO Auto-generated method stub

	}

	void updateGameState() {
		// TODO Auto-generated method stub
		book.update();
		if ((book.score > 0) && (scorestarted == false)) {
			scorestarted = true;
		}
		if (((lord.health <= 0) || (book.score <= 0)) && (scorestarted == true)) {
			lord.isAlive = false;
		}
		if (lord.isAlive == false) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		gameSpeed.start();
		book = new ObjectManager(this);
		book.addObject(lord);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gameSpeed) {
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

		if (e.getSource() == levelUp) {
			level++;
		}

	}
}
