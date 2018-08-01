package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawingCommandPolyLine implements DrawingCommand {
	
	PolyLine polyline;
	
	DrawingCommandPolyLine(PolyLine line) {
		this.polyline = line;
	}

	@Override
	public void execute(Graphics2D g2d) {
		// Draw PolyLine
		g2d.setColor(polyline.getColor());
		g2d.setStroke(new BasicStroke(polyline.getPolyLineThickness()));
		ArrayList<Point> linepoints = polyline.getPoints();
		if (polyline.getPolyLineStyle() == "Filled"){
			g2d.fillPolygon(polyline.getXPoints(), polyline.getYPoints(), polyline.getPoints().size());
			
		} else {
			for (int i = 0; i < linepoints.size()-1; i++){
				Point p0 = linepoints.get(i);
				Point pnext = linepoints.get(i+1);
				g2d.drawLine(p0.getX(), p0.getY(), pnext.getX(), pnext.getY());
			}
		}
	}
}
