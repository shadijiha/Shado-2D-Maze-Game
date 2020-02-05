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
import ShadoMath.Vertex;

public class Bullet extends GameObject {

	private Vertex positionXY;
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

		Vertex initPos = source.getIndeces();
		var d = source.getDimensions();
		this.positionXY = new Vertex(initPos.x * d.width, initPos.y * d.height);
		this.shape = new Shado.Rectangle(positionXY.x, positionXY.y, d.width * 0.40, d.height * 0.40)
				.setFill(Color.ORANGE);

		allBullets.add(this);
	}

	public Bullet(int damage, GameObject source) {
		this(damage, source, null);
	}

	public void update() {
		positionXY.x += velocity.x;
		positionXY.y += velocity.y;
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

	public Vertex getPositionXY() {
		return new Vertex(positionXY);
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

	public void setPositionXY(Vertex newPosition) {
		this.positionXY.x = newPosition.x;
		this.positionXY.y = newPosition.y;
	}

	@Override
	public Vertex getIndeces() {
		// TODO Auto-generated method stub
		return source.getIndeces();
	}
}
