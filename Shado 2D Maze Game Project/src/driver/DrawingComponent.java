/***
 *
 * Driver class
 * */

package driver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import PremetiveShapes.Shado;

public class DrawingComponent extends JComponent implements ActionListener {

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

	// Constructor
	DrawingComponent(JFrame window) {
		this.parentWindow = window;
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
							grid.getIndex().x * grid.getDimension() + grid.getDimension() / 2,
							grid.getIndex().y * grid.getDimension() + grid.getDimension() / 2).draw(g2);

				}
			});
		});

		// Timer
		tm.start();

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
}
