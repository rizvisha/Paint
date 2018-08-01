package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandSquiggle implements DrawingCommand {
	
	Squiggle squiggle;
	
	DrawingCommandSquiggle(Squiggle squiggle){
		this.squiggle = squiggle;
	}

	@Override
	public void execute(Graphics2D g2d) {
		// Draw Lines
		ArrayList<Point> points = squiggle.getPoints();
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);

			//change colour graphics will use before each draw
			g2d.setColor(p1.getColor());
			g2d.setStroke(new BasicStroke(p1.getSquiggleThickness()));
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
}
