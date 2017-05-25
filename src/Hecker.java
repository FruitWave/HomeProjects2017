import java.awt.Color;
import java.awt.Graphics;

public class Hecker extends GameObject {
	int transpex;
	int transpey;
	int health;
	static int bulletAmmo;

	public Hecker(int x, int y, int width, int height, int flynnhealth, int bulletAmmo) {
		super(x, y, width, height);
		transpex = 5;
		transpex = 5;
		health = flynnhealth;
		Hecker.bulletAmmo = bulletAmmo;
	}

	public void update() {
		super.update();
		x += transpex;
		y += transpey;

	}

	public void draw(Graphics pvd) {
		pvd.setColor(Color.BLACK);
		pvd.fillRect(x, y, width, height);
	}
}
