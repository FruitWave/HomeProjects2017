
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;
	
	private int score = 0;
	/**
	 * the below showScore method/variable/code does not work. This message has been printed below every instance where something related to it is mentioned.
	 */
	//Font funFont;
	long enemyTimer = 0;
	int enemySpawnTime = 1;

	public ObjectManager() {
		objects = new ArrayList<GameObject>();
		/**
		 * the below showScore method/variable/code does not work. This message has been printed below every instance where something related to it is mentioned.
		 */
		// funFont = new Font("Comic Sans MS", Font.CENTER_BASELINE, 30);
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
		}

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
			addObject(new Horde(new Random().nextInt(HordeRunner.width), 0, 200, 200));
			enemyTimer = System.currentTimeMillis();
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
						score++;
						System.out.println(score);
						o1.isAlive = false;
						o2.isAlive = false;
					} else if ((o1 instanceof Horde && o2 instanceof Hecker)
							|| (o2 instanceof Horde && o1 instanceof Hecker)) {
						o1.isAlive = false;
						o2.isAlive = false;
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
	
	/**
	 * the below showScore method/variable/code does not work. This message has been printed below every instance where something related to it is mentioned.
	 */
//	public void showScore(Graphics g) {
//		g.setColor(Color.WHITE);
//		g.fillRect(800, 200, 75, 25);
//		g.setColor(Color.BLACK);
//		g.setFont(funFont);
//		g.drawString(xavier, 805, 22);
//	}
	
	public void cheatScore(int s){
		setScore(score + s);
	}

	public void reset() {
		objects.clear();
	}
}
