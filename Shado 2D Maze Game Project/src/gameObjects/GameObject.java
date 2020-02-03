/**
 * @author shadi
 *
 */
package gameObjects;

import java.util.ArrayList;
import java.util.List;

import ShadoMath.Vertex;

public abstract class GameObject {
	
	protected String objectName;
	protected long id;
	protected int x;
	protected int y;
	protected static List<GameObject> allObjects = new ArrayList<GameObject>();

	/**
	 * 
	 */
	protected GameObject(String objectName) {
		this.objectName = objectName;
		id = (long)(Math.random() * 1e9);
		x = 0;
		y = 0;
		
		// Add to all objects
		allObjects.add(this);
	}
	
	/**
	 * @return The x and y position of the calling object
	 */
	public Vertex getCoordinates()	{
		return new Vertex(this.x, this.y);
	}
	
	/**
	 * @return The id of the calling object
	 */
	public long getId()	{
		return id;
	}
	
	/**
	 * @return The name of the calling Object
	 */
	public String getObjectName()	{
		return objectName;
	}
	
	/**
	 * @return Returns all the game objects created
	 */
	public static List<GameObject> getAllObjects()	{
		return allObjects;
	}
}
