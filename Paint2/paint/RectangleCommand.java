package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RectangleCommand implements PaintCommand {
	private Rectangle rectangle;
	public RectangleCommand(Rectangle rectangle){
		this.rectangle = rectangle;
	}
	public void execute(Graphics2D g2d){
		g2d.setColor(rectangle.getColor());
		Point topLeft = this.rectangle.getTopLeft();
		Point dimensions = this.rectangle.getDimensions();
		if(rectangle.isFill()){
			g2d.fillRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		} else {
			g2d.drawRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		}
	}
	@Override
	public String fileDefinition() {
		Color color = rectangle.getColor();
		Point p1 = rectangle.getP1();
		Point p2 = rectangle.getP2();
		
		StringBuilder sb = new StringBuilder("");
		sb.append("Rectangle\n");
		sb.append("\t\tcolor:" + color.getRed() +"," + color.getGreen() +","+color.getBlue() + "\n");
		sb.append("\t\tfilled:"+rectangle.isFill() +"\n");
		sb.append("\t\tp1:("+p1.x+ "," +p1.y+ ")\n");
		sb.append("\t\tp2:("+p2.x+ "," +p2.y+ ")\n");
		sb.append("End Rectangle\n");
		return sb.toString();
	}
}
