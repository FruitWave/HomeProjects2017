import java.awt.Color;
import java.awt.Graphics;

public class Hecker extends GameObject {
	int transpex;
	int transpey;
	int health;
	public int nukeCount;
	public int nukeSuitCount;
	public boolean nukeSuitEquipped;
	int imagewidth;
	int imageheight;
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
		width = imagewidth;
		height = imageheight;
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
			imagewidth = SketcHex.spectreright.getWidth();
			imageheight = SketcHex.spectreright.getHeight();
			pvd.drawImage(SketcHex.spectreright, x, y, imagewidth, imageheight, null);
		} else if (transpex < 0) {
			imagewidth = SketcHex.spectreleft.getWidth();
			imageheight = SketcHex.spectreleft.getHeight();
			pvd.drawImage(SketcHex.spectreleft, x, y, imagewidth, imageheight, null);
		} else {
			imagewidth = SketcHex.reaper.getWidth();
			imageheight = SketcHex.reaper.getHeight();
			pvd.drawImage(SketcHex.reaper, x, y, imagewidth, imageheight, null);
		}
	}
}
