package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class Squiggle {
	private ArrayList<Point> points;
	private Color color;
	private int thickness;
	
	public Squiggle(Color color, int thickness){
		this.color = color;
		this.thickness = thickness;
		this.points = new ArrayList<Point>();
	}
	
	public void addPoint(Point p){
		this.points.add(p);
	}
	
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public int getThickness(){
		return this.thickness;
	}
	
	public void setThickness(int thickness){
		this.thickness = thickness;
	}
}