/**
 * @author shadi
 *
 */
package driver;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import PremetiveShapes.Shado;
import ShadoMath.Vector;
import ShadoMath.Vertex;
import gameObjects.Bullet;
import gameObjects.Monster;
import gameObjects.Player;

public abstract class Game {

	public static Player player = new Player("IG", new Vertex(0, 0));
	public static List<Shado.Form> enviroment = new ArrayList<>();

	/**
	 * 
	 */
	protected Game() {
		// Nothing
	}

	public static void shootAllMonsters() {
		Monster.allMonsters.parallelStream().forEachOrdered(e -> e.shoot());
	}

	public static void updateAndDrawAllBullets(Graphics2D g) {
		Bullet.allBullets.parallelStream().forEachOrdered(b -> {
			b.update();
			b.draw(g);
		});
	}

	protected static void generateEnviroment(JFrame window) {

		final int GROUND_LEVEL = (int) (window.getHeight() * 0.60);

		// Draw ground
		var ground = new Shado.Rectangle(0, GROUND_LEVEL, window.getWidth(), window.getHeight() - GROUND_LEVEL)
				.setFill(Shado.GREEN);
		enviroment.add(ground);

		var sky = new Shado.Rectangle(0, 0, window.getWidth(), GROUND_LEVEL).setFill(Shado.LIGHT_BLUE);
		enviroment.add(sky);

		var sun = new Shado.Circle(40, 20, 50).setFill(Shado.YELLOW);
		enviroment.add(sun);

		var monster = new Monster(1000, 5, 60, 23, new Vertex(600, GROUND_LEVEL - player.getDimensions().height));

		// Place player
		player.move(new Vector(10, GROUND_LEVEL - player.getDimensions().height));
	}

}
