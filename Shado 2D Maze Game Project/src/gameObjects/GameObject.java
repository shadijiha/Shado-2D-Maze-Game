/**
 * @author shadi
 *
 */
package gameObjects;

import java.util.ArrayList;
import java.util.List;

import PremetiveShapes.Shado;
import ShadoMath.Vertex;

public abstract class GameObject {

	protected String objectName;
	protected long id;
	protected static List<GameObject> allObjects = new ArrayList<GameObject>();
	protected Shado.Dimension<Float> dimensions;

	/**
	 * 
	 */
	protected GameObject(String objectName) {
		this.objectName = objectName;
		id = (long) (Math.random() * 1e9);
		this.dimensions = new Shado.Dimension<Float>();

		// Add to all objects
		allObjects.add(this);
	}

	/**
	 * @return Thie position index of the calling object on the grid
	 */
	public abstract Vertex getIndeces();

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

	/**
	 * @return Returns the dimensions of the calling object
	 */
	public Shado.Dimension<Float> getDimensions() {
		return new Shado.Dimension<Float>(this.dimensions);
	}

	/**
	 * @return Returns all the game objects created
	 */
	public static List<GameObject> getAllObjects() {
		return allObjects;
	}
}
