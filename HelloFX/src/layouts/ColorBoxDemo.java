package layouts;
// ColorBoxDemo.java
// 2018 Barrett Koster
// demo of pane with button in it.  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;


public class ColorBoxDemo extends Application
{
	protected Color theColor;
	protected FlowPane root;

	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("ColorBox demo");
        root = new FlowPane();
        Scene scene = new Scene(root, 500, 500);
	    stage.setScene(scene);
	    stage.show();
        
	    // put a bunch of ColorBox objects in the scene
        for ( int i=0; i<15; i++ )
        {
        	ColorBox cb = new ColorBox( /* this */ );
        	root.getChildren().add(cb);
        }
        
        scene.addEventHandler(ActionEvent.ANY, new ClickHandler() );
   }
    
    public class ClickHandler implements EventHandler<ActionEvent>
    {
    	@Override
        public void handle( ActionEvent e )
        {
        	System.out.println("ClickHandler.handle .... um ...?");
        	setColor( ColorBox.getLastOne().getColor() );
        }
    }
    
    // set the background color of this window to c
    public void setColor( Color c ) 
    { 
    	theColor = c; 
		root.setBackground(new Background(new BackgroundFill(
				c, CornerRadii.EMPTY, Insets.EMPTY
				                                       )));
    }
   
}
