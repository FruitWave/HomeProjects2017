
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	SketcHex hex;
	private int score = 0;
	// static int
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
						// SketcHex.casualtyCount++;
						// System.out.println(SketcHex.casualtyCount);
						Horde shotHorde = (Horde) o1;
						shotHorde.health -= 1;
						o2.isAlive = false;
						if (shotHorde.health == 0) {
							shotHorde.isAlive = false;
						}
					}
					if (o2 instanceof Horde && o1 instanceof Bullet) {
						System.out.println("sniped");
						// SketcHex.casualtyCount++;
						// System.out.println(SketcHex.casualtyCount);
						Horde shotHorde = (Horde) o2;
						shotHorde.health -= 1;
						o1.isAlive = false;
						if (shotHorde.health == 0) {
							shotHorde.isAlive = false;
						}
					} else if (o1 instanceof Horde && o2 instanceof Hecker) {
						o1.isAlive = false;/* o1.isAlive = true; instead */
						o2.isAlive = true;
						// Horde deadHorde = (Horde) o1;
						Hecker omflynn = (Hecker) o2;
						omflynn.health -= 1;
						// omflynn.health -= deadHorde.deathPotential;
						System.out.println("Flynn's health is now " + omflynn.health + "! Be careful, and quick!");
						o1.isAlive = false;

						// this temporary life grant to the horde is likely to
						// cause an issue as it might be able to combine with
						// other horde during that small temporary life grant
						// period
						SketcHex.casualtyCount++;
						if (omflynn.health <= 0) {
							o2.isAlive = false;
						}
					} else if (o2 instanceof Horde && o1 instanceof Hecker) {
						o2.isAlive = false;/* o2.isAlive = true; instead */
						o1.isAlive = true;
						// Horde deadHorde = (Horde) o2;
						Hecker omflynn = (Hecker) o1;
						omflynn.health -= 1;
						// omflynn.health -= deadHorde.deathPotential;
						// find out why the above line doesn't work
						System.out.println("Flynn's health is now " + omflynn.health + "! Be careful, and quick!");
						// o2.isAlive = false;
						SketcHex.casualtyCount++;
						if (omflynn.health <= 0) {
							o1.isAlive = false;
						}
						// this temporary life grant to the horde is likely to
						// cause an issue as it might be able to combine with
						// other horde during that small temporary life grant
						// period
					} else if (o1 instanceof Horde && o2 instanceof Horde) {
						Horde zombieone = (Horde) o1;
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
						} else if ((zombietwo.color == Color.magenta) && (zombieone.color == Color.magenta)) {
							zombietwo.color = Color.green;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Horde!");
						}
						if ((zombieone.color == Color.green) && (zombietwo.color == Color.green)) {
							zombieone.color = Color.blue;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Megahorde!");
						} else if ((zombietwo.color == Color.green) && (zombieone.color == Color.green)) {
							zombietwo.color = Color.blue;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Megahorde!");
						}
						if ((zombieone.color == Color.blue) && (zombietwo.color == Color.blue)) {
							zombieone.color = Color.red;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Ultrahorde!");
						} else if ((zombietwo.color == Color.blue) && (zombieone.color == Color.blue)) {
							zombietwo.color = Color.red;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Ultrahorde!");
						}
						if ((zombieone.color == Color.red) && (zombietwo.color == Color.red)) {
							zombieone.color = Color.yellow;
							zombietwo.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Hellhorde!");
						} else if ((zombietwo.color == Color.red) && (zombieone.color == Color.red)) {
							zombietwo.color = Color.yellow;
							zombieone.isAlive = false;
							zombieone.deathPotential *= 2;
							zombieone.width *= 2;
							zombieone.height *= 2;
							zombieone.speed += 1;
							zombieone.health += 1;
							System.out.println("Hellhorde!");
						} else {
							System.out.println("Hey, don't bump into me you stupid Horde!!");
						}

						// zombieone.deathPotential *= 2;
						// zombieone.width *= 2;
						// zombieone.height *= 2;
						// zombieone.speed += 1; zombieone.health += 1;
					}

				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	// public void showScore(Graphics g) {
	// g.setColor(Color.WHITE);
	// g.fillRect(800, 200, 75, 25);
	// g.setColor(Color.BLACK);
	// g.setFont(funFont);
	// g.drawString(xavier, 805, 22);
	// }

	public void reset() {
		objects.clear();
	}
}
