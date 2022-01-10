package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawController {

	private DrawView drawView;
	private ShapesFactory shapesFactory;
	private String btnPressed;
	private DrawModel drawModel;
	private ShapesJPanel shapesJPanel;
	private Shape temp;
	private Color borderColor = null;
	private Color fillColor = null;
	private String savePath, loadPath;

	public DrawController(DrawView drawView) {
		this.drawView = drawView;
		drawModel = new DrawModel();
		shapesFactory = new ShapesFactory();
	}

	public void setUndo() {
		drawModel.undo();
		int size = drawModel.getShapes().length;
		drawView.updateShapesCompoBox(size);
		shapesJPanel.setDrawModel(drawModel);
		shapesJPanel.repaint();
	}

	public void setRedo() {
		drawModel.redo();
		int size = drawModel.getShapes().length;
		drawView.updateShapesCompoBox(size);
		shapesJPanel.setDrawModel(drawModel);
		shapesJPanel.repaint();
	}

	public void setSave() {
		drawView.setSaveOptionPane();
	}

	public void setSavePath(String path) {
		savePath = path;

		// saving
		drawModel.save(savePath);
		int size = drawModel.getShapes().length;
		drawView.updateShapesCompoBox(size);
	}

	public void setLoad() {
		drawView.setLoadOptionPane();
	}

	public void setLoadPath(String path) {
		loadPath = path;

		// loading
		drawModel.load(loadPath);
		int size = drawModel.getShapes().length;
		drawView.updateShapesCompoBox(size);
		shapesJPanel.setShape(null, null);
		shapesJPanel.setDrawModel(drawModel);
		shapesJPanel.repaint();
	}

	public void setDrawPanel(JPanel drawPanel) {
		shapesJPanel = (ShapesJPanel) drawPanel;
	}

	public DrawModel getDrawModel() {
		return drawModel;
	}

	public void setRemoveShape(int i) {
		if ((i < drawModel.getShapes().length) && (i >= 0)) {
			drawModel.removeShape(drawModel.getShapes()[i]);
			drawView.removeShapeFromCompoBox(i);
			shapesJPanel.setDrawModel(drawModel);
			shapesJPanel.repaint();
		} else {
			drawView.showErrorMassage("There is no Shape to remove");
		}
	}

	public void addShapeToUpdate(String shapeName, Point position, Color borderColor, Color fillColor,
			Map<String, Double> properties) {

		Shape newShape;
		shapesFactory.setShape(shapeName);
		newShape = shapesFactory.getShape();

		newShape.setPosition(position);
		newShape.setFillColor(fillColor);
		newShape.setColor(borderColor);
		newShape.setProperties(properties);

		int i = drawView.getSelectedShapeIndex();

		if ((i < drawModel.getShapes().length) && (i >= 0)) {

			Shape oldShape = drawModel.getShapes()[i];
			drawModel.updateShape(oldShape, newShape);

			int size = drawModel.getShapes().length;
			drawView.updateShapesCompoBox(size);

			shapesJPanel.setDrawModel(drawModel);
			shapesJPanel.repaint();
		} else {
			drawView.showErrorMassage("Shape can't be updated Or there is no Shape to update");
		}
	}

	public void refresh() {
		drawModel.refresh(shapesJPanel.getGraphics());
	}

	public void addShape(DrawModel drawModel, Shape shape) {
		drawModel.addShape(shape);
		refresh();
	}

	public DrawView getDrawView() {
		return drawView;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setBtnSource(String btnSrc) {
		btnPressed = btnSrc;
	}

	public void prepareCanvas() {

		shapesFactory.setShape(btnPressed);
		temp = shapesFactory.getShape();

		if (borderColor != null) {
			temp.setColor(borderColor);
		}

		if (fillColor != null) {
			temp.setFillColor(fillColor);
		}

		shapesJPanel.setShape(temp, btnPressed);

		shapesJPanel.setDrawModel(drawModel);

		shapesJPanel.createMouseListener();

		if (temp != null) {
			drawModel.addShape(temp);
			drawView.addShapeToCompoBox();
		}

	}

	public void setShapePosition(Shape shape, Point shapePosition) {
		if (shape != null) {
			shape.setPosition(shapePosition);
		}
	}

	public void setShapeProperties(Shape shape, Map<String, Double> shapeProperties) {
		if (shape != null) {
			shape.setProperties(shapeProperties);
		}
	}
}