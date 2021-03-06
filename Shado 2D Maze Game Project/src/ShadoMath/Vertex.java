package ShadoMath;

import java.util.function.Consumer;

public class Vertex implements Cloneable {

	public double x;
	public double y;
	public double z;

	/****
	 * @param _x The x position of the vertex
	 * @param _y The y position of the vertex
	 * @param _z The z position of the vertex
	 */
	public Vertex(double _x, double _y, double _z) {
		this.x = _x;
		this.y = _y;
		this.z = _z;
	}

	/****
	 * @param _x The x position of the vertex
	 * @param _y The y position of the vertex
	 */
	public Vertex(double _x, double _y) {
		this(_x, _y, 0);
	}

	/****
	 * @param v The vertex you want to copy
	 */
	public Vertex(Vertex v) {
		this(v.x, v.y, v.z);
	}

	/****
	 * Default constructor initializes the vertex to (0, 0, 0)
	 */
	public Vertex() {
		this(0, 0, 0);
	}

	/****
	 * Gives a distance between the calling vertex and the passed vertex
	 *
	 * @param v The vertex you want to compute the distance with
	 * @return Returns the distance between the calling vertex and the passed vertex
	 */
	public double getDistance(Vertex v) {
		return Math.sqrt(Math.pow(v.x - this.x, 2) + Math.pow(v.y - this.y, 2) + Math.pow(v.z - this.z, 2));
	}

	/**
	 * Returns if 2 vertex have same x and y
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		} else {
			Vertex v = (Vertex) o;
			return v.x == this.x && v.y == this.y;
		}
	}

	/**
	 * To string method
	 */
	@Override
	public String toString() {
		return String.format("(%.0f, %.0f)", x, y);
	}

	/**
	 * Generates a vertex with random x and y
	 * 
	 * @param max The maximum value a x or y can take
	 * @return Returns the random vertex
	 */
	public static Vertex random(int max) {
		return new Vertex((int) (Math.random() * max), (int) (Math.random() * max));
	}

	public Object transform(Consumer<Vertex> func) {
		// TODO Auto-generated method stub
		func.accept(this);
		return this;
	}

	// Setters

	/***
	 * Changes the x of the vertex
	 *
	 * @param _x The new X position of the vertex
	 */
	public void setX(int _x) {
		this.x = _x;
	}

	/***
	 * Changes the y of the vertex
	 *
	 * @param _y The new Y position of the vertex
	 */
	public void setY(int _y) {
		this.y = _y;
	}

	/***
	 * Changes the z of the vertex
	 *
	 * @param _z The new Z position of the vertex
	 */
	public void setZ(int _z) {
		this.z = _z;
	}

	/***
	 * Changes the position of the vertex to the passed vertex
	 *
	 * @param v The vertex you want to copy its position
	 */
	public void set(Vertex v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	/***
	 * @return Returns a clone of the calling object
	 */
	@Override
	public Object clone() {
		try {
			return new Vertex(this.x, this.y, this.z);
		} catch (Exception e) {
			return null;
		}
	}
}
