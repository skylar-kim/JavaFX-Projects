package basics;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MyCircleApp extends Application
{
   public static void main( String[] args )
   {
	   launch(args );
   }
   
   @Override
   public void start( Stage stage )
   {
      stage.setTitle("MyCircleApp");
	  Group root = new Group(); // root is a type of pane
	  Scene scene = new Scene(root, 400, 300); 
	  stage.setScene(scene); // put the scene into the stage
	  stage.show();
	  stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );

	  
      Circle circ = new Circle(200,100,50);
      circ.setFill( new Color( 0.2,0.3,0.9,1) );
      
      // root.getChildren() gets a list, so you can add/append like you can as
      // a normal Java object
	  root.getChildren().add(circ); // put the circle obj to the children of root
   }
}