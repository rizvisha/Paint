package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class PolyLine {
	private ArrayList<Point> points;
	//private ArrayList<Integer> xpoints;
	//private ArrayList<Integer> ypoints;
	int[] xpoints;
	int[] ypoints;
	private Color color;
	String style;
	private int thickness;
	
	public PolyLine(Color color, String style, int thickness){
		points = new ArrayList<Point>();
		this.color = color;
		this.style = style;
		this.thickness = thickness;
	}
	public PolyLine(Point p, Color color, String style, int thickness){
		points = new ArrayList<Point>();
		points.add(p);
		this.style = style;
		this.color = color;
		this.thickness = thickness;
	}

	public void addPoint(Point p){
		points.add(p);
		//xpoints.add(p.getX());
		//ypoints.add(p.getY());
		xpoints = new int[points.size()];
		ypoints = new int[points.size()];
		for (int i = 0; i < points.size(); i++){
			Point cpoint = points.get(i);
			xpoints[i] = cpoint.getX();
			ypoints[i] = cpoint.getY();
		}
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void removeLastPoint(){
		points.remove(points.size()-1);
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	/**gets the instance's thickness
	 * 
	 * @return int which is the thickness of the instance
	 */
	public int getPolyLineThickness(){
		return thickness;
	}
	/**Takes in an integer which which will be the thickness of the polyline
	 * @param int: this will be the new thickness of the polyline
	 */
	public void setPolyLineThickness(int i){
		this.thickness = i;
	}
	
	public String getPolyLineStyle(){
		return style;
	}
	
	public void setPolyLineStyle(String style){
		this.style = style;
	}
	
	public int[] getXPoints(){
		return xpoints;
	}
	
	public int[] getYPoints(){
		return ypoints;
	}
}
