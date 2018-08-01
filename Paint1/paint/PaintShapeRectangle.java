package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.Color;

/**
 * The class takes the current mouse event as an argument for each of the methods from PaintPanel and performs the appropriate action. For example getMousePressed
 * is called by the mousePressed method in PaintPanel (which implements MouseListener and MouseMotionListener). Depending on the mode of the PaintPanel the 
 * getMousePressed method, for instance, will be called in the appropriate PaintShape*ShapeName* class.
 * @author Shabeeh Abbas
 *
 */

public class PaintShapeRectangle extends Observable implements PaintShape {

	Rectangle rectangle;
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
	/**
	 * When mouse is pressed, create a rectangle with that point as the top left corner and color and thickness of the model. The side length is 0 as it 
	 * is just a point right now.
	 */
	@Override
	public void getMousePressed(MouseEvent e) {
		if (!pressed){
			pressed = true;
			// A single point has no lengths
			int length = 0;
			
			// A single point on the screen where the mouse is pressed stored as top left corner of rectangle
			Point rectangle_top_left_corner = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
			this.rectangle = new Rectangle(rectangle_top_left_corner, length, length, this.model.getColor(), this.model.getStyle(), this.model.getThickness());
			
			currentCommand = new DrawingCommandRectangle(this.rectangle);
			 this.model.addCommand(currentCommand);
		}
	}
	
	/**
	 * When the mouse is dragged the current point is compared with the point where the mouse was pressed so that the top left corner of the rectangle can be 
	 * determined. The minimum of each coordinate is the coordinate for the new top left corner. Each update influences the comparison of the two points (where 
	 * the mouse was pressed and the current point). If the current point has lower values for x and y coordinates (to the left and up of the initial point)
	 * then the current point becomes the top left corner. The initial point becomes the diagonally opposite corner (bottom left).
	 */
	@Override
	public void getMouseDragged(MouseEvent e) {
		if (pressed){
			dynamicSides(e);
		}
	}
	
	/**
	 * When the mouse is released the current rectangle is added to the list and the rectangle object of this is set to null, as the next mouse press and drag will
	 * instantiate a new rectangle. If the rectangle was null, that means the mouse was not dragged and nothing is added to the ArrayList, square, in PaintModel. 
	 */
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
	@Override
	public void setThickness(int i) {
		this.thickness = i;
	}*/
	
	private void dynamicSides(MouseEvent e){
		Point rectangle_top_left_corner = this.rectangle.getCorner();
		Point currentPoint = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
		
		// height and width cannot be negative as the top left corner is the origin.
		int width = Math.abs(e.getX() - rectangle_top_left_corner.getX());
		int height = Math.abs(e.getY() - rectangle_top_left_corner.getY());

		this.rectangle.setWidth(width);
		this.rectangle.setHeight(height);
		
		int x = Math.min(rectangle.getCorner().getX(), currentPoint.getX());
		int y = Math.min(rectangle.getCorner().getY(), currentPoint.getY());
		
		this.rectangle.setDynamicPoint(new Point(x, y, this.model.getColor(), this.model.getThickness()));
	}
}
