
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	SketcHex hex;
	HordeRunner ohgoddontcauseaglitch;

	/**
	 * the below showScore method/variable/code does not work. This message has
	 * been printed below every instance where something related to it is
	 * mentioned.
	 */
	// Font funFont;
	long enemyTimer = 0;
	int enemySpawnTime = 1;

	public ObjectManager(SketcHex hex) {
		objects = new ArrayList<GameObject>();
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

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void manageEnemies(int xdisplacement) {
		// if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
		// addObject(new Horde(new Random().nextInt(HordeRunner.width), 0, 200,
		// 200, hex));
		// enemyTimer = System.currentTimeMillis();
		// }
		for (int i = 0; i < objects.size(); i++) {
			GameObject o1 = objects.get(i);
			if (o1 instanceof Horde) {
				o1.x += xdisplacement;
			}
		}

	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
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
						// SketcHex.casualtyCount++; // comment(out) this
						System.out.println("Flynn's health is now " + omflynn.health + "! Be careful, and quick!");

						if (omflynn.health <= 0) {
							omflynn.isAlive = false;
						} else if (hordie.health <= 0) {
							hordie.isAlive = false;
						}
					} else if (o1 instanceof Horde && o2 instanceof Horde) {
						Horde zombie = (Horde) o1;
						// System.out.println("zombie initial health is at "
						// + zombie.health);
						Horde zombietwo = (Horde) o2;
						if ((zombie.color == Color.darkGray) && (zombietwo.color == Color.darkGray)) {
							zombie.color = Color.magenta;
							zombietwo.isAlive = false;
							zombie.deathPotential *= 2;
							zombie.width *= 2;
							zombie.height *= 2;
							zombie.speed += 1;
							zombie.health += 1;
							System.out.println("Minihorde!");
						} else if ((zombie.color == Color.magenta) && (zombietwo.color == Color.magenta)) {
							zombie.color = Color.green;
							zombietwo.isAlive = false;
							zombie.deathPotential *= 2;
							zombie.width *= 2;
							zombie.height *= 2;
							zombie.speed += 1;
							zombie.health += 1;
							System.out.println("Horde!");
						} else if ((zombietwo.color == Color.green) && (zombie.color == Color.green)) {
							zombietwo.color = Color.blue;
							zombie.isAlive = false;
							zombie.deathPotential *= 2;
							zombie.width *= 2;
							zombie.height *= 2;
							zombie.speed += 1;
							zombie.health += 1;
							System.out.println("Megahorde!");
						} else if ((zombietwo.color == Color.blue) && (zombie.color == Color.blue)) {
							zombietwo.color = Color.red;
							zombie.isAlive = false;
							zombie.deathPotential *= 2;
							zombie.width++;
							zombie.height++;
							zombie.speed += 1;
							zombie.health += 1;
							System.out.println("Ultrahorde!");
						} else if ((zombie.color == Color.red) && (zombietwo.color == Color.red)) {
							zombie.color = Color.yellow;
							zombietwo.isAlive = false;
							zombie.deathPotential *= 2;
							zombie.width++;
							zombie.height++;
							zombie.speed += 1;
							zombie.health += 1;
							System.out.println("Hellhorde!");
						}
						

					}

				}
			}
		}
	}

	public void reset() {
		objects.clear();
	}
}
