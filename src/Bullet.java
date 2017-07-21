import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends GameObject {
	int speed;
	SketcHex hex;
	boolean isGoingRight = true;
	Color darkred = new Color(148, 22, 10);
	int flyspeed;

	public Bullet(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		speed = 7;
		this.hex = hex;
	}

	public void update() {
		super.update();
		if (isGoingRight) {
			flyspeed = speed;
			x += flyspeed;
		} else if (isGoingRight == false) {
			flyspeed = -speed;
			x += flyspeed;
		}

	}

	public void draw(Graphics pvd) {

		if (flyspeed > 0) {
			pvd.drawImage(SketcHex.rightbulletImg, x, y, null);
		} else if (flyspeed < 0) {
			pvd.drawImage(SketcHex.leftbulletImg, x, y, null);
		}

	}
}
