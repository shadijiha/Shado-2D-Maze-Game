/**
 * @author shadi
 *
 */

package gameObjects;

import java.awt.Dimension;

public class Player extends GameObject {
	
	private String name;
	private int w;
	private int h;
	
	private int hp = 1000;		// Current health
	private int maxHp = 1000;	// Max health
	private int hpRegen = 5;	// Health regenration per second
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
