package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class LineSegment extends ShapesModel {

	public LineSegment() {

		// initializing
		shapeName = "line";
		setColor(Color.BLACK);
		setPosition(new Point(0, 0));
		properties = new HashMap<String, Double>();
		properties.put("x2", 5.00);
		properties.put("y2", 0.00);
		setProperties(properties);
	}

	@Override
	public void draw(Graphics canvas) {
		int x1 = getPosition().x;
		int y1 = getPosition().y;
		int x2 = getProperties().get("x2").intValue();
		int y2 = getProperties().get("y2").intValue();
		((Graphics2D) canvas).setStroke(new BasicStroke());
		((Graphics2D) canvas).setColor(getColor());
		canvas.drawLine(x1, y1, x2, y2);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape newLine = new LineSegment();
		newLine.setPosition(getPosition());
		newLine.setColor(getColor());
		newLine.setFillColor(getFillColor());
		Map<String, Double> m = new HashMap<>();
		for (Map.Entry s : getProperties().entrySet())
			m.put(s.getKey().toString(), (Double) s.getValue());
		newLine.setProperties(m);
		return (Object) newLine;
	}
}
