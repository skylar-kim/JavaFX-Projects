package hw03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DealKim extends Application {
	Random rand = new Random();
	Scene scene;
	Stage st;
	VBox root;
	
	
	List<Double> moneyTableList;
	List<Label> suitcaseList = new ArrayList<>();
	List<Integer> suitcaseMoneyList = new ArrayList<>();
	Set<Integer> visited = new HashSet<>();
	Set<Integer> removedSuitcaseIdx = new HashSet<>();
	Map<Label, Integer> suitcaseMap;
	List<Label> moneyLabel = new ArrayList<>();
	Map<Integer, Label> moneyLabelMap = new HashMap<>();
	
	boolean chosenSuitcase = false;
	int chosenSuitcaseIdx = 0;
	int suitcaseLeft = 10;
	double offer;
	boolean dealChoice = false;
	boolean promptDealChoice = false;
	String moneyTableStyleFilled = "-fx-font-size:20px; -fx-border-color:black; -fx-padding: 5px 6px; -fx-background-color:pink;";
	
	Label offerLabel;
	Label prompt;
	Label chosenSuitcaseOutput;
	Label earningsLabel;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		root = new VBox();
		stage.setTitle("Deal or No Deal");
		scene = new Scene(root, 700,700);
		stage.setScene(scene);
		stage.show();
		st = stage;
		
		root.setStyle("-fx-background-color: white;");
		offer = 0;
		Dealer dealerBox = new Dealer();
		moneyTableList = new ArrayList<>();
		
		MoneyTable moneytable = new MoneyTable();
		
		ChosenSuitcase io = new ChosenSuitcase();
		
		Suitcase suitcases = new Suitcase();
		
		DealButtons dealbuttons = new DealButtons();
		
		Earnings earnings = new Earnings();
		
		root.getChildren().addAll(dealerBox, moneytable, io, suitcases, dealbuttons, earnings);
		
		
	}
	
	public class Dealer extends GridPane {
		
		public Dealer(){
			offer = 0;
			setPrefHeight(100);
			setPadding(new Insets(10));
			
			
			Label label = new Label();
			label.setText("Dealer's Offer: ");
			label.setStyle("-fx-font-size:44px;");
			
			offerLabel = new Label();
			offerLabel.setText("");
			offerLabel.setStyle("-fx-font-size:44px;");
			
			add(label, 0, 0);
			add(offerLabel, 1, 0);
		}
	}
	
	public class Earnings extends GridPane{
		public Earnings() {
			setPadding(new Insets(10));
			setHgap(5);
			setVgap(20);
			
			Label label = new Label();
			label.setText("Your Earnings: ");
			label.setStyle("-fx-font-size:35px;");
			
			earningsLabel = new Label();
			earningsLabel.setText("");
			earningsLabel.setStyle("-fx-font-size:35px;");
			
			add(label, 0,0);
			add(earningsLabel, 1,0);
		}
	}
	
	public class MoneyTable extends GridPane {
		String moneyTableStyle = "-fx-font-size:20px; -fx-border-color:black; -fx-padding: 5px 6px;";
		public MoneyTable() {
			
			setPadding(new Insets(5));
			setHgap(11);
			setVgap(10);
			
			String[] amounts = {"$1", "$5", "$10", "$100", "$1000", "$5000",
					"$10K", "$100K", "$500K", "$1M"};
			int[] assignedamt = {1,5,10,100,1000,5000,10000,100000,500000,1000000};
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < amounts.length; j++) {
					Label label = new Label(amounts[j]);
					label.setStyle(moneyTableStyle);
					moneyLabelMap.put(assignedamt[j], label);
					moneyLabel.add(label);
					add(label, j, 0);
				}
				
			}
			
		
		}
	}
	public class ChosenSuitcase extends GridPane {
		String style0 = "-fx-font-size:20px; -fx-padding: 20px 10px;";
		public ChosenSuitcase() {
			setPadding(new Insets(10));
			setHgap(20);
			setVgap(10);
			prompt = new Label();
			prompt.setText("Choose Your Suitcase");
			prompt.setStyle(style0);
			add(prompt, 0,0);
			
			chosenSuitcaseOutput = new Label();
			chosenSuitcaseOutput.setText("");
			chosenSuitcaseOutput.setStyle(style0);
			add(chosenSuitcaseOutput, 1,0);
			
			
		}
		
		
	}
	
	public class Suitcase extends GridPane {
		
		String style = "-fx-font-size:20px; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		String focusstyle = "-fx-font-size:20px; -fx-border-color: pink; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		String invisible = "-fx-text-fill: white;-fx-font-size:20px; -fx-border-color: white; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		public Suitcase() {
			suitcaseMap = new HashMap<>();
			
			setPadding(new Insets(10));
			setHgap(20);
			setVgap(10);
			
			int[] assignedamt = {1,5,10,100,1000,5000,10000,100000,500000,1000000};
			int randomIdx = rand.nextInt(10);
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 10; j++) {
					Label box = new Label();
					box.setText("Suitcase "+Integer.toString(j+1));
					box.setFocusTraversable(true);
					box.setStyle(style);
					
					while (visited.contains(randomIdx)) {
						randomIdx = rand.nextInt(10);
					}
					visited.add(randomIdx);
					suitcaseMap.put(box, assignedamt[randomIdx]);
					suitcaseList.add(box);
					suitcaseMoneyList.add(assignedamt[randomIdx]);

				}
			}
			

			for (int i = 0; i < 5; i++) {
				add(suitcaseList.get(i), i, 1);
			}
			for (int i = 5; i < 10; i++) {
				add(suitcaseList.get(i), i-5, 2);
			}
			
			scene.setOnKeyReleased(e->{
				focusCheck();
			});
			
			//SuitcaseHandler sh = new SuitcaseHandler();
			//scene.addEventHandler(MouseEvent.MOUSE_CLICKED, sh);
			scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent m) -> {
				int eraseSuitcaseIdx = clickAt(m.getX(), m.getY());
				suitcaseAction(eraseSuitcaseIdx);
			});
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
				if (ke.getCode() == KeyCode.ENTER) {
					int eraseSuitcaseIdx = 0;
					for (int i = 0; i < 10; i++) {
						Label l = suitcaseList.get(i);
						if (l.isFocused() && !chosenSuitcase) {
							// this is the suitcase the player chooses in the beginning
							chosenSuitcaseIdx = i;
						} else if (l.isFocused() && chosenSuitcase) {
							// this is the suitcase the player chooses to erase
							eraseSuitcaseIdx = i;
						}
					}
					suitcaseAction(eraseSuitcaseIdx);
				} 
				
			});
			
		}
		
//		public class SuitcaseHandler implements EventHandler<MouseEvent> {
//			public SuitcaseHandler() {
//				
//			}
//			
//			@Override 
//			public void handle(MouseEvent m) {
//				int eraseSuitcaseIdx = clickAt(m.getX(), m.getY());
//				suitcaseAction(eraseSuitcaseIdx);
//				System.out.println(eraseSuitcaseIdx);
//				
//			}
//		}
		
		public void suitcaseAction(int eraseSuitcaseIdx) {
			if (!chosenSuitcase) {
				chosenSuitcaseIdx = eraseSuitcaseIdx;
				chosenSuitcase = true;
				suitcaseLeft--;
				System.out.println("Suitcases Left: " + suitcaseLeft);
				System.out.println("Player chose suitcase: " + chosenSuitcaseIdx);
				prompt.setText("Your Suitcase: ");
				chosenSuitcaseOutput.setText("Suitcase " + (chosenSuitcaseIdx+1));
				
				System.out.println("Player's Suitcase: " + (chosenSuitcaseIdx+1) + " value is " + 
				suitcaseMoneyList.get(chosenSuitcaseIdx));
				
				double totalLeft = 0;
				for (int i = 0; i < suitcaseMoneyList.size(); i++) {
					if (i != chosenSuitcaseIdx) {
						totalLeft += suitcaseMoneyList.get(i); 
					}
				}
				offer = (totalLeft / suitcaseLeft) * .90;
				offerLabel.setText("$" + Integer.toString((int)offer));
				promptDealChoice = true;
				
				
			} else if (chosenSuitcase && eraseSuitcaseIdx!= chosenSuitcaseIdx && !promptDealChoice){
				int suitcaseIndex = eraseSuitcaseIdx;
				System.out.println("Want to get rid of this suitcase.");
				System.out.println("Suitcase: " + (suitcaseIndex+1) + " value is " + 
						suitcaseMoneyList.get(suitcaseIndex));
				
				removedSuitcaseIdx.add(suitcaseIndex);
				suitcaseList.get(suitcaseIndex).setStyle(invisible);
				suitcaseLeft--;
				int suitcaseAmount = suitcaseMoneyList.get(suitcaseIndex);
				System.out.println("Suitcase Amount: " + suitcaseAmount);
				//System.out.println("Does moneyLabelMap contain amount? " + moneyLabelMap.containsKey(suitcaseAmount));
				moneyLabelMap.get(suitcaseMoneyList.get(suitcaseIndex)).setStyle(moneyTableStyleFilled);
				//moneyLabel.get(0).setStyle(style + " -fx-background-color:pink;");
				//moneyLabel.get(suitcaseIndex).setStyle();
				System.out.println("Suitcases Left: " + suitcaseLeft);
				
				double totalLeft = 0;
				for (int i = 0; i < suitcaseMoneyList.size(); i++) {
					if (i != chosenSuitcaseIdx && !removedSuitcaseIdx.contains(i)) {
						//System.out.println(suitcaseMoneyList.get(i));
						totalLeft += suitcaseMoneyList.get(i); 
					}
				}
				
				offer = (totalLeft / suitcaseLeft) * .90;
				offerLabel.setText("$" + Integer.toString((int)offer));
				promptDealChoice = true;
			}
			
			if (suitcaseLeft == 0){
				System.out.println("No more suitcases, must end game.");
				earningsLabel.setText("$" + suitcaseMoneyList.get(chosenSuitcaseIdx));
			} 
		}
		
		public void focusCheck() {
			
			for (int i = 0; i < 10; i++) {
				Label l = suitcaseList.get(i);
				if (l.isFocused() && !removedSuitcaseIdx.contains(i)) {
					System.out.println("Focus on Suitcase: " + (i+1));
					l.setStyle(focusstyle);
				} else if (!l.isFocused() && !removedSuitcaseIdx.contains(i)){
					l.setStyle(style);
				} else {
					l.setStyle(invisible);
				}
			}
			st.show();
		}
		
		public int clickAt(double x, double y) {
			if (y >= 255 && y <= 320) {
				if (x >= 13.0 && x <= 118) {
					System.out.println("clicked on suitcase 1");
					return 0;
				}
				else if (x >= 144 && x <= 254) {
					// suitcase 2
					System.out.println("clicked on suitcase 2");
					return 1;
				}
				else if (x >= 277 && x <= 386) {
					System.out.println("clicked on suitcase 3");
					return 2;
				}
				else if (x >= 409 && x <= 518) {
					System.out.println("clicked on suitcase 4");
					return 3;
				}
				else if (x >= 542 && x <= 650) {
					System.out.println("clicked on suitcase 5");
					return 4;
				}
			}
			else if (y >= 355 && y <= 403) {
				if (x >= 13.0 && x <= 118) {
					System.out.println("clicked on suitcase 6");
					return 5;
				}
				else if (x >= 144 && x <= 254) {
					// suitcase 2
					System.out.println("clicked on suitcase 7");
					return 6;
				}
				else if (x >= 277 && x <= 386) {
					System.out.println("clicked on suitcase 8");
					return 7;
				}
				else if (x >= 409 && x <= 518) {
					System.out.println("clicked on suitcase 9");
					return 8;
				}
				else if (x >= 542 && x <= 661) {
					System.out.println("clicked on suitcase 10");
					return 9;
				}
			}
			
			return 0;
		}
	}
	
	
	
	public class DealButtons extends GridPane {
		String style = "-fx-font-size:20px; -fx-border-color:black; -fx-padding: 20px 10px;";
		public DealButtons() {
			setPadding(new Insets(70));
			setHgap(150);
			setVgap(10);
			
			Button dealButton = new Button();
			dealButton.setText("DEAL");
			dealButton.setStyle(style);
			dealButton.setPrefWidth(200);
			dealButton.setFocusTraversable(true);
			add(dealButton, 0,0);
			dealButton.setOnAction(e->{
				if (promptDealChoice) {
					System.out.println("Deal Button Pressed when supposed to.");
					promptDealChoice = false;
					dealChoice = true;
					// print out how much money the person earned i guess
					earningsLabel.setText("$" + (int)offer);
				}
				
			});
			
			
			
			Button nodealButton = new Button();
			nodealButton.setText("NO DEAL");
			nodealButton.setStyle(style);
			nodealButton.setPrefWidth(200);
			nodealButton.setFocusTraversable(true);
			add(nodealButton, 1,0);
			nodealButton.setOnAction(e->{
				if (promptDealChoice) {
					System.out.println("No Deal Button Pressed when supposed to.");
					promptDealChoice = false;
					dealChoice = false;
				}
				
			});
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
				System.out.println("Filter key code: " + ke.getCode());
				
				if (ke.getCode() == KeyCode.D) {
					if (promptDealChoice) {
						System.out.println("d was pressed when supposed to");
						// same code as dealButton's action
						promptDealChoice = false;
						dealChoice = true;
						earningsLabel.setText("$" + (int)offer);
					}
					
					
				} else if (ke.getCode() == KeyCode.N) {
					System.out.println("n was pressed");
					// same code as No deal button's action
					System.out.println("No Deal Button Pressed when supposed to.");
					promptDealChoice = false;
					dealChoice = false;
				}
			});
		}
		
	}
	
	
	
}









