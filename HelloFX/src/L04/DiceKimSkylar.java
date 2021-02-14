package L04;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

public class DiceKimSkylar extends Application {
	Random randy = new Random(); // used for random colors
	// I didn't get very far with this lab

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		FlowPane root = new FlowPane();
		stage.setTitle("Dice Lab");
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		for (int i = 0; i < 6; i++) {
			DiceBox db = new DiceBox(i+1);
			
			root.getChildren().add(db);
		}

	}

	public class DiceBox extends VBox{
		public DiceBox(int num) {
			makeHBox();
			makePane(num);
		}
		public void makeHBox() {
			// make buttons
			HBox pane = new HBox();
			
			Button btn1 = new Button();
			btn1.setText("roll");
			
			Button btn2 = new Button();
			btn2.setText("hold");
			
			pane.getChildren().add(btn1);
			pane.getChildren().add(btn2);
			
			getChildren().add(pane);
		}
		
		public void makePane(int num) {
			Pane pane = new Pane();
			
			pane.prefHeight(100);
			pane.prefWidth(100);
			
			Text text = new Text();
			text.setText(Integer.toString(num));
			
			pane.getChildren().add(text);
			getChildren().add(pane);
		}
	}

	public String pastel() {
		String c = "";

		for (int i = 0; i < 3; i++) {
			c += pastel1();
		}

		return c;
	}

	// return a string which is aa, bb, cc, dd, ee, or ff,
	public String pastel1() {
		char it = 'a';
		it += randy.nextInt(6);
		String s = "" + it + it;
		return s;
	}

}
