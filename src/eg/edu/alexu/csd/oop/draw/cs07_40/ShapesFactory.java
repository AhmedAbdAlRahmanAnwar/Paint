package eg.edu.alexu.csd.oop.draw.cs07_40;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ShapesFactory {

	private String shapeName;
	private Shape shape;

	public void setShape(String shapeName) {

		this.shapeName = shapeName;

	}

	private void prepareName() {
		shapeName = shapeName.toLowerCase();
	}

	public Shape getShape() {

		prepareName();
		switch (shapeName) {

		case "ellipse":
			shape = new Ellipse();
			break;
		case "line":
			shape = new LineSegment();
			break;
		case "rectangle":
			shape = new Rectangle();
			break;
		case "square":
			shape = new Square();
			break;
		case "triangle":
			shape = new Triangle();
			break;

		case "right triangle":
			shape = new Triangle();
			break;

		case "circle":
			shape = new Circle();
			break;
		default:
			shape = null;
			break;
		}

		return shape;
	}

}
