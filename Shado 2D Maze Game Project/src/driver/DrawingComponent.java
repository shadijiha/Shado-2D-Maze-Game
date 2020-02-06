/***
 *
 * Driver class
 * */

package driver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import PremetiveShapes.Shado;
import gameObjects.Bullet;
import gameObjects.Grid;

public class DrawingComponent extends JComponent implements ActionListener, KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 4659119803554492770L;

	private final int FPS = 60;
	private boolean HAS_INIT = false;
	private JFrame parentWindow;
	private Timer tm = new Timer(1000 / FPS, this);
	private long deltaTime;
	private long time1 = new Date().getTime();
	private long totalFrames = 0;

	// Constructor
	DrawingComponent(JFrame window) {
		this.parentWindow = window;
		setFocusable(true);
		addKeyListener(this);
	}

	// This is the renderer (Only draw). You also can put logic here (collision,
	// forces, etc)
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		/***************************
		 * ****** DRAW STUFF********
		 ***************************/
		if (!HAS_INIT) {
			Game.generateGrid(this.parentWindow);
			HAS_INIT = true;
		}

		Game.grid.parallelStream().forEachOrdered(e -> {
			e.parallelStream().forEachOrdered(grid -> {
				grid.draw(g2);

				if (Main.LOGGER.debugMode()) {
					new Shado.Text(grid.getIndex().toString(),
							grid.getIndex().x * grid.getDimensions().width + grid.getDimensions().height / 2,
							grid.getIndex().y * grid.getDimensions().width + grid.getDimensions().height / 2).draw(g2);
				}
			});
		});

		// Draw player
		Grid playerLocation = Game.grid.get((int) Game.player.getIndeces().y).get((int) Game.player.getIndeces().x);
		Game.player.draw(g2, playerLocation);

		// Make the monsters shoot every 90 frames
		if (totalFrames % 90 == 0) {
			Game.shootAllMonsters();
		}

		// Update all bullets
		Game.updateAndDrawAllBullets(g2);

		// Detected collision
		Bullet.allBullets.stream().filter(e -> e.isActive()).forEach(b -> {
			if (b.getShape().collides(Game.player.getShape())) {
				Game.player.damage(60);
				b.setActiveTo(false);
			}
		});

		// Timer
		tm.start();

		if (totalFrames >= Long.MAX_VALUE) {
			totalFrames = 0;
		}
		totalFrames++;

		// Calculate real FPS
		/*
		 * long time2 = new Date().getTime(); this.deltaTime = time2 - time1;
		 * this.parentWindow.setTitle(Long.toString((long) 1000 / deltaTime) +
		 * " frames per second"); time1 = new Date().getTime();
		 */
	}

	// Here YOU CAN perform logic (add forces/velocity, check collision, etc)
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Game.player.moveBy(1, 0);
		} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			Game.player.moveBy(-1, 0);
		} else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			Game.player.moveBy(0, -1);
		} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			Game.player.moveBy(0, 1);
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
