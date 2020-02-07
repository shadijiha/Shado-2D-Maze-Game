/**
 * @author shadi
 *
 */
package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import PremetiveShapes.Shado;
import ShadoMath.Vector;

public class Bullet extends GameObject {

	private int damage;
	private GameObject source;
	private Vector velocity;
	private Shado.Rectangle shape;

	public static List<Bullet> allBullets = new ArrayList<>();

	/**
	 * 
	 */
	public Bullet(int damage, GameObject source, Vector velocity) {
		super("bullet");
		this.damage = damage;
		this.source = source;
		this.velocity = velocity;
		this.position = source.getPosition();

		Shado.Dimension<Double> d = source.getDimensions();
		this.dimensions = new Shado.Dimension<Double>(d.width * 0.50, d.height * 0.20);
		this.shape = new Shado.Rectangle(this.position, this.dimensions).setFill(Color.ORANGE);

		allBullets.add(this);
	}

	public Bullet(int damage, GameObject source) {
		this(damage, source, new Vector(0, 0));
	}

	public void update() {
		this.move(velocity);
		this.shape.move(velocity);
	}

	public void draw(Graphics2D g) {
		this.shape.draw(g);
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @return the source
	 */
	public GameObject getSource() {
		return source;
	}

	/**
	 * @return the velocity
	 */
	public Vector getVelocity() {
		return velocity;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(GameObject source) {
		this.source = source;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
}
