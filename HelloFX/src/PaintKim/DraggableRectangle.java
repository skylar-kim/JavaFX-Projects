package PaintKim;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class DraggableRectangle extends GeneralShape {
	Rectangle r;
	Point imAt;
	Label label;
	private double x = 0;
	private double y = 0;
	private double mousex = 0;
	private double mousey = 0;

	public DraggableRectangle(double xCoord, double yCoord) {
		r = new Rectangle(xCoord, yCoord);
		r.setX(0);
		getChildren().add(r);
	}

	public Rectangle getRectangle() {
		return this.r;
	}
	
	public void addTextLabel(String text) {
		label = new Label(text);
		label.relocate((this.r.getX() + this.r.getWidth()/2) - text.length()*2, (this.r.getY() + this.r.getHeight()/2));
		getChildren().add(label);
	}

	@Override
	void setColor(Color c) {
		// TODO Auto-generated method stub
		this.r.setFill(c);

	}

	@Override
	protected boolean zatYou(PaintKim.Point p) {
		System.out.println("zatYou x="+p.getX()+" y="+p.getY());
		double x1 = this.r.getX();
		double y1 = this.r.getY();
		double x2 = x1 + this.r.getWidth();
		//double y2 = y1;
		//double x3 = x1;
		double y3 = y1 + this.r.getHeight();
		//double x4 = x2;
		//double y4 = y3;
		
		boolean isOnRect = false;
		isOnRect = ((p.getX() >= x1 && p.getX() <= x2) && (p.getY() >= y1 && p.getY() <= y3));
		System.out.println("zatYou ret="+isOnRect);
		return isOnRect;
	}

	@Override
	void dragged(PaintKim.Point p, MouseEvent m) {
		// TODO Auto-generated method stub
		double distx = m.getSceneX() - this.mouseX;
		double disty = m.getSceneY() - this.mouseY;
		this.r.setLayoutX(distx);
		this.r.setLayoutY(disty);
		imAt = p;
		System.out.println("imAt x: " + imAt.getX());
		System.out.println("imAt Y: " + imAt.getY());
	}

	@Override
	void setHighlight(boolean bool) {
		// TODO Auto-generated method stub
		if (bool) {
			this.r.setStrokeType(StrokeType.OUTSIDE);
			this.r.setStrokeWidth(5);
			if (this.r.getStroke() == Color.YELLOW) {
				this.r.setStroke(Color.RED);
			} else {
				this.r.setStroke(Color.YELLOW);
			}
		} else {
//			this.r.setStrokeType(StrokeType.CENTERED);
//			this.r.setStroke(Color.BLACK);
			this.r.setStrokeWidth(0);
		}
		
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
	
	String convertToString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		String result = sb.toString();
		return result;
	}

}
