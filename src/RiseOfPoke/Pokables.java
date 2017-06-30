package RiseOfPoke;

import java.awt.Color;
import java.awt.Graphics;

public class Pokables extends GameObject {

	public Pokables(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
