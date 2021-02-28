package PaintKim;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

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
	DraggableLine dl;

	// MyLine l;
	boolean mouseIsPressedLine = false;
	boolean drawLineBool = false;

	// Text Stuff
	Label label;
	boolean putTextBool = false;

	Rectangle theRectangle;
	DraggableRectangle theDR;
	boolean mouseIsPressed = false;

	DraggableLine theLine;

	List<GeneralShape> itemList;
	GeneralShape theItem;
	
	int deleteIndex = -1;

	// MODES
	enum Mode {
		RECT, LINE, SELECT, DELETE
	}

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

		// rectangleList = new ArrayList<Rectangle>();

		itemList = new ArrayList<GeneralShape>();
		PaintMenu paintMenu = new PaintMenu();

		root.getChildren().add(paintMenu);
		
	}

	public GeneralShape findItem(Point p) {
		GeneralShape found = null;
		for (GeneralShape gs : itemList) {
			if (gs.zatYou(p)) {
				found = gs;
			}
			gs.setHighlight(false);
		}
		return found;
	}
	
	public int findItemIndex(Point p) {
		int index = -1;
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println("find index: " + i);
			if (itemList.get(i).zatYou(p)) {
				System.out.println("zat you return true: " + i);
				index = i;
				break;
			}
		}
		return index;
	}

	public class PaintMenu extends VBox {
		Button rectangleB;
		Button lineB;
		Button selectB;
		Button deleteB;
		Button puttextB;
		TextField textField;
		ColorPicker cpB;
		Button loadB;
		Button saveB;

		public PaintMenu() {
			rectangleB = new Button("Rectangle");
			lineB = new Button("Line");
			selectB = new Button("Select");
			deleteB = new Button("Delete");
			puttextB = new Button("Put Text");
			textField = new TextField("Start writing");
			cpB = new ColorPicker(Color.BLUE);
			loadB = new Button("Load File");
			saveB = new Button("Save");

			rectangleB.setOnAction(e -> {
				System.out.println("Rectangle Button Pressed");

				currentMode = Mode.RECT;
			});

			lineB.setOnAction(e -> {
				System.out.println("Line Button Pressed");

				currentMode = Mode.LINE;
			});
			

			puttextB.setOnAction(e -> {
				System.out.println("Put Text Button Pressed");

				//putText();
				if (theItem != null && theItem instanceof DraggableRectangle) {
					System.out.println("Put Text on DraggableRectangle");
					
					String textFieldInput = textField.getText();
					System.out.println("Text Field Input: " + textFieldInput);
					theItem.addTextLabel(textFieldInput);
				}
			});

			selectB.setOnAction(e -> {
				System.out.println("Select Button Pressed");

				currentMode = Mode.SELECT;
			});
			
			deleteB.setOnAction(e -> {
				System.out.println("Delete Button Pressed");
				currentMode = Mode.DELETE;
				
				if (deleteIndex > -1) {
					System.out.println("Shape chosen to be deleted: " + deleteIndex);
					root.getChildren().remove(theItem);
					itemList.remove(deleteIndex);
					deleteIndex = -1;
					
				} else {
					System.out.println("No shape chosen to be deleted");
				}
			});

			cpB.setOnAction(e -> {
				System.out.println("Color Picker Clicked");
				Color c = cpB.getValue();
				if (theItem != null) {
					System.out.println("Shape was chosen");
					theItem.setColor(c);

				} else {
					System.out.println("Shape was not chosen :(");
				}
			});

			paintAction();

			getChildren().addAll(rectangleB, lineB, selectB, deleteB, puttextB, textField, cpB, loadB, saveB);
		}

		public void paintAction() {
			root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
				if (currentMode == Mode.RECT) {
					corners[0] = new Point(m);
					dr = new DraggableRectangle(10, 10);
					dr.getRectangle().setX(corners[0].getX());
					dr.getRectangle().setY(corners[0].getY());
					itemList.add(dr);
					root.getChildren().add(dr);
					mouseIsPressed = true;
				} else if (currentMode == Mode.LINE) {
					dl = new DraggableLine(m.getX(), m.getY(), m.getX(), m.getY());
					dl.setOrigCoordStart(m.getX(), m.getY());
					root.getChildren().add(dl);
					itemList.add(dl);
					mouseIsPressed = true;
				} else if (currentMode == Mode.SELECT) {
					Point p = new Point(m);
					theItem = findItem(p);
					deleteIndex = findItemIndex(p);
					theItem.setMouseX(p.getX());
					theItem.setMouseY(p.getY());
					theItem.setHighlight(true);
					mouseIsPressed = true;
				} 
			});

			root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent m) -> {
				if (currentMode == Mode.RECT) {
					corners[1] = new Point(m);
					double xdif = corners[0].xdif(corners[1]);
					double ydif = corners[0].ydif(corners[1]);
					dr.getRectangle().setWidth(xdif);
					dr.getRectangle().setHeight(ydif);
					// put these lines back in to get tracking in all 4 quadrants
					dr.getRectangle().setX(min(corners[0].getX(), corners[1].getX()));
					dr.getRectangle().setY(min(corners[0].getY(), corners[1].getY()));
				} else if (currentMode == Mode.LINE) {
//					l.setEndX(m.getX());
//					l.setEndY(m.getY());
					dl.getLine().setEndX(m.getX());
					dl.getLine().setEndY(m.getY());
					dl.setOrigCoordEnd(m.getX(), m.getY());
					
				} else if (currentMode == Mode.SELECT) {
					Point p = new Point(m);
					if (theItem != null) {
						theItem.dragged(p, m);
					}
				}
			});

			root.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent m) -> {
				mouseIsPressed = false;
			});
		}

		public double min(double x, double y) {
			return (x < y) ? x : y;
		}

		public void putText() {
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
