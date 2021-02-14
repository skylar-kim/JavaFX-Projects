package layouts;
// Stackers.java
// 2020 Barrett Koster
// check out stacking text on a pane, dragging it around
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Stackers extends Application
{
	Pane root;
	
	public static void main( String[] args )
	{ launch(args);}
	
    @Override
    public void start(Stage stage)
    {  
        root = new Pane();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("more buttons ");
	    stage.setScene(scene);
	    stage.show();

	    root.getChildren().add( new BoxPlus(50,100));
   }
    
   public class BoxPlus extends StackPane
   {
	  Rectangle theRectangle;
	  Label theLabel;
	  double x, y; // current layout position of this BoxPlus (Pane)
	  double offx, offy; // where is click in pane
	  
	  public BoxPlus(double x1, double y1 )
	  {
	     x = x1; y = y1; 
	     beThere();
	     
		 theRectangle = new Rectangle(100,100);
		 theRectangle.setFill( Color.PINK);
		 getChildren().add(theRectangle);
		 
		 theLabel = new Label("hi there");
         getChildren().add(theLabel);
         
         addEventHandler( MouseEvent.MOUSE_PRESSED, e->{pressed(e);} );
         addEventHandler( MouseEvent.MOUSE_DRAGGED, e->{dragged(e);} );
	  }
	  
	  // note where the mouse was clicked (offx,offy)
	  public void pressed( MouseEvent m )
	  {
		 System.out.println("box was clicked at x="+m.getX()+" y="+m.getY());
		 offx = m.getX();  offy = m.getY();
	  }
	  
	  // change layout x,y if we move from previous position.
	  public void dragged( MouseEvent m)
	  {
		 x += m.getX() - offx;
		 y += m.getY() - offy;
		 beThere();
	  }
	  
	  public void beThere() { setLayoutX(x); setLayoutY(y); }
   }
}
