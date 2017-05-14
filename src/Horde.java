import java.awt.Color;
import java.awt.Graphics;

public class Horde extends GameObject {
	SketcHex quarantine = new SketcHex();
	int transpex;
	int transpey;

	public Horde(int x, int y, int width, int height) {
		super(x, y, width, height);
		transpex = 0;
		transpey = 0;
	}

	public void update() {
		super.update();
		if (quarantine.flynn.x > x) {
			transpex++;
		} else if (quarantine.flynn.x > x) {
			transpex--;
		}
		if (quarantine.flynn.y > y) {
			transpey++;
		} else if (quarantine.flynn.y > y) {
			transpey--;
		}

	}

	public void draw(Graphics pvd) {
		// pvd.drawImage(SketcHex.alienImg, x, y, width, height,
		// null);
		pvd.setColor(Color.darkGray);
		pvd.fillRect(x, y, width, height);
	}
}