import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpawningItem extends GameObject {
	String bulletpack = "Bullet Pack";
	String healthpack = "Healthpack";
	String nuclearwarhead = "Nuke";
	String nukesuit = "Nuka-Cola";
	String typeparameter;
	String type1 = "Bullet";
	String type2 = "Health";
	String type3pt1 = "Nuke";
	String type3pt2 = "Nuke Suit";
	String type;
	Color colortype;
	Color bcolor = Color.BLACK;
	Color hcolor = Color.RED;
	Color ncolor = Color.MAGENTA;
	Color scolor = Color.WHITE;

	public SpawningItem(int x, int y, int width, int height, String typeparameter) {
		super(x, y, width, height);
		this.typeparameter = typeparameter;
		if (typeparameter.equalsIgnoreCase(bulletpack)) {
			System.out.println(".");
		}
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics quantummarshmallowcake) {
		quantummarshmallowcake.setColor(colortype);
	}

	public int randomColor() {
		int randomNum = new Random().nextInt(4);
		return (randomNum + 1);
	}
}
