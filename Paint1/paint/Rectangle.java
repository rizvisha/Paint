package ca.utoronto.utm.paint;
import java.awt.Color;
public class Rectangle {
	private Point topLeftCorner;
	private int width;
	private int height;
	private Point dynamicPoint;
	private Color color;
	private String style;
	private int thickness;
	
	/**
	 * Create a square with a side length equal to s, a color, style (filled or outline) and thickness (the thickness of the line if the style is "outline"). 
	 * The point argument marks coordinates of the top left corner of the rectangle.
	 * @param corner The top left corner for the rectangle.
	 * @param w An integer value for the width of the rectangle.
	 * @param h An integer value for the length of the rectangle.
	 * @param color The color of the square when painted. (outline or filled).
	 * @param style The style, outline or filled, of the shape.
	 * @param thickness The line thickness of the object being created, when style is "outline".
	 */
	public Rectangle(Point corner, int w, int h, Color color, String style, int thickness) {
		this.topLeftCorner = corner;
		this.width = w;
		this.height = h;
		this.dynamicPoint = null;
		this.color = color;
		this.style = style;
		this.thickness = thickness;
	}
	
	public Point getCorner() {
		return this.topLeftCorner;
	}
	
	public Point getDynamicPoint() {
		return this.dynamicPoint;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setCorner(Point p) {
		this.topLeftCorner = p;
	}
	
	public  void setDynamicPoint(Point p) {
		this.dynamicPoint = p;
	}
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public String getRectangleStyle(){
		return this.style;
	}
	
	public void setRectangleStyle(String style){
		this.style = style;
	}
	/**Takes in an integer which which will be the thickness of the rectangle
	 * @param int: this will be the new thickness of the rectangle
	 */
	public void setRectangleThickness(int thickness) {
		this.thickness= thickness;
	}
	/**Returns the thickness of the rectangle
	 * @return thickness
	 */
	public int getRectangleThickness() {
		return this.thickness;
		}
}
