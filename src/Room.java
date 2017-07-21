import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Room extends GameObject implements ActionListener {
	SketcHex hex;
	HordeRunner dimensionTeller;
	int roomStuckTime = 0;
	int roomsroomnumber;
	boolean unspawnedhorde;
	Color color;
	Color darkdarkblue = new Color(0, 0, 50);
	int randomfunint1 = new Random().nextInt(255);
	int randomfunint2 = new Random().nextInt(255);
	int randomfunint3 = new Random().nextInt(255);
	Color randomfun = new Color(randomfunint1, randomfunint2, randomfunint3);
	int randomfunint11 = new Random().nextInt(255);
	int randomfunint22 = new Random().nextInt(255);
	int randomfunint33 = new Random().nextInt(255);
	Color randomfun2 = new Color(randomfunint11, randomfunint22, randomfunint33);
	int randomfunint111 = new Random().nextInt(255);
	int randomfunint222 = new Random().nextInt(255);
	int randomfunint333 = new Random().nextInt(255);
	Color randomfun3 = new Color(randomfunint111, randomfunint222, randomfunint333);

	// *
	Room newNonbaseRoom;
	int level = 1;
	int levelupper = 0;
	int leveluppermultiplier = 1;
	int leveluppermultipliercounter = 0;
	SpawningItem xenomorpheousSubstance;

	public Room(int x, int y, int width, int height, int roomsroomnumber, boolean unspawnedhorde, Color color,
			SketcHex hex) {
		super(x, y, width, height);
		this.hex = hex;
		this.roomsroomnumber = roomsroomnumber;
		this.unspawnedhorde = unspawnedhorde;
		this.color = color;

	}

	public void update() {
		super.update();

		// moving to the next room right
		if (hex.flynn.x >= 1000) {
			roomSwitch(true);
		} else if (hex.flynn.x <= 0) {
			roomSwitch(false);
		}

	}

	@SuppressWarnings("static-access")
	private void roomSwitch(boolean isRight) {
		Room r00m;
		int itemwidth = 20;
		int itemheight = 20;
		int randomItemX = new Random().nextInt(800 - itemwidth);
		int randomItemY = new Random().nextInt(800 - itemheight);
		hex.flynn.x += isRight ? -995 : 995;
		hex.flynnroomnumber += isRight ? 1 : -1;
		hex.roomcolors.add(color);
		Color a = randomColor();

		// *
		hex.roomColor = a;
		levelupper++;

		if ((levelupper >= level) && (levelupper % 2 == 0)) {
			int apoint = level;
			level += levelupper / 2;
			int bpoint = level;
			System.out.println("Level Upper is: " + levelupper);
			if (apoint != bpoint) {
				System.out.println("Level Up! (Now Level " + level + "!)");
			}
			levelupper = 0;
		}
		if (hex.megahead.getRoom(hex.flynnroomnumber) == null) {
			r00m = new Room(0, 0, dimensionTeller.width, dimensionTeller.height, hex.flynnroomnumber, true, a, hex);
			hex.megahead.addObject(r00m);
			if (isRight) {
				hex.megahead.addRoom(r00m, true);
				hex.hordeAdder = level;
			} else {
				hex.megahead.addRoom(r00m, false);
				hex.hordeAdder = level;
			}
			hex.enteredNewRoom(isRight, true);
			xenomorpheousSubstance = new SpawningItem(randomItemX + 200, randomItemY + 200, itemwidth, itemheight,
					"type unset", hex);
			hex.megahead.addObject(xenomorpheousSubstance);
		} else {
			r00m = hex.megahead.getRoom(hex.flynnroomnumber);
			hex.enteredNewRoom(isRight, false);
		}

		hex.onScreenRoom = r00m;
	}

	public int levelShower() {
		return level;

	}

	public Color randomColor() {
		Color roomColorSetter;
		int randomNum = new Random().nextInt(8);
		if (randomNum == 0) {
			roomColorSetter = randomfun;
		} else if (randomNum == 1) {
			roomColorSetter = Color.cyan;
		} else if (randomNum == 2) {
			roomColorSetter = Color.green;
		} else if (randomNum == 3) {
			roomColorSetter = Color.pink;
		} else if (randomNum == 4) {
			roomColorSetter = darkdarkblue;
		} else if (randomNum == 5) {
			roomColorSetter = Color.red;
		} else if (randomNum == 6) {
			roomColorSetter = randomfun2;
		} else if (randomNum == 7) {
			roomColorSetter = randomfun3;
		} else {
			roomColorSetter = Color.yellow;

		}
		return roomColorSetter;
	}

	// *
	public void draw(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((hex.flynn.x == 1000) || (hex.flynn.x == 0)) {
			roomStuckTime++;
			if (roomStuckTime == 2) {
				roomStuckTime = 0;
				hex.flynn.x += 200;
			} else {
				JOptionPane.showMessageDialog(null, "Moving in: " + (4 - roomStuckTime) + ".");
			}
		}
	}

}
