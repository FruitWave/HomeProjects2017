import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends GameObject {
	 int speed;
	SketcHex hex;

	public Bullet(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		speed = 7;
		this.hex = hex;
	}

	public void update() {
		super.update();
		// code to record mouse x at time of firing, then have the bullet travel
		// that way at the rate "speed"

		if (Hecker.bulletAmmo >= 25) {
			// 25 ammo or above
			int randomSide = new Random().nextInt(2);
			if (randomSide == 0) {
				x += 30;
				x -= speed;
			} else if (randomSide == 1) {
				x += speed;
			}
			// 25 ammo or above
		} else if (Hecker.bulletAmmo < 25) {
			if (hex.flynn.transpex > 0) {
				x -= speed;
			} else if (hex.flynn.transpex < 0) {
				x += speed;
			}
			if (hex.flynn.transpey > 0) {
				y -= speed;
			} else if (hex.flynn.transpey < 0) {
				y += speed;
			}
		}

	}

	public void draw(Graphics pvd) {
		pvd.setColor(Color.white);
		pvd.fillRect(x, y, width, height);
	}
}
