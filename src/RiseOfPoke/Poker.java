package RiseOfPoke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Poker extends GameObject {

	int health;
	Color color = new Color(50, 250, 250);

	public Poker(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		this.health = health;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if (health <= 0) {
			isAlive = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.drawOval(x, y, width, height);
	}
}
