package PaintKim;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class DraggableRectangle extends GeneralShape {
	Rectangle r;
	Point imAt;
	Label label;
	Color color = Color.BLACK;
	private double x = 0;
	private double y = 0;
	private double mousex = 0;
	private double mousey = 0;

	public DraggableRectangle(double xCoord, double yCoord) {
		r = new Rectangle(xCoord, yCoord);
		getChildren().add(r);
	}
	
	public DraggableRectangle(double xCoord, double yCoord, double width, double height, double red, double green, double blue) {
		r = new Rectangle(xCoord, yCoord);
		this.r.setX(xCoord);
		this.r.setY(yCoord);
		r.setWidth(width);
		r.setHeight(height);
		color = new Color(red, green, blue, 1.0);
		setColor(color);
		getChildren().add(r);
	}
	
	
	public DraggableRectangle(double xCoord, double yCoord, double width, double height, double red, double green, double blue, String text) {
		r = new Rectangle(xCoord, yCoord);
		this.r.setX(xCoord);
		this.r.setY(yCoord);
		r.setWidth(width);
		r.setHeight(height);
		color = new Color(red, green, blue, 1.0);
		setColor(color);
		getChildren().add(r);
		addTextLabel(text);
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
		this.color = c;

	}

	@Override
	protected boolean zatYou(PaintKim.Point p) {
		System.out.println("zatYou x="+p.getX()+" y="+p.getY());
		double x1 = this.r.getX();
		double y1 = this.r.getY();
		double x2 = x1 + this.r.getWidth();
		double y3 = y1 + this.r.getHeight();
		
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
		setLayoutX(distx);
		setLayoutY(disty);
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
			this.r.setStroke(Color.RED);
			
		} else {
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
	
	@Override
	public String convertToString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		sb.append("box ");
		sb.append(this.r.getX() + " ");
		sb.append(this.r.getY() + " ");
		sb.append(this.r.getWidth() + " ");
		sb.append(this.r.getHeight() + " ");
		sb.append(this.color.getRed() + " ");
		sb.append(this.color.getGreen() + " ");
		sb.append(this.color.getBlue() + " ");
		if (this.label != null) {
			sb.append(this.label.getText());
		};
		sb.append("\n");
		
		String result = sb.toString();
		return result;
	}

}
