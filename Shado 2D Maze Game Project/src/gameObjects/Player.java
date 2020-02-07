/**
 * @author shadi
 *
 */

package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import PremetiveShapes.Shado;
import ShadoMath.Vector;
import ShadoMath.Vertex;

public class Player extends GameObject {

	private String name;
	private Shado.Rectangle shape;

	private int hp = 1200; // Current health
	private int maxHp = 1000; // Max health
	private int hpRegen = 5; // Health regeneration per second
	private int armor = 12; // Player's armor (ability to resist to damage)
	private int ad = 60; // Player's attack damage

	/**
	 * 
	 */
	public Player(String playername, final Vertex p) {
		super("player");
		this.name = playername;
		this.position = new Vertex(p);
		this.dimensions = new Shado.Dimension<Double>(50.0, 150.0);
		this.shape = new Shado.Rectangle(position, dimensions).setFill(Color.orange);
	}

	public void draw(Graphics2D g) {
		this.shape.draw(g);
	}

	@Override
	public void move(Vector v) {
		this.position.x += v.x;
		this.position.y += v.y;
		this.shape.move(v);
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
	public Shado.Rectangle[] renderHealthBar(Vertex place, Shado.Dimension<Float> d) {

		// Calculate the relative health
		double percentage = (double) hp / (double) maxHp;
		percentage = percentage > 1 ? 1 : percentage;

		var mainRect = new Shado.Rectangle(place.x, place.y, d.width, d.height).setFill(Color.WHITE);
		var coloredBar = new Shado.Rectangle(place.x, place.y, mainRect.getWidth() * percentage, mainRect.getHeight())
				.setFill(Color.GREEN);

		return new Shado.Rectangle[] { mainRect, coloredBar };
	}

	/**
	 * Get name of player
	 */
	public String getName() {
		return name;
	}
}
