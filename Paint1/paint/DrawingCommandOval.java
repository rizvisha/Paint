package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandOval extends PaintShapeOval implements DrawingCommand {
	
	Oval oval;
	DrawingCommandOval (Oval oval){
		this.oval = oval;
	}
	
	@Override
	public void execute(Graphics2D g2d) {
		// draw oval
		int x = oval.getCentre().getX();
		int y = oval.getCentre().getY();
		int xradius = oval.getXRadius();
		int yradius = oval.getYRadius();

		//change colour graphics will use before each draw
		g2d.setColor(oval.getColor());

		//NOTE: arg3 and arg4 are WIDTH and HEIGHT of the oval/circle.BUT giving the circles radius as an argument to drawOval will yield 
		//a circle of DIAMETER equal to the radius. We can just multiply by 2 in the argument of drawOval.
		if (oval.getOvalStyle() == "Filled") { 
			g2d.fillOval(x-xradius, y-yradius, 2*xradius, 2*yradius);
		} else {
			g2d.setStroke(new BasicStroke(oval.getOvalThickness()));
			g2d.drawOval(x-xradius, y-yradius, 2*xradius, 2*yradius);
		}
	}
}
