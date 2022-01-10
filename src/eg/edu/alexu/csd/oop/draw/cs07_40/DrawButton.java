package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



@SuppressWarnings("serial")
public class DrawButton extends JButton{

	DrawController drawController;
	DrawView drawView;
	
	public DrawButton(DrawController drawController,DrawView drawView,String name) {
		super();
		this.drawController = drawController;
		this.drawView = drawView;
		setText(name);
		setActionCommand(name);

		
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				drawController.setBtnSource(e.getActionCommand());
				drawController.prepareCanvas();
			}
		});
	}
}
