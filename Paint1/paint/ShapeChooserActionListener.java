package ca.utoronto.utm.paint;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ShapeChooserActionListener implements ActionListener {

	private ShapeChooserPanel p;

	ShapeChooserActionListener(ShapeChooserPanel panel) {
		this.p = panel; // Hook up the ShapeChooserPanel
	}

	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton outline = new JButton();
	JButton filled = new JButton();
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (Component b : p.getComponents()) {
			 if (b instanceof JButton) {
				 if (((JButton) b).getActionCommand().equals("Outline")) {
					 outline = (JButton) b;
				 }
				 if (((JButton) b).getActionCommand().equals("Filled")) {
					 filled = (JButton) b;
				 }
			 }
		}
		
		PaintPanel paintPanel = p.getView().getPaintPanel();
		
		// Set the mode to the appropriate PaintShape class. Disable the button pressed. Enable the button last pressed, stored as button2. Make the current
		// selection button2 so it is the "last pressed button" in the next iteration.

		if (e.getActionCommand().equals("Circle")) {
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;
		}
		if (e.getActionCommand().equals("Rectangle")) {
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;
		}
		if (e.getActionCommand().equals("Square")) {
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;;
		}
		if (e.getActionCommand().equals("Squiggle")) {
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;
		}
		if (e.getActionCommand().equals("PolyLine")) {
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;
		}
		if (e.getActionCommand()=="Oval"){
			p.shape.setText(e.getActionCommand());
			paintPanel.setMode(e.getActionCommand());
			button1.setEnabled(false);
			button2.setEnabled(true);
			button2 = button1;
		}
		
		if (e.getActionCommand().equals("Outline")) {
			p.style.setText(e.getActionCommand());
			paintPanel.setStyle(e.getActionCommand());
			outline.setEnabled(false); // outline style selected
			filled.setEnabled(true); // filled style enabled
			
		}
		if (e.getActionCommand().equals("Filled")) {
			p.style.setText(e.getActionCommand());
			paintPanel.setStyle(e.getActionCommand());;
			filled.setEnabled(false); // filled style selected
			outline.setEnabled(true); // outline style disabled
		}
	}		
}

