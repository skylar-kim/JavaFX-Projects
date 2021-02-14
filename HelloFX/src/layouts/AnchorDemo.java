package layouts;
// LalyoutDemo.java
// 2018 Barrett Koster
// shows some of the layouts for JavaFX
 
import java.util.Random;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class AnchorDemo extends Application
{
    Random randy = new Random(); // used for random colors
    int count=0; // numbers all of the Buttons
    
    // runs the program
    public static void main( String[] args )
    { launch(args);}
	
    @Override
    public void start(Stage stage)
    {    
	    Button b = new Button();
	    b.setText("a");

        AnchorPane root = new AnchorPane();
        root.getChildren().add(b);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Layouts Demo");
	    stage.setScene(scene);
	    stage.show();
	      
	    //AnchorPane.setBottomAnchor(root,0);
	    Button b1 = new Button("upleft");
	    AnchorPane.setTopAnchor(b1,40.0 );
	    root.getChildren().add(b1);
	    AnchorPane.setLeftAnchor(b1,100.0);
	    //root.getChildren().add(new Button("wwef") );
	    //root.getChildren().add(new Button("oii") );
	    //root.getChildren().add(new Button("sdtygh") );
     
    }
  
 
}
