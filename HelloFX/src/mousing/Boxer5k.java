package mousing;
// Boxer5.java
// 2018 Barrett Koster
// mouse drag makes box , is visible while dragging 
// and does the kaleidescope effect
 
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

public class Boxer5k extends Application
{
    Pane root; // all the stuff in the window attaches here
    Point[] corners = new Point[2];
    Rectangle r;
    Boxes theBoxes;
    
    double sizex = 500;
    double sizey = 500;
   
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
        Scene scene = new Scene(root, sizex, sizey);
	    stage.setScene(scene);
	    stage.show();
        
	    // mouse pressed makes the box
        root.addEventHandler
        (  MouseEvent.MOUSE_PRESSED,
           (MouseEvent m) ->
           {
               corners[0] = new Point( m );
               theBoxes = new Boxes( corners[0], root );
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
               theBoxes.dragged(corners[1]);

           }
        );
    }
    public double min( double x, double y)
    {
    	return (x<y)? x : y;
    }
    
    public class Boxes
    {
    	protected double myX, myY; // center coordinates
    	Rectangle[] shadows;
    	Point[] corners;
    	
    	public static final double startW = 10;
    	public Boxes( Point p, Pane thePane)
    	{
    		corners = new Point[2];
    		corners[0] = p;
    		double x = p.getX();
    		double y = p.getY();
    		shadows = new Rectangle[4]; 
    		// where we did click the mouse
    		shadows[0] = new Rectangle(x,y, 10, 10);
    		
    		shadows[1] = new Rectangle(sizex-x-10,y, 10, 10);
    		shadows[2] = new Rectangle(x,sizey-y-10, 10, 10);
    		shadows[3] = new Rectangle(sizex-x-10,sizey-y-10, 10, 10);
    		
    		for ( int i=0; i<4; i++ ) 
    		{ thePane.getChildren().add(shadows[i]); }
    	}
    	
    	public void dragged( Point p1 )
    	{
    		corners[1] = p1;
    		Point p0 = corners[0];
    		double xdif = corners[0].xdif(corners[1]);
    		double ydif = corners[0].ydif(corners[1]);
    		shadows[1].setX( sizex - p0.getX() - xdif );
    		shadows[2].setY( sizey - p0.getY() - ydif );
    		shadows[3].setX( sizex - p0.getX() - xdif );
    		shadows[3].setY( sizey - p0.getY() - ydif );
    		for ( int i=0; i<4; i++ )
    		{
    			shadows[i].setWidth(xdif);
    			shadows[i].setHeight(ydif);
    		}
    	}
    }

}
