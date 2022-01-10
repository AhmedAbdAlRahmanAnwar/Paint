package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Triangle extends ShapesModel {

	public Triangle() {

		// initializing
		shapeName = "triangle";
		setColor(Color.black);
		setFillColor(Color.WHITE);
		setPosition(new Point(0, 0));
		properties = new HashMap<String, Double>();
		properties.put("x2", 0.000);
		properties.put("y2", 0.000);
		properties.put("x3", 0.000);
		properties.put("y3", 0.000);
		setProperties(properties);

	}

	@Override
	public void draw(Graphics canvas) {

		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = getProperties().get("x2").intValue();
		int y2 = getProperties().get("y2").intValue();
		int x3 = getProperties().get("x3").intValue();
		int y3 = getProperties().get("y3").intValue();
		int[] xPoints = { x1, x2, x3 };
		int[] yPoints = { y1, y2, y3 };

		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillPolygon(xPoints, yPoints, 3);
		((Graphics2D) canvas).setStroke(new BasicStroke());
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawPolygon(xPoints, yPoints, 3);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape newTriangle = new Triangle();
		newTriangle.setPosition(getPosition());
		newTriangle.setColor(getColor());
		newTriangle.setFillColor(getFillColor());
		Map<String, Double> m = new HashMap<>();
		for (Map.Entry s : getProperties().entrySet())
			m.put(s.getKey().toString(), (Double) s.getValue());
		newTriangle.setProperties(m);
		return (Object) newTriangle;
	}

}
