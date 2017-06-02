import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Room extends GameObject {
	SketcHex hex;

	public Room(int x, int y, int width, int height, SketcHex hex) {
		super(x, y, width, height);
		this.hex = hex;
	}

	public void update() {
		super.update();
		// AS COMMENTED OUT BELOW, CHANGE THE CODE SO THAT THE PREVIOUS ROOM'S
		// COLOR IS SAVED, AND WHEN FLYNN GOES BACK INTO THAT ROOM ITS COLOR IS
		// THE SAME (MASSIVE EDIT)

		// moving to the next room right
		if (hex.flynn.x >= 1000) {
			System.out.println(">= 1000 x 2nd");
			hex.hordeAdder = 2;
			hex.flynn.x -= 1000;

			Color a = randomXColor();
			hex.roomColor = a;
			hex.enteredNewRoom(true);
		}
		// moving to the next room left
		if (hex.flynn.x <= 0) {
			System.out.println("<= 0 x");
			hex.hordeAdder = 2;
			hex.flynn.x += 1000;
			Color a = randomXColor();
			hex.roomColor = a;
			hex.enteredNewRoom(false);
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
			/* THIS ACTUALLY OCURRED FIGURE OUT WHY AND HOW TO FIX IT */
		}
		return roomColorSetter;
	}

	public void draw(Graphics g) {

	}

}
