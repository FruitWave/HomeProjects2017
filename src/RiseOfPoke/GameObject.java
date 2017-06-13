package RiseOfPoke;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Rectangle collisionArea = new Rectangle(x, y, width, height);

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update() {
		collisionArea.setBounds(x, y, width, height);
		if (y < (0)) {
			y = 0;
		} else if (y > 1000 - height) {
			y = 1000 - height;
		}

	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}