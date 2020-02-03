/**
 * @author shadi
 *
 */

package gameObjects;

import java.awt.Color;
import java.awt.Dimension;

import PremetiveShapes.Shado;
import ShadoMath.Vertex;

public class Player extends GameObject {
	
	private String name;
	private int w;
	private int h;
	
	private int hp = 1000;		// Current health
	private int maxHp = 1000;	// Max health
	private int hpRegen = 5;	// Health regeneration per second
	private int armor = 12;		// Player's armor (ability to resist to damage)
	private int ad = 60;		// Player's attack damage
	
	/**
	 * 
	 */
	public Player(String playername) {
		super("player");
		name = playername;
		w = 50;
		h = 150;
	}

	/**
	 * Calculates how much player takes damage based on his armor and then damages him.
	 * The damage is calculated as follows:
	 * 	damage = (1 - armor / (100 + armor)) * amount;
	 * @param amount
	 */
	public void damage(float amount)	{
		float reduction = armor / (100 + armor);
		this.hp -= amount * (1 - reduction);
	}

	/**
	 * Adds player's hpRegen to his health
	 */
	public void regenerateHealth()	{
		this.hp += this.hpRegen;
	}
	
	/**
	 * Renders the health bar of the player: Green rectangle (whose width depends on the health percentage) on top of a white rectangle
	 * @param place The x and y where to player the health bar
	 * @param the desired dimensions of the health bar
	 * @return Returns an array of the produces Rectangles
	 */
	public Shado.Rectangle[] renderHealthBar(Vertex place, Dimension d)	{
		
		// Calculate the relative health
		float percentage = hp / maxHp;
		
		var mainRect = new Shado.Rectangle(place.x, place.y, d.width, d.height).setFill(Color.WHITE);
		var coloredBar = new Shado.Rectangle(place.x, place.y, mainRect.getWidth() * percentage, mainRect.getHeight() * percentage).setFill(Color.GREEN);
		
		return new Shado.Rectangle[]{mainRect, coloredBar};
	}
	
	/**
	 * Get name of player
	 */
	public String getName()	{
		return name;
	}
	
	/**
	 * @return The dimensions of the calling object
	 */
	public Dimension getDimension()	{
		return new Dimension(w, h);
	}

}
