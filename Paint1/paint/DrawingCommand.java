package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
/**
 * The interface that will be used to implement the Command design pattern and will be implemented for each shape.
 * @author Shabeeh Abbas
 *
 */
public interface DrawingCommand {
	
	public abstract void execute(Graphics2D g2d);
}
