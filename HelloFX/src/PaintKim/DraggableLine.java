package PaintKim;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DraggableLine extends GeneralShape {
	Line l;
	Point imAt;
	private double x = 0;
	private double y = 0;
	
	
	public DraggableLine(double xStart, double yStart, double xEnd, double yEnd) {
		l = new Line(xStart, yStart, xEnd, yEnd);
		
		getChildren().add(l);
	}
	
	public Line getLine() {
		return this.l;
	}
	
	public boolean zatYou(Point p) {
		System.out.println("zatYou x="+p.getX()+" y="+p.getY());
		double lineDist = Math.hypot(this.l.getEndX() - this.l.getStartX(), this.l.getEndY() - this.l.getStartY());
		double dist1 = Math.hypot(this.l.getEndX() - p.getX(), this.l.getEndY() - p.getY());
		double dist2 = Math.hypot(this.l.getStartX() - p.getX(), this.l.getStartY() - p.getY());
		System.out.println("Line Dist: " + (int)lineDist);
		System.out.println("dist1: " + (int)dist1);
		System.out.println("dist2: " + (int)dist2);
		boolean isOnLine = ( (((int)dist1+(int)dist2) > (int)lineDist - 5) && 
				(((int)dist1+(int)dist2) < (int)lineDist + 5));
		
     	System.out.println("zatYou ret="+isOnLine);
     	return isOnLine; 
	}

	@Override
	void setColor(Color c) {
		// TODO Auto-generated method stub
		this.l.setStroke(c);
	}

	@Override
	void setSelected(boolean bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void dragged(Point p, MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void setMouseX(double x) {
		// TODO Auto-generated method stub
		this.mouseX = x;
	}

	@Override
	void setMouseY(double y) {
		// TODO Auto-generated method stub
		this.mouseY = y;
	}
	
}
