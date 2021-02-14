package HW00;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Anythingkims extends Application {
	public static void main( String[] args )
	   {
		   launch(args );
	   }
	   
	   @Override
	   public void start( Stage stage )
	   {
	      stage.setTitle("MyCircleApp");
		  Group root = new Group();
		  Scene scene = new Scene(root, 400, 300); 
		  stage.setScene(scene);
		  stage.show();
		  stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );

		  
	      Circle circ = new Circle(100,40,30);
	      circ.setFill( new Color( 1,0,0,1) );
		  root.getChildren().add(circ);
	   }
	
}
