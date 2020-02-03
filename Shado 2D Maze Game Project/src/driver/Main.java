/**
 * @author shadi
 *
 */
package driver;

import ShadoLogger.Logger;
import gameObjects.GameObject;
import gameObjects.Player;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class Main {

	public static Logger LOGGER = new Logger(Logger.LOG_LEVEL_INFO, false);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// All the application goes here
				JFrame mainWindow = new JFrame("Shado Mario Java");
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindow.setPreferredSize(new Dimension(1920, 1080));
				
				DrawingComponent canvas = new DrawingComponent(mainWindow);
				mainWindow.add(canvas);
				
				mainWindow.pack();
				mainWindow.setLocationRelativeTo(null);
				mainWindow.setVisible(true);
				
			}			
		});	

	}

}
