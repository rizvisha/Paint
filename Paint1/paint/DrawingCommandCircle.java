package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandCircle implements DrawingCommand {
	
	Circle circle;
	
	DrawingCommandCircle (Circle circle){
		this.circle = circle;
	}

	@Override
	public void execute(Graphics2D g2d) {
		// Draw Circle
		int x = circle.getCentre().getX();
		int y = circle.getCentre().getY();
		int radius = circle.getRadius();

		//change colour graphics will use before each draw
		g2d.setColor(circle.getColor());

		//NOTE: arg3 and arg4 are WIDTH and HEIGHT of the oval/circle.BUT giving the circles radius as an argument to drawOval will yield 
		//a circle of DIAMETER equal to the radius. We can just multiply by 2 in the argument of drawOval.
		if (circle.getCircleStyle() == "Filled") { 
			g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		} else {
			g2d.setStroke(new BasicStroke(circle.getCircleThickness()));
			g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);

		}
	}
}
