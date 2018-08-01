package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** This Panel creates and takes in a color choosen by the boxes created. 
 * 
 */

class ColorChooserPanel extends JPanel implements ActionListener {
	private View view;
	private final Color[] colors = {Color.WHITE, Color.BLACK, Color.BLUE, 
			Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.GREEN, 
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
	public ColorChooserPanel(View view){
		this.view = view;
		this.setLayout(new FlowLayout(FlowLayout.LEADING, 1,1));
		for (Color c : colors){
			JButton b = new JButton();
			b.setBackground(c);
			b.setPreferredSize(new Dimension(20,20));
			b.setOpaque(true);
			b.addActionListener(this);
			b.setFocusPainted(true);
			this.add(b);
		}
		JButton b = new JButton("Custom");
		b.setActionCommand("JColorChooser");
		b.addActionListener(this);
		this.add(b);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		PaintPanel p = view.getPaintPanel();
		if (e.getActionCommand().equals("JColorChooser")){
			p.setColor(JColorChooser.showDialog(this, "Color Chooser", p.getColor()));
			System.out.println(p.getColor());
			System.out.println(e.getActionCommand());
		}
		else {
			JButton b = (JButton)e.getSource();
			p.setColor(b.getBackground());
			System.out.println(b.getBackground());
			System.out.println(e.getActionCommand());
		}
	}

}
