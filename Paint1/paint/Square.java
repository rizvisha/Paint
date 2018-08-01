package ca.utoronto.utm.paint;
import java.awt.Color;
public class Square {
	private Point origin;
	private int side;
	private Point dynamicPoint;
	private Color color;
	private String style;
	private int thickness;

	/**
	 * Create a square with a side length equal to s, a color, style (filled or outline) and thickness (the thickness of the line if the style is "outline"). 
	 * The point argument marks coordinates of the top left corner of the square.
	 * @param corner The top left corner for the square.
	 * @param s An integer value for the length of one side of the square.
	 * @param color The color of the square when painted. (outline or filled).
	 * @param style The style, outline or filled, of the shape.
	 * @param thickness The line thickness of the object being created, when style is "outline".
	 */
	public Square(Point origin, int s, Color color, String style, int thickness) {

		this.origin = origin;
		this.side = s;
		this.dynamicPoint = null;
		this.color = color;
		this.style = style;
		this.thickness = thickness;
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	
	public Point getDynamicPoint() {
		return this.dynamicPoint;
	}
	
	public int getSide() {
		return this.side;
	}
	
	public void setOrigin(Point p) {
		this.origin = p;
	}
	
	public  void setDynamicPoint(Point p) {
		this.dynamicPoint = p;
	}

	public void setSide(int s) {
		this.side = s;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public String getSquareStyle(){
		return this.style;
	}
	
	public void setSquareStyle(String style){
		this.style = style;
	}
	/**Takes in an integer which which will be the thickness of the square
	 * @param int: this will be the new thickness of the square
	 */
	public void setSquareThickness(int thickness) {
		this.thickness= thickness;
		}
	/**gets the instance's thickness
	 * 
	 * @return int which is the thickness of the instance
	 */
	public int getSquareThickness() {
		return this.thickness;
	}
}
