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

public class PaintShapeSquare extends Observable implements PaintShape {

	Square square;
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
	 * When mouse is pressed, create a square with that point as the top left corner and color and thickness of the model. The side length is 0 as it 
	 * is just a point right now.
	 */
	@Override
	public void getMousePressed(MouseEvent e) {
		if (!pressed){
			pressed = true;
			// A single point has no length
			int sidelength = 0;
			
			// A single point on the screen where the mouse is pressed stored as top left corner of square
			Point top_left_corner = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
			this.square = new Square(top_left_corner, sidelength, this.model.getColor(), this.model.getStyle(), this.model.getThickness());
			
			currentCommand = new DrawingCommandSquare(this.square);
			this.model.addCommand(currentCommand);
		}
	}
	
	/**
	 * When the mouse is dragged, dynamicSquareCorner is called to keep track of the current point and compare it with the point where the mouse was pressed so 
	 * that the top left corner of the square can be determined. Each update influences the comparison of the two points (top left corner initially set as the point 
	 * where the mouse was pressed and the current point). If the current point has lower values for x and y coordinates (to the left and up of the initial point)
	 * then the current point becomes the top left corner. The initial point becomes the diagonally opposite corner (bottom left).
	 * The implementation is more complicated than that of rectangle as simply the minimum of the two points cannot be set as the top left corner, because
	 * the length of each side is to be set equal.
	 */
	@Override
	public void getMouseDragged(MouseEvent e) {
		if (pressed){
			dynamicSide(e);
		}
	}
	
	/**
	 * When the mouse is released the current square is added to the list and the square object of this is set to null, as the next mouse press and drag will
	 * instantiate a new square. If the square was null, that means the mouse was not dragged and nothing is added to the ArrayList, square, in PaintModel. 
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
	*/
	private void dynamicSide(MouseEvent e){
		int xlength = square.getOrigin().getX()-e.getX();
		int ylength = square.getOrigin().getY()-e.getY();
		int side = Math.max(Math.abs(xlength), Math.abs(ylength));
		square.setSide(side);
		Point newDynamicPoint = new Point(square.getOrigin().getX(), square.getOrigin().getY(),this.model.getColor(), this.model.getThickness());
		if (xlength > 0){
			newDynamicPoint.setX(square.getOrigin().getX()-side);
		}
		if (ylength > 0){
			newDynamicPoint.setY(square.getOrigin().getY()-side);
		}
		square.setDynamicPoint(newDynamicPoint);
	}
	/**Takes in an integer which which will be the thickness of the shape
	 * @param int: this will be the new thickness of the shape
	 */
	/*@Override
	public void setThickness(int i) {
		this.thickness = i;
	}*/
}
