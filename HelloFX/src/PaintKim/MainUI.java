package PaintKim;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	//Rectangle r;
	DraggableRectangle dr;
	boolean mouseIsPressedRect = false;
	boolean drawRectangleBool = false;

	// Line Stuff
	Line l;
	// MyLine l;
	boolean mouseIsPressedLine = false;
	boolean drawLineBool = false;

	// Text Stuff
	Label label;
	boolean putTextBool = false;

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
				//drawRectangle();
				dr = new DraggableRectangle();
				dr.drawRectangle();
			});

			lineB.setOnAction(e -> {
				System.out.println("Line Button Pressed");
				drawRectangleBool = false;

				drawLineBool = true;
				drawLine();
			});

			puttextB.setOnAction(e -> {
				System.out.println("Put Text Button Pressed");
				drawLineBool = false;
				drawRectangleBool = false;

				putTextBool = true;
				putText();
			});

			getChildren().addAll(rectangleB, lineB, selectB, deleteB, puttextB, textField, loadB, saveB);
		}
		/*
		public void drawRectangle() {
			if (drawRectangleBool) {
				System.out.println("Drawing Rectangle");
				System.out.println("drawRectangleBool: " + drawRectangleBool);

				root.setOnMousePressed(e -> {
					corners[0] = new Point(e.getX(), e.getY());
					r = new Rectangle(10, 10);
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
		*/

		

		

		public void drawLine() {
			if (drawLineBool) {
				System.out.println("Drawing Line.");
				root.setOnMousePressed(e -> {
					l = new Line(e.getX(), e.getY(), e.getX(), e.getY());
					root.getChildren().add(l);
					// l = new MyLine(e.getX(), e.getY(), e.getX(), e.getY());
				});

				root.setOnMouseDragged(e -> {
					l.setEndX(e.getX());
					l.setEndY(e.getY());
				});
			}
		}

		public void putText() {
			if (putTextBool) {
				System.out.println("Putting text on screen.");

				String textFieldInput = textField.getText();
				System.out.println("Text Field Input: " + textFieldInput);

				label = new Label(textFieldInput);
				double randX = Math.random() * 800;
				double randY = Math.random() * 800;
				System.out.println("randx: " + randX + " randy: " + randY);
				label.relocate(randX, randY);
				// label.setText(textFieldInput);

				root.getChildren().add(label);

			}
		}

	}
	
	public class DraggableRectangle extends Rectangle {
		private double mouseX;
		private double mouseY;

		public DraggableRectangle() {
			super(10,10);
			
			

//			setOnMousePressed(e -> {
//				mouseX = e.getSceneX();
//				mouseY = e.getSceneY();
//			});
//
//			setOnMouseDragged(e -> {
//				double deltaX = e.getSceneX() - mouseX;
//				double deltaY = e.getSceneY() - mouseY;
//
//				relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
//				mouseX = e.getSceneX();
//				mouseY = e.getSceneY();
//			});
		}
		
		public void drawRectangle() {
			root.setOnMousePressed(e -> {
				corners[0] = new Point(e.getX(), e.getY());
				setX(corners[0].getX());
				setY(corners[0].getY());
				root.getChildren().add(this);
			});
			
			root.setOnMouseDragged(e -> {
				corners[1] = new Point(e.getX(), e.getY());
				double xdif = corners[0].xdif(corners[1]);
				double ydif = corners[0].ydif(corners[1]);
				setWidth(xdif);
				setHeight(ydif);
				// put these lines back in to get tracking in all 4 quadrants
				setX(min(corners[0].getX(), corners[1].getX()));
				setY(min(corners[0].getY(), corners[1].getY()));
			});
		}
		
		public double min(double x, double y) {
			return (x < y) ? x : y;
		}
	}

	
}