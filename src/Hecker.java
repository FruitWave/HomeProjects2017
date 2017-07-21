import java.awt.Color;
import java.awt.Graphics;

public class Hecker extends GameObject {
	int transpex;
	int transpey;
	int health;
	public int nukeCount;
	public int nukeSuitCount;
	public boolean nukeSuitEquipped;
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
		if (health <= 0) {
			isAlive = false;
		}
	}

	public void draw(Graphics pvd) {
		// pvd.setColor(Color.BLACK);
		//
		// //*
		// pvd.fillRect(x, y, width, height);
		if (transpex > 0) {
			pvd.drawImage(SketcHex.spectreright, x, y, null);
		} else if (transpex < 0) {
			pvd.drawImage(SketcHex.spectreleft, x, y, null);
		} else {
			pvd.drawImage(SketcHex.reaper, x, y, null);

		}
	}
}
