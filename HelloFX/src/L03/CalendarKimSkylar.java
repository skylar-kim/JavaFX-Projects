package L03;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.util.Random;



public class CalendarKimSkylar extends Application{
	
	Random randy = new Random(); // used for random colors
	// runs the program
    public static void main( String[] args )
    { launch(args);}
    
    @Override
    public void start(Stage stage)
    {    
        // overall layout is a grid, ends up being 2x2
        // because of the add calls below
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("Calendar Lab");
	    stage.setScene(scene);
	    stage.show();
	    
	    int date = 1;
	    
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 7; j++) {
        		if (i == 0 && (j >= 0 && j <= 2)) {
        			root.add(doAVBoxWhite(), j, i);
        		}
        		else if (i == 4 && j == 6) {
        			root.add(doAVBoxWhite(), j, i);
        		}
        		else {
        			root.add(doAVBox(Integer.toString(date)),j, i);
        			date++;
        		}
    			
        	}

        }
     
    }
    
    public VBox doAVBox (String date) {
    	VBox pane = new VBox();
    	pane.setStyle("-fx-background-color: #"+pastel()+";");
    	pane.setPrefSize(100,100);
    	pane.setPadding(new Insets(5,5,5,5));
    	
    	Text t = new Text();
    	t.setFont(new Font(20));
    	t.setText(date);
    	Text description = new Text();
    	description.setText("special day :D");
    	
    	pane.getChildren().add(t);
    	pane.getChildren().add(description);
    	
    	return pane;
    }
    
    public VBox doAVBoxWhite () {
    	VBox pane = new VBox();
    	pane.setStyle("-fx-background-color: #fff;");
    	pane.setPrefSize(100,100);
    	
    	return pane;
    }
    
    // From Professor Koster
    public String pastel()
    {
        String c = "";
        
        for( int i=0; i<3; i++ ) { c += pastel1(); }
       
        return c;
    }
    
    public String pastel1()
    {
        char it = 'a';
        it += randy.nextInt(6);
        String s = ""+ it + it;
        return s;
    }
    
    
    
    
    
}
