package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	//private View view; // So we can talk to our parent or other components of the view
	private PaintShape mode; // The shape to be drawn
	private PaintShapeFactory factory;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
//		this.thickness = 1;
//		this.style = "outline";
		this.mode = null;
//		this.color = Color.black;

		this.model = model;
		this.model.addObserver(this);
		//this.view = view;
		this.factory = new PaintShapeFactory();
		this.setMode("Circle"); //start with default of circle shape
		this.setStyle("outline"); //start with default of outlined shapes
		
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(Color.black);
        g2d.drawString ("i="+i, 50, 75);
		i=i+1;
		
		for (DrawingCommand c : this.model.getCommands()) { // For all the DrawingComand*Shape* each of their execute method is called which draws each shape.
			c.execute(g2d);
		}
		g2d.dispose();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	*Setter for color variable
	*/
	public void setColor(Color color){
		this.model.setColor(color);
	}
	
	/**gets the colour attribute of the current drawing
	 * 
	 * @return Color color
	 */
	public Color getColor(){
		return this.model.getColor();
	}
	
	/**
	*Setter for thickness variable
	*/
	public void setThickness(int i) {
		this.model.setThickness(i);
	}
	
	/**gets the thickness of the panel attribute
	 * 
	 * @return int this.thickness
	 */
	public int getThickness() {
		return this.model.getThickness();
	}
	/**
	 * This method sets the current shape to be drawn, called by ShapeChooserActionListener
	 * which references the pressed button's action command string
	 * @param type	a string that represents which shape should be drawn next
	 */
	public void setMode(String type){
		this.mode = factory.makePaintShape(type);
		mode.addModel(this.model);
	}
	
	public PaintShape getMode() {
		return this.mode;
	}
	/**sets filled or outlines for the shapes
	 * 
	 * @param s: which is either "outline" or "filled"
	 */
	public void setStyle(String s){
		this.model.setStyle(s);
	}
	
	public PaintModel getModel() {
		return this.model;
	}

	/**
	 * Calls getMousePressed from the PaintShape class that the mode is currently assigned to.
	 */
	public void mousePressed(MouseEvent e) {
		if(this.mode!=null) {
			/*this.mode.setColor(color);
			this.mode.setThickness(thickness);*/
			this.mode.getMousePressed(e);
		}
		this.repaint();
	}
	
	/**
	 * Calls getMouseDragged from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseDragged(MouseEvent e) {
		if(this.mode!=null) {
			/*this.mode.setColor(color);
			this.mode.setThickness(thickness);*/
			this.mode.getMouseDragged(e);
		}
		this.repaint();
	}
	
	/**
	 * Calls getMouseReleased from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseReleased(MouseEvent e) {
		if(this.mode!=null) {
			/*this.mode.setColor(color);
			this.mode.setThickness(thickness);*/
			this.mode.getMouseReleased(e);
		}
		this.repaint();
	}
	
	/**
	 * Calls getMouseClicked from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseClicked(MouseEvent e) {
		if(this.mode!=null) {
			/*this.mode.setColor(color);
			this.mode.setThickness(thickness);*/
			this.mode.getMouseClicked(e);
		}
	}
	
	/**
	 * Calls getMouseEntered from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseEntered(MouseEvent e) {
		if(this.mode!=null) {
			this.mode.getMouseEntered(e);
		}
	}
	
	/**
	 * Calls getMouseExited from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseExited(MouseEvent e) {
		if(this.mode!=null) {
			this.mode.getMouseExited(e);
		}
	}
	
	/**
	 * Calls getMouseMoved from the PaintShape class that the mode is currently assigned to.
	 */
	public void mouseMoved(MouseEvent e) {
		if(this.mode!=null) {
			this.mode.getMouseMoved(e);
		}
	}
}













//ROUGH  CODE  ***********
//if (this.mode!=null) {
//if (s=="Circle") {
//	this.mode = new PaintShapeCircle();
//}
//if (s=="Rectangle") {
//	this.mode = new PaintShapeRectangle();
//}
//if (s=="Square") {
//	this.mode = new PaintShapeSquare();
//}
//if (s=="Squiggle") {
//	this.mode = new PaintShapeSquiggle();
//}
//if (s=="PolyLine") {
//	this.mode = new PaintShapePolyLine();
//}