package PaintKim;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import mousing.Point;

public class MainUI extends Application {

	Pane root;
	
	// Rectangle Stuff
	Point[] corners = new Point[2];
	Rectangle r;
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
	
	Rectangle theRectangle;
	DraggableRectangle theDR;
	boolean mouseIsPressed = false;
	
	// List of items on screen
	//List<Rectangle> rectangleList;
	List<DraggableRectangle> drList;
	
	
	// MODES
	enum Mode {RECT, LINE, SELECT, DELETE}
	
	Mode currentMode;
	
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
		
		//rectangleList = new ArrayList<Rectangle>();
		
		drList = new ArrayList<DraggableRectangle>();
		
		PaintMenu paintMenu = new PaintMenu();

		root.getChildren().add(paintMenu);
		/*
		root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
			Point p = new Point(m.getX(), m.getY());
			theItem = findItem(p);
			mouseIsPressed = true;
		});
		
		root.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent m) -> {
			mouseIsPressed = false;
		});
		
		root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent m)-> {
			Point p = new Point(m.getX(), m.getY());
			if(theItem!=null) {theItem.dragged(p); }
		});
		*/
	}
	
	public DraggableRectangle findDraggedRectangle(Point p) {
		DraggableRectangle found = null;
		for (DraggableRectangle dr: drList) {
			if (dr.zatYou(p)) {
				found = dr;
			}
		}
		return found;
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
//				drawLineBool = false;
//
//				drawRectangleBool = true;
//				drawRectangle();
				//dr = new DraggableRectangle();
				//dr.drawRectangle();
				//root.getChildren().add(dr);
				//itemList.add(dr);
				
				currentMode = Mode.RECT;
			});

			lineB.setOnAction(e -> {
				System.out.println("Line Button Pressed");
//				drawRectangleBool = false;
//
//				drawLineBool = true;
//				drawLine();
				currentMode = Mode.LINE;
			});

			puttextB.setOnAction(e -> {
				System.out.println("Put Text Button Pressed");
//				drawLineBool = false;
//				drawRectangleBool = false;
//
//				putTextBool = true;
//				putText();
			});
			
			selectB.setOnAction(e -> {
				System.out.println("Select Button Pressed");
//				drawLineBool = false;
//				drawRectangleBool = false;
//				
//				select();
			});
			paintAction();

			getChildren().addAll(rectangleB, lineB, selectB, deleteB, puttextB, textField, loadB, saveB);
		}
		
		public void paintAction() {
			root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
				if (currentMode == Mode.RECT) {
					corners[0] = new Point(m);
					dr = new DraggableRectangle(10, 10);
					dr.setX(corners[0].getX());
					dr.setY(corners[0].getY());
					root.getChildren().add(dr);
					mouseIsPressedRect = true;
				}
				else if (currentMode == Mode.LINE) {
					l = new Line(m.getX(), m.getY(), m.getX(), m.getY());
					root.getChildren().add(l);
				}
			});
			
			root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent m) -> {
				if (currentMode == Mode.RECT) {
					corners[1] = new Point(m);
					double xdif = corners[0].xdif(corners[1]);
					double ydif = corners[0].ydif(corners[1]);
					dr.setWidth(xdif);
					dr.setHeight(ydif);
					// put these lines back in to get tracking in all 4 quadrants
					dr.setX(min(corners[0].getX(), corners[1].getX()));
					dr.setY(min(corners[0].getY(), corners[1].getY()));
				}
				else if (currentMode == Mode.LINE) {
					l.setEndX(m.getX());
					l.setEndY(m.getY());
				}
			});
		}
		/*
		public void drawRectangle() {
			if (drawRectangleBool) {
				System.out.println("Drawing Rectangle");
				System.out.println("drawRectangleBool: " + drawRectangleBool);

				root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
//					corners[0] = new Point(e.getX(), e.getY());
//					r = new Rectangle(10, 10);
//					r.setX(corners[0].getX());
//					r.setY(corners[0].getY());
//					root.getChildren().add(r);
					corners[0] = new Point(m);
					dr = new DraggableRectangle(10, 10);
					dr.setX(corners[0].getX());
					dr.setY(corners[0].getY());
					root.getChildren().add(dr);
					mouseIsPressedRect = true;
				});

				root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent m) -> {
					
					corners[1] = new Point(e.getX(), e.getY());
					double xdif = corners[0].xdif(corners[1]);
					double ydif = corners[0].ydif(corners[1]);
					r.setWidth(xdif);
					r.setHeight(ydif);
					// put these lines back in to get tracking in all 4 quadrants
					r.setX(min(corners[0].getX(), corners[1].getX()));
					r.setY(min(corners[0].getY(), corners[1].getY()));
					
					corners[1] = new Point(m);
					double xdif = corners[0].xdif(corners[1]);
					double ydif = corners[0].ydif(corners[1]);
					dr.setWidth(xdif);
					dr.setHeight(ydif);
					// put these lines back in to get tracking in all 4 quadrants
					dr.setX(min(corners[0].getX(), corners[1].getX()));
					dr.setY(min(corners[0].getY(), corners[1].getY()));
				});
				
				root.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent m) -> {
					
					System.out.println("Layout X: " + r.getX());
					System.out.println("Layout Y: " + r.getY());
					//rectangleList.add(r);
					 
					 
					System.out.println("Layout X: " + dr.getX());
					System.out.println("Layout Y: " + dr.getY());
					dr.setimAt(dr.getX(), dr.getY());
					mouseIsPressedRect = false;
					drList.add(dr);
					drawRectangleBool = false;
				});

			}
		}
		*/
		public double min(double x, double y) {
			return (x < y) ? x : y;
		}

		/*
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
		 */
		
//		public void putText() {
//			if (putTextBool) {
//				System.out.println("Putting text on screen.");
//
//				String textFieldInput = textField.getText();
//				System.out.println("Text Field Input: " + textFieldInput);
//
//				label = new Label(textFieldInput);
//				double randX = Math.random() * 800;
//				double randY = Math.random() * 800;
//				System.out.println("randx: " + randX + " randy: " + randY);
//				label.relocate(randX, randY);
//				// label.setText(textFieldInput);
//
//				root.getChildren().add(label);
//
//			}
//		}
		
		
//		public void select() {
//			root.addEventHandler
//	        (  MouseEvent.MOUSE_PRESSED,
//	           (MouseEvent m) ->
//	           {
//	               Point p = new Point( m );
//	               theDR = findDraggedRectangle( p );
//	               mouseIsPressed = true;
//	           }
//	        );
//			
//			root.addEventHandler
//	        (  MouseEvent.MOUSE_RELEASED,
//	           (MouseEvent m) ->  { mouseIsPressed = false; }
//	        );
//			
//			root.addEventHandler
//	        (  MouseEvent.MOUSE_DRAGGED,
//	           (MouseEvent m)->
//	           {   
//	               Point p = new Point( m );
//	               if(theDR!=null) {theDR.dragged(p); }
//	           }
//	        );
//		}
		
//		public Rectangle findRectangle(Point p) {
//			Rectangle found = null;
//			for (Rectangle r: rectangleList) {
//				if ()
//			}
//		}
		

	}
	
	
	
//	public class DraggableShape extends Shape {
//		
//	}
	
	/*
	public class DraggableRectangle extends Rectangle {
		private double mouseX;
		private double mouseY;
		Point location;
		
		private boolean canDragRectangle = false;

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
				//root.getChildren().add(this);
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
			
//			root.setOnMouseReleased(e -> {
//				System.out.println("Layout X: " + getLayoutX());
//				System.out.println("Layout Y: " + getLayoutY());
//			});
			
			this.canDragRectangle = true;
		}
		
		public double min(double x, double y) {
			return (x < y) ? x : y;
		}
		
//		public boolean zatYou(Point m) {
//			
//		}
	}
	*/
	
}
