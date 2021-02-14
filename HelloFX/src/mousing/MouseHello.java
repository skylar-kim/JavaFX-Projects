package mousing;
// MouseHello.java
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

public class MouseHello extends Application
{
    Pane root; // all the stuff in the window attaches here
    
	public static void main( String[] args )
	{ launch(args); }
	
   @Override
   public void start(Stage stage)
   { 
      stage.setTitle("Mouse Hello");
      root = new FlowPane();
      Scene scene = new Scene(root, 200, 200);
 	        stage.setScene(scene);
	        stage.show();

        
        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, 
           (MouseEvent m)->
           {
               Label b = new Label();
               b.setText("x="+m.getX()+" y="+m.getY()+" ");
               root.getChildren().add(b);
           }
                            );
    
        //root.setOnMouseClicked( (MouseEvent m)->{} );

    }
   
}
