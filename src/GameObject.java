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
		if (y < SketcHex.statisticsrectheight) {
			y = SketcHex.statisticsrectheight;
		} else if (y > HordeRunner.height - height) {
			y = HordeRunner.height - height;
		}
		// fix sinking into floor D:
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
