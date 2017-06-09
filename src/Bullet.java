import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends GameObject {
	int speed;
	SketcHex hex;
	public int leftorrightLEFTisZEROrightISone = 0;
	Color darkred = new Color(148, 22, 10);

	public Bullet(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		speed = 7;
		this.hex = hex;
	}

	public void update() {
		super.update();

		if (leftorrightLEFTisZEROrightISone == 0) {
			x -= speed;
		} else if (leftorrightLEFTisZEROrightISone == 1) {
			x += speed;
		}
		// } //screwed up desperation mode below
		// else if (Hecker.bulletAmmo < 25) {
		// if (hex.flynn.transpex > 0) {
		// x -= speed;
		// } else if (hex.flynn.transpex < 0) {
		// x += speed;
		// }
		// //// if (hex.flynn.transpey > 0) {
		// //// y -= speed;
		// //// } else if (hex.flynn.transpey < 0) {
		// //// y += speed;
		// //// }
		// }

	}

	public void draw(Graphics pvd) {
		pvd.setColor(darkred);
		pvd.fillRect(x, y, width, height);
	}
}
