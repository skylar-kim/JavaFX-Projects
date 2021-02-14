package mousing;
// Boxer2.java
// 2018 Barrett Koster
// button to make boxes, no size adjustment  
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Boxer2 extends Application
{
   Pane root; // all the stuff in the window attaches here

	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { 
        stage.setTitle("Boxer2, click makes box");
    	root = new Pane();
    	Scene scene = new Scene(root, 200, 200);
    	stage.setScene(scene);
    	stage.show();

        root.addEventHandler
        (  MouseEvent.MOUSE_CLICKED, 
           (MouseEvent m)->
           {               
               Rectangle r = new Rectangle(10,10);
               r.setX(m.getX());
               r.setY(m.getY());
               root.getChildren().add(r);
               
               System.out.println("click at x="+m.getX()+" y="+m.getY()+" ");
           }
        );
   }
}
