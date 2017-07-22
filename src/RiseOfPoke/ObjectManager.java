package RiseOfPoke;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	Inscriptor illuminator;
	public int score = 0;

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

					if ((o1 instanceof Pokables && o2 instanceof Projectile)
							|| (o2 instanceof Pokables && o1 instanceof Projectile)) {
						Pokables thePokedOne = (o1 instanceof Pokables) ? (Pokables) o1 : (Pokables) o2;
						Projectile thePokingOne = (o1 instanceof Projectile) ? (Projectile) o1 : (Projectile) o2;
						thePokedOne.isAlive = false;
						thePokingOne.isAlive = false;
					} else if ((o1 instanceof Poker && o2 instanceof Pokables)
							|| (o2 instanceof Pokables && o1 instanceof Pokables)) {
						Pokables thePokingOne = (o1 instanceof Pokables) ? (Pokables) o1 : (Pokables) o2;
						Poker theOne = (o1 instanceof Poker) ? (Poker) o1 : (Poker) o2;
						thePokingOne.isAlive = false;
						theOne.health -= 1;
						score -= 2;
					}

				}
			}
		}
	}

	public void reset() {
		objects.clear();
	}
}
