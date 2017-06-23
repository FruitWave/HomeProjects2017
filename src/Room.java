import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Room extends GameObject implements ActionListener {
	SketcHex hex;
	int roomStuckTime = 0;

	public Room(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		this.hex = hex;

	}

	public void update() {
		super.update();
		// CHANGE THE CODE SO THAT THE PREVIOUS ROOM'S
		// COLOR IS SAVED, AND WHEN FLYNN GOES BACK INTO THAT ROOM ITS COLOR IS
		// THE SAME (MASSIVE EDIT)

		// moving to the next room right
		if (hex.flynn.x >= 1000) {
			hex.hordeAdder = 2;
			hex.flynn.x -= 999;
			Color a = randomXColor();
			hex.roomColor = a;
			hex.enteredNewRoom(true);
			hex.roomnumber++;
		}
		// moving to the next room left
		if (hex.flynn.x <= 0) {
			hex.hordeAdder = 2;
			hex.flynn.x += 999;
			Color a = randomXColor();
			hex.roomColor = a;
			hex.enteredNewRoom(false);
			hex.roomnumber--;
		}
	}

	public Color randomXColor() {
		Color roomColorSetter;
		int randomNum = new Random().nextInt((5) + 1);
		if (randomNum == 1) {
			roomColorSetter = Color.lightGray;
		} else if (randomNum == 2) {
			roomColorSetter = Color.cyan;
		} else if (randomNum == 3) {
			roomColorSetter = Color.green;
		} else if (randomNum == 4) {
			roomColorSetter = Color.pink;
		} else if (randomNum == 5) {
			roomColorSetter = Color.white;
		} else if (randomNum == 6) {
			roomColorSetter = Color.red;
		} else {
			roomColorSetter = Color.yellow;

		}
		return roomColorSetter;
	}

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
				System.out.println("Moving in: " + (4 - roomStuckTime) + ".");
			}
		}
	}

}
