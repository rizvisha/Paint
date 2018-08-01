package ca.utoronto.utm.paint;
import java.awt.event.*;

public class ThicknessMenuActionListener implements ActionListener {
	public int thickness;
	private ShapeChooserPanel p;
	private PaintPanel paintPanel;

	public ThicknessMenuActionListener(ShapeChooserPanel p, PaintPanel pp) {
		this.p = p;
		this.paintPanel = pp;
	}
	/**Controls the thickness buttons as well as the ComboBox when the user interacts with them
	 * 
	 */
			
	@Override
	public void actionPerformed(ActionEvent e) {
		// Update the JBox to show current thickness when changed using the thickness buttons, as long as 1 =< thickness <= 30.
		if(e.getActionCommand().equals("+thickness")) {
			int increment = this.paintPanel.getThickness()+1;
			this.paintPanel.setThickness(increment);
			System.out.println(this.paintPanel.getThickness());
			this.p.thicknessBox.setSelectedIndex(increment-1);

			
			
		} else if(e.getActionCommand().equals("-thickness")) {
			if (this.paintPanel.getThickness() > 1) {
				int decrement = this.paintPanel.getThickness()-1;
				this.paintPanel.setThickness(decrement);
				System.out.println(this.paintPanel.getThickness());
				this.p.thicknessBox.setSelectedIndex(decrement-1);
			}
			
		} else {
			this.paintPanel.setThickness(this.p.thicknessBox.getSelectedIndex()+1); 
		}
	}

}
