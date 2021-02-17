package L06;
// LightsOut.java

// 2019 Barrett Koster
// user defined 'buttons'  

import javax.swing.JOptionPane;

//import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.input.*;

public class LightsOut extends Application {
	Pane root; // all the stuff in the window attaches here
	boolean[] lights;
	Rectangle[] lightsR;
	int many;
	Stage st;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		initUI(stage);
	}

	private void initUI(Stage stage) {
		stage.setTitle("Lights Out");
		root = new Pane();
		Scene scene = new Scene(root, 800, 200);
		stage.setScene(scene);
		stage.show();
		st = stage;

		// String aString = JOptionPane.showInputDialog("How many? ");
		// many = Integer.parseInt(aString);
		many = 9; // for debugging
		lights = new boolean[many];
		lightsR = new Rectangle[many];
		for (int i = 0; i < many; i++) {
			lights[i] = (Math.random() > 0.5);
			Rectangle r = new Rectangle(50, 50);
			lightsR[i] = r;
			r.setFocusTraversable(true);
			doLight(i);
			r.setX(20 + 55 * i);
			r.setY(100);
			root.getChildren().add(r);

			// put on borders, so we can set color to black and white to
			// represent focus
			r.setStrokeType(StrokeType.OUTSIDE);
			r.setStrokeWidth(2);
			r.setStroke(Color.WHITE);
		}

		// call focusCheck on every click, to move our manual focus
		// (black border on Rectangle) any time focus might have changed.
		scene.setOnKeyReleased(e -> {
			focusCheck();
		}); // pressed is too soon
		// use filter to catch space before it is eaten by handler
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
			System.out.println("filter key code:" + ke.getCode());
			if (ke.getCode() == KeyCode.SPACE) {
				focusCheckDo();
			}
		});

		// set a bunch of buttons to be another way to click on the lights.
		// For access, this is the easy way to use arrow and spacebar ... because
		// the focus for buttons is already built it.
		for (int j = 0; j < many; j++) {
			Button b = new Button();
			b.setText("b" + j);
			b.setLayoutX(30 + 55 * j);
			b.setLayoutY(60);
			b.setFocusTraversable(true);
			root.getChildren().add(b);
			int jj = j;
			b.setOnAction(e -> {
				flip3(jj);
			});
		}

		focusCheck();

		// this handles use clicks mouse directly on a light.
		root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (MouseEvent m) -> {
			clickAt(m.getX(), m.getY());
		});
	}

	// put black rectangle around rectange in focus
	public void focusCheck() {
		for (int i = 0; i < many; i++) {
			Rectangle r = lightsR[i];
			if (r.isFocused()) {
				System.out.println("focus: " + i);
				r.setStroke(Color.BLACK);
			} else {
				r.setStroke(Color.WHITE);
			}
		}
		st.show();
	}

	// flip Rectangle if it's in focus
	public void focusCheckDo() {
		for (int i = 0; i < many; i++) {
			Rectangle r = lightsR[i];
			if (r.isFocused()) {
				flip3(i);
			}
		}
		st.show();
	}

	// handle click at these coordinates
	// Compute which rectangle, then flip3 on that one
	public void clickAt(double x, double y) {
		if (100 < y && y < 150) {
			double id = (x - 20) / 55;
			int i = (int) id;
			System.out.println("i=" + i);
			if (0 <= i && i < many) {
				flip3(i);
			}
		}
	}

	// flip light i and possibly one on each side
	public void flip3(int i) {
		flip(i);
		if (i - 1 >= 0) {
			flip(i - 1);
		}
		if (i + 1 < many) {
			flip(i + 1);
		}
	}

	// flip light it, including keeping track of lights[] boolean
	public void flip(int i) {
		lights[i] = !lights[i];
		doLight(i);

	}

	// actually change the color of light i
	public void doLight(int i) {
		if (lights[i]) {
			lightsR[i].setFill(new Color(.9, .9, .2, 1));
		} else {
			lightsR[i].setFill(new Color(.4, .4, .0, 1));
		}
	}

}
