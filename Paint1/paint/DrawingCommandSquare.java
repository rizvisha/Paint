package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandSquare implements DrawingCommand {
	
	Square square;
	
	DrawingCommandSquare(Square square) {
		this.square = square;
	}

	@Override
	public void execute(Graphics2D g2d) {
		// Draw Square
		if (square.getDynamicPoint()!=null) { // so clicking doesn't cause exception
			int side = square.getSide();

			// If the current position coordinates are smaller, that is, mouse dragged towards top/left adjust the top left corner of square so that the initial
			// mouse pressed location becomes the bottom left corner. 
			// NOTE: The implementation is significantly different than a rectangle because the sides need to be of equal length and picking minimum of 
			// current or initial x and y does not work.
			int x = square.getDynamicPoint().getX();
			int y = square.getDynamicPoint().getY();

			//change colour graphics will use before each draw
			g2d.setColor(square.getColor());

			// x, y are the coordinates of the top left corner of the square. w, h are the width and heights.
			if (square.getSquareStyle() == "Filled") {
				g2d.fillRect(x, y, side, side);
			} else {	
				g2d.setStroke(new BasicStroke(square.getSquareThickness()));
				g2d.drawRect(x, y, side, side);
			}
		}
	}
}
