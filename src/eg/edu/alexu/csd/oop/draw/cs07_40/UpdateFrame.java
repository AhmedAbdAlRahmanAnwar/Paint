package eg.edu.alexu.csd.oop.draw.cs07_40;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UpdateFrame extends JFrame {

	private JPanel contentPane;
	private Color borderColor;
	private Color fillColor;
	private String shapeNAme;
	private Point position;
	private Map<String, Double> shapeProberties;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblTopLeftX;
	private JLabel lblTopLeftY;
	private JLabel lblBottomRightX;
	private JLabel lblBottomRightY;
	private JLabel lblRadius;
	private JLabel lblLength;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JLabel lbFirstX;
	private JLabel lblFirstY;
	private JLabel lblSecondX;
	private JLabel lblSecondY;
	private JLabel lblThirdX;
	private JLabel lblThirdY;
	private JTextField topLeftX_TF;
	private JTextField topLeftY_TF;
	private JTextField bottomRightX_TF;
	private JTextField bottomRightY_TF;
	private JTextField length_TF;
	private JTextField radius_TF;
	private JTextField width_TF;
	private JTextField height_TF;
	private JTextField firstX_TF;
	private JTextField firstY_TF;
	private JTextField secondX_TF;
	private JTextField secondY_TF;
	private JTextField thirdX_TF;
	private JTextField thirdY_TF;

	public UpdateFrame(DrawController drawController) {

		shapeProberties = new HashMap<String, Double>();

		borderColor = Color.BLACK;
		fillColor = Color.WHITE;

		setTitle("Update Shape");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChooseShapeTo = new JLabel("Choose shape to replace the old shape");
		lblChooseShapeTo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChooseShapeTo.setBounds(10, 11, 253, 30);
		contentPane.add(lblChooseShapeTo);

		JButton btnBorderColor = new JButton("Select Border Color");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(btnBorderColor, "Choose border color for shape", Color.BLACK);
			}
		});
		btnBorderColor.setBounds(49, 52, 155, 30);
		contentPane.add(btnBorderColor);

		JButton btnFillcolor = new JButton("Select Fill Color");
		btnFillcolor.setBounds(230, 52, 155, 30);
		btnFillcolor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillColor = JColorChooser.showDialog(btnBorderColor, "Choose fill color for shape", Color.BLACK);
			}
		});

		contentPane.add(btnFillcolor);

		JComboBox<String> shapesComboBox = new JComboBox<String>();
		lblChooseShapeTo.setLabelFor(shapesComboBox);
		shapesComboBox.setBounds(273, 11, 141, 30);

		shapesComboBox.addItem("Line");
		shapesComboBox.addItem("Circle");
		shapesComboBox.addItem("Ellipse");
		shapesComboBox.addItem("Square");
		shapesComboBox.addItem("Rectangle");
		shapesComboBox.addItem("Triangle");

		shapesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();
				contentPane.add(shapesComboBox);
				contentPane.add(btnBorderColor);
				contentPane.add(btnFillcolor);
				contentPane.add(lblChooseShapeTo);

				switch (shapesComboBox.getSelectedItem().toString()) {

				case "Line":

					btnOk = new JButton("OK");
					btnOk.setBounds(49, 172, 155, 30);
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (topLeftX_TF.getText().isEmpty() || topLeftY_TF.getText().isEmpty()
									|| bottomRightX_TF.getText().isEmpty() || bottomRightY_TF.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Line";

								int x1 = Integer.valueOf(topLeftX_TF.getText());
								int y1 = Integer.valueOf(topLeftY_TF.getText());
								Double x2 = Double.valueOf(bottomRightX_TF.getText());
								Double y2 = Double.valueOf(bottomRightY_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("x2", x2);
								shapeProberties.put("y2", y2);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});

					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 172, 155, 30);
					contentPane.add(btnCancel);

					lblTopLeftX = new JLabel("Top Left X");
					lblTopLeftX.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftX.setBounds(10, 93, 80, 30);
					contentPane.add(lblTopLeftX);

					topLeftX_TF = new JTextField();
					topLeftX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(topLeftX_TF);
					topLeftX_TF.setColumns(10);

					topLeftY_TF = new JTextField();
					topLeftY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(topLeftY_TF);
					topLeftY_TF.setColumns(10);

					lblTopLeftY = new JLabel("Top Left Y");
					lblTopLeftY.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftY.setBounds(220, 93, 80, 30);
					contentPane.add(lblTopLeftY);

					lblBottomRightX = new JLabel("Bottom Right X");
					lblBottomRightX.setHorizontalAlignment(SwingConstants.CENTER);
					lblBottomRightX.setBounds(10, 134, 80, 30);
					contentPane.add(lblBottomRightX);

					bottomRightX_TF = new JTextField();
					lblBottomRightX.setLabelFor(bottomRightX_TF);
					bottomRightX_TF.setBounds(100, 133, 104, 28);
					contentPane.add(bottomRightX_TF);
					bottomRightX_TF.setColumns(10);

					lblBottomRightY = new JLabel("Bottom Right Y");
					lblBottomRightY.setHorizontalAlignment(SwingConstants.CENTER);
					lblBottomRightY.setBounds(220, 132, 80, 30);
					contentPane.add(lblBottomRightY);

					bottomRightY_TF = new JTextField();
					lblBottomRightY.setLabelFor(bottomRightY_TF);
					bottomRightY_TF.setBounds(310, 133, 104, 28);
					contentPane.add(bottomRightY_TF);
					bottomRightY_TF.setColumns(10);

					lblChooseShapeTo.setText("Set Line Properties");
					setBounds(100, 100, 440, 252);
					break;

				case "Square":

					btnOk = new JButton("OK");

					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (topLeftX_TF.getText().isEmpty() || topLeftY_TF.getText().isEmpty()
									|| length_TF.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Square";

								int x1 = Integer.valueOf(topLeftX_TF.getText());
								int y1 = Integer.valueOf(topLeftY_TF.getText());

								Double r = Double.valueOf(length_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("width", r);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});
					btnOk.setBounds(49, 172, 155, 30);
					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 172, 155, 30);
					contentPane.add(btnCancel);

					lblTopLeftX = new JLabel("Top Left X");
					lblTopLeftX.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftX.setBounds(10, 93, 80, 30);
					contentPane.add(lblTopLeftX);

					topLeftX_TF = new JTextField();
					topLeftX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(topLeftX_TF);
					topLeftX_TF.setColumns(10);

					topLeftY_TF = new JTextField();
					topLeftY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(topLeftY_TF);
					topLeftY_TF.setColumns(10);

					lblTopLeftY = new JLabel("Top Left Y");
					lblTopLeftY.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftY.setBounds(220, 93, 80, 30);
					contentPane.add(lblTopLeftY);

					lblLength = new JLabel("Length");
					lblLength.setHorizontalAlignment(SwingConstants.CENTER);
					lblLength.setBounds(100, 131, 104, 30);
					contentPane.add(lblLength);

					length_TF = new JTextField();
					lblLength.setLabelFor(length_TF);
					length_TF.setBounds(220, 131, 80, 30);
					contentPane.add(length_TF);
					length_TF.setColumns(10);

					lblChooseShapeTo.setText("Set Square Properties");
					setBounds(100, 100, 440, 252);
					break;

				case "Circle":

					btnOk = new JButton("OK");
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (topLeftX_TF.getText().isEmpty() || topLeftY_TF.getText().isEmpty()
									|| radius_TF.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Circle";

								int x1 = Integer.valueOf(topLeftX_TF.getText());
								int y1 = Integer.valueOf(topLeftY_TF.getText());

								Double r = Double.valueOf(radius_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("radius", r);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});

					btnOk.setBounds(49, 172, 155, 30);
					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 172, 155, 30);
					contentPane.add(btnCancel);

					lblTopLeftX = new JLabel("Top Left X");
					lblTopLeftX.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftX.setBounds(10, 93, 80, 30);
					contentPane.add(lblTopLeftX);

					topLeftX_TF = new JTextField();
					topLeftX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(topLeftX_TF);
					topLeftX_TF.setColumns(10);

					topLeftY_TF = new JTextField();
					topLeftY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(topLeftY_TF);
					topLeftY_TF.setColumns(10);

					lblTopLeftY = new JLabel("Top Left Y");
					lblTopLeftY.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftY.setBounds(220, 93, 80, 30);
					contentPane.add(lblTopLeftY);

					lblRadius = new JLabel("Radius");
					lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
					lblRadius.setBounds(100, 131, 104, 30);
					contentPane.add(lblRadius);

					radius_TF = new JTextField();
					lblRadius.setLabelFor(radius_TF);
					radius_TF.setBounds(220, 131, 80, 30);
					contentPane.add(radius_TF);
					radius_TF.setColumns(10);

					lblChooseShapeTo.setText("Set Circle Properties");
					setBounds(100, 100, 440, 252);
					break;

				case "Triangle":

					btnOk = new JButton("OK");
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (firstX_TF.getText().isEmpty() || firstY_TF.getText().isEmpty()
									|| secondX_TF.getText().isEmpty() || secondY_TF.getText().isEmpty()
									|| thirdX_TF.getText().isEmpty() || thirdY_TF.getText().isEmpty()) {
								
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Triangle";

								int x1 = Integer.valueOf(firstX_TF.getText());
								int y1 = Integer.valueOf(firstY_TF.getText());

								Double x2 = Double.valueOf(secondX_TF.getText());
								Double y2 = Double.valueOf(secondY_TF.getText());
								
								Double x3 = Double.valueOf(thirdX_TF.getText());
								Double y3 = Double.valueOf(thirdY_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("x2", x2);
								shapeProberties.put("y2", y2);
								shapeProberties.put("x3", x3);
								shapeProberties.put("y3", y3);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});
					btnOk.setBounds(49, 229, 155, 30);
					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 229, 155, 30);
					contentPane.add(btnCancel);

					lbFirstX = new JLabel("First X");
					lbFirstX.setHorizontalAlignment(SwingConstants.CENTER);
					lbFirstX.setBounds(10, 93, 80, 30);
					contentPane.add(lbFirstX);

					firstX_TF = new JTextField();
					lbFirstX.setLabelFor(firstX_TF);
					firstX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(firstX_TF);
					firstX_TF.setColumns(10);

					firstY_TF = new JTextField();
					firstY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(firstY_TF);
					firstY_TF.setColumns(10);

					lblFirstY = new JLabel("First Y");
					lblFirstY.setLabelFor(firstY_TF);
					lblFirstY.setHorizontalAlignment(SwingConstants.CENTER);
					lblFirstY.setBounds(220, 93, 80, 30);
					contentPane.add(lblFirstY);

					lblSecondX = new JLabel("Second X");
					lblSecondX.setHorizontalAlignment(SwingConstants.CENTER);
					lblSecondX.setBounds(10, 134, 80, 30);
					contentPane.add(lblSecondX);

					secondX_TF = new JTextField();
					lblSecondX.setLabelFor(secondX_TF);
					secondX_TF.setBounds(100, 133, 104, 28);
					contentPane.add(secondX_TF);
					secondX_TF.setColumns(10);

					lblSecondY = new JLabel("Second Y");
					lblSecondY.setHorizontalAlignment(SwingConstants.CENTER);
					lblSecondY.setBounds(220, 132, 80, 30);
					contentPane.add(lblSecondY);

					secondY_TF = new JTextField();
					lblSecondY.setLabelFor(secondY_TF);
					secondY_TF.setBounds(310, 133, 104, 28);
					contentPane.add(secondY_TF);
					secondY_TF.setColumns(10);

					lblThirdX = new JLabel("Third X");
					lblThirdX.setHorizontalAlignment(SwingConstants.CENTER);
					lblThirdX.setBounds(10, 175, 80, 30);
					contentPane.add(lblThirdX);

					lblThirdY = new JLabel("Third Y");
					lblThirdY.setHorizontalAlignment(SwingConstants.CENTER);
					lblThirdY.setBounds(220, 175, 80, 30);
					contentPane.add(lblThirdY);

					thirdX_TF = new JTextField();
					lblThirdX.setLabelFor(thirdX_TF);
					thirdX_TF.setColumns(10);
					thirdX_TF.setBounds(100, 172, 104, 28);
					contentPane.add(thirdX_TF);

					thirdY_TF = new JTextField();
					lblThirdY.setLabelFor(thirdY_TF);
					thirdY_TF.setColumns(10);
					thirdY_TF.setBounds(310, 172, 104, 28);
					contentPane.add(thirdY_TF);

					lblChooseShapeTo.setText("Set Triangle Properties");
					setBounds(100, 100, 440, 309);

					break;
				case "Rectangle":

					btnOk = new JButton("OK");
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (topLeftX_TF.getText().isEmpty() || topLeftY_TF.getText().isEmpty()
									|| width_TF.getText().isEmpty() || height_TF.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Rectangle";

								int x1 = Integer.valueOf(topLeftX_TF.getText());
								int y1 = Integer.valueOf(topLeftY_TF.getText());

								Double w = Double.valueOf(width_TF.getText());
								Double l = Double.valueOf(height_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("width", w);
								shapeProberties.put("height", l);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});
					btnOk.setBounds(49, 172, 155, 30);
					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 172, 155, 30);
					contentPane.add(btnCancel);

					lblTopLeftX = new JLabel("Top Left X");
					lblTopLeftX.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftX.setBounds(10, 93, 80, 30);
					contentPane.add(lblTopLeftX);

					topLeftX_TF = new JTextField();
					topLeftX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(topLeftX_TF);
					topLeftX_TF.setColumns(10);

					topLeftY_TF = new JTextField();
					topLeftY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(topLeftY_TF);
					topLeftY_TF.setColumns(10);

					lblTopLeftY = new JLabel("Top Left Y");
					lblTopLeftY.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftY.setBounds(220, 93, 80, 30);
					contentPane.add(lblTopLeftY);

					lblWidth = new JLabel("Width");
					lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
					lblWidth.setBounds(10, 134, 80, 30);
					contentPane.add(lblWidth);

					width_TF = new JTextField();
					lblWidth.setLabelFor(width_TF);
					width_TF.setBounds(100, 133, 104, 28);
					contentPane.add(width_TF);
					width_TF.setColumns(10);

					lblHeight = new JLabel("Height");
					lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
					lblHeight.setBounds(220, 132, 80, 30);
					contentPane.add(lblHeight);

					height_TF = new JTextField();
					lblHeight.setLabelFor(height_TF);
					height_TF.setBounds(310, 133, 104, 28);
					contentPane.add(height_TF);
					height_TF.setColumns(10);

					lblChooseShapeTo.setText("Set Rectangle Properties");
					setBounds(100, 100, 440, 252);

					break;

				case "Ellipse":
					btnOk = new JButton("OK");
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (topLeftX_TF.getText().isEmpty() || topLeftY_TF.getText().isEmpty()
									|| width_TF.getText().isEmpty() || height_TF.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please, Enter shape properties");
							} else {
								shapeNAme = "Ellipse";

								int x1 = Integer.valueOf(topLeftX_TF.getText());
								int y1 = Integer.valueOf(topLeftY_TF.getText());

								Double w = Double.valueOf(width_TF.getText());
								Double l = Double.valueOf(height_TF.getText());

								position = new Point(x1, y1);

								shapeProberties.put("width", w);
								shapeProberties.put("height", l);

								drawController.addShapeToUpdate(shapeNAme, position, borderColor, fillColor,
										shapeProberties);

								dispose();
							}

						}
					});
					btnOk.setBounds(49, 172, 155, 30);
					contentPane.add(btnOk);

					btnCancel = new JButton("Cancel");
					btnCancel.setBounds(230, 172, 155, 30);
					contentPane.add(btnCancel);

					lblTopLeftX = new JLabel("Top Left X");
					lblTopLeftX.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftX.setBounds(10, 93, 80, 30);
					contentPane.add(lblTopLeftX);

					topLeftX_TF = new JTextField();
					topLeftX_TF.setBounds(100, 92, 104, 30);
					contentPane.add(topLeftX_TF);
					topLeftX_TF.setColumns(10);

					topLeftY_TF = new JTextField();
					topLeftY_TF.setBounds(310, 93, 104, 30);
					contentPane.add(topLeftY_TF);
					topLeftY_TF.setColumns(10);

					lblTopLeftY = new JLabel("Top Left Y");
					lblTopLeftY.setHorizontalAlignment(SwingConstants.CENTER);
					lblTopLeftY.setBounds(220, 93, 80, 30);
					contentPane.add(lblTopLeftY);

					lblWidth = new JLabel("Width");
					lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
					lblWidth.setBounds(10, 134, 80, 30);
					contentPane.add(lblWidth);

					width_TF = new JTextField();
					lblWidth.setLabelFor(width_TF);
					width_TF.setBounds(100, 133, 104, 28);
					contentPane.add(width_TF);
					width_TF.setColumns(10);

					lblHeight = new JLabel("Height");
					lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
					lblHeight.setBounds(220, 132, 80, 30);
					contentPane.add(lblHeight);

					height_TF = new JTextField();
					lblHeight.setLabelFor(height_TF);
					height_TF.setBounds(310, 133, 104, 28);
					contentPane.add(height_TF);
					height_TF.setColumns(10);

					lblChooseShapeTo.setText("Set Ellipse Properties");
					setBounds(100, 100, 440, 252);

					break;
				default:
					break;
				}

				contentPane.remove(shapesComboBox);

				btnCancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

			}
		});

		contentPane.add(shapesComboBox);

	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
}
