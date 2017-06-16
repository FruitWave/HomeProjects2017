
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

					if (o1 instanceof Horde && o2 instanceof Bullet) {
						System.out.println("sniped");
						Horde shotHorde = (Horde) o1;
						shotHorde.health -= 1;
						o2.isAlive = false;
						if (shotHorde.health <= 0) {
							shotHorde.isAlive = false;
							SketcHex.casualtyCount++;

						}
					}
					if (o2 instanceof Horde && o1 instanceof Bullet) {
						System.out.println("sniped");
						// SketcHex.casualtyCount++;
						// System.out.println(SketcHex.casualtyCount);
						Horde shotHorde = (Horde) o2;
						shotHorde.health -= 1;
						o1.isAlive = false;
						if (shotHorde.health <= 0) {
							shotHorde.isAlive = false;
							SketcHex.casualtyCount++;
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
						Horde zombieone = (Horde) o1;
						// System.out.println("zombie one initial health is at "
						// + zombieone.health);
						Horde zombietwo = (Horde) o2;
						if ((zombieone.color == Color.darkGray) && (zombietwo.color == Color.darkGray)) {
							zombieone.color = Color.magenta;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Minihorde!");
						} else if ((zombieone.color == Color.magenta) && (zombietwo.color == Color.magenta)) {
							zombieone.color = Color.green;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Horde!");
						} else if ((zombietwo.color == Color.green) && (zombieone.color == Color.green)) {
							zombietwo.color = Color.blue;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Megahorde!");
						} else if ((zombietwo.color == Color.blue) && (zombieone.color == Color.blue)) {
							zombietwo.color = Color.red;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width++;
							zombieone.height++;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Ultrahorde!");
						} else if ((zombieone.color == Color.red) && (zombietwo.color == Color.red)) {
							zombieone.color = Color.yellow;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width++;
							zombieone.height++;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Hellhorde!");
						}
						// System.out.println("Zombie health is " +
						// zombieone.health);

					}

				}
			}
		}
	}

	public void reset() {
		objects.clear();
	}
}
