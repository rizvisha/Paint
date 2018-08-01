package ca.utoronto.utm.paint;
import java.awt.Color;
public class Circle {
	private Point centre;
	private int radius;
	private Color color;
	private String style;
	private int thickness;
	
	/**
	 * Create a circle with a center, radius, color, style (filled or outline) and thickness (the thickness of the line if the style is "outline"). 
	 * The point argument marks coordinates of the center of the circle.
	 * @param centre A point object that marks the center of the circle
	 * @param radius An integer value for the length of the radius of the circle
	 * @param color The color of the circle when painted (outline or filled).
	 * @param style The style, outline or filled, of the shape.
	 * @param thickness The line thickness of the object being created, when style is "outline".
	 */
	public Circle(Point centre, int radius, Color color, String style, int thickness){
		this.centre = centre;
		this.radius = radius;
		this.color = color;
		this.style = style;
		this.thickness = thickness;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public String getCircleStyle(){
		return this.style;
	}
	
	public void setCircleStyle(String style){
		this.style = style;
	}
	/**Takes in an integer which which will be the thickness of the squiggle
	 * @param int: this will be the new thickness of the squiggle
	 */
	public void setCircleThickness(int thickness) {
		this.thickness= thickness;
	}
	/**gets the instance's thickness
	 * 
	 * @return int which is the thickness of the instance
	 */
	public int getCircleThickness() {
		return this.thickness;
	}
}
