package RiseOfPoke;

import javax.swing.JFrame;

public class PokeRunner {
	JFrame frame;
	static final int width = 1665;
	static final int height = 1040;
	Inscriptor sand;

	// constructor
	public PokeRunner() {
		// TODO Auto-generated constructor stub

		frame = new JFrame();
		sand = new Inscriptor();
		setup();
	}

	public static void main(String[] args) {
		PokeRunner nudge = new PokeRunner();
	}

	void setup() {
		frame.add(sand);
		frame.setTitle("Rise Of Poke");
		frame.addKeyListener(sand);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sand.startGame();
	}
}
