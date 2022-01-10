package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Ellipse extends ShapesModel {

	public Ellipse() {

		// initializing
		shapeName = "ellipse";
		setColor(Color.BLACK);
		setFillColor(Color.white);
		setPosition(new Point(0, 0));
		properties = new HashMap<String, Double>();
		properties.put("width", 0.0);
		properties.put("height", 0.0);
		setProperties(properties);

	}

	@Override
	public void draw(Graphics canvas) {

		int x = getPosition().x;
		int y = getPosition().y;
		int width = getProperties().get("width").intValue();
		int height = getProperties().get("height").intValue();

		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillOval(x, y, width, height);

		((Graphics2D) canvas).setStroke(new BasicStroke());

		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawOval(x, y, width, height);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape newEllipse = new Ellipse();
		newEllipse.setPosition(getPosition());
		newEllipse.setColor(getColor());
		newEllipse.setFillColor(getFillColor());
		Map<String, Double> m = new HashMap<>();
		for (Map.Entry s : getProperties().entrySet())
			m.put(s.getKey().toString(), (Double) s.getValue());
		newEllipse.setProperties(m);
		return (Object) newEllipse;
	}

}
