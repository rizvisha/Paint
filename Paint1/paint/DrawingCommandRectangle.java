package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandRectangle implements DrawingCommand {
	
	Rectangle rectangle;
	
	DrawingCommandRectangle (Rectangle rectangle){
		this.rectangle = rectangle;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		// Draw Rectangle
		if (rectangle.getDynamicPoint()!=null) { // so clicking doesn't cause exception
			
			// arg0 and arg1 mark the x and y coordinates of the top left corner of the rectangle. Set that to be the minimum of the current point or the
			// point of initial press. NOTE: The top left corner is the origin, so cannot have negative arguments.
			int x = rectangle.getDynamicPoint().getX();
			int y = rectangle.getDynamicPoint().getY();
			
			int w = rectangle.getWidth();
			int h = rectangle.getHeight();

			//change colour graphics will use before each draw
			g2d.setColor(rectangle.getColor());

			// x, y are the coordinates of the top left corner of the rectangle. w, h are the width and heights.
			if (rectangle.getRectangleStyle() == "Filled") {
				g2d.fillRect(x, y, w, h);
			} else {
				g2d.setStroke(new BasicStroke(rectangle.getRectangleThickness()));
				g2d.drawRect(x, y, w, h);
			}
		}
	}
}
