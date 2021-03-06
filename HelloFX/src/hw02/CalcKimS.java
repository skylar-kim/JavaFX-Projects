package hw02;
// Name: Skylar Kim
// HW02: Converter

import javafx.application.Application;
import javafx.application.Application;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.util.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class CalcKimS extends Application {
	
	// string builder for building input
	StringBuilder sb = new StringBuilder();
	double result = 0;
	// Buttons declared here so eventhandlers can access them
	Button inputButton = new Button();
	Button outputButton = new Button();
	Button lbTokg;
	Button kgTolb;
	Button fToc;
	Button cTof;
	// boolean to tell us if we need to clear the IO or not
	boolean clearScreen = false;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		VBox window = new VBox();
		Scene scene = new Scene(window, 700, 500);
		stage.setTitle("Converter");
		stage.setScene(scene);
		stage.show();
		
		stage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		IoMenu ioMenu = new IoMenu();
		ConverterMenu converterMenu = new ConverterMenu();
		
		// window styling
		window.setPadding(new Insets(75,100,75,100));
		window.setStyle("-fx-background-color: #F2AEB4;");
		
		
		window.getChildren().addAll(ioMenu, converterMenu);
	}

	public class IoMenu extends HBox {
		public IoMenu() {
			makeIO();
		}

		public void makeIO() {

			inputButton.setText("");
			outputButton.setText("");
			
			// button styling
			inputButton.setPrefSize(250, 70);
			inputButton.setFont(new Font("Arial", 20));
			String inputStyle = "-fx-background-color: #F2CED1; "
					+ "-fx-border-color: #261413;";
			inputButton.setStyle(inputStyle);

			outputButton.setPrefSize(250, 70);
			outputButton.setFont(new Font("Arial", 20));
			outputButton.setStyle("-fx-background-color: #F2CED1; -fx-border-color: #261413");
			

			getChildren().addAll(inputButton, outputButton);
		}

	}

	public class ConverterMenu extends HBox {
		public ConverterMenu() {
			makeNumPad();
			makeConverterButton();
		}

		public void makeNumPad() {
			GridPane grid = new GridPane();
			// arraylist to store buttons for easy access
 			ArrayList<Button> buttonList = new ArrayList<>();
 			// this is a dictionary to pass the correct string for each numpad
 			String[] dictionary = new String[] {"-",".",
					"0","1","2","3","4","5","6","7","8","9"};
 			
 			// instantiating buttons w/correct text and adding to arraylist
 			for (int i = 0; i < dictionary.length; i++) {
 				Button button = new Button(dictionary[i]);
 				buttonList.add(button);
 			}
 			
			// instantiating and adding event handlers for each button
			for (int i = 0; i < dictionary.length; i++) {
				NumpadBtnHandler bh = new NumpadBtnHandler(buttonList.get(i), dictionary[i]);
				buttonList.get(i).addEventHandler(ActionEvent.ANY, bh);
			}

			// Button Styling
			for (Button button: buttonList) {
				button.setPrefSize(100, 70);
				button.setFont(new Font("Arial", 20));
			}


			// add num pad buttons to grid
			// this one I didn't do in a for loop because of the exact grid placements
			grid.add(buttonList.get(9), 0, 0);
			grid.add(buttonList.get(10), 1, 0);
			grid.add(buttonList.get(11), 2, 0);
			
			grid.add(buttonList.get(6), 0,1);
			grid.add(buttonList.get(7), 1, 1);
			grid.add(buttonList.get(8), 2, 1);
			
			grid.add(buttonList.get(3), 0, 2);
			grid.add(buttonList.get(4), 1, 2);
			grid.add(buttonList.get(5), 2, 2);
			
			grid.add(buttonList.get(0), 0, 3);
			grid.add(buttonList.get(1), 1, 3);
			grid.add(buttonList.get(2), 2, 3);

			getChildren().add(grid);

		}
		
		public class NumpadBtnHandler implements EventHandler<ActionEvent> {
			Button b;
			String n;

			public NumpadBtnHandler(Button btn, String num) {
				b = btn;
				n = num;
			}

			@Override
			public void handle(ActionEvent event) {
				try {
					if (clearScreen) {
						inputButton.setText("");
						outputButton.setText("");
						clearScreen = false;
					}
					sb.append(n);
					inputButton.setText(sb.toString());

				} catch (Exception e) {

				}

			}
		}

		public void makeConverterButton() {
			VBox vbox = new VBox();
			
			// Button instantiation
			lbTokg = new Button("lb to kg");
			kgTolb = new Button("kg to lb");
			fToc = new Button("F to C");
			cTof = new Button("C to F");

			// Button Handlers
			lbTokgBtnHandler lbtokgBtnHandler = new lbTokgBtnHandler();
			lbTokg.addEventHandler(ActionEvent.ANY, lbtokgBtnHandler);

			kgTolbBtnHandler kgtolbBtnHandler = new kgTolbBtnHandler();
			kgTolb.addEventHandler(ActionEvent.ANY, kgtolbBtnHandler);

			fTocBtnHandler ftocBtnHandler = new fTocBtnHandler();
			fToc.addEventHandler(ActionEvent.ANY, ftocBtnHandler);

			cTofBtnHandler ctofBtnHandler = new cTofBtnHandler();
			cTof.addEventHandler(ActionEvent.ANY, ctofBtnHandler);

			// Button Styling
			lbTokg.setPrefSize(200, 70);
			lbTokg.setFont(new Font("Arial", 20));
			
			kgTolb.setPrefSize(200, 70);
			kgTolb.setFont(new Font("Arial", 20));
			
			fToc.setPrefSize(200, 70);
			fToc.setFont(new Font("Arial", 20));
			
			cTof.setPrefSize(200, 70);
			cTof.setFont(new Font("Arial", 20));

			vbox.getChildren().addAll(lbTokg, kgTolb, fToc, cTof);

			getChildren().add(vbox);

		}

		public class lbTokgBtnHandler implements EventHandler<ActionEvent> {
			Button b;

			public lbTokgBtnHandler() {
				b = lbTokg;
			}

			@Override
			public void handle(ActionEvent event) {
				try {
					result = Double.parseDouble(sb.toString());
					result *= 0.45359237;
					outputButton.setText(Double.toString(result));
					clearScreen = true;
					sb.setLength(0);
				} catch (Exception e) {
					System.out.println("Exception caught: " + e.getMessage());
					inputButton.setText("");
					sb.setLength(0);

				}

			}
		}

		public class kgTolbBtnHandler implements EventHandler<ActionEvent> {
			Button b;

			public kgTolbBtnHandler() {
				b = kgTolb;
			}

			@Override
			public void handle(ActionEvent event) {
				try {
					result = Double.parseDouble(sb.toString());
					result *= 2.2046226218;
					outputButton.setText(Double.toString(result));
					clearScreen = true;
					sb.setLength(0);
				} catch (Exception e) {
					System.out.println("Exception caught: " + e.getMessage());
					inputButton.setText("");
					sb.setLength(0);

				}

			}
		}

		public class fTocBtnHandler implements EventHandler<ActionEvent> {
			Button b;

			public fTocBtnHandler() {
				b = fToc;
			}

			@Override
			public void handle(ActionEvent event) {
				try {
					result = Double.parseDouble(sb.toString());
					result = (result - 32.0) * (5.0 / 9.0);
					outputButton.setText(Double.toString(result));
					clearScreen = true;
					sb.setLength(0);
				} catch (Exception e) {
					System.out.println("Exception caught: " + e.getMessage());
					inputButton.setText("");
					sb.setLength(0);

				}

			}
		}

		public class cTofBtnHandler implements EventHandler<ActionEvent> {
			Button b;

			public cTofBtnHandler() {
				b = fToc;
			}

			@Override
			public void handle(ActionEvent event) {
				try {
					result = Double.parseDouble(sb.toString());
					result = (result * 1.8) + 32.0;
					outputButton.setText(Double.toString(result));
					clearScreen = true;
					sb.setLength(0);
				} catch (Exception e) {
					System.out.println("Exception caught: " + e.getMessage());
					inputButton.setText("");
					sb.setLength(0);

				}

			}
		}

	}

}
