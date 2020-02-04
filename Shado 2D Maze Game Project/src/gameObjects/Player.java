/**
 * @author shadi
 *
 */

package gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import PremetiveShapes.Shado;
import ShadoMath.Vertex;
import driver.Game;

public class Player extends GameObject {

	private String name;
	private Vertex gridPosition;

	private int hp = 1000; // Current health
	private int maxHp = 1000; // Max health
	private int hpRegen = 5; // Health regeneration per second
	private int armor = 12; // Player's armor (ability to resist to damage)
	private int ad = 60; // Player's attack damage

	/**
	 * 
	 */
	public Player(String playername) {
		super("player");
		name = playername;
		gridPosition = new Vertex(1, 1);
	}

	public void draw(Graphics2D g) {
		// Get the grid dimensions where player is
		Grid where = Game.grid.get((int) gridPosition.y).get((int) gridPosition.x);
		new Shado.Circle(gridPosition.x * where.getDimension(), gridPosition.y * where.getDimension(),
				where.getDimension() / 2).setFill(Color.BLUE).draw(g);
	}

	/**
	 * Calculates how much player takes damage based on his armor and then damages
	 * him. The damage is calculated as follows: damage = (1 - armor / (100 +
	 * armor)) * amount;
	 * 
	 * @param amount
	 */
	public void damage(float amount) {
		float reduction = armor / (100 + armor);
		this.hp -= amount * (1 - reduction);
	}

	/**
	 * Move player to another grid index
	 * 
	 * @param x The grid colone position
	 * @param y The grid row position
	 */
	public void moveTo(int x, int y) {
		gridPosition.x = x;
		gridPosition.y = y;
	}

	/**
	 * Move player to another grid index
	 * 
	 * @param v The new indeces
	 */
	public void moveTo(Vertex v) {
//		if (gridPosition.x >= Game.grid.get(0).size() - 1 || gridPosition.y >= Game.grid.size() - 1
//				|| gridPosition.x < 1 || gridPosition.y < 1) {
//			return;
//		} else {
//
//		}
//
//		if (Game.canMoveToGrid(this)) {
//			gridPosition.x = v.x;
//			gridPosition.y = v.y;
//		}

	}

	/**
	 * Move player by a certain index
	 * 
	 * @param x The grid colone position to add
	 * @param y The grid row position to add
	 */
	public void moveBy(int x, int y) {

//		if (gridPosition.x + x >= Game.grid.get(0).size() - 1 || gridPosition.y + y >= Game.grid.size() - 1
//				|| gridPosition.x + x < 1 || gridPosition.y + y < 1) {
//			return;
//		} else {
//
//		}

		if (Game.canMoveToGrid(this, new Vertex(x, y))) {
			gridPosition.x += x;
			gridPosition.y += y;
		}

	}

	/**
	 * Adds player's hpRegen to his health
	 */
	public void regenerateHealth() {
		this.hp += this.hpRegen;
	}

	/**
	 * Renders the health bar of the player: Green rectangle (whose width depends on
	 * the health percentage) on top of a white rectangle
	 * 
	 * @param place The x and y where to player the health bar
	 * @param the   desired dimensions of the health bar
	 * @return Returns an array of the produces Rectangles
	 */
	public Shado.Rectangle[] renderHealthBar(Vertex place, Dimension d) {

		// Calculate the relative health
		float percentage = hp / maxHp;

		var mainRect = new Shado.Rectangle(place.x, place.y, d.width, d.height).setFill(Color.WHITE);
		var coloredBar = new Shado.Rectangle(place.x, place.y, mainRect.getWidth() * percentage,
				mainRect.getHeight() * percentage).setFill(Color.GREEN);

		return new Shado.Rectangle[] { mainRect, coloredBar };
	}

	/**
	 * Get name of player
	 */
	public String getName() {
		return name;
	}

	public Vertex getGridPosition() {
		return new Vertex(gridPosition);
	}
}
