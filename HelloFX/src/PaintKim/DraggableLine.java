package PaintKim;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class DraggableLine extends GeneralShape {
	Line l;
	Point imAt;
	Color color;
	private double x = 0;
	private double y = 0;
	private double origStartX;
	private double origStartY;
	private double origEndX;
	private double origEndY;

	public DraggableLine(double xStart, double yStart, double xEnd, double yEnd) {
		l = new Line(xStart, yStart, xEnd, yEnd);
		color = Color.BLACK;
		getChildren().add(l);
	}

	public DraggableLine(double xStart, double yStart, double xEnd, double yEnd, double r, double g, double b) {
		l = new Line(xStart, yStart, xEnd, yEnd);
		color = new Color(r, g, b, 1.0);
		setColor(color);
		getChildren().add(l);
	}

	public Line getLine() {
		return this.l;
	}

	public void setOrigCoordStart(double x1, double y1) {
		origStartX = x1;
		origStartY = y1;
	}

	public void setOrigCoordEnd(double x2, double y2) {
		origEndX = x2;
		origEndY = y2;
	}

	public boolean zatYou(Point p) {
		System.out.println("zatYou x=" + p.getX() + " y=" + p.getY());
		System.out.println("L startX: " + this.l.getStartX() + "L startY: " + this.l.getStartY());
		System.out.println("L endX: " + this.l.getEndX() + "L endY: " + this.l.getEndY());
		double lineDist = Math.hypot(this.l.getEndX() - this.l.getStartX(), this.l.getEndY() - this.l.getStartY());
		double dist1 = Math.hypot(this.l.getEndX() - p.getX(), this.l.getEndY() - p.getY());
		double dist2 = Math.hypot(this.l.getStartX() - p.getX(), this.l.getStartY() - p.getY());
		System.out.println("Line Dist: " + (int) lineDist);
		System.out.println("dist1: " + (int) dist1);
		System.out.println("dist2: " + (int) dist2);
		boolean isOnLine = ((((int) dist1 + (int) dist2) > (int) lineDist - 5)
				&& (((int) dist1 + (int) dist2) < (int) lineDist + 5));

		System.out.println("zatYou ret=" + isOnLine);
		return isOnLine;
	}

	@Override
	public String convertToString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		sb.append("line ");
		sb.append(this.l.getStartX() + " ");
		sb.append(this.l.getStartY() + " ");
		sb.append(this.l.getEndX() + " ");
		sb.append(this.l.getEndY() + " ");
		sb.append(this.color.getRed() + " ");
		sb.append(this.color.getGreen() + " ");
		sb.append(this.color.getBlue() + " ");
		sb.append("\n");

		String result = sb.toString();
		return result;
	}

	@Override
	void dragged(Point p, MouseEvent m) {
		double distAC = Math.hypot(this.l.getStartX() - m.getX(), this.l.getStartY() - m.getY());
		double distBC = Math.hypot(this.l.getEndX() - m.getX(), this.l.getEndY() - m.getY());

		System.out.println("distAC: " + distAC);
		System.out.println("distBC: " + distBC);
		System.out.println("m.getX: " + m.getX());
		System.out.println("m.getY: " + m.getY());

		if (distAC < distBC) {
			this.l.setStartX(m.getX());
			this.l.setStartY(m.getY());
			System.out.println("Line's start x: " + this.l.getStartX());
			System.out.println("Line's start Y: " + this.l.getStartY());
			System.out.println("Line's end x: " + this.l.getEndX());
			System.out.println("Line's end y: " + this.l.getEndY());
		} else if (distAC > distBC) {
			this.l.setEndX(m.getX());
			this.l.setEndY(m.getY());
			System.out.println("Line's start x: " + this.l.getStartX());
			System.out.println("Line's start Y: " + this.l.getStartY());
			System.out.println("Line's end x: " + this.l.getEndX());
			System.out.println("Line's end y: " + this.l.getEndY());
		}

	}

	@Override
	void setColor(Color c) {
		// TODO Auto-generated method stub
		color = c;
		this.l.setStroke(color);
	}

	@Override
	void setHighlight(boolean bool) {
		// TODO Auto-generated method stub
		if (bool) {
			this.l.setStrokeType(StrokeType.OUTSIDE);
			this.l.setStrokeWidth(2);
			if (this.l.getStroke() == Color.YELLOW) {
				this.l.setStroke(Color.RED);
			} else {
				this.l.setStroke(Color.YELLOW);
			}
		} else {
			this.l.setStrokeType(StrokeType.CENTERED);
			this.l.setStroke(color);
			this.l.setStrokeWidth(1);
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

}
