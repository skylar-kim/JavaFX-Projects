package mousing;

// Shewt.java
// 2018 Barrett Koster
// demo to show how a button works  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.input.*;


import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;


public class Shewt extends Application
{
    Pane root; // all the stuff in the window attaches here
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        root = new Pane(); //new FlowPane();
        
        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, 
           (MouseEvent m)->
           {
               Circle c = new Circle(5);
               c.setCenterX( m.getX() );
               c.setCenterY( m.getY() );
               root.getChildren().add(c);
               /*
               Label b = new Label();
               b.setText("x="+m.getX()+" y="+m.getY()+" ");
               root.getChildren().add(b);
               */
           }
                            );
    
        Scene scene = new Scene(root, 200, 200);

        stage.setTitle("Mouse Hello");
	        stage.setScene(scene);
	        stage.show();
   }
   
}
