package mousing;
// Boxer3.java
// 2018 Barrett Koster
// 2 clicks makes a box (corner to corner)  
 
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

public class Boxer3 extends Application
{
   Pane root; // all the stuff in the window attaches here
   Point[] corners = new Point[2];
   int which = 0; // index of next point to make with click (0 or 1)
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Boxer3, 2 clicks makes box");
        root = new Pane();
        Scene scene = new Scene(root, 200, 200);
 	    stage.setScene(scene);
	    stage.show();
     
        root.addEventHandler
        (  MouseEvent.MOUSE_CLICKED, 
           (MouseEvent m)->
           {               
               corners[which] = new Point( m );
               if ( which==0 ) // if on point 0, just wait for point 1
               { which = 1; }
               else if ( which==1 ) // if this is point 1, then draw
               {
                   double xdif = corners[0].xdif(corners[1]);
                   double ydif = corners[0].ydif(corners[1]);
                   Rectangle r = new Rectangle(xdif,ydif);
                   r.setX(corners[0].getX());
                   r.setY(corners[0].getY());
                   root.getChildren().add(r);
                   
                   System.out.println("click at x="
                        +m.getX()+" y="+m.getY()+" ");
                   which= 0; // reset for next pair
                }
           }
        );


   }
   
}
