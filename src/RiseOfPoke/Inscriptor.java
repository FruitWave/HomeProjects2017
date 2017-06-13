package RiseOfPoke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Inscriptor extends JPanel implements ActionListener, KeyListener {
	Timer gameSpeed;
	ObjectManager book;
	int lordwidth = 100;
	int lordheight = 100;
	Poker lord = new Poker(500 - lordwidth, 500 - lordheight, lordwidth, lordheight, 5 /* health */);

	public Inscriptor() {
		gameSpeed = new Timer(1000 / 120, this);
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

	public void startGame() {
		gameSpeed.start();
		book.addObject(lord);
	}

}
