import java.awt.Color;
import java.awt.Graphics;

public class Hecker extends GameObject {
	int transpex;
	int transpey;

	public Hecker(int x, int y, int width, int height) {
		super(x, y, width, height);
		transpex = 5;
		transpex = 5;
	}

	public void update() {
		super.update();
		x += transpex;
		y += transpey;
		if (x >= 1000) {

		}
	}

	public void draw(Graphics pvd) {
		pvd.setColor(Color.BLACK);
		pvd.fillRect(x, y, width, height);
	}
}
