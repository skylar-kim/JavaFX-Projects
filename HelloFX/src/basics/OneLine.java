package basics;
// OneLine.java
// 2018 Barrett Koster
// demo of line drawing, loops, randomness
 
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.shape.*;

public class OneLine extends Application
{
	public static void main( String[] args )
	{ launch(args);}
	
    @Override
    public void start(Stage stage)
    {   
        stage.setTitle("One Line");
    	Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
	    stage.setScene(scene);
	    stage.show();
        
        Line liney = new Line(100,200,300,400 );
        root.getChildren().add(liney);
   }
}
