package eg.edu.alexu.csd.oop.draw.cs07_40;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

@SuppressWarnings("serial")
public class DrawView extends JFrame {

	private String buttonPressed;
	private ShapesJPanel drawPanel;
	private DrawController drawController;
	private Color borderColor = null;
	private Color fillColor = null;
	private JComboBox<String> shapesCB;
	private int shapeIndex = 0;

	/**
	 * Create the application.
	 */
	public DrawView() {

		/**
		 * Initialize the contents of the frame.
		 */
		super();
		setResizable(false);

		this.setTitle("Drawing Shapes Application");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Abdo\\Desktop\\paint.jpg"));
		this.setBounds(0, 0, 1378, 684);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		drawController = new DrawController(this);

		//

		final JComboBox<SelectableColor> fillColorCB = new JComboBox<SelectableColor>();

		fillColorCB.addItem(new SelectableColor("WHITE", Color.WHITE));
		fillColorCB.addItem(new SelectableColor("YELLOW", Color.YELLOW));
		fillColorCB.addItem(new SelectableColor("ORANGE", Color.ORANGE));
		fillColorCB.addItem(new SelectableColor("Red", Color.RED));
		fillColorCB.addItem(new SelectableColor("PINK", Color.PINK));
		fillColorCB.addItem(new SelectableColor("MAGENTA", Color.MAGENTA));
		fillColorCB.addItem(new SelectableColor("Blue", Color.BLUE));
		fillColorCB.addItem(new SelectableColor("CYAN", Color.CYAN));
		fillColorCB.addItem(new SelectableColor("GRAY", Color.GRAY));
		fillColorCB.addItem(new SelectableColor("DARK_GRAY", Color.DARK_GRAY));
		fillColorCB.addItem(new SelectableColor("BLACK", Color.BLACK));
		fillColorCB.setRenderer(new ColorCellRenderer());
		getContentPane().add(fillColorCB);
		fillColorCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object value = fillColorCB.getSelectedItem();
				if (value instanceof SelectableColor) {
					fillColorCB.setBackground(((SelectableColor) value).getColor());
					fillColor = ((SelectableColor) value).getColor();
					drawController.setFillColor(fillColor);
				} else {
					fillColorCB.setSelectedIndex(0);
					fillColorCB.setBackground(Color.WHITE);
				}
			}
		});
		fillColorCB.setSelectedItem(null);

		fillColorCB.setBounds(1237, 150, 108, 20);
		fillColorCB.setVisible(true);

		final JComboBox<SelectableColor> borderColorCB = new JComboBox<SelectableColor>();
		borderColorCB.addItem(new SelectableColor("WHITE", Color.WHITE));
		borderColorCB.addItem(new SelectableColor("YELLOW", Color.YELLOW));
		borderColorCB.addItem(new SelectableColor("ORANGE", Color.ORANGE));
		borderColorCB.addItem(new SelectableColor("Red", Color.RED));
		borderColorCB.addItem(new SelectableColor("PINK", Color.PINK));
		borderColorCB.addItem(new SelectableColor("MAGENTA", Color.MAGENTA));
		borderColorCB.addItem(new SelectableColor("Blue", Color.BLUE));
		borderColorCB.addItem(new SelectableColor("CYAN", Color.CYAN));
		borderColorCB.addItem(new SelectableColor("GRAY", Color.GRAY));
		borderColorCB.addItem(new SelectableColor("DARK_GRAY", Color.DARK_GRAY));
		borderColorCB.addItem(new SelectableColor("BLACK", Color.BLACK));
		borderColorCB.setRenderer(new ColorCellRenderer());
		getContentPane().add(borderColorCB);
		borderColorCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object value = borderColorCB.getSelectedItem();
				if (value instanceof SelectableColor) {
					borderColorCB.setBackground(((SelectableColor) value).getColor());
					borderColor = ((SelectableColor) value).getColor();
					drawController.setBorderColor(borderColor);
				} else {
					borderColorCB.setSelectedIndex(10);
					borderColorCB.setBackground(Color.gray);

				}
			}
		});

		borderColorCB.setSelectedItem(null);

		borderColorCB.setBounds(1237, 88, 108, 20);
		borderColorCB.setVisible(true);

		drawPanel = new ShapesJPanel(drawController);
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setBounds(10, 55, 1217, 579);
		this.getContentPane().add(drawPanel);

		drawController.setDrawPanel(drawPanel);

		JButton lineBtn = new DrawButton(drawController, this, "Line");

		lineBtn.setBounds(1237, 287, 115, 23);
		this.getContentPane().add(lineBtn);

		JButton circleBtn = new DrawButton(drawController, this, "Circle");

		circleBtn.setBounds(1237, 321, 115, 23);
		this.getContentPane().add(circleBtn);

		JButton ellipseBtn = new DrawButton(drawController, this, "Ellipse");

		ellipseBtn.setBounds(1237, 355, 115, 23);
		this.getContentPane().add(ellipseBtn);

		JButton squareBtn = new DrawButton(drawController, this, "Square");

		squareBtn.setBounds(1237, 423, 115, 23);
		this.getContentPane().add(squareBtn);

		JButton rectangleBtn = new DrawButton(drawController, this, "Rectangle");

		rectangleBtn.setBounds(1237, 389, 115, 23);
		this.getContentPane().add(rectangleBtn);

		JButton triangleBtn = new DrawButton(drawController, this, "Triangle");

		triangleBtn.setBounds(1237, 457, 115, 23);
		this.getContentPane().add(triangleBtn);

		JButton rightTriangleBtn = new DrawButton(drawController, this, "Right Triangle");

		rightTriangleBtn.setBounds(1237, 491, 115, 23);
		this.getContentPane().add(rightTriangleBtn);

		JLabel lblFillColor = new JLabel("Fill Color");
		lblFillColor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFillColor.setForeground(Color.BLACK);
		lblFillColor.setLabelFor(fillColorCB);
		lblFillColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFillColor.setBackground(Color.WHITE);
		lblFillColor.setBounds(1237, 119, 108, 20);
		getContentPane().add(lblFillColor);

		JLabel lblBorderColor = new JLabel("Border Color");
		lblBorderColor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBorderColor.setLabelFor(borderColorCB);
		lblBorderColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorderColor.setBackground(Color.WHITE);
		lblBorderColor.setForeground(Color.BLACK);
		lblBorderColor.setBounds(1237, 55, 108, 22);
		getContentPane().add(lblBorderColor);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawController.setUndo();
			}
		});
		btnUndo.setBounds(10, 23, 104, 23);
		getContentPane().add(btnUndo);

		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawController.setRedo();
			}
		});
		btnRedo.setBounds(365, 23, 104, 23);
		getContentPane().add(btnRedo);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawController.setSave();
			}
		});
		btnSave.setBounds(124, 23, 115, 23);
		getContentPane().add(btnSave);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawController.setLoad();
			}
		});
		btnLoad.setBounds(251, 23, 104, 23);
		getContentPane().add(btnLoad);

		shapesCB = new JComboBox<String>();
		shapesCB.setBounds(857, 24, 108, 20);
		getContentPane().add(shapesCB);

		JLabel lblShapes = new JLabel("Select Shape to modify");
		lblShapes.setLabelFor(shapesCB);
		lblShapes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShapes.setHorizontalAlignment(SwingConstants.CENTER);
		lblShapes.setBounds(685, 24, 162, 20);
		getContentPane().add(lblShapes);

		JButton btnRemoveShape = new JButton("Remove Shape");
		btnRemoveShape.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawController.setRemoveShape(getSelectedShapeIndex());
			}
		});
		btnRemoveShape.setBounds(975, 23, 121, 23);
		getContentPane().add(btnRemoveShape);

		JButton btnUpdate = new JButton("Update Shape");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFrame update = new UpdateFrame(drawController);
				update.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				update.setVisible(true);
			}
		});
		btnUpdate.setBounds(1106, 23, 121, 23);
		getContentPane().add(btnUpdate);

	}

	public void setSaveOptionPane() {

		JFrame savePane = new JFrame("Save");

		JLabel saveLabel = new JLabel("Save Path");
		saveLabel.setBounds(10, 15, 230, 20);
		saveLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 40, 230, 20);

		JComboBox<String> fileType = new JComboBox<String>();
		fileType.setBounds(15, 90, 90, 20);
		fileType.addItem("json");
		fileType.addItem("xml");
		fileType.setSelectedItem(null);

		fileType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textArea.getText().isEmpty()) {
					textArea.setText(textArea.getText() + "." + fileType.getSelectedItem());
				}
			}
		});

		JButton savePath = new JButton("...");
		savePath.setBounds(250, 40, 35, 20);
		savePath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(savePane);
				textArea.setText(fileChooser.getSelectedFile().getPath());
			}
		});

		JLabel label = new JLabel("File Type");
		label.setBounds(15, 65, 90, 20);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton saveBtn = new JButton("Save");
		saveBtn.setBounds(115, 90, 80, 20);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				int length = text.length();

				if (length >= 4) {

					if (text.substring(length - 5, length).equals(".json")
							|| text.substring(length - 4, length).equals(".xml")) {
						drawController.setSavePath(textArea.getText());
						savePane.dispose();
						JOptionPane.showMessageDialog(null, "Saving done");
					} else {
						JOptionPane.showMessageDialog(null, "Please,Enter a valid path");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please,Enter a valid path");
				}
			}
		});

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(205, 90, 80, 20);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				savePane.dispose();
			}
		});

		savePane.getContentPane().add(saveLabel);
		savePane.getContentPane().add(textArea);
		savePane.getContentPane().add(savePath);
		savePane.getContentPane().add(label);
		savePane.getContentPane().add(fileType);
		savePane.getContentPane().add(saveBtn);
		savePane.getContentPane().add(cancelBtn);

		savePane.setBounds(300, 200, 300, 150);
		savePane.setResizable(false);
		savePane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		savePane.getContentPane().setLayout(null);

		savePane.setVisible(true);
	}

	public void addShapeToCompoBox() {
		shapesCB.addItem("Shape # " + "" + shapeIndex);
		++shapeIndex;
	}

	public int getSelectedShapeIndex() {
		return shapesCB.getSelectedIndex();
	}

	public void removeShapeFromCompoBox(int index) {
		shapesCB.removeItemAt(index);
	}

	public void updateShapesCompoBox(int size) {
		shapesCB.removeAllItems();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				shapesCB.addItem("Shape # " + "" + i);
			}
		}
	}

	public void showErrorMassage(String massage) {
		JOptionPane.showMessageDialog(null, massage);
	}

	public void setLoadOptionPane() {

		JFrame loadPane = new JFrame("Load");

		JLabel loadLabel = new JLabel("Load Path");
		loadLabel.setBounds(10, 15, 230, 20);
		loadLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 40, 230, 20);

		JButton loadPath = new JButton("...");
		loadPath.setBounds(250, 40, 35, 20);
		loadPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(loadPane);
				textArea.setText(fileChooser.getSelectedFile().getPath());
			}
		});

		JButton loadBtn = new JButton("Load");
		loadBtn.setBounds(60, 90, 80, 20);
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = textArea.getText();
				int length = text.length();

				if (length >= 4) {

					if (text.substring(length - 5, length).equals(".json")
							|| text.substring(length - 4, length).equals(".xml")) {
						drawController.setLoadPath(text);
						loadPane.dispose();
						JOptionPane.showMessageDialog(null, "Loading Done");
					} else {
						JOptionPane.showMessageDialog(null, "Please,Enter a valid path");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please , Enter a valid path");
				}
			}
		});

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(160, 90, 80, 20);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadPane.dispose();
			}
		});

		loadPane.getContentPane().add(loadLabel);
		loadPane.getContentPane().add(textArea);
		loadPane.getContentPane().add(loadPath);
		loadPane.getContentPane().add(loadBtn);
		loadPane.getContentPane().add(cancelBtn);

		loadPane.setBounds(300, 200, 300, 150);
		loadPane.setResizable(false);
		loadPane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loadPane.getContentPane().setLayout(null);

		loadPane.setVisible(true);
	}

	public String getButton() {
		return buttonPressed;
	}

	public JPanel getDrawPanel() {
		return (ShapesJPanel) drawPanel;
	}
}

class SelectableColor {
	private String name;
	private Color color;

	public SelectableColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

}

@SuppressWarnings("serial")
class ColorCellRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof SelectableColor) {
			SelectableColor sc = (SelectableColor) value;
			if (!isSelected) {
				setBackground(sc.getColor());
				setOpaque(true);
			}
			setText(sc.getName());
		}
		return this;
	}

}
