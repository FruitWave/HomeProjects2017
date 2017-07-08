import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpawningItem extends GameObject {
	String typeparameter;
	String type1 = "Bullet Cartridge";
	String type2 = "Healthpack";
	String type3pt1 = "Nuke";
	String type3pt2 = "Nuka-Cola Suit";
	String type;
	Color colortype;
	Color bcolor = Color.BLACK;
	Color hcolor = Color.RED;
	Color ncolor = Color.GREEN;
	Color scolor = Color.WHITE;
	boolean isDetonated = false;
	SketcHex hex;

	public SpawningItem(int x, int y, int width, int height, String typeparameter, SketcHex hex) {
		super(x, y, width, height);
		this.typeparameter = typeparameter;
		this.hex = hex;
	}

	public void update() {
		super.update();
		if ((typeparameter.equals(type3pt1)) && (isDetonated == true)) {
			isAlive = false;
			hex.flynn.nukeCount--;
			if (hex.flynn.nukeSuitEquipped) {
				hex.flynn.nukeSuitCount--;
				hex.flynn.nukeSuitEquipped = false;
			} else {
				hex.flynn.isAlive = false;
			}

		}
	}

	public void typeSetter() {
		int rannum = randomNumber();
		String typeset = parseRandomNumber(rannum);
		typeparameter.equals(typeset);
		System.out.println("Item is a " + typeset + ".");

	}

	public String parseRandomNumber(int randomNumber) {
		System.out.println("Random number is " + randomNumber + ".");
		if (randomNumber < 9) {
			return null;
		} else if ((randomNumber >= 9) && (randomNumber < 12)) {
			return type1;
		} else if ((randomNumber >= 12) && (randomNumber < 14)) {
			return type2;
		} else if ((randomNumber >= 14) && (randomNumber < 16)) {
			return type3pt1;
		} else if (randomNumber == 4) {
			return type3pt2;
		} else {
			return "Random type generator sequence failed.";
		}
	}

	public int randomNumber() {
		int randomNum = new Random().nextInt(16);
		return (randomNum + 1);
	}

	public void draw(Graphics quantummarshmallowcake) {
		if (typeparameter.equals(type1)) {
			colortype = bcolor;
		} else if (typeparameter.equals(type2)) {
			colortype = hcolor;
		} else if (typeparameter.equals(type3pt1)) {
			colortype = ncolor;
		} else if (typeparameter.equals(type3pt2)) {
			colortype = scolor;
		}
		quantummarshmallowcake.setColor(colortype);
		quantummarshmallowcake.fillRect(x, y, width, height);
	}
}
