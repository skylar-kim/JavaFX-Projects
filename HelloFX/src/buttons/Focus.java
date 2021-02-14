// Barrett Koster 2020
// Demo of how to do focus on Shapes (Circles in this case).

package buttons;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Focus extends Application
{
	Pane root; // place to draw some dots
	int maxid = 1; // give each an id so we can tell it's working, start at 1
	int inFocus = 0; // the number of the Dot in focus
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    {   
    	// the usual 5 lines
        stage.setTitle("Focus demo");
        root = new Pane();
        Scene scene = new Scene(root, 600, 400);
	    stage.setScene(scene);
	    stage.show();
	    
	    // make 5 dots, spaced out
	    for ( int i=0; i<5; i++ )
	    {
	       new Dot( 100+60*i,100 );
	    }
	    
	    // make a button to verify that we can do something with the 
	    // dot that is in focus
	    Button b = new Button("do stuff");
	    root.getChildren().add(b);
	    b.setOnAction
	    ( (ActionEvent e)->
	      {   System.out.println("use dot "+inFocus );  }
	    );
    }
    
    
    // Dot, does focus, it's really a Circle with and id and hanler added
    public class Dot extends Circle
    {
    	int id;
    	public int getIdd(){ return id; }
        // This makes a dot which can be focused on.
	    Dot( int x, int y )
	    {
	    	super(x,y,30);
	    	
	    	// it's a circle, with some color, added to root
		    setPastel(this);
		    root.getChildren().add(this);
	
		    // set it so the system knows you can focus on it.
		    // You can see from the print out that this works, but this
		    // does not make it visible in the GUI.
		    setFocusTraversable(true);
		    
		    // This sets a border on the circle you can turn on and off.
		    setStrokeType(StrokeType.INSIDE);
		    setStroke(Color.BLACK);
		    
		    // put a number label on each so we can see it is working
		    id = maxid++;
		    Text t = new Text(""+id);
		    t.setX(x+10);
		    t.setY(y+10);
		    root.getChildren().add(t);
		    
		    // listens for change in focus state
		    // Print where the focus is, also turn the border on or off as appropriate.
		    focusedProperty().addListener
		    ((observalbe, oldState, newState) -> 
		       {
		    	  if ( newState )
		    	  {
		    	     System.out.println("focus now on  " + t.getText() );
		    	     setStrokeWidth(2);
		    	     inFocus = Integer.parseInt( t.getText() );
		          }
		    	  else
		    	  {   setStrokeWidth(0);
		    	
		    	  }
		       }
		    );
	    }
    }
    
    void setPastel( Circle c1 )
    {
       	double r = 0.5 + Math.random()*0.5;
       	double g = 0.5 + Math.random()*0.5;
       	double b = 0.5 + Math.random()*0.5;
       	c1.setFill( new Color(r,g,b,1) );
    }

}
