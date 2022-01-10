package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawModel implements DrawingEngine {

	private Stack<ShapeStates> stack1, stack2;
	private ArrayList<Shape> drawnShapes;
	private int shapeLocation = 0;
	private int redoCounter = 0;
	private int undoCounter = 0;
	private JarInputStream jarInputStream;

	public DrawModel() {

		drawnShapes = new ArrayList<Shape>();
		stack1 = new Stack<ShapeStates>();
		stack2 = new Stack<ShapeStates>();
	}

	@Override
	public void refresh(Graphics canvas) {

		Shape temp;
		if (getShapes() != null) {
			for (int i = 0; i < getShapes().length; i++) {
				temp = getShapes()[i];
				if (temp != null) {
					temp.draw(canvas);
				}
			}
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub

		ShapeStates shapeInfo = new ShapeStates();
		shapeInfo.shape = shape;
		drawnShapes.add(shape);
		shapeLocation = drawnShapes.indexOf(shape);
		shapeInfo.location = shapeLocation;
		shapeInfo.states = "add";
		++shapeLocation;
		if (stack1.size() >= 20) {
			stack1.remove(0);
		}
		stack1.push(shapeInfo);
		stack2.clear();
		undoCounter = 0;
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub

		ShapeStates shapeInfo = new ShapeStates();
		shapeInfo.shape = shape;
		shapeInfo.location = drawnShapes.indexOf(shape);
		drawnShapes.remove(shape);
		shapeInfo.states = "remove";
		--shapeLocation;
		if (stack1.size() >= 20) {
			stack1.remove(0);
		}
		stack1.push(shapeInfo);
		stack2.clear();
		undoCounter = 0;

	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub

		int index = drawnShapes.indexOf(oldShape);
		drawnShapes.remove(index);
		drawnShapes.add(index, newShape);

		// for old shape
		ShapeStates shapeInfo1 = new ShapeStates();
		// for new shape
		ShapeStates shapeInfo2 = new ShapeStates();

		shapeInfo1.shape = oldShape;
		shapeInfo2.shape = newShape;
		shapeInfo1.location = shapeInfo2.location = index;
		shapeInfo1.states = "oldupdate";
		shapeInfo2.states = "newupdate";
		if (stack1.size() >= 20) {
			stack1.remove(0);
			stack1.remove(0);
		}
		stack1.push(shapeInfo1);
		stack1.push(shapeInfo2);
		stack2.clear();
		undoCounter = 0;

	}

	@SuppressWarnings("unchecked")
	public void save_loadColors(JSONObject jsonWriter, Shape shape) {
		jsonWriter.put("Colorblue", shape.getColor().getBlue());
		jsonWriter.put("Colorgreen", shape.getColor().getGreen());
		jsonWriter.put("Colorred", shape.getColor().getRed());
		jsonWriter.put("FillColobluer", shape.getFillColor().getBlue());
		jsonWriter.put("FillColorgreen", shape.getFillColor().getGreen());
		jsonWriter.put("FillColorred", shape.getFillColor().getRed());
	}
	
	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		if (drawnShapes.size() >= 0) {
			Shape[] shapeList = new Shape[drawnShapes.size()];
			drawnShapes.toArray(shapeList);
			return shapeList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub

		List<Class<? extends Shape>> supportedShapes = new LinkedList<Class<? extends Shape>>();

		supportedShapes.add(Circle.class);
		supportedShapes.add(Ellipse.class);
		supportedShapes.add(LineSegment.class);
		supportedShapes.add(Rectangle.class);
		supportedShapes.add(Square.class);
		supportedShapes.add(Triangle.class);

		String[] filePath = System.getProperty("java.class.path").split("" + File.pathSeparatorChar);

		for (int i = 0; i < filePath.length; ++i) {
			if (filePath[i].endsWith(".jar")) {
				try {
					jarInputStream = new JarInputStream(new FileInputStream(filePath[i]));
					JarEntry jarEntry;
					while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
						if (jarEntry.getName().endsWith(".class")) {
							String name = jarEntry.getName().replace("/", ".").replaceAll(".class", "");
							Class<?> newClass;
							try {
								newClass = Class.forName(name);
							} catch (Exception e) {
								// TODO: handle exception
								continue;
							}
							if (name.indexOf("$") == -1) {
								int classModifier = newClass.getModifiers();
								if (!((Modifier.isAbstract(classModifier)) || Modifier.isInterface(classModifier))) {
									Class<?>[] interfaces = newClass.getInterfaces();
									for (int k = 0; k < interfaces.length; k++) {
										if (interfaces[k].getName().equals("eg.edu.alexu.csd.oop.draw.Shape")) {
											supportedShapes.add((Class<? extends Shape>) newClass);
											break;
										}
									}
								}

							}
						}
					}
				}

				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						jarInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return supportedShapes;

	}

	@Override
	public void redo() {

		if (redoCounter > 20 || stack2.isEmpty()) {
			return;
		} else {
			if (stack2.peek().states.equals("add")) {
				stack1.push(stack2.pop());
				drawnShapes.add(stack1.peek().shape);
				++shapeLocation;
			} else if (stack2.peek().states.equals("remove")) {
				stack1.push(stack2.pop());
				drawnShapes.remove(stack1.peek().shape);
				--shapeLocation;
			} else if (stack2.peek().states.equals("oldupdate")) {
				stack1.push(stack2.pop());
				drawnShapes.remove(stack1.peek().shape);
				stack1.push(stack2.pop());
				drawnShapes.add(stack1.peek().location, stack1.peek().shape);
			}
			++redoCounter;
			undoCounter = 0;
		}
	}

	@Override
	public void undo() {
		if (undoCounter > 20 || stack1.isEmpty()) {
			return;
		} else {
			if (stack1.peek().states.equals("add")) {
				stack2.push(stack1.pop());
				drawnShapes.remove(stack2.peek().shape);
				--shapeLocation;
			} else if (stack1.peek().states.equals("remove")) {
				stack2.push(stack1.pop());
				drawnShapes.add(stack2.peek().location, stack2.peek().shape);
				++shapeLocation;
			} else if (stack1.peek().states.equals("newupdate")) {
				stack2.push(stack1.pop());
				drawnShapes.remove(stack2.peek().shape);
				drawnShapes.add(stack2.peek().location, stack1.peek().shape);
				stack2.push(stack1.pop());
			}
			++undoCounter;
			redoCounter = 0;
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		if (path.charAt(path.length() - 1) == 'l') {
			XML_Save(path);
		} else if (path.charAt(path.length() - 1) == 'n') {
			json_Save(path);
		}
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		drawnShapes.clear();
		stack1.clear();
		stack2.clear();
		if (path.charAt(path.length() - 1) == 'l') {
			XML_Load(path);
		} else if (path.charAt(path.length() - 1) == 'n') {
			json_Load(path);
		}
	}

	@SuppressWarnings("unchecked")
	public void json_Save(String path) {

		JSONObject jsonWriter;
		JSONArray jsonArray = new JSONArray();
		JSONObject mainObject = new JSONObject();
		for (int i = 0; i < drawnShapes.size(); i++) {
			jsonWriter = new JSONObject();
			Shape shape = drawnShapes.get(i);
			
			String className = shape.getClass().getName().toLowerCase();
			int beginningOfName = className.lastIndexOf(".");
			String shapeName = className.substring(beginningOfName + 1);
			if (shapeName.equals("linesegment")) {
				jsonWriter.put("shapeName", "linesegment");
				jsonWriter.put("x1", shape.getPosition().x);
				jsonWriter.put("y1", shape.getPosition().y);
				jsonWriter.put("x2", shape.getProperties().get("x2"));
				jsonWriter.put("y2", shape.getProperties().get("y2"));
				jsonWriter.put("Colorblue", shape.getColor().getBlue());
				jsonWriter.put("Colorgreen", shape.getColor().getGreen());
				jsonWriter.put("Colorred", shape.getColor().getRed());
			} else if (shapeName.equals("circle")) {
				jsonWriter.put("shapeName", "circle");
				jsonWriter.put("x", shape.getPosition().x);
				jsonWriter.put("y", shape.getPosition().y);
				jsonWriter.put("radius", shape.getProperties().get("radius"));
				save_loadColors(jsonWriter, shape);
			} else if (shapeName.equals("ellipse")) {
				jsonWriter.put("shapeName", "ellipse");
				jsonWriter.put("x", shape.getPosition().x);
				jsonWriter.put("y", shape.getPosition().y);
				jsonWriter.put("width", shape.getProperties().get("width"));
				jsonWriter.put("height", shape.getProperties().get("height"));
				save_loadColors(jsonWriter, shape);
			} else if (shapeName.equals("triangle")) {
				jsonWriter.put("shapeName", "triangle");
				jsonWriter.put("x1", shape.getPosition().x);
				jsonWriter.put("y1", shape.getPosition().y);
				jsonWriter.put("x2", shape.getProperties().get("x2"));
				jsonWriter.put("y2", shape.getProperties().get("y2"));
				jsonWriter.put("x3", shape.getProperties().get("x3"));
				jsonWriter.put("y3", shape.getProperties().get("y3"));
				save_loadColors(jsonWriter, shape);
			} else if (shapeName.equals("square")) {
				jsonWriter.put("shapeName", "square");
				jsonWriter.put("x1", shape.getPosition().x);
				jsonWriter.put("y1", shape.getPosition().y);
				jsonWriter.put("width", shape.getProperties().get("width"));
				save_loadColors(jsonWriter, shape);
			} else if (shapeName.equals("rectangle")) {
				jsonWriter.put("shapeName", "rectangle");
				jsonWriter.put("x1", shape.getPosition().x);
				jsonWriter.put("y1", shape.getPosition().y);
				jsonWriter.put("width", shape.getProperties().get("width"));
				jsonWriter.put("height", shape.getProperties().get("height"));
				save_loadColors(jsonWriter, shape);
			}
			jsonArray.add(jsonWriter);
		}
		mainObject.put("Shapes", jsonArray);
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(mainObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void json_Load(String path) {

		try {
			JSONParser parser = new JSONParser();
			JSONObject shapeObject;
			String shapename;
			JSONArray array = new JSONArray();
			JSONObject zObject = (JSONObject) parser.parse(new FileReader(path));

			array = (JSONArray) zObject.get("Shapes");
			for (int i = 0; i < array.size(); i++) {
				shapeObject = (JSONObject) array.get(i);
				shapename = shapeObject.get("shapeName").toString();
				if (shapename.equalsIgnoreCase("linesegment")) {
					LineSegment line = new LineSegment();
					Point point = new Point();
					point.x = (int) shapeObject.get("x1");
					point.y = (int) shapeObject.get("y1");
					line.setPosition(point);
					line.properties.put("x2", (Double) shapeObject.get("x2"));
					line.properties.put("y2", (Double) shapeObject.get("y2"));
					line.setColor((Color) shapeObject.get("Color"));
					addShape(line);
				} else if (shapename.equalsIgnoreCase("circle")) {
					Circle circle = new Circle();
					Point point = new Point();
					point.x = (int) shapeObject.get("x");
					point.y = (int) shapeObject.get("y");
					circle.setPosition(point);
					circle.properties.put("radius", (Double) shapeObject.get("radius"));
					circle.setColor((Color) shapeObject.get("Color"));
					circle.setFillColor((Color) shapeObject.get("FillColor"));
					addShape(circle);
				} else if (shapename.equalsIgnoreCase("ellipse")) {
					Ellipse ellipse = new Ellipse();
					Point point = new Point();
					point.x = (int) shapeObject.get("x");
					point.y = (int) shapeObject.get("y");
					ellipse.setPosition(point);
					ellipse.properties.put("width", (Double) shapeObject.get("width"));
					ellipse.properties.put("height", (Double) shapeObject.get("height"));
					ellipse.setColor((Color) shapeObject.get("Color"));
					ellipse.setFillColor((Color) shapeObject.get("FillColor"));
					addShape(ellipse);
				} else if (shapename.equalsIgnoreCase("triangle")) {
					Triangle triangle = new Triangle();
					Point point1 = new Point();
					point1.x = (int) shapeObject.get("x1");
					point1.y = (int) shapeObject.get("y1");
					triangle.setPosition(point1);
					triangle.properties.put("x2", (Double) shapeObject.get("x2"));
					triangle.properties.put("y2", (Double) shapeObject.get("y2"));
					triangle.properties.put("x3", (Double) shapeObject.get("x3"));
					triangle.properties.put("y", (Double) shapeObject.get("y3"));
					triangle.setColor((Color) shapeObject.get("Color"));
					triangle.setFillColor((Color) shapeObject.get("FillColor"));
					addShape(triangle);
				} else if (shapename.equalsIgnoreCase("square")) {
					Square square = new Square();
					Point point = new Point();
					point.x = (int) shapeObject.get("x1");
					point.y = (int) shapeObject.get("y1");
					square.setPosition(point);
					square.properties.put("width", (Double) shapeObject.get("width"));
					square.setColor((Color) shapeObject.get("Color"));
					square.setFillColor((Color) shapeObject.get("FillColor"));
					addShape(square);
				} else if (shapename.equalsIgnoreCase("rectangle")) {
					Rectangle rectangle = new Rectangle();
					Point point = new Point();
					point.x = (int) shapeObject.get("x1");
					point.y = (int) shapeObject.get("y1");
					rectangle.setPosition(point);
					rectangle.properties.put("width", (Double) shapeObject.get("width"));
					rectangle.properties.put("height", (Double) shapeObject.get("height"));
					rectangle.setColor((Color) shapeObject.get("Color"));
					rectangle.setFillColor((Color) shapeObject.get("FillColor"));
					addShape(rectangle);
				}
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void XML_Save(String path) {

		try {
			FileWriter newfile = new FileWriter(path);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
			XMLEncoder xml = new XMLEncoder(out);
			xml.writeObject(drawnShapes);
			xml.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void XML_Load(String path) {

		try {
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path));
			XMLDecoder decoder = new XMLDecoder(inputStream);
			drawnShapes = (ArrayList<Shape>) decoder.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}