package ca.utoronto.utm.paint;
import java.awt.Color;
public class Oval {
	private Point centre;
	private int xradius, yradius;
	private Color color;
	private String style;
	private int thickness;
	
	/**
	 * Create an oval with a center, radius, color, style (filled or outline) and thickness (the thickness of the line if the style is "outline"). 
	 * The point argument marks coordinates of the center of the oval.
	 * @param centre A point object that marks the center of the oval
	 * @param xradius An integer value for the length of the x radius of the oval
	 * @param yradius An integer value for the length of the y radius of the oval
	 * @param color The color of the oval when painted (outline or filled).
	 * @param style The style, outline or filled, of the shape.
	 * @param thickness The line thickness of the object being created, when style is "outline".
	 */
	public Oval(Point centre, int xradius, int yradius, Color color, String style, int thickness){
		this.centre = centre;
		this.xradius = xradius;
		this.yradius = yradius;
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

	public int getXRadius() {
		return xradius;
	}

	public void setXRadius(int xradius) {
		this.xradius = xradius;
	}
	
	public int getYRadius() {
		return yradius;
	}
	
	public void setYRadius(int yradius) {
		this.yradius = yradius;
	}
	/**gets the color of the instance
	 * 
	 * @return the color of the instance
	 */
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public String getOvalStyle(){
		return this.style;
	}
	
	public void setOvalStyle(String style){
		this.style = style;
	}
	/**Takes in an integer which which will be the thickness of the oval
	 * @param int: this will be the new thickness of the oval
	 */
	public void setOvalThickness(int thickness) {
		this.thickness= thickness;
	}
	/**gets the instance's thickness
	 * 
	 * @return int which is the thickness of the instance
	 */
	public int getOvalThickness() {
		return this.thickness;
	}
}
