/**
 * @author shadi
 *
 */
package driver;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ShadoMath.Vector;
import ShadoMath.Vertex;
import gameObjects.Bullet;
import gameObjects.Grid;
import gameObjects.Monster;
import gameObjects.Player;

public class Game {

	protected static Player player = new Player("IG");

	public static List<List<Grid>> grid = new ArrayList<>();

	/**
	 * 
	 */
	protected Game() {
		// TODO Auto-generated constructor stub
	}

	public static void shootAllMonsters() {
		for (var monster : Monster.allMonsters) {
			monster.shoot();
		}
	}

	public static void updateAndDrawAllBullets(Graphics2D g) {
		for (var bullet : Bullet.allBullets) {
			bullet.update();
			bullet.draw(g);
		}
	}

	public static boolean canMoveToGrid(Player p, Vertex offset) {

		Vertex v = p.getGridPosition();

		if (Game.grid.get((int) (v.y + offset.y)).get((int) (v.x + offset.x)).isEdge()) {
			return false;
		} else if (Game.grid.get((int) (v.y + offset.y)).get((int) (v.x + offset.x)).hasMonster()) {
			// TODO: Damage player
			return false;
		} else if (Game.grid.get((int) (v.y + offset.y)).get((int) (v.x + offset.x)).hasItem()) {
			// TODO: get Buff from item;
			return true;
		} else {
			return true;
		}
	}

	protected static void generateGrid(JFrame window) {

		grid.clear();

		// 20 x 20 grid
		final short GRID_COUNT = 30;
		float width = window.getWidth() / (float) GRID_COUNT;

		for (int y = 0; y < GRID_COUNT; y++) {
			var innerList = new ArrayList<Grid>();
			for (int x = 0; x < GRID_COUNT; x++) {

				boolean isOnEdge = x == 0 || y == 0 || x == GRID_COUNT - 1 || y == GRID_COUNT - 1;
				Grid temp = new Grid(new Vertex(x, y), width - 1.0f, isOnEdge);

				innerList.add(temp);
			}
			grid.add(innerList);
		}

		// Put 10 random monsters on the grid
		final short MAX_MONSTERS = 10;
		int monsterCount = 0;
		while (monsterCount < MAX_MONSTERS) {
			var v = Vertex.random(GRID_COUNT - 1);
			var g = grid.get((int) v.y).get((int) v.x);

			// Add monster if the tile is not an EDGE or in range of 2 (So player doesn't
			// spawn directly on top of it)
			if (!g.isEdge() && v.x >= 2 && v.y >= 2) {

				final short random = (short) (Math.random() * 4);
				Vector randomVel = new Vector(0, 0);
				switch (random) {
				case 0:
					randomVel.x = 1;
					break;
				case 1:
					randomVel.x = -1;
					break;
				case 2:
					randomVel.y = 1;
					break;
				case 3:
					randomVel.y = -1;
				}

				Monster m = new Monster(1000, 5, 60, 12, v, randomVel);
				g.setMonster(m);
				monsterCount++;
			}
		}
	}

}
