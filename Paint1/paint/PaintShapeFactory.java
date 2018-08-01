package ca.utoronto.utm.paint;

public class PaintShapeFactory {

	public PaintShape makePaintShape(String type){
		PaintShape paintShape = null;
		
		if (type == "Circle"){
			paintShape = new PaintShapeCircle();
		} else if (type == "Oval"){
			paintShape = new PaintShapeOval();
		} else if (type == "PolyLine"){
			paintShape = new PaintShapePolyLine();
		} else if (type == "Rectangle"){
			paintShape = new PaintShapeRectangle();
		} else if (type == "Square"){
			paintShape = new PaintShapeSquare();
		} else if (type == "Squiggle"){
			paintShape = new PaintShapeSquiggle();
		}
		
		return paintShape;
	}
}
