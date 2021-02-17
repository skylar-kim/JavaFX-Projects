package mousing;
// Boxer5.java
// 2018 Barrett Koster
// mouse drag makes box , is visible while dragging 
 
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

public class Boxer5 extends Application
{
    Pane root; // all the stuff in the window attaches here
    Point[] corners = new Point[2];
    Rectangle r;
    //Line r;
   
    boolean mouseIsPressed = false;
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Mouse Hello");
        root = new Pane();
        Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();
        
	    // mouse pressed makes the box
        root.addEventHandler
        (  MouseEvent.MOUSE_PRESSED,
           (MouseEvent m) ->
           {
               corners[0] = new Point( m );
               r = new Rectangle(10,10);
               r.setX(corners[0].getX());
               r.setY(corners[0].getY());
               root.getChildren().add(r);
               mouseIsPressed = true;
           }
        );
        
        // mouse released does nothing (except note that mouse is up)
        root.addEventHandler
        (  MouseEvent.MOUSE_RELEASED,
           (MouseEvent m) ->
           {
               mouseIsPressed = false;
           }
        );
     
        root.addEventHandler
        (  MouseEvent.MOUSE_DRAGGED,
           (MouseEvent m)->
           {   
               corners[1] = new Point( m );
               double xdif = corners[0].xdif(corners[1]);
               double ydif = corners[0].ydif(corners[1]);
               r.setWidth(xdif);
               r.setHeight(ydif);
               // put these lines back in to get tracking in all 4 quadrants
               r.setX(min( corners[0].getX(), corners[1].getX() ));
               r.setY(min( corners[0].getY(), corners[1].getY() ));
           }
        );
    }
    public double min( double x, double y)
    {
    	return (x<y)? x : y;
    }
}
