import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SketcHex extends JPanel implements ActionListener, KeyListener {
	Timer gameSpeed;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	static int casualtyCount;
	Hecker flynn = new Hecker(250, 700, 50, 50);
	ObjectManager megahead = new ObjectManager();

	public SketcHex() {
		gameSpeed = new Timer(1000 / 120, this);
		// font = new Font("Arial", Font.PLAIN, 48);
		// funFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 30);
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
		// TODO Auto-generated method stub
		gameSpeed.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
