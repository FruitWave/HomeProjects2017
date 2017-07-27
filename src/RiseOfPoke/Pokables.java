package RiseOfPoke;

import java.awt.Color;
import java.awt.Graphics;

public class Pokables extends GameObject {
	int speed;
	Color truecolor = Color.white;
	/**
	 * the peasant spawn guard protects Lord from being damaged in the case of a
	 * peasant being spawned on it
	 */
	public boolean peasantSpawnGuardOn;

	public Pokables(int x, int y, int width, int height, int speed) {
		super(x, y, width, height);
		this.speed = speed;

	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.setColor(truecolor);
		g.fillRect(x, y, width, height);
	}
}
