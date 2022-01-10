package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Circle extends ShapesModel {


	public Circle() {

		// initializing
		shapeName = "circle";
		setColor(Color.BLACK);
		setFillColor(Color.WHITE);
		setPosition(new Point(0, 0));
		properties = new HashMap<String, Double>();
		properties.put("radius", 0.000);
		setProperties(properties);
	}

	@Override
	public void draw(Graphics canvas) {

		int x = getPosition().x;
		int y = getPosition().y;
		int width = 2 * (int) getProperties().get("radius").intValue();

		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillOval(x, y, width, width);

		((Graphics2D) canvas).setStroke(new BasicStroke(0));

		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawOval(x, y, width, width);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape newCircle = new Circle();
		newCircle.setPosition(getPosition());
		newCircle.setColor(getColor());
		newCircle.setFillColor(getFillColor());
		Map<String, Double> m = new HashMap<>();
		for (Map.Entry s : getProperties().entrySet())
			m.put(s.getKey().toString(), (Double) s.getValue());
		newCircle.setProperties(m);
		return (Object) newCircle;
	}
}
