/**
 * @author shadi
 *
 */
package gameObjects;

public class Monster extends GameObject {

	private int hp; // Current health
	private int maxHp; // Max health
	private int hpRegen;// Health regeneration per second
	private int armor; // Monster's armor (ability to resist to damage)
	private int ad; // Monster's attack damage

	/**
	 * 
	 */
	public Monster(int hp, int maxHp, int hpRegen, int armor, int ad) {
		super("monster");
		this.hp = hp;
		this.maxHp = maxHp;
		this.hpRegen = hpRegen;
		this.armor = armor;
		this.ad = ad;
	}

	// Monster builder
	public static class MonsterBuilder {
		// Attributes
		private int hp;
		private int maxHp;
		private int hpRegen;
		private int armor;
		private int ad;

		public MonsterBuilder() {
			hp = 1000;
			maxHp = 1000;
			hpRegen = 5;
			armor = 12;
			ad = 60;
		}

		public MonsterBuilder setHp(int newHP) {
			this.hp = newHP;
			return this;
		}

		public MonsterBuilder setMaxHp(int newMaxHP) {
			this.maxHp = newMaxHP;
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

		public Monster build() {
			return new Monster(hp, maxHp, hpRegen, armor, ad);
		}
	}

}
