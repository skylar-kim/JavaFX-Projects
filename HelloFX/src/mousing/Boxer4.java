package mousing;
// Boxer4.java
// 2018 Barrett Koster
// mouse drag makes box  
 
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

public class Boxer4 extends Application
{
    Pane root; // all the stuff in the window attaches here
    Point[] corners = new Point[2];
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Boxer4, drag makes box");
        root = new Pane();
        Scene scene = new Scene(root, 300, 300);
	    stage.setScene(scene);
	    stage.show();
        
        root.addEventHandler
        (  MouseEvent.MOUSE_PRESSED,
           (MouseEvent m) ->
           {
               corners[0] = new Point( m );
           }
        );
     
        root.addEventHandler
        (  MouseEvent.MOUSE_RELEASED, 
           (MouseEvent m)->
           {               
               corners[1] = new Point( m );
               double xdif = corners[0].xdif(corners[1]);
               double ydif = corners[0].ydif(corners[1]);
               Rectangle r = new Rectangle(xdif,ydif);
               r.setX(corners[0].getX());
               r.setY(corners[0].getY());
               root.getChildren().add(r);
               
               System.out.println("click at x="
                    +m.getX()+" y="+m.getY()+" ");
           }
        );
    }
}
