package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class SquiggleCommand implements PaintCommand {
	private Squiggle squiggle;
	public SquiggleCommand(Squiggle squiggle){
		this.squiggle = squiggle;
	}
	public void execute(Graphics2D g2d){
		ArrayList<Point> points = this.squiggle.getPoints();
		g2d.setColor(squiggle.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	@Override
	public String fileDefinition() {
		Color color = squiggle.getColor();
		ArrayList<Point> points = squiggle.getPoints();
		StringBuilder sb = new StringBuilder("Squiggle\n");
		sb.append("\t\tcolor:" + color.getRed() +"," + color.getGreen() +","+color.getBlue() + "\n");
		sb.append("\t\tfilled:"+squiggle.isFill() +"\n");
		sb.append("\t\tpoints\n");
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			Point point = (Point) iterator.next();
			sb.append("\t\t\tpoint:("+point.x+ "," +point.y+ ")\n");
		}
		sb.append("\t\tend points\n");
		sb.append("End Squiggle\n");
		return sb.toString();
	}
}
