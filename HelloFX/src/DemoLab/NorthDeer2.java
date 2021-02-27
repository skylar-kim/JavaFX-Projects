package DemoLab;
// NorthPole.java
// 2018 Barrett Koster
// mouse drag makes box , is visible while dragging 
 
import java.util.LinkedList;
import java.util.Random;

//import Point;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import mousing.Point;
import javafx.scene.input.*;
import javafx.scene.shape.*;

public class NorthDeer2 extends Application
{
	Pane root;
    Deer theDeer; // the one you are dragging
    LinkedList<Deer> deerList;
   
    boolean mouseIsPressed = false;
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    {   
        stage.setTitle("woods");
        root = new Pane();
        Scene scene = new Scene(root, 400, 400);
	    stage.setScene(scene);
	    stage.show();
	    
    	Random randy = new Random();
    	deerList = new LinkedList<Deer>();
        for ( int i=0; i<5; i++ )
        {
        	double x1 = 50+randy.nextInt(300);
        	double y1 = 50+randy.nextInt(300);
        	Deer d = new Deer( new Point(x1,y1) ) ;
        	deerList.add( d );
        	root.getChildren().add(d);
        }

	    // mouse pressed makes the box
        root.addEventHandler
        (  MouseEvent.MOUSE_PRESSED,
           (MouseEvent m) ->
           {
               Point p = new Point( m );
               theDeer = findDeer( p );
               mouseIsPressed = true;
           }
        );
        
        // mouse released does nothing (except note that mouse is up)
        root.addEventHandler
        (  MouseEvent.MOUSE_RELEASED,
           (MouseEvent m) ->  { mouseIsPressed = false; }
        );
     
        root.addEventHandler
        (  MouseEvent.MOUSE_DRAGGED,
           (MouseEvent m)->
           {   
               Point p = new Point( m );
               if(theDeer!=null) {theDeer.dragged(p); }
           }
        );
    }
    public double min( double x, double y)
    {
    	return (x<y)? x : y;
    }
    
    public Deer findDeer( Point p )
    {
    	Deer found = null;
    	for ( Deer d : deerList )
    	{
    		if ( d.zatYou( p ) ) { found = d; }
    	}
    	return found;
    }
    
    public class Deer extends Group
    {
       Rectangle r;
       Circle c;
       Point imAt;
       
       public Deer( Point p )
       {
    	   imAt = p;
    	   r = new Rectangle(20,20);
           getChildren().add(r);
           r.setX(0);
           
           c = new Circle( 15 );
           getChildren().add(c);
           c.setCenterX(30);
           
           setLayoutX( p.getX() );
           setLayoutY( p.getY() ); 
       }
       
       public void dragged( Point p )
       {
    	   setLayoutX( p.getX() );
    	   setLayoutY( p.getY() );
    	   imAt = p;
       }
       
       
       public boolean zatYou( Point m )
       {
       	System.out.println("zatYou x="+m.getX()+" y="+m.getY());
       	double d = Math.abs(m.getX()-imAt.getX())
       			  +Math.abs(m.getY()-imAt.getY());
       	boolean ret = (d<50);
       	System.out.println("zatYou ret="+ret);
       	return ret;
       }
    }
}


