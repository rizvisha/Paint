package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;

public class CircleCommand implements PaintCommand {
	private Circle circle;
	public CircleCommand(Circle circle){
		this.circle=circle;
	}
	public void execute(Graphics2D g2d){
		g2d.setColor(circle.getColor());
		int x = this.circle.getCentre().x;
		int y = this.circle.getCentre().y;
		int radius = this.circle.getRadius();
		if(circle.isFill()){
			g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		} else {
			g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);
		}
	}
	@Override
	public String fileDefinition() {
		Color color = circle.getColor();
		Point center = circle.getCentre();
		StringBuilder sb = new StringBuilder("Circle\n");
		sb.append("\t\tcolor:" + color.getRed() +"," + color.getGreen() +","+color.getBlue() + "\n");
		sb.append("\t\tfilled:"+circle.isFill() +"\n");
		sb.append("\t\tcenter:("+center.x+ "," +center.y+ ")\n");
		sb.append("\t\tradius:"+circle.getRadius()+"\n");
		sb.append("End Circle\n");
		return sb.toString();
	}
}
