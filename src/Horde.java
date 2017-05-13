import java.awt.Color;
import java.awt.Graphics;

public class Horde extends GameObject {
	public Horde(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update() {
		super.update();
		y++;

	}

	public void draw(Graphics littleBlueBox) {
		//littleBlueBox.drawImage(SketcHex.alienImg, x, y, width, height, null);

	}
}