import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Horde extends GameObject {
	SketcHex hex;
	int transpex;
	int transpey;
	BufferedImage imagex;

	// *
	int speed;
	int deathPotential;
	int health;
	int level;

	public Horde(int x, int y, int width, int height, SketcHex hex, int health, BufferedImage imagex) {

		// *
		super(x, y, width, height);
		transpex = 0;
		transpey = 0;
		this.hex = hex;
		this.imagex = imagex;
		this.speed = 1;
		deathPotential = 1;
		this.health = health;
		level = 1;
	}

	public void update() {
		super.update();
		if (hex.flynn.x > x) {
			transpex = 0;
			transpex += speed;
		} else if (hex.flynn.x < x) {
			transpex = 0;
			transpex -= speed;
		}
		if (hex.flynn.y > y) {
			transpey = 0;
			transpey += speed;
		} else if (hex.flynn.y < y) {
			transpey = 0;
			transpey -= speed;
		}
		x += transpex;
		y += transpey;
		// fix shaking

	}

	public void draw(Graphics pvd) {
		pvd.drawImage(imagex, x, y, width, height, null);
		// pvd.setColor(color);

		// *
		// pvd.fillRect(x, y, width, height);
	}

}