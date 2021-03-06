package DealKim;

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
import javafx.scene.layout.BorderPane;
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

public class DealorNoDeal extends Application {
	Random rand = new Random();
	Scene scene;
	Stage st;
	VBox root;
	
	// Data Structures to keep track of the actions going on in the program
	List<Label> suitcaseList = new ArrayList<>();
	List<Integer> suitcaseMoneyList = new ArrayList<>();
	Set<Integer> visited = new HashSet<>();
	Set<Integer> removedSuitcaseIdx = new HashSet<>();
	Map<Label, Integer> suitcaseMap;
	List<Label> moneyLabel = new ArrayList<>();
	Map<Integer, Label> moneyLabelMap = new HashMap<>();
	
	
	int chosenSuitcaseIdx = -1;
	int suitcaseLeft = 10;
	double offer = 0;
	boolean chosenSuitcase = false;
	boolean dealChoice = false;
	boolean promptDealChoice = false;
	
	String moneyTableStyleFilled = "-fx-font-size:20px; -fx-border-color:black; -fx-padding: 5px 6px; -fx-background-color:pink;";
	String normalbtnstyle = "-fx-font-size:20px; -fx-border-color:black; -fx-padding: 20px 10px;";
	String promptstyle = "-fx-background-color: yellow; -fx-font-size:20px; -fx-border-color:black; -fx-padding: 20px 10px;";
	String nodealpromptstyle = "-fx-background-color: red; -fx-font-size:20px; -fx-border-color:black; -fx-padding: 20px 10px;";
	
	Label offerLabel;
	Label prompt;
	Label chosenSuitcaseOutput;
	Label earningsLabel;
	Label labelChooseSuitcase;
	
	Button dealButton;
	Button nodealButton;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		root = new VBox();
		stage.setTitle("Deal or No Deal");
		scene = new Scene(root, 700,900);
		stage.setScene(scene);
		stage.show();
		st = stage;
		
		root.setStyle("-fx-background-color: white;");
		offer = 0;
		Dealer dealerBox = new Dealer();
		
		MoneyTable moneytable = new MoneyTable();
		
		ChosenSuitcase io = new ChosenSuitcase();
		
		ChooseASuitcase chooseSuitcasePrompt = new ChooseASuitcase();
		
		Suitcase suitcases = new Suitcase();
		
		DealButtons dealbuttons = new DealButtons();
		
		Earnings earnings = new Earnings();
		
		root.getChildren().addAll(dealerBox, moneytable, io, suitcases, chooseSuitcasePrompt, dealbuttons, earnings);
		
		
	}
	
	public class Dealer extends GridPane {
		
		public Dealer(){
			// This is for displaying the Dealer's offer
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
			// this class is for displaying the player's earnings at the end
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
			// this class displays the suitcases that were eliminated
			
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
			// this class is for displaying the suitcase that the player chose
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
	
	public class ChooseASuitcase extends BorderPane {
		String style1 = "-fx-font-size:40px; -fx-padding: 10px 10px;";
		public ChooseASuitcase() {
			setPadding(new Insets(10));
			labelChooseSuitcase = new Label();
			labelChooseSuitcase.setText("");
			labelChooseSuitcase.setStyle(style1);
			setCenter(labelChooseSuitcase);
		}
	}
	
	public class Suitcase extends GridPane {
		
		String style = "-fx-font-size:20px; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		String focusstyle = "-fx-font-size:20px; -fx-border-color: pink; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		String invisible = "-fx-text-fill: white;-fx-font-size:20px; -fx-border-color: white; -fx-border-width: 2px; -fx-padding: 20px 10px;";
		public Suitcase() {
			// this class is for the suitcase and the main game logic
			suitcaseMap = new HashMap<>();
			
			setPadding(new Insets(10));
			setHgap(20);
			setVgap(10);
			
			// randomly assign the values to the suitcases
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
					// these data structures are helpful for the main game logic
					visited.add(randomIdx);
					suitcaseMap.put(box, assignedamt[randomIdx]);
					suitcaseList.add(box);
					suitcaseMoneyList.add(assignedamt[randomIdx]);

				}
			}
			
			
			
			// add the labels to the gridpane
			for (int i = 0; i < 5; i++) {
				add(suitcaseList.get(i), i, 1);
			}
			for (int i = 5; i < 10; i++) {
				add(suitcaseList.get(i), i-5, 2);
			}
			
			// this is for toggling
			scene.setOnKeyReleased(e->{
				focusCheck();
			});
			
			// event handler for when the suitcases are clicked
			// calls suitcaseAction (the main game logic)
			scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent m) -> {
				int eraseSuitcaseIdx = clickAt(m.getX(), m.getY());
				if (eraseSuitcaseIdx != chosenSuitcaseIdx) {
					suitcaseClickedAction(eraseSuitcaseIdx);
				} 
				
			});
			
			// event filter for when the suitcases are toggled with the keyboard
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
					if (eraseSuitcaseIdx != chosenSuitcaseIdx) {
						suitcaseClickedAction(eraseSuitcaseIdx);
					}
				} 
			});
			
			
			
		}
		public void suitcaseClickedAction(int eraseSuitcaseIdx) {
			dealButton.setStyle(promptstyle);
			nodealButton.setStyle(nodealpromptstyle);
			
			labelChooseSuitcase.setText("");
			
			suitcaseAction(eraseSuitcaseIdx);
		}
		
		
		public void suitcaseAction(int eraseSuitcaseIdx) {
			if (!chosenSuitcase) {
				// this is when the player first chooses their suitcase
				chosenSuitcaseIdx = eraseSuitcaseIdx;
				chosenSuitcase = true;
				suitcaseLeft--;
				
				// Update the board to display the player's first chosen suitcase
				prompt.setText("Your Suitcase: ");
				chosenSuitcaseOutput.setText("Suitcase " + (chosenSuitcaseIdx+1));
				
				// Calculate the dealer's first offer and display
				calculateOffer();
				offerLabel.setText("$" + Integer.toString((int)offer));
				promptDealChoice = true;
				
				
			} else if (chosenSuitcase && eraseSuitcaseIdx!= chosenSuitcaseIdx && !promptDealChoice){
				// this is when the player has chosen their suitcase
				// and now is choosing suitcases to eliminate
				int suitcaseIndex = eraseSuitcaseIdx;
				
				// add the desired suitcase to remove to a set of other removed suitcase idx's
				// so that we don't add removed suitcase values when calculating offer
				removedSuitcaseIdx.add(suitcaseIndex);
				
				// make the removed suitcase invisible to the player
				suitcaseList.get(suitcaseIndex).setStyle(invisible);
				
				// decrement the suitcases available on the board
				suitcaseLeft--;
				
				// set the corresponding money table value as marked
				moneyLabelMap.get(suitcaseMoneyList.get(suitcaseIndex)).setStyle(moneyTableStyleFilled);
				
				calculateOffer();
				offerLabel.setText("$" + Integer.toString((int)offer));
				promptDealChoice = true;
			}
			
			if (suitcaseLeft == 0){
				// if there are no more suitcases, game is over
				earningsLabel.setText("$" + suitcaseMoneyList.get(chosenSuitcaseIdx));
				labelChooseSuitcase.setText("Game Over");
				promptDealChoice = false;
			} 
		}
		
		public void calculateOffer() {
			double totalLeft = 0;
			for (int i = 0; i < suitcaseMoneyList.size(); i++) {
				if (i != chosenSuitcaseIdx && !removedSuitcaseIdx.contains(i)) {
					totalLeft += suitcaseMoneyList.get(i); 
				}
			}
			offer = (totalLeft / suitcaseLeft) * .90;
		}
		
		public void focusCheck() {
			
			for (int i = 0; i < 10; i++) {
				Label l = suitcaseList.get(i);
				if (l.isFocused() && !removedSuitcaseIdx.contains(i)) {
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
					return 0;
				}
				else if (x >= 144 && x <= 254) {
					return 1;
				}
				else if (x >= 277 && x <= 386) {
					return 2;
				}
				else if (x >= 409 && x <= 518) {
					return 3;
				}
				else if (x >= 542 && x <= 650) {
					return 4;
				}
			}
			else if (y >= 355 && y <= 403) {
				if (x >= 13.0 && x <= 118) {
					return 5;
				}
				else if (x >= 144 && x <= 254) {
					return 6;
				}
				else if (x >= 277 && x <= 386) {
					return 7;
				}
				else if (x >= 409 && x <= 518) {
					return 8;
				}
				else if (x >= 542 && x <= 661) {
					return 9;
				}
			}
			return 0;
		}
	}
	
	
	
	public class DealButtons extends GridPane {
		
		public DealButtons() {
			setPadding(new Insets(70));
			setHgap(150);
			setVgap(10);
			
			
			dealButton = new Button();
			dealButton.setText("DEAL");
			dealButton.setStyle(normalbtnstyle);
			dealButton.setPrefWidth(200);
			dealButton.setFocusTraversable(true);
			add(dealButton, 0,0);
			dealButton.setOnAction(e->{
				if (promptDealChoice) {
					dealActions();
				}
				
			});
			
			
			nodealButton = new Button();
			nodealButton.setText("NO DEAL");
			nodealButton.setStyle(normalbtnstyle);
			nodealButton.setPrefWidth(200);
			nodealButton.setFocusTraversable(true);
			add(nodealButton, 1,0);
			nodealButton.setOnAction(e->{
				if (promptDealChoice) {
					nodealActions();
				}
			});
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
				if (ke.getCode() == KeyCode.D) {
					if (promptDealChoice) {
						dealActions();
					}
					
				} else if (ke.getCode() == KeyCode.N) {
					nodealActions();
				}
			});
		}
		
		// Program actions to execute when player hits DEAL
		public void dealActions() {
			dealButton.setStyle(normalbtnstyle);
			nodealButton.setStyle(normalbtnstyle);
			promptDealChoice = false;
			dealChoice = true;
			earningsLabel.setText("$" + (int)offer);
			labelChooseSuitcase.setText("Game Over");
		}
		
		// Program actions to execute when player hits NO DEAL
		public void nodealActions() {
			dealButton.setStyle(normalbtnstyle);
			nodealButton.setStyle(normalbtnstyle);
			
			labelChooseSuitcase.setText("Choose a Suitcase");
			
			promptDealChoice = false;
			dealChoice = false;
		}
		
	}
	
	
	
}









