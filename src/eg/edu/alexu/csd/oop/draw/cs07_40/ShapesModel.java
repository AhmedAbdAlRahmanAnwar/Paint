package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class ShapesModel implements Shape {

	private Point shapePosition = new Point();
	protected Map<String, Double> properties;
	private Color borderColor;
	private Color fillColor;
	protected String shapeName = null;

	@Override
	public void setPosition(Point position) {
		shapePosition = (Point) position.clone();
	}

	@Override
	public Point getPosition() {
		return shapePosition;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Color color) {
		borderColor = color;
	}

	@Override
	public Color getColor() {
		return borderColor;
	}

	@Override
	public void setFillColor(Color color) {
		fillColor = color;
	}

	@Override
	public Color getFillColor() {
		return fillColor;
	}

	@Override
	public void draw(Graphics canvas) {

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return null;
	}
}