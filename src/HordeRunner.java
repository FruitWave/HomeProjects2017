import javax.swing.JFrame;

public class HordeRunner {
	JFrame frame;
	static final int width = 1000;
	static final int height = 800;
	SketcHex hex;

	// constructor
	public HordeRunner() {
		frame = new JFrame();
		hex = new SketcHex();
		setup();
	}

	public static void main(String[] args) {
		HordeRunner battlehead = new HordeRunner();
	}

	void setup() {
		frame.add(hex);
		frame.setTitle("League Invaders");
		frame.addKeyListener(hex);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hex.startGame();
	}
}
