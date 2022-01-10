package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends ShapesModel {

	public Square() {

		// initializing
		shapeName = "square";
		setColor(Color.BLACK);
		setFillColor(Color.WHITE);
		setPosition(new Point(0, 0));
		properties = new HashMap<String, Double>();
		properties.put("width", 0.000);
		setProperties(properties);

	}

	@Override
	public void draw(Graphics canvas) {
		int x = getPosition().x;
		int y = getPosition().y;
		int width = getProperties().get("width").intValue();
		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillRect(x, y, width, width);
		((Graphics2D) canvas).setStroke(new BasicStroke());
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawRect(x, y, width, width);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape newSquare = new Square();
		newSquare.setPosition(getPosition());
		newSquare.setColor(getColor());
		newSquare.setFillColor(getFillColor());
		Map<String, Double> m = new HashMap<>();
		for (Map.Entry s : getProperties().entrySet())
			m.put(s.getKey().toString(), (Double) s.getValue());
		newSquare.setProperties(m);
		return (Object) newSquare;
	}

}
