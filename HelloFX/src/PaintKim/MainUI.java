package PaintKim;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mousing.Point;

public class MainUI extends Application {

	Pane root;

	// Rectangle Stuff
	Point[] corners = new Point[2];
	Rectangle r;
	boolean mouseIsPressedRect = false;
	boolean drawRectangleBool = false;
	
	// Line Stuff
	Line l;
	boolean mouseIsPressedLine = false;
	boolean drawLineBool = false;
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Paint");
		root = new Pane();
		Scene scene = new Scene(root, 900, 700);
		stage.setScene(scene);
		stage.show();

		PaintMenu paintMenu = new PaintMenu();

		root.getChildren().add(paintMenu);
	}

	public class PaintMenu extends VBox {
		Button rectangleB;
		Button lineB;
		Button selectB;
		Button deleteB;
		Button puttextB;
		TextField textField;
		Button loadB;
		Button saveB;

		public PaintMenu() {
			rectangleB = new Button("Rectangle");
			lineB = new Button("Line");
			selectB = new Button("Select");
			deleteB = new Button("Delete");
			puttextB = new Button("Put Text");
			textField = new TextField("Start writing");
			loadB = new Button("Load File");
			saveB = new Button("Save");

			rectangleB.setOnAction(e -> {
				System.out.println("Rectangle Button Pressed");
				drawLineBool = false;
				
				drawRectangleBool = true;
				drawRectangle();
			});
			
			lineB.setOnAction(e-> {
				System.out.println("Line Button Pressed");
				drawRectangleBool = false;
				
				drawLineBool = true;
				drawLine();
			});

			getChildren().addAll(rectangleB, lineB, selectB, deleteB, puttextB, textField, loadB, saveB);
		}

	}

	public void drawRectangle() {
		if (drawRectangleBool) {
			System.out.println("Drawing Rectangle");
			System.out.println("drawRectangleBool: " + drawRectangleBool);
			/*
			root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
				corners[0] = new Point(m);
				r = new Rectangle(10, 10);
				r.setX(corners[0].getX());
				r.setY(corners[0].getY());
				root.getChildren().add(r);
				mouseIsPressedRect = true;
			});

			root.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent m) -> {
				mouseIsPressedRect = false;
			});

			root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent m) -> {
				corners[1] = new Point(m);
				double xdif = corners[0].xdif(corners[1]);
				double ydif = corners[0].ydif(corners[1]);
				r.setWidth(xdif);
				r.setHeight(ydif);
				// put these lines back in to get tracking in all 4 quadrants
				r.setX(min(corners[0].getX(), corners[1].getX()));
				r.setY(min(corners[0].getY(), corners[1].getY()));
			});
			*/
			
			root.setOnMousePressed(e -> {
				corners[0] = new Point(e.getX(), e.getY());
				r = new Rectangle(10,10);
				r.setX(corners[0].getX());
				r.setY(corners[0].getY());
				root.getChildren().add(r);
				
			});
			
			root.setOnMouseDragged(e -> {
				corners[1] = new Point(e.getX(), e.getY());
				double xdif = corners[0].xdif(corners[1]);
				double ydif = corners[0].ydif(corners[1]);
				r.setWidth(xdif);
				r.setHeight(ydif);
				// put these lines back in to get tracking in all 4 quadrants
				r.setX(min(corners[0].getX(), corners[1].getX()));
				r.setY(min(corners[0].getY(), corners[1].getY()));
			});
			
			
		}
	}
	
	public void drawLine() {
		if (drawLineBool) {
			
			root.setOnMousePressed(e -> {
				l = new Line(e.getX(), e.getY(), e.getX(), e.getY());
				root.getChildren().add(l);
			});
			
			root.setOnMouseDragged(e -> {
				l.setEndX(e.getX());
				l.setEndY(e.getY());
			});
		}
	}

	public double min(double x, double y) {
		return (x < y) ? x : y;
	}
}
