import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends GameObject {
	int speed;
	SketcHex hex;
	boolean isGoingRight = true;
	Color darkred = new Color(148, 22, 10);

	public Bullet(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		speed = 7;
		this.hex = hex;
	}

	public void update() {
		super.update();
		if (isGoingRight) {
			x += speed;
		} else if (isGoingRight == false) {
			x -= speed;
		}

	}

	public void draw(Graphics pvd) {
		pvd.setColor(darkred);
		//*
		pvd.fillRect(x, y, width, height);
	}
}
