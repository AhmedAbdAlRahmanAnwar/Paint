package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.Shape;

@SuppressWarnings("serial")
public class ShapesJPanel extends JPanel {

	private DrawController drawController;
	private DrawModel drawModel;
	private Shape shape;
	private String shapeName;
	private Point topLeft, bottomRight;
	private Map<String, Double> shapeProperties;

	public ShapesJPanel(DrawController drawController) {

		super();

		this.drawController = drawController;
		shapeProperties = new HashMap<String, Double>();
	}

	public void setShape(Shape shape, String shapeName) {
		this.shape = shape;
		this.shapeName = shapeName;
	}

	public Shape getShape() {
		return shape;
	}

	public void createMouseListener() {

		if (shape != null) {

			addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {

					topLeft = bottomRight = e.getPoint();
					drawController.setShapePosition(shape, topLeft);

					switch (shapeName) {

					case "Line":
						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Circle":
						Double radius = 0.0;
						shapeProperties.put("radius", radius);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Square":
						Double width = 0.0;
						shapeProperties.put("width", width);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Rectangle":
						shapeProperties.put("width", 0.0);
						shapeProperties.put("height", 0.0);
						drawController.setShapeProperties(shape, shapeProperties);
						break;
					case "Ellipse":
						shapeProperties.put("width", 0.0);
						shapeProperties.put("height", 0.0);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Triangle":
						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						shapeProperties.put("x3", bottomRight.getX());
						shapeProperties.put("y3", bottomRight.getY());
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Right Triangle":
						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						shapeProperties.put("x3", bottomRight.getX());
						shapeProperties.put("y3", bottomRight.getY());
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					default:
						break;
					}
					repaint();
				}

				public void mouseReleased(MouseEvent e) {

					if (!shapeName.equals("Triangle")) {
						bottomRight = e.getPoint();
					}

					switch (shapeName) {
					case "Line":
						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Circle":
						
						Double radius = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						radius = radius / (2);
						shapeProperties.put("radius", radius);
						drawController.setShapeProperties(shape, shapeProperties);

						break;

					case "Square":
						Double width = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", width);
						drawController.setShapeProperties(shape, shapeProperties);

						break;
					case "Rectangle":
						Double recWidth = Point.distance(topLeft.getX(), topLeft.getY(), bottomRight.getX(),
								topLeft.getY());

						Double recHeight = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", recWidth);
						shapeProperties.put("height", recHeight);
						drawController.setShapeProperties(shape, shapeProperties);
						break;
					case "Ellipse":
						Double ovalWidth = Point.distance(topLeft.getX(), topLeft.getY(), bottomRight.getX(),
								topLeft.getY());

						Double ovalHeight = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", ovalWidth);
						shapeProperties.put("height", ovalHeight);
						drawController.setShapeProperties(shape, shapeProperties);
						break;
					case "Triangle":
						Double x1 = topLeft.getX();
						Double y1 = topLeft.getY();

						bottomRight = e.getPoint();

						Double x2 = bottomRight.getX();
						Double y2 = bottomRight.getY();

						int x0 = (int) ((x1 + x2) / 2);
						int y0 = ((int) (y1 + 0.0));

						Point p1 = new Point(x0, y0);

						drawController.setShapePosition(shape, p1);

						shapeProperties.put("x2", x2);
						shapeProperties.put("y2", y2);
						shapeProperties.put("x3", x1);
						shapeProperties.put("y3", y2);

						drawController.setShapeProperties(shape, shapeProperties);

						break;

					case "Right Triangle":

						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						shapeProperties.put("x3", topLeft.getX());
						shapeProperties.put("y3", bottomRight.getY());

						drawController.setShapeProperties(shape, shapeProperties);

						break;

					default:
						break;
					}
					repaint();

					// adding shape to shapes list
					if (shape != null) {
						
						shapeProperties = new HashMap<String, Double>();
						shape = null;
					}
				}
			});

			addMouseMotionListener(new MouseMotionAdapter() {

				public void mouseDragged(MouseEvent e) {
					super.mouseDragged(e);

					if (!shapeName.equals("Triangle")) {
						bottomRight = e.getPoint();
					}

					switch (shapeName) {
					case "Line":

						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						drawController.setShapeProperties(shape, shapeProperties);

						break;

					case "Circle":
						
						Double radius = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						radius = radius / (2);
						shapeProperties.put("radius", radius);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Square":
						Double width = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", width);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Rectangle":
						Double recWidth = Point.distance(topLeft.getX(), topLeft.getY(), bottomRight.getX(),
								topLeft.getY());

						Double recHeight = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", recWidth);
						shapeProperties.put("height", recHeight);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Ellipse":
						Double ovalWidth = Point.distance(topLeft.getX(), topLeft.getY(), bottomRight.getX(),
								topLeft.getY());

						Double ovalHeight = Point.distance(topLeft.getX(), topLeft.getY(), topLeft.getX(),
								bottomRight.getY());

						shapeProperties.put("width", ovalWidth);
						shapeProperties.put("height", ovalHeight);
						drawController.setShapeProperties(shape, shapeProperties);
						break;

					case "Triangle":
						Double x1 = topLeft.getX();
						Double y1 = topLeft.getY();

						bottomRight = e.getPoint();

						Double x2 = bottomRight.getX();
						Double y2 = bottomRight.getY();

						int x0 = (int) ((x1 + x2) / 2);
						int y0 = ((int) (y1 + 0.0));

						Point p1 = new Point(x0, y0);

						drawController.setShapePosition(shape, p1);

						shapeProperties.put("x2", x2);
						shapeProperties.put("y2", y2);
						shapeProperties.put("x3", x1);
						shapeProperties.put("y3", y2);

						drawController.setShapeProperties(shape, shapeProperties);

						break;

					case "Right Triangle":

						shapeProperties.put("x2", bottomRight.getX());
						shapeProperties.put("y2", bottomRight.getY());
						shapeProperties.put("x3", topLeft.getX());
						shapeProperties.put("y3", bottomRight.getY());

						drawController.setShapeProperties(shape, shapeProperties);

						break;

					default:
						break;
					}
					repaint();
				}
			});
		}
	}

	public void setDrawModel(DrawModel drawModel) {
		this.drawModel = drawModel;
	}

	public void refresh() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics canvas) {

		super.paintComponent(canvas);

		if (shape != null) {
			 shape.draw(canvas);
		}
		
		if (drawModel != null) {
			drawModel.refresh(canvas);
		}
	}
}
