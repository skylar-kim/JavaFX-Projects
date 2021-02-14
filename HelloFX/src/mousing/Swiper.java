package mousing;
// Swiper.java
// 2020 Barrett Koster
//  
 
//import Point;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;

public class Swiper extends Application
{
    Pane root; // all the stuff in the window attaches here
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Swipe Demo");
        root = new Pane();
        Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();

	    scene.setOnSwipeRight( e->{System.out.println("swipe right");} );

    }

}
