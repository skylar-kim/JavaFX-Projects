package L02;

// Name: Skylar Kim

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
//import javafx.scene.control.*; ..........
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TTTKimSkylar extends Application {
	
	protected Button btn;
	boolean isX = true;

	public static void main( String[] args )
	{ launch(args); }
	
	static volatile boolean x = true;
	
	@Override
    public void start(Stage stage)
    {   
		stage.setTitle("Tic Tac Toe");
		FlowPane fp = new FlowPane();
        Scene scene = new Scene(fp, 600,600);
	    stage.setScene(scene);
	    stage.show();
	    
	    stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );
	    
	    
	    
	    for (int i = 0; i < 9; i++) {
	    	btn = new Button();
	    	
//	    	temp.setOnAction((ActionEvent e) -> {
//	    		temp.setText(x ? "X" : "O");
//	    		temp.setStyle(x ? "-fx-background-color: #ffc0cb;" : "-fx-background-color: #cff0ec;");
//	    		x = !x;
//	    	});
	    	
	    	btn.setMaxSize(200,200);
	    	btn.setMinSize(200,200);
	    	fp.getChildren().add(btn);
	    	
	    	ButtonHandler bh = new ButtonHandler();
	    	btn.addEventHandler(ActionEvent.ANY, bh);
	    }
		
		
		
		
    }
	
	public class ButtonHandler implements EventHandler<ActionEvent>
	{
		
		String x = "X";
		String o = "O";
		Button b;
		
		public ButtonHandler() {
			b = btn;
		}
		
		
		@Override
		public void handle(ActionEvent event)
		{
			
			if (isX) {
				b.setText(x);
				b.setStyle( "-fx-background-color: #ffc0cb;");
				isX = !isX;
			}
			else {
				b.setText(o);
				b.setStyle("-fx-background-color: #cff0ec;");
				isX = !isX;
			}
		}
	}
	
	
}
