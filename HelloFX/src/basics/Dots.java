package basics;
// Dots.java
// 2018 Barrett Koster
// demo of drawing Circles, random position, color gradient
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;

public class Dots extends Application
{
    Random r;
	
	public static void main( String[] args )
	{ launch(args);}
	
	// is called once when object is created
	public void init()
	{
        r = new Random();
	}
	
    @Override
    public void start(Stage stage)
    {  
        stage.setTitle("Dots");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
        
        // make multiple dots with colors depending on their coordinate placement
        for ( int i=0; i<400; i++ )
        {      	
            Circle dotty = new Circle(50);
            int cx = r.nextInt(500);
            int cy = r.nextInt(500);
            
            dotty.setCenterX(cx);
            dotty.setCenterY(cy);
            double red = 0;
            double green = dotty.getCenterX()/500.0; // green to the right
            double blue = dotty.getCenterY()/500.0; // blue to the bottom
          
            dotty.setFill( new Color ( red, green, blue, 1)  );
            //if ( cx<200 || cx>300 )
            {root.getChildren().add(dotty);}
        }
    }
}
