package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
/**
 * The class PaintModel creates an ArrayList for each of the objects of type Circle, Square, Rectangle and Point. Whenever a mouse event create an object of 
 * whatever type is chosen on the shape chooser panel, that object is stored in the ArrayList. Each time a mouse is clicked/dragged and a property of the shape (for
 * example length) changes a new circle with that new set of properties is created and added to the list. This list is iterated in PaintPanel and the each 
 * iteration of the object is painted.
 * @author Shabeeh Abbas
 *
 */
public class PaintModel extends Observable {
	private ArrayList<DrawingCommand> commands = new ArrayList<DrawingCommand>();
	private ArrayList<DrawingCommand> removedCommands = new ArrayList<DrawingCommand>();
	private String style;
	private Color color;
	private int thickness;
	public ArrayList<DrawingCommand> getCommands(){
		return this.commands;
	}
	
	public void addCommand(DrawingCommand d) {
		commands.add(d);
		removedCommands.clear();
	}
	
	public String getStyle() {
		return this.style;
	}
	
	public void setStyle(String style){
		this.style = style;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	/**
	 * Removes the command at the top of the stack (commands list).
	 */
	public void removeCommand() {
		if (this.commands.size() >= 1) {
			int temp = this.commands.size()-1;
			removedCommands.add(this.commands.remove(temp));
		}
	}
	
	public void redoCommand() {
		if (this.removedCommands.size() >= 1) {
			int temp = this.removedCommands.size()-1;
			this.commands.add(this.removedCommands.get(temp));
			this.removedCommands.remove(temp);
		}
	}
	
	public void clear() {
		commands.clear();
	}
}
