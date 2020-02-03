/***
 * Shado shapes
 * */

package PremetiveShapes;

import ShadoMath.Vector;
import ShadoMath.Vertex;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public abstract class Shado {

	public static class Rectangle {
		private Rectangle2D.Double rectangle; // Stores shape
		private Color fill = new Color(255, 255, 255, 255); // Stores color
		private Color stroke = new Color(0, 0, 0);

		// Main constructor
		public Rectangle(double x, double y, double w, double h) {
			rectangle = new Rectangle2D.Double(x, y, w, h);
		}

		// Renderer
		public void draw(Graphics2D graphics) {
			graphics.setColor(this.fill);
			graphics.fill(this.rectangle);
			graphics.setColor(this.stroke);
			graphics.draw(this.rectangle);
		}

		public void move(double dx, double dy) {
			this.rectangle.x += dx;
			this.rectangle.y += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		// Math operations
		public boolean collides(Rectangle other) {
			return this.getX() + this.getWidth() >= other.getX() && this.getX() <= other.getX() + other.getWidth()
					&& this.getY() + this.getHeight() >= other.getY()
					&& this.getY() <= other.getY() + other.getHeight();
		}

		public boolean collides(Circle circle) {
			return circle.collides(this);
		}

		// Getters
		public Color getFill() {
			return this.fill;
		}

		public Color getStroke() {
			return this.stroke;
		}

		public double getX() {
			return this.rectangle.x;
		}

		public double getY() {
			return this.rectangle.y;
		}

		public double getWidth() {
			return this.rectangle.width;
		}

		public double getHeight() {
			return this.rectangle.width;
		}

		// Setters
		public Rectangle setFill(Color c) {
			this.fill = c;
			return this;
		}

		public Rectangle setStroke(Color c) {
			this.stroke = c;
			return this;
		}
	}

	public static class Circle {
		private Ellipse2D.Double ellipse; // Stores shape
		private Color fill = new Color(255, 255, 255, 255); // Stores fill color
		private Color stroke = Color.BLACK; // Stores stroke color

		// Constructors
		public Circle(double x, double y, double r) {
			this.ellipse = new Ellipse2D.Double(x, y, r * 2, r * 2);
		}

		// Graphics operations
		public void draw(Graphics2D graphics) {
			graphics.setColor(this.fill);
			graphics.fill(this.ellipse);
			graphics.setColor(this.stroke);
			graphics.draw(this.ellipse);
		}

		public void move(double dx, double dy) {
			this.ellipse.x += dx;
			this.ellipse.y += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		// Math operations and collisions
		public Vertex getCenter() {
			return new Vertex(this.getX() + this.ellipse.width / 2, this.getY() + this.ellipse.height / 2);
		}

		public boolean collides(Circle other) {
			Vertex posThis = this.getCenter();
			Vertex posOther = other.getCenter();
			return posThis.getDistance(posOther) <= this.getR() + other.getR();
		}

		public boolean collides(Rectangle rect) {
			Rectangle hitBox = new Rectangle(this.getX(), this.getY(), this.getR() * 2, this.getR() * 2);
			return hitBox.collides(rect);
		}

		// Setters
		public Circle setFill(Color c) {
			this.fill = c;
			return this;
		}

		public Circle setStroke(Color c) {
			this.stroke = c;
			return this;
		}

		public Circle setPosition(Vertex newPosition) {
			this.ellipse.x = newPosition.x;
			this.ellipse.y = newPosition.y;
			return this;
		}

		public Circle setRadius(double r) {
			this.ellipse.width = r * 2;
			this.ellipse.height = r * 2;
			return this;
		}

		// Getters
		public Color getFill() {
			return this.fill;
		}

		public Color getStroke() {
			return this.stroke;
		}

		public double getX() {
			return this.ellipse.x;
		}

		public double getY() {
			return this.ellipse.y;
		}

		public double getR() {
			return this.ellipse.width / 2;
		}
	}

	public static class Line {
		private Line2D.Double line; // Stores shape
		private Color color; // Store color
		private int strokeWeight = 1;

		public Line(int fromX, int fromY, int toX, int toY) {
			this.line = new Line2D.Double(fromX, fromY, toX, toY);
		}

		public void draw(Graphics2D graphics) {
			graphics.setColor(this.color);
			graphics.setStroke(new BasicStroke(this.strokeWeight));
			graphics.draw(this.line);
			graphics.setColor(Color.BLACK);
			graphics.setStroke(new BasicStroke(1));
		}

		public void move(double dx, double dy) {
			this.line.x1 += dx;
			this.line.y1 += dy;
			this.line.x2 += dx;
			this.line.y2 += dy;
		}

		public void move(Vector v) {
			this.move(v.x, v.y);
		}

		// Setters

		/***
		 *  Changes the fill Color of the calling object
		 * @param c The new color
		 * @return Returns the calling object with the changes
		 */
		public Line setColor(Color c) {
			this.color = c;
			return this;
		}

		/***
		 * Changes the line stroke weight (line width)
		 * @param newStrokeWeight The new stroke weight
		 * @return Returns the calling object with the changes
		 */
		public Line setLineWidth(int newStrokeWeight) {
			this.strokeWeight = newStrokeWeight;
			return this;
		}

		// Getters
		public Color getColor() {
			return this.color;
		}
	}

	public static class Text {

		private String text;
		private Font font = new Font("Times new Roman", Font.BOLD, 14);
		private Vertex vertex;

		// Constructors
		public Text(String text, Vertex coodinates, Font font) {
			this.text = text;
			this.font = font;
			this.vertex = coodinates;
		}

		public Text(String text, Vertex coodinates) {
			this.text = text;
			this.vertex = coodinates;
		}

		public Text(String text, double x, double y) {
			this.text = text;
			this.vertex = new Vertex(x, y);
		}

		// Graphics
		public void draw(Graphics2D graphics) {
			graphics.setFont(this.font);
			graphics.drawString(this.text, (int) vertex.x, (int) vertex.y);
		}

		// Math operations & collisions
		public boolean collides(Rectangle other) throws Exception {
			throw new Exception("Not coded yet");
			// Rectangle hitBox = new Rectangle(this.vertex.x, this.vertex.y,)
		}

		// Setters
		public Text setCoordinates(Vertex v) {
			this.vertex = v;
			return this;
		}

		public Text setX(double x) {
			this.vertex.x = x;
			return this;
		}

		public Text setY(double y) {
			this.vertex.y = y;
			return this;
		}

		public Text setFont(Font font) {
			this.font = font;
			return this;
		}

		public Text setText(String text) {
			this.text = text;
			return this;
		}

		// Getters
		public Vertex getCoordinates() {
			return this.vertex;
		}

		public double getX() {
			return this.vertex.x;
		}

		public double getY() {
			return this.vertex.y;
		}

		public Font getFont() {
			return this.font;
		}

		public String getText() {
			return this.text;
		}
	}

}