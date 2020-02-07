/**
 * @author shadi
 *
 */
package gameObjects;

import java.util.ArrayList;
import java.util.List;

import PremetiveShapes.Shado;
import ShadoMath.Vector;
import ShadoMath.Vertex;

public abstract class GameObject {

	protected Vertex position;
	protected String objectName;
	protected long id;
	protected Shado.Dimension<Double> dimensions;
	protected Shado.Form shape;

	public static List<GameObject> allObjects = new ArrayList<GameObject>();

	/**
	 * 
	 */
	protected GameObject(String objectName) {
		this.objectName = objectName;
		this.id = (long) (Math.random() * Long.MAX_VALUE);
		this.dimensions = new Shado.Dimension<Double>();

		// Add to all objects
		allObjects.add(this);
	}

	/**
	 * Moves the object by a certain amount
	 * 
	 * @param v The amount to move
	 */
	public void move(final Vector v) {
		this.position.x += v.x;
		this.position.y += v.y;
	}

	/**
	 * @return The position index of the calling object on the grid
	 */
	public Vertex getPosition() {
		return new Vertex(this.position);
	}

	/**
	 * @return Returns the dimensions of the calling object
	 */
	public Shado.Dimension<Double> getDimensions() {
		return new Shado.Dimension<Double>(this.dimensions);
	}

	/**
	 * @return The id of the calling object
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return The name of the calling Object
	 */
	public String getObjectName() {
		return objectName;
	}
}
