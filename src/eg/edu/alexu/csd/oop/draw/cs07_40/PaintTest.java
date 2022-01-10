package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.Shape;

public class PaintTest {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintTest window = new PaintTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaintTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new ShapesTest();
		panel.setBounds(10, 11, 414, 390);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
	}

}

@SuppressWarnings("serial")
class ShapesTest extends JPanel {
	Shape c, l, t, r, s, e, l2;
	int x1, x2, y1, y2;
	DrawModel drawController;
	Map<String, Double> pro;
	Point first,second;

	public ShapesTest() {

		drawController = new DrawModel();
		c = new Circle();

		e = new Ellipse();

		l = new LineSegment();
		first = new Point();
		t = new Triangle();
		s = new Square();
		r = new Rectangle();

		c.setColor(e.getFillColor());
		c.setPosition(new Point(100, 100));
		pro = new HashMap<String, Double>();
		pro.put("radius", 25.5);
		c.setProperties(pro);

		c.setFillColor(e.getColor());

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent m) {
				first = m.getPoint();
				second = first;
				l.setPosition(first);	
				pro.put("x2", Double.valueOf(second.getX()));
				pro.put("y2", Double.valueOf(second.getY()));
				l.setProperties(pro);
				repaint();
			}

			public void mouseReleased(MouseEvent m) {

				second = m.getPoint();			
//				l.setPosition(first);
				pro.put("x2", Double.valueOf(second.getX()));
				pro.put("y2", Double.valueOf(second.getY()));
				l.setProperties(pro);
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent m) {
				second = m.getPoint();
				pro.put("x2", Double.valueOf(second.getX()));
				pro.put("y2", Double.valueOf(second.getY()));
				l.setProperties(pro);
				repaint();
			}
		});

		// drawController.addShape(l);
		// drawController.addShape(c);

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D graphics2d = (Graphics2D) g;

		l.setColor(Color.blue);

		// pro.put("x2", Double.valueOf(x2));
		// pro.put("y2", Double.valueOf(y2));
		//
		// l.setPosition(new Point(x1, y1));

		l.draw(graphics2d);

//		l.setPosition(new Point(x1, y1));

		// c.draw(graphics2d);
		// e.draw(graphics2d);
		// l.draw(graphics2d);
		// s.draw(graphics2d);
		// t.draw(graphics2d);
		// r.draw(graphics2d);

		// drawController.refresh(graphics2d);

		// drawController.updateShape(l, c);

		// drawController.refresh(graphics2d);

		// drawController.removeShape(e);

		// drawController.addShape(l);

		// drawController.refresh(graphics2d);

		// drawController.updateShape(c,l2);
		//
		// drawController.refresh(graphics2d);
		//
		// try {
		// l2 = (Shape) l.clone();
		// l2.setPosition(new Point(300, 350));
		// l2.draw(graphics2d);
		// } catch (CloneNotSupportedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
	}
}
