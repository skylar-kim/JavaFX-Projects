package buttons;
// KeyDemo2.java
// 2020 Barrett Koster
// demo of pane with keyboard handler
// ... and we are trying to let it use button focus as well.
// ENTER does it explicitly, but apparently SPACE -> click
// is already in there.
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*; // <----!!!!!!
import javafx.stage.*;

public class KeyDemo2 extends Application
{
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Key Demo 2");
        HBox root = new HBox();
        Scene scene = new Scene(root, 200, 200);
	    stage.setScene(scene);
	    stage.show();
	    
	    // bunch of buttons, when you click on one
	    // it prints its name to console.  
	    for ( int i=0; i<5; i++ )
	    {
	    	Button b = new Button("B"+i);
	    	root.getChildren().add( b );
	    	final int ii = i; // ii is effectively final, for use below
	    	b.setOnAction( (ActionEvent e) ->
	    	{ System.out.println("clicked: "+b.getText()+" "+ii); });
	    }
	    
	    // set a filter.  mostly this is just the same as the 
	    // handler, but the space bar can be eaten by the button
	    // handler (and never seen by the handler below), so ...
	    // putting a FILTER here can catch it before that.
	    // Note: long form (2 arg) needed, still can use
	    // lambda on the 2nd ag.
	    scene.addEventFilter
	    ( KeyEvent.KEY_PRESSED, (KeyEvent ke) ->
	       { System.out.println("filter key code:"+ke.getCode() );  }
	    );
              
	    // add a keyboard handler ..... 
        scene.setOnKeyPressed 
        (  (KeyEvent ke) -> 
           {
        	   // print out the key text and codes, just so we
        	   // can see what it did when you click a key.
           	   System.out.println("text:"+ke.getText());
           	   System.out.println("key code:"+ke.getCode());
           	   
           	   // If you hit the ENTER key, check the Node
           	   // in focus and send it an ActionEvent (as though
           	   // someone actually clicked on it
           	   if ( ke.getCode()==KeyCode.ENTER )
           	   {
                   Node n = scene.getFocusOwner();
                   n.fireEvent(new ActionEvent() );
           	   }
           } 
        );
   }
}
