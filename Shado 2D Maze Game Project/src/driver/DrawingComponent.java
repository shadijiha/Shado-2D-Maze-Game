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
import java.io.Serializable;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import gameObjects.Monster;

public class DrawingComponent extends JComponent implements ActionListener, KeyListener, Serializable {

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
			Game.generateEnviroment(this.parentWindow);
			HAS_INIT = true;
		}

		// Draw enviroment
		Game.enviroment.parallelStream().forEachOrdered(e -> e.draw(g2));
		Monster.allMonsters.parallelStream().forEachOrdered(e -> e.draw(g2));

		// Draw player
		Game.player.draw(g2);

		// Make the monsters shoot every 90 frames
		if (totalFrames % 90 == 0) {
			// Game.shootAllMonsters();
		}

		// Update all bullets
		Game.updateAndDrawAllBullets(g2);

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
		// Key events
		var key = e.getKeyCode();
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			Game.player.move(new ShadoMath.Vector(5, 0));
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
