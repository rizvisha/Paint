package ca.utoronto.utm.paint;
import java.awt.Color;


public class Point {
	private int x, y;
	Color color;
	int thickness;
	
	/**
	 * A point object has an x and a y coordinate, and a color. The Point object is utilized by mouse events to mark and store points. It is used in other shape
	 * classes (Circle, Square, Rectangle, etc.) to mark corners and current points, and directly used in painting a squiggle which is just a collection of points.
	 * @param x The x coordinate of the Point.
	 * @param y The y coordinate of the Point.
	 * @param color The color of the point when painted.
	 * The point should have a thickness too but I am not doing that UserStory and I will leave up to the person in charge to modify the JavaDoc appropriately.
	 */

	Point(int x, int y, Color color, int thickness){
		this.x=x; this.y=y;
		this.color = color;
		this.thickness = thickness;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	/**gets the instance's thickness
	 * 
	 * @return int which is the thickness of the instance
	 */
	public int getSquiggleThickness(){
		return thickness;
	}
	/**Takes in an integer which which will be the thickness of the point
	 * @param int: this will be the new thickness of the point
	 */
	public void setSquiggleThickness(int i){
		this.thickness = i;
	}
}
