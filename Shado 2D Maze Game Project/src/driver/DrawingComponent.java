/***
 *
 * Driver class
 * */

package driver;

import ShadoMath.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PremetiveShapes.*;

import static ShadoMath.Util.random;
import static ShadoMath.Util.randomColor;

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
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		/***************************
		 * ****** DRAW STUFF********
		 ***************************/


		// Timer
		tm.start();

		// Calculate real FPS
		/*long time2 = new Date().getTime();
		this.deltaTime = time2 - time1;
		this.parentWindow.setTitle(Long.toString((long) 1000 / deltaTime) + " frames per second");
		time1 = new Date().getTime();*/
	}

	// Here YOU CAN perform logic (add forces/velocity, check collision, etc)
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
