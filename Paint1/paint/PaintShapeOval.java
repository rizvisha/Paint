package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.Color;

/**
 * The class takes the current mouse event as an argument for each of the methods from PaintPanel and performs the appropriate action. For example getMousePressed
 * is called by the mousePressed method in PaintPanel (which implements MouseListener and MouseMotionListener). Depending on the mode of the PaintPanel the 
 * getMousePressed method, for instance, will be called in the appropriate PaintShape*ShapeName* class.
 *
 */
public class PaintShapeOval extends Observable implements PaintShape {

	Oval oval;
	PaintModel model;
	boolean pressed = false;
	private DrawingCommand currentCommand;
	
	/**
	 * Connects the current instance of PaintModel to this class so that the shape objects can be added to the appropriate ArrayList.
	 * @param The current instance of PaintModel passed to it in PaintPanel (which has the correct instance PaintModel in its constructor).
	 */
	@Override
	public void addModel(PaintModel m) {
		this.model = m;
	}
	
	@Override
	public void getMousePressed(MouseEvent e) {
		if (!pressed){
			pressed = true;
			//ovals = new ArrayList<Oval>();
			// A single point has radius 0
			int xradius = 0;
			int yradius = 0;
	
			// A single point on the screen where the mouse is pressed stored as center of oval.
			Point centre = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
			this.oval = new Oval(centre, xradius, yradius, this.model.getColor(), this.model.getStyle(), this.model.getThickness());
			
			currentCommand = new DrawingCommandOval(this.oval);
			this.model.addCommand(currentCommand);
		}
	}
	
	@Override
	public void getMouseDragged(MouseEvent e) {
		if (pressed){
			dynamicRadius(e); // Using the dynamicRadius function defined below to get the radius of the current instance.
		}
	}
	
	@Override
	public void getMouseReleased(MouseEvent e) {
		if (pressed) pressed = false;
	}
	
	@Override
	public void getMouseClicked(MouseEvent e) {
	}
	
	@Override
	public void getMouseEntered(MouseEvent e) {

	}
	
	@Override
	public void getMouseExited(MouseEvent e) {

	}
	
	@Override
	public void getMouseMoved(MouseEvent e) {

	}
	/*@Override
	public void setColor(Color color){
		this.color = color;
	}
	*//**Takes in an integer which which will be the thickness of the shape
	 * @param int: this will be the new thickness of the shape
	 *//*
	public void setThickness(int i){
		this.thickness = i;
	}*/
	
	/** Calculates the radius of the circle when the mouse click is released by using the Pythagoras' theorem. The distance traveled along the x axis and y axis
	 * are determined. The radius would then equal sqrt[(distance_in_x)^2 + (distance_in_y)^2] in the right angled triangle. 
	 * @return The radius of the circle.
	 */

	public void dynamicRadius(MouseEvent e) {

		int run = this.oval.getCentre().getX() - e.getX();
		int rise = this.oval.getCentre().getY() - e.getY();
		
		//int radius_squared = run*run  + rise*rise;
		//int radius = (int) Math.sqrt(radius_squared);
		this.oval.setXRadius(Math.abs(run));
		this.oval.setYRadius(Math.abs(rise));
	}
}
