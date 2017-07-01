import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Room extends GameObject implements ActionListener {
	SketcHex hex;
	HordeRunner dimensionTeller;
	int roomStuckTime = 0;
	int roomsroomnumber;
	boolean unspawnedhorde;
	Color color;
	Color darkdarkblue = new Color(0, 0, 50);
	int randomfunint1 = new Random().nextInt(256);
	int randomfunint2 = new Random().nextInt(256);
	int randomfunint3 = new Random().nextInt(256);
	Color randomfun = new Color(randomfunint1, randomfunint2, randomfunint3);
	int randomfunint11 = new Random().nextInt(256);
	int randomfunint22 = new Random().nextInt(256);
	int randomfunint33 = new Random().nextInt(256);
	Color randomfun2 = new Color(randomfunint11, randomfunint22, randomfunint33);

	Room newNonbaseRoom;

	// save the rooms color, "if roomnumber is this then roomcolor is this",
	// kill the room, create a new room
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
		// CHANGE THE CODE SO THAT THE PREVIOUS ROOM'S
		// COLOR IS SAVED, AND WHEN FLYNN GOES BACK INTO THAT ROOM ITS COLOR IS
		// THE SAME (MASSIVE EDIT)

		// moving to the next room right
		if (hex.flynn.x >= 1000) {
			roomSwitch(true);
		} else if (hex.flynn.x <= 0) {
			roomSwitch(false);
		}

	}

	private void roomSwitch(boolean isRight) {
		hex.hordeAdder = 2;
		hex.flynn.x += isRight ? -995 : 995;
		hex.enteredNewRoom(true);
		hex.flynnroomnumber += isRight ? 1 : -1;
		hex.roomcolors.add(color);
		Color a = randomColor();
		hex.roomColor = a;
		Room r00m = new Room(0, 0, dimensionTeller.width, dimensionTeller.height, hex.flynnroomnumber, true, a,
				hex);
		hex.onScreenRoom = r00m;
	}

	public Color randomColor() {
		Color roomColorSetter;
		int randomNum = new Random().nextInt(7);
		if (randomNum == 0) {
			roomColorSetter = randomfun2;
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
			roomColorSetter = randomfun;
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
