/**
 * @author shadi
 *
 */
package driver;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ShadoMath.Vertex;
import gameObjects.Grid;
import gameObjects.Monster;

public class Game {

	protected static List<List<Grid>> grid = new ArrayList<>();

	/**
	 * 
	 */
	protected Game() {
		// TODO Auto-generated constructor stub
	}

	protected static void generateGrid(JFrame window) {

		grid.clear();

		// 20 x 20 grid
		final short GRID_COUNT = 10;
		float width = window.getWidth() / (float) GRID_COUNT;

		for (int y = 0; y < GRID_COUNT; y++) {
			var innerList = new ArrayList<Grid>();
			for (int x = 0; x < GRID_COUNT; x++) {

				boolean isOnEdge = x == 0 || y == 0 || x == GRID_COUNT - 1 || y == GRID_COUNT - 1;
				Grid temp = new Grid(new Vertex(x, y), width, isOnEdge);

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
			if (!g.isEdge()) {
				Monster m = new Monster(1000, 5, 60, 12);
				g.setMonster(m);
				monsterCount++;
			}
		}
	}

}
