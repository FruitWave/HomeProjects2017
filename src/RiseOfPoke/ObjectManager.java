package RiseOfPoke;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManager {
	ArrayList<GameObject> objects;
	Inscriptor illuminator;
	public int score = 0;
	int hordeSpeed;
	long enemyTimer = 0;
	int enemySpawnTime = 1;
	// public int level = 1;
	int pokepeasantx;
	int pokepeasanty;

	Pokables pokepeasant;
	boolean peasantAdded = false;

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
		peasantAdded = false;
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			hordeSpeed = new Random().nextInt(illuminator.level) + 1;
			pokepeasantx = new Random().nextInt(PokeRunner.width);
			pokepeasanty = new Random().nextInt(PokeRunner.height);

			pokepeasant = new Pokables(pokepeasantx, pokepeasanty, 20, 20, hordeSpeed);
			pokepeasant.peasantSpawnGuardOn = true;
			if (pokepeasant.collisionArea.intersects(illuminator.lord.collisionArea)) {
				pokepeasantx = new Random().nextInt(PokeRunner.width);
				pokepeasanty = new Random().nextInt(PokeRunner.height);
				pokepeasant.truecolor = Color.green;
				setPokepeasantCoordinates(pokepeasant);
				addObject(pokepeasant);
				pokepeasant.peasantSpawnGuardOn = false;
				peasantAdded = true;
				// for some reason setpokepeasantcoordinates is not being called, even if
				// allowed to live upon impact with Lord the peasants do not change color nor
				// position, and yet they appear and stick as currently intended if they don't
				// appear on top of Lord. This is very weird.
			} else if (peasantAdded == false) {
				addObject(pokepeasant);
				peasantAdded = true;
				pokepeasant.peasantSpawnGuardOn = false;
			}
			// addObject(new Pokables(new Random().nextInt(PokeRunner.width),
			// new Random().nextInt(PokeRunner.height), 200,
			// 200));
			enemyTimer = System.currentTimeMillis();
		}
	}

	void setPokepeasantCoordinates(Pokables p) {
		JOptionPane.showMessageDialog(null, "inside setPokepeasantCoordinates method");
		pokepeasantx = new Random().nextInt(PokeRunner.width);
		pokepeasanty = new Random().nextInt(PokeRunner.height);
		// recursive attempt below!
		if (!p.collisionArea.intersects(illuminator.lord.collisionArea)) {
			System.out.println(p + " does not intersect Lord");
		} else if (p.collisionArea.intersects(illuminator.lord.collisionArea)) {
			p.truecolor = Color.blue;
			System.out.println(p + " intersects Lord");
			setPokepeasantCoordinates(p);
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
							|| (o2 instanceof Poker && o1 instanceof Pokables)) {

						Pokables thePokingOne = (o1 instanceof Pokables) ? (Pokables) o1 : (Pokables) o2;
						if (thePokingOne.peasantSpawnGuardOn == false) {
							Poker theOne = (o1 instanceof Poker) ? (Poker) o1 : (Poker) o2;
							thePokingOne.isAlive = false;
							// theOne.health -= 1;
							score -= 2;
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
