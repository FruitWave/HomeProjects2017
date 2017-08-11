import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;

public class SpawningItem extends GameObject {
	String typeparameter;
	String type1 = "Bullet Cartridge";
	String type2 = "Healthpack";
	String type3pt1 = "Nuke";
	String type3pt2 = "Nuka-Cola Suit";
	String type0 = "Nothing";
	Color colortype;
	// *
	Color bcolor = Color.BLACK;
	Color hcolor = Color.RED;
	Color ncolor = Color.GREEN;
	Color scolor = Color.WHITE;

	// *
	SketcHex hex;

	public SpawningItem(int x, int y, int width, int height, String typeparameto, SketcHex hex) {
		super(x, y, width, height);
		typeparameter = typeparameto;
		this.hex = hex;
		typeparameter = typeSetter();
	}

	public void update() {
		super.update();
		if (typeparameter.equals(type0)) {
			isAlive = false;
		}
		// System.out.println("typeparameter is: " + typeparameter);
	}

	public String typeSetter() {
		int rannum = randomNumber();
		String typeset = parseRandomNumber(rannum);
		if (typeset.equals(null)) {
			JOptionPane.showMessageDialog(null, "Something went wrong. Typset is null.");
		}
		// typeparameter.equals(typeset);
		// the above line doesn't seem to work with any temporary string for
		// xeno in the
		// room class, such as "type unset", so i made this method have a return
		// type to
		// give a new item its type in the constructor.
		System.out.println("Item is a " + typeset + ".");
		System.out.println("Item is at " + x + ", " + y + ".");
		return typeset;

	}

	public String parseRandomNumber(int randomNumber) {
		System.out.println("Random number is " + randomNumber + ".");
		if (randomNumber < 9) {
			return type0;
		} else if ((randomNumber >= 9) && (randomNumber < 12)) {
			return type1;
		} else if (((randomNumber >= 12) && (randomNumber < 14)) || ((randomNumber >= 21) && (randomNumber <= 25))) {
			return type2;
		} else if (((randomNumber >= 14) && (randomNumber < 16)) || (randomNumber == 20)) {
			return type3pt1;
		} else if ((randomNumber >= 16) || (randomNumber <= 19)) {
			return type3pt2;
		} else {
			return "Random type generator sequence failed.";
		}
	}

	public int randomNumber() {
		int randomNum = new Random().nextInt(25);
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
		} else if (typeparameter.equals(type0)) {
			colortype = Color.red;
		}

		if (colortype == bcolor) {
			quantummarshmallowcake.drawImage(SketcHex.bpGun, x, y, width, height, null);
		} else if (colortype == hcolor) {
			quantummarshmallowcake.drawImage(SketcHex.healthpackimg, x, y, width, height, null);
		} else if (colortype == ncolor) {
			quantummarshmallowcake.drawImage(SketcHex.nukeimg, x, y, width, height, null);
		} else if (colortype == scolor) {
			quantummarshmallowcake.drawImage(SketcHex.nukacola, x, y, width, height, null);
		} else {
			quantummarshmallowcake.drawImage(SketcHex.notFound, x, y, width, height, null);
		}
		// *
		// System.out.println("Colortype: " + colortype);
		// quantummarshmallowcake.setColor(colortype);

		// *
		// quantummarshmallowcake.fillRect(x, y, width, height);
		// System.out.println("stats: " + x + " " + y + " " + width + " " +
		// height);
	}
}
