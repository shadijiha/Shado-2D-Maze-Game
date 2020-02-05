/**
 * @author shadi
 *
 */
package gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import PremetiveShapes.Shado;
import ShadoMath.Vector;
import ShadoMath.Vertex;

public class Monster extends GameObject {

	private int hp; // Current health
	private int maxHp; // Max health
	private int hpRegen;// Health regeneration per second
	private int armor; // Monster's armor (ability to resist to damage)
	private int ad; // Monster's attack damage
	private Vector dir;
	private Vertex positionIndex;
	public static List<Monster> allMonsters = new ArrayList<>();

	/**
	 * 
	 */
	public Monster(int maxHp, int hpRegen, int armor, int ad, Vertex v, Vector dir) {
		super("monster");
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.hpRegen = hpRegen;
		this.armor = armor;
		this.ad = ad;
		this.dir = new Vector(dir);
		this.positionIndex = new Vertex(v);

		Monster.allMonsters.add(this);
	}

	/**
	 * Draw the calling monster at a certain position
	 * 
	 * @param g
	 * @param position
	 */
	public void draw(Graphics2D graphics, Shado.Dimension<Float> dimensions) {
		this.dimensions = dimensions;
		new Shado.Circle(positionIndex.x * this.dimensions.width, positionIndex.y * this.dimensions.height,
				dimensions.width / 2).setFill(Color.RED).draw(graphics);
	}

	/**
	 * Calculates how much monster takes damage based on his armor and then damages
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
	 * Adds monster's hpRegen to his health
	 */
	public void regenerateHealth() {
		this.hp += this.hpRegen;
	}

	public void shoot() {
		// Add bullet to Bullet.allBullets global List
		new Bullet(60, this, this.dir);
	}

	/**
	 * Renders the health bar of the monster: Green rectangle (whose width depends
	 * on the health percentage) on top of a white rectangle
	 * 
	 * @param place The x and y where to put monster health bar
	 * @param the   desired dimensions of the health bar
	 * @return Returns an array of the produces Rectangles
	 */
	public Shado.Rectangle[] renderHealthBar(Vertex place, Dimension d) {

		// Calculate the relative health
		float percentage = hp / maxHp;

		var mainRect = new Shado.Rectangle(place.x, place.y, d.width, d.height).setFill(Color.WHITE);
		var coloredBar = new Shado.Rectangle(place.x, place.y, mainRect.getWidth() * percentage,
				mainRect.getHeight() * percentage).setFill(Color.GREEN);

		return new Shado.Rectangle[] { mainRect, coloredBar };
	}

	@Override
	public Vertex getIndeces() {
		// TODO Auto-generated method stub
		return new Vertex(positionIndex);
	}

	// Monster builder
	public static class MonsterBuilder {
		// Attributes
		private int hp;
		private int maxHp;
		private int hpRegen;
		private int armor;
		private int ad;
		private Vector dir;
		private Vertex positionIndex;

		public MonsterBuilder() {
			hp = 1000;
			maxHp = 1000;
			hpRegen = 5;
			armor = 12;
			ad = 60;
		}

		public MonsterBuilder setMaxHp(int newMaxHP) {
			this.maxHp = newMaxHP;
			this.hp = newMaxHP;
			return this;
		}

		public MonsterBuilder setHpRegen(int newHPRegen) {
			this.hpRegen = newHPRegen;
			return this;
		}

		public MonsterBuilder setArmor(int armor) {
			this.armor = armor;
			return this;
		}

		public MonsterBuilder setAD(int ad) {
			this.ad = ad;
			return this;
		}

		public MonsterBuilder setPositionIndex(Vertex v) {
			positionIndex = new Vertex(v);
			return this;
		}

		public MonsterBuilder setDir(Vector dir) {
			this.dir = new Vector(dir);
			return this;
		}

		public Monster build() {
			return new Monster(maxHp, hpRegen, armor, ad, positionIndex, dir);
		}
	}

}
