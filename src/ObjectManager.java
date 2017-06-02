
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

					if ((o1 instanceof Horde && o2 instanceof Bullet)
							|| (o2 instanceof Horde && o1 instanceof Bullet)) {
						System.out.println("true");
						SketcHex.casualtyCount++;
						System.out.println(SketcHex.casualtyCount);
						o1.isAlive = false;
						o2.isAlive = false;
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
						o1.isAlive = true;
						o2.isAlive = false;
						Horde topdoggey = (Horde) o1;
						System.out.println("The horde has combined!");
						topdoggey.deathPotential *= 2;
						topdoggey.color = Color.magenta;
						topdoggey.width *= 2;
						topdoggey.height *= 2;
						topdoggey.speed += 1;
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
