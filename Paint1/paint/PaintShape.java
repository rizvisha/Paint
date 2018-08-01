package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
/**
 * An interface that is implemented by each of the PaintShape*ShapeName* classes. The classes that implement this interface take as argument the mouse events 
 * and the current instance of PaintModel from the PaintPanel. Each class implementing this interface performs the appropriate function for each mouse event.
 * @author Shabeeh Abbas
 *
 */
public interface PaintShape {
	
	public void addModel(PaintModel m);
	
	public void getMousePressed(MouseEvent e);
	
	public void getMouseDragged(MouseEvent e);
	
	public void getMouseReleased(MouseEvent e);
	
	public void getMouseClicked(MouseEvent e);
	
	public void getMouseEntered(MouseEvent e);
	
	public void getMouseExited(MouseEvent e);
	
	public void getMouseMoved(MouseEvent e);

	/*public void setColor(Color color);
	
	public void setThickness(int i);*/
}









//public void construct(MouseEvent e) {
//	
//	this.getMousePressed(e);
//	
//	this.getMouseDragged(e);
//	
//	this.getMouseReleased(e);
//	
//	this.getMouseClicked(e);
//	
//	this.getMouseEntered(e);
//	
//	this.getMouseExited(e);
//	
//	this.getMouseMoved(e);
//	
//}