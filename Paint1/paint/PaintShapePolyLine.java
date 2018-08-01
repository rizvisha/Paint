package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.Color;

public class PaintShapePolyLine extends Observable implements PaintShape {
 
	PolyLine line;
	PaintModel model;
	boolean pressed = false;
	private DrawingCommand currentCommand;
	
	public void addModel(PaintModel m) {
		this.model = m;
	}
	
	public void getMousePressed(MouseEvent e) {
		if (!pressed){
			pressed = true;
			if (this.line == null) {
				this.line = new PolyLine(this.model.getColor(), this.model.getStyle(), this.model.getThickness());
				currentCommand = new DrawingCommandPolyLine(this.line);
				this.model.addCommand(currentCommand);
			}
			
			Point p = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
//			if (!line.getPoints().isEmpty()) {
//				this.model.removeLastPolyLine();
//			}
			this.line.addPoint(p);
			
			
		}
	}
	
	public void getMouseDragged(MouseEvent e) {
		if (pressed){
			Point p = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
			//this.model.removeLastPolyLine();
			if (this.line.getPoints().size() == 1){
				this.line.addPoint(p);
			} else {
				this.line.removeLastPoint();
				this.line.addPoint(p);
			}
			//this.model.addLine(this.line);
		}
	}
	
	public void getMouseReleased(MouseEvent e) {
		if (pressed){
			pressed = false;
			Point p = new Point(e.getX(), e.getY(), this.model.getColor(), this.model.getThickness());
			this.line.removeLastPoint();
			//this.model.removeLastPolyLine();
			if (this.line.getPoints().size()>1 && isNearInitialPoint(p)){
				this.line.addPoint(line.getPoints().get(0));
				//this.model.addLine(line);
				this.line = null;
			} else if (this.line.getPoints().size()>1 && isNearLastPoint(p)){
				//this.model.addLine(line);
				this.line = null;
			} else {
				this.line.addPoint(p);
				//this.model.addLine(line);
			}
		}
	}
	
	public void getMouseClicked(MouseEvent e) {
		
	}
	
	public void getMouseEntered(MouseEvent e) {
		
	}
	
	public void getMouseExited(MouseEvent e) {
		
	}
	
	public void getMouseMoved(MouseEvent e) {
		
	}
	/*public void setColor(Color color){
		this.color = color;
	}
	
	*//**Takes in an integer which which will be the thickness of the shape
	 * @param int: this will be the new thickness of the shape
	 *//*
	@Override
	public void setThickness(int i) {
		this.thickness = i;
		
	}*/
	
	private boolean isNearInitialPoint(Point p){
		Point p0 = line.getPoints().get(0);
		if (p.getX() > p0.getX()+10 || p.getX() < p0.getX()-10 || p.getY() > p0.getY()+10 || p.getY() < p0.getY()-10){
			return false;
		}
		return true;
	}
	
	private boolean isNearLastPoint(Point p){
		Point p0 = line.getPoints().get(line.getPoints().size()-1);
		if (p.getX() > p0.getX()+10 || p.getX() < p0.getX()-10 || p.getY() > p0.getY()+10 || p.getY() < p0.getY()-10){
			return false;
		}
		return true;
	}
	
	
}
