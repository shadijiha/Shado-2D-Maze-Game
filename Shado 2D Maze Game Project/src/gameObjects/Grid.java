
/**
 * @author Shadi
 *
 */
package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import PremetiveShapes.Shado;
import ShadoMath.Vertex;

public class Grid extends GameObject {

	private boolean isEdge;
	private Monster monster;
	private GameObject item;
	private Player player;
	private Vertex index;
	private Shado.Rectangle rect;

	private static Color defaultFill = Color.WHITE;
	private static Color edgeFill = Color.BLACK;

	/**
	 * 
	 */
	public Grid(Vertex index, float dimension, boolean isEdge) {
		super("grid");
		this.index = index;
		this.isEdge = isEdge;
		this.dimensions = new Shado.Dimension<Float>(dimension, dimension);
		this.rect = new Shado.Rectangle(index.x * dimension, index.y * dimension, dimension, dimension);

		if (isEdge)
			rect.setFill(edgeFill);
		else
			rect.setFill(defaultFill);

		// Other null
		this.monster = null;
		this.player = null;
		this.item = null;
	}

	/***
	 * Draws the calling grid to the screen
	 * 
	 * @param graphics
	 */
	public void draw(Graphics2D graphics) {
		this.rect.draw(graphics);

		// if the grid has a monster draw it
		if (hasMonster()) {
			monster.draw(graphics, this.dimensions);
		}
	}

	/**
	 * @param isEdge the isEdge to set
	 */
	public void setEdge(boolean isEdge) {
		this.isEdge = isEdge;
		if (this.isEdge)
			this.rect.setFill(edgeFill);
		else
			this.rect.setFill(defaultFill);
	}

	/**
	 * @param monster the monster to set
	 */
	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(GameObject item) {
		this.item = item;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Vertex index) {
		this.index = index;
		this.rect = new Shado.Rectangle(index.x * dimensions.width, index.y * dimensions.height, dimensions.width,
				dimensions.height);
	}

	/**
	 * @return Returns if a monster is on the calling grid
	 */
	public boolean hasMonster() {
		return monster != null;
	}

	/**
	 * @return Returns if an item is on the calling grid
	 */
	public boolean hasItem() {
		return item != null;
	}

	/**
	 * @return Returns if a player is on the calling grid
	 */
	public boolean hasPlayer() {
		return player != null;
	}

	/**
	 * @return the isEdge
	 */
	public boolean isEdge() {
		return isEdge;
	}

	/**
	 * @return the monster
	 */
	public Monster getMonster() {
		return monster;
	}

	/**
	 * @return the item
	 */
	public GameObject getItem() {
		return item;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the index
	 */
	public Vertex getIndex() {
		return new Vertex(index);
	}

	@Override
	public Vertex getIndeces() {
		return new Vertex(index);
	}

}
