package ca.utoronto.utm.paint;
/**
 * The squiggle class is broken and will be fixed and appropriately JavaDoc'd as part of assignment 2 part 2.
 */
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.Color;

public class PaintShapeSquiggle extends Observable implements PaintShape {

	PaintModel model;
	Squiggle squiggle;
	boolean pressed = false;
	private DrawingCommand currentCommand;
	
	public void addModel(PaintModel m) {
		this.model = m;
	}
	
	public void getMousePressed(MouseEvent e) {
		if (!pressed){
			pressed = true;
			this.squiggle = new Squiggle(this.model.getColor(), this.model.getThickness());
			currentCommand = new DrawingCommandSquiggle(this.squiggle);
			this.model.addCommand(currentCommand);
		}
	}
	
	public void getMouseDragged(MouseEvent e) {
		if (pressed){
			this.squiggle.addPoint(new Point(e.getX(), e.getY(),this.model.getColor(), this.model.getThickness()));
		}
	}
	
	public void getMouseReleased(MouseEvent e) {
		if (pressed) pressed = false;
	}
	
	public void getMouseClicked(MouseEvent e) {
		
	}
	
	public void getMouseEntered(MouseEvent e) {
		
	}
	
	public void getMouseExited(MouseEvent e) {
		
	}
	
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
}
