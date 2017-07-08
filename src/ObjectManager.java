
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	ArrayList<Room> rooms;
	SketcHex hex;
	Color dark1 = new Color(100, 50, 50);
	Color dark2 = new Color(200, 50, 80);
	Color dark3 = new Color(250, 100, 10);
	/**
	 * the below showScore method/variable/code does not work. This message has been
	 * printed below every instance where something related to it is mentioned.
	 */
	// Font funFont;
	long enemyTimer = 0;
	int enemySpawnTime = 1;

	public ObjectManager(SketcHex hex) {
		objects = new ArrayList<GameObject>();
		rooms = new ArrayList<Room>();
		this.hex = hex;
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
		}
		checkCollision();
		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
	}

	public void addRoom(Room r, boolean isRight) {
		if (isRight) {
			rooms.add(r);
			addObject(r);
		} else {
			rooms.add(0, r);
			addObject(r);
		}
	}

	public Room getRoom(int roomNumber) {
		for (Room r : rooms) {
			if (r.roomsroomnumber == roomNumber) {
				return r;
			}
		}
		return null;
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void manageEnemiesAndItems(int xdisplacement) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o1 = objects.get(i);
			if ((o1 instanceof Horde) || (o1 instanceof SpawningItem)) {
				o1.x += xdisplacement;
			}
		}

	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				// what is the purpose of the 'i' for loop enclosing the 'j' for loop?
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);

				if (o1.collisionArea.intersects(o2.collisionArea)) {

					if ((o1 instanceof Horde && o2 instanceof Bullet)
							|| (o2 instanceof Horde && o1 instanceof Bullet)) {
						Bullet bullet = (o1 instanceof Bullet) ? (Bullet) o1 : (Bullet) o2;
						Horde shotHorde = (o1 instanceof Horde) ? (Horde) o1 : (Horde) o2;
						shotHorde.health -= 1;
						o2.isAlive = false;
						if (shotHorde.health <= 0) {
							shotHorde.isAlive = false;
							SketcHex.casualtyCount += shotHorde.deathPotential;

						}
					} else if ((o1 instanceof Horde && o2 instanceof Hecker)
							|| (o2 instanceof Horde && o1 instanceof Hecker)) {

						Horde hordie = o1 instanceof Horde ? (Horde) o1 : (Horde) o2;
						Hecker omflynn = o1 instanceof Hecker ? (Hecker) o1 : (Hecker) o2;

						int randomDisplacementNum = new Random().nextInt(2);
						if (randomDisplacementNum == 0) {
							hordie.x -= 200;
						} else if (randomDisplacementNum == 1) {
							hordie.x += 200;
						}
						omflynn.health -= hordie.deathPotential;
						System.out.println("Flynn's health is now " + omflynn.health + ".");

						if (omflynn.health <= 0) {
							omflynn.isAlive = false;
						} else if (hordie.health <= 0) {
							hordie.isAlive = false;
						}
					} else if ((o1 instanceof SpawningItem && o2 instanceof Hecker)
							|| (o2 instanceof SpawningItem && o1 instanceof Hecker)) {

						SpawningItem item = o1 instanceof SpawningItem ? (SpawningItem) o1 : (SpawningItem) o2;
						Hecker imflynnity = o1 instanceof Hecker ? (Hecker) o1 : (Hecker) o2;

						// System.out.println("Flynn's health is now " + imflynnity.health + ".");
						if (item.typeparameter.equals(item.type1)) {
							imflynnity.bulletAmmo += 10;
							System.out.println("Stats up code reached (" + item.typeparameter + ").");
						} else if (item.typeparameter.equals(item.type2)) {
							imflynnity.health += 15;
							System.out.println("New Health: " + imflynnity.health);
							System.out.println("Stats up code reached (" + item.typeparameter + ").");

						} else if (item.typeparameter.equals(item.type3pt1)) {
							imflynnity.nukeCount += 1;
							System.out.println("New Nuke Count: " + imflynnity.nukeCount);
							System.out.println("Stats up code reached (" + item.typeparameter + ").");

						} else if (item.typeparameter.equals(item.type3pt2)) {
							imflynnity.nukeSuitCount += 1;
							System.out.println("New Nuke Count: " + imflynnity.nukeSuitCount);
							System.out.println("Stats up code reached (" + item.typeparameter + ").");

						}
						System.out.println("Item " + item.typeparameter + " picked up!");
						item.isAlive = false;
					} else if (o1 instanceof Horde && o2 instanceof Horde) {
						Horde zombie = (Horde) o1;
						// System.out.println("zombie initial health is at "
						// + zombie.health);
						Horde zombietwo = (Horde) o2;
						if ((zombie.color == Color.darkGray) && (zombietwo.color == Color.darkGray)) {
							zombie.color = Color.magenta;
							hordefunctionslowerlvl(zombie, zombietwo);
							System.out.println("Minihorde!");
						} else if ((zombie.color == Color.magenta) && (zombietwo.color == Color.magenta)) {
							zombie.color = Color.green;
							hordefunctionslowerlvl(zombie, zombietwo);
							System.out.println("Horde!");
						} else if ((zombie.color == Color.green) && (zombietwo.color == Color.green)) {
							zombie.color = Color.blue;
							hordefunctionslowerlvl(zombie, zombietwo);
							System.out.println("Megahorde!");
						} else if ((zombie.color == Color.blue) && (zombietwo.color == Color.blue)) {
							zombie.color = Color.red;
							hordefunctionshigherlvl(zombie, zombietwo);
							System.out.println("Ultrahorde!");
						} else if ((zombie.color == Color.red) && (zombietwo.color == Color.red)) {
							zombie.color = Color.yellow;
							hordefunctionshigherlvl(zombie, zombietwo);
							System.out.println("Hellhorde!");
						} else if ((zombie.color == Color.yellow) && (zombietwo.color == Color.yellow)) {
							zombie.color = dark1;
							hordefunctionshigherlvl(zombie, zombietwo);
							System.out.println("Deathhorde!");
						} else if ((zombie.color == dark1) && (zombietwo.color == dark1)) {
							zombie.color = dark2;
							hordeFunctionsMaxedSizeAndSpeed(zombie, zombietwo);
							System.out.println("Archpack!");
						} else if ((zombie.color == dark2) && (zombietwo.color == dark2)) {
							zombie.color = dark3;
							hordeFunctionsMaxedSizeAndSpeed(zombie, zombietwo);
							System.out.println("Darkpack!");
						}
					}

				}
			}
		}
	}

	public void reset() {
		objects.clear();
	}

	public void hordefunctionslowerlvl(Horde one, Horde two) {
		two.isAlive = false;
		one.deathPotential *= 2;
		one.width *= 2;
		one.height *= 2;
		one.speed += 1;
		one.health += 1;
	}

	public void hordefunctionshigherlvl(Horde one, Horde two) {
		two.isAlive = false;
		one.deathPotential *= 2;
		one.width++;
		one.height++;
		one.speed += 1;
		one.health += 1;
	}

	public void hordeFunctionsMaxedSizeAndSpeed(Horde one, Horde two) {
		two.isAlive = false;
		one.deathPotential *= 2;
		one.health += 1;
	}
}
