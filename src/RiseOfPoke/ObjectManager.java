package RiseOfPoke;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	Inscriptor illuminator;
	private int score = 0;

	long enemyTimer = 0;
	int enemySpawnTime = 1;

	public ObjectManager(Inscriptor illuminator) {
		objects = new ArrayList<GameObject>();
		this.illuminator = illuminator;
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

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			int choosexory = new Random().nextInt(2);
			if (choosexory == 0) {
				addObject(new Pokables(new Random().nextInt(PokeRunner.width), 0, 200, 200));
			} else if (choosexory == 1) {
				addObject(new Pokables(0, new Random().nextInt(PokeRunner.height), 200, 200));
			}
			// addObject(new Pokables(new Random().nextInt(PokeRunner.width),
			// new Random().nextInt(PokeRunner.height), 200,
			// 200));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);

				if (o1.collisionArea.intersects(o2.collisionArea)) {

					if (o1 instanceof Pokables && o2 instanceof Projectile) {
						o1.isAlive = false;
						o2.isAlive = false;
					}
					if (o2 instanceof Pokables && o1 instanceof Projectile) {
						o2.isAlive = false;
						o1.isAlive = false;
					} else if (o1 instanceof Pokables && o2 instanceof Poker) {
						o1.isAlive = false;
						o2.isAlive = true;
						Poker pokenought = (Poker) o1;
						pokenought.health -= 1;
						score--;
					} else if (o2 instanceof Pokables && o1 instanceof Poker) {
						o2.isAlive = false;
						o1.isAlive = true;
						Poker pokenought = (Poker) o1;
						pokenought.health -= 1;
						score--;
					}

				}
			}
		}
	}

	public void reset() {
		objects.clear();
	}
}
