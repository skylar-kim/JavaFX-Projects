package L06;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;

public class LightsOutKimS extends Application {
	int numBox;
	LightBox[] board;
	VBox root;
	boolean[] lightson;
	
	public static void main( String[] args )
	{ launch(args); }
	
	@Override
    public void start(Stage stage)
    {   root = new VBox();
        stage.setTitle("Lights Out Lab");
        Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();
	    
	    UserInput userinput = new UserInput();
	    
	    
	    root.getChildren().add(userinput);

    }
	
	
	
	public class UserInput extends Pane {
		public UserInput() {
			makeTextBox();
		}
		
		public void makeTextBox() {
			Label label = new Label("Input a number: ");
			TextField input = new TextField();
			
			input.setOnAction(e -> {
				numBox = Integer.parseInt(input.getText());
				System.out.println(numBox);
				makeBoxes();
			});
			
			lightson = new boolean[numBox];
			
			getChildren().add(label);
			getChildren().add(input);
			
		}
	}
	
	public void makeBoxes() {
		
	    board = new LightBox[numBox];
	    
		for (int i = 0; i < numBox; i++) {

			board[i] = new LightBox(i);
			root.getChildren().add(board[i]);

		}
		
//		root.getChildren().add(pane);
	    
	}
	
	public class LightBox extends Rectangle {
		int myI;
    	int myJ;
    	double leftOffset = 60; // space on left of boxes
    	double topOffset = 20; // space above set of boxes
    	double hSpacing = 100; // horizontal spacing of boxes
    	double vSpacing = 85; // vertical spacing of boxes
    	
    	boolean isLightOn = true;
    	
		public LightBox(int i) {
			super(50,50);
			myI = i;
//			myJ = j;
//			
			setLayoutX( leftOffset + (hSpacing)*i );
    		setY( topOffset + vSpacing );
    		setLightsOn(this.isLightOn);
    		
    		// each square has an event handler 
    		addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent m)->
    		{ 
    			System.out.println("hey, i=" + myI);
    			setLightsOn(!this.isLightOn);
    			this.isLightOn = !this.isLightOn;
    		});
		}
		
		
		public void setLightsOn( boolean isLightOn)
        {
			if (isLightOn) {
				Color color = Color.rgb(255, 255, 0);
	    		setFill( color );
			} else {
				Color color = Color.rgb(220, 220, 220);
	    		setFill( color );
			}
			
        } 
	}
	
	
}
