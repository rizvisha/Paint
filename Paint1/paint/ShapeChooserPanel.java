package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * This class implements a constructor that implements JPanel. This JPanel would be added to the main JFrame and will have buttons that would enable the user
 * to draw from the different available shapes, with different available colours, in different styles (filled or outline), with lines of different thicknesses.
 * @author Shabeeh Abbas
 *
 */
class ShapeChooserPanel extends JPanel {
	private View view; // So we can talk to our parent or other components of the view
	public JComboBox<Integer> thicknessBox = new JComboBox<Integer>();	
	public JLabel style = new JLabel("Outline");
	public JLabel shape = new JLabel("Shape: None");
	
	/**
	 * This is the constructor for the afore mentioned JPanel. It takes the main instance view (the JFrame) as argument which allows access to the current 
	 * instance of PaintPanel. Accessing the PaintPanel is important to the action listeners that will be hooked to the buttons to make changes to the PaintPanel.
	 * JButtons are created and modified so that they display icons/images instead of text, and are hooked to appropriate action listeners. This is done by making
	 * two appropriately ordered arrays, one for ImageIcons (created by using the stored images in the "images" folder in the repository) and one for button 
	 * names, so that the ImageIcon at index i corresponds to the button name at index i. The static method makePanel is utilized to convert the ImageIcons 
	 * to the desired size and attach them to the appropriate buttons. These buttons are then added to an ArrayList which is returned. Iterating over this 
	 * ArrayList the buttons can be added to this ShapeChooserPanel and hooked up with the appropriate action listeners.
	 * 
	 * @param view The current (and only) instance of view, which is the main JFrame.
	 */
	public ShapeChooserPanel(View view) {	
		this.view = view;
		ThicknessMenuActionListener TMAL = new ThicknessMenuActionListener(this, this.getView().getPaintPanel());
		ActionListener shapeChooserlActionListener = new ShapeChooserActionListener(this);
		
		// The size of the panel set to fit all buttons from top down.
		this.setLayout(new GridLayout(13,1));
		
		// ImageIcons for each button at the same index as the button labels.
		ImageIcon[] paintIcons = {new ImageIcon("images/circle.png"), new ImageIcon("images/oval.png"), new ImageIcon("images/rectangle.png"), new ImageIcon("images/square.png"), 
				new ImageIcon("images/squiggle.png"), new ImageIcon("images/polyline.png") };
		String[] buttonLabels = { "Circle", "Oval", "Rectangle", "Square", "Squiggle", "PolyLine"};
		ArrayList<JButton> shapePanel = makePanel(paintIcons, buttonLabels, 50, 50);
		
		// Add the returned lists to the panel.
		for (JButton b : shapePanel) {
			b.addActionListener(shapeChooserlActionListener);
			b.setPreferredSize(new Dimension(70,70));
			this.add(b);
		}
		
		// Make two more lists for buttons with icons of different sizes.
		ImageIcon[] styleIcons = {new ImageIcon("images/outline.png"), new ImageIcon("images/filled.png")};
		String[] styleButtonLabels = {"Outline", "Filled"};
		ArrayList<JButton> stylePanel = makePanel(styleIcons, styleButtonLabels, 20, 20);
		
		// Add the returned lists to the panel.
		for (JButton b : stylePanel) {
			b.addActionListener(shapeChooserlActionListener);
			this.add(b);
		}
		stylePanel.get(0).setEnabled(false); // Disable the outline button as it is the default style by default.
		
		// Add buttons to manually increment/decrement the thickness of lines. Last minute addition. Not necessarily needed, but did not know where the graphics
		// function setStroke is bounded above. Bounded below by 1.
		ImageIcon[] thicknessIcons = {new ImageIcon("images/+thickness.png"), new ImageIcon("images/-thickness.png")};
		String[] thicknessLabels	= {"+thickness", "-thickness"};
		ArrayList<JButton> subPanel2 = makePanel(thicknessIcons, thicknessLabels, 50, 50);
		
		for (JButton b : subPanel2) {
			b.addActionListener(TMAL);
			this.add(b);
		}
		
		//The JComboBox allows to select from the first 30 levels of line thickness increments.
		thicknessBox.setSize(new Dimension(70,70));
		thicknessBox.addActionListener(TMAL);
		this.add(thicknessBox);
		for(int i=1;i<31;i++) {
			thicknessBox.addItem(i);
		}
		//  Add the JLabels
		this.add(style);
		this.add(shape);
	}
	
	public View getView() {
		return this.view;
	}
	
	/**
	 * The takes as parameters array of ImageIcons made using the address to the images stored in a folder named "images" in the repository, an array of string
	 * that holds the labels of the JButtons at indices corresponding to the indices of their ImageIcon in the ImageIcon array, and the desired width and height 
	 * of the icons. It adjusts the icons to the desired size, creates a new JButton that icon is for, and attaches the icon to the button. Text is undesired 
	 * for a JButton so no text is set, however, an action command is set so that the action listener knows which button was pressed. The button is then added 
	 * to an ArrayList. This is repeated for all elements of both lists (WHICH ARE OF EQUAL SIZE) and returns the ArrayList when it is done.
	 * 
	 * @param icons The array of icons for the JButtons.
	 * @param buttonLabels The array of labels for the JButtons.
	 * @param width The desired width of the icon that is to be added to the JButton.
	 * @param height The desired width of the icon that is to be added to the JButton.
	 * @return An ArrayList of buttons with labels set as action command and appropriate icon attached.
	 */
	public static ArrayList<JButton> makePanel(ImageIcon[] icons, String[] buttonLabels, int width, int height) {
		ArrayList<JButton> list = new ArrayList<JButton>();
		for (int i = 0; i < buttonLabels.length; i++) {

			JButton button = new JButton();
			ImageIcon icon;
			icon = icons[i]; //Take the ImageIcon at index i
			
			// Adjust is to the desired settings by getting and modifying the image object of the ImageIcon and making a new ImageIcon with this modified image.
			Image image = icon.getImage();
			Image new_image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH );
			icon = new ImageIcon (new_image);		
			
			button.setPreferredSize(new Dimension(70,70));
			button = new JButton(icon);
			button.setOpaque(true); // Ensures the button is completely painted with color
			button.setBackground(Color.lightGray); // Set button color
			button.setBorder(null); // Disable borders to the buttons
			button.setFocusPainted(false); // So borders don't show around any button
			
			// So getActionCommand in the action listener works but we don't see the text on the button as needed.
			button.setActionCommand(buttonLabels[i]);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			list.add(button);
		}
		return list;
	}
}

















//Rough  Code ******

//this.view.getPaintPanel().setMode(e.getActionCommand());

//for (String label : buttonLabels) {
//	
//	JButton button = new JButton();
//	ImageIcon icon;
//	
//	if (label.equals("Circle")) {
//		
//		icon = new ImageIcon("images/circle.png");
//		
//		
//	} else if (label.equals("Rectangle")) {
//		
//		icon = new ImageIcon("images/rectangle.png");
//
//		
//	} else if (label.equals("Square")) {
//		
//		icon = new ImageIcon("images/square.png");
//
//		
//	} else if (label.equals("Squiggle")) {
//		
//		icon = new ImageIcon("images/squiggle.png");
//
//	} else  {
//		
//		icon = new ImageIcon("images/polyline.png");
//	}