package layouts;
// LalyoutDemo.java
// 2018 Barrett Koster
// shows some of the layouts for JavaFX
 
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.text.*;
import java.util.*;

public class LayoutDemo extends Application
{
    Random randy = new Random(); // used for random colors
    int count=0; // numbers all of the Buttons
    
    // runs the program
    public static void main( String[] args )
    { launch(args);}
	
    @Override
    public void start(Stage stage)
    {    
        // overall layout is a grid, ends up being 2x2
        // because of the add calls below
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Layouts Demo");
	    stage.setScene(scene);
	    stage.show();

        // methods doAWhatever() are coded below, to demo
        // various types.  The are all added to the grid,
        // to be seen all at once.
	    // 20 is the num of buttons in Flow Pane
        root.add(doAFlowPane(20), 0, 0);
        // 10 is the num of buttons in VBox
        root.add(doAVBox(10), 0, 1);
        // root.add(doAHBox(10), 1, 0);
        root.add( new IsAnHBox(10),1,0);
        root.add(doABorderPane(), 1, 1);
        
     
    }
    
    // makes a FlowPane with numButtons Buttons in it.
    // Color is random pastel so we can see where it is.
    public FlowPane doAFlowPane( int numButtons)
    {
        FlowPane pane = new FlowPane();
        pane.setStyle("-fx-background-color: #"+pastel()+";");
      
        
        // loop to stick a lot of buttons
        Button btn;
        for ( double i=0; i<numButtons; i++ )
        {
            // add a button with number one more than 
            // previous button.  (see method below)
    	    pane.getChildren().add(counterButton());
        }

        return pane;
    }
    
    /**
     * make a pane with buttons in it, oriented vertically
     * @param numButtons how many buttons
     * @return the VBox pane
     */
    public VBox doAVBox(int numButtons)
    {
        VBox pane = new VBox();
        pane.setStyle("-fx-background-color: #"+pastel()+";");
        
        // run a loop to put buttons in
        Button btn;
        for ( double i=0; i<numButtons; i++ )
        {
    	   pane.getChildren().add(counterButton());
        }

        return pane;
    }
    
    /**
     * make a pane with buttons in it, oriented horizontally
     * @param numButtons how many buttons
     * @return the HBox pane
     */
    public HBox doAHBox(int numButtons)
    {
        HBox pane = new HBox();
        pane.setStyle("-fx-background-color: #"+pastel()+";");
        
        Button btn;
        for ( double i=0; i<numButtons; i++ )
        {
    	   pane.getChildren().add(counterButton());
        }

        return pane;
    }
    
    public class IsAnHBox extends HBox
    {
    	public IsAnHBox( int numButtons)
    	{
            setStyle("-fx-background-color: #"+pastel()+";");
        
	        //Button btn;
	        for ( double i=0; i<numButtons; i++ )
	        {
	    	   getChildren().add(counterButton());
	        }
    	}
    }
    
    // make a BorderPane with a different colored pane
    // in each of its 5 regions.  
    /**
     * make a BorderPane with a different colored pane
     * in each of its 5 regions, and some buttons in each.  
     * 
     * @return the BorderPane
     */
    public BorderPane doABorderPane()
    {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #"+pastel()+";");
        
        pane.setTop( doAFlowPane(6) );
        pane.setLeft( doAVBox(3) );
        pane.setRight( doAVBox(3) );
        pane.setBottom( doAFlowPane(4) );
        pane.setCenter( doAFlowPane(3) );

        return pane;
    }
    
    // makes a Button (and returns it).  text on the
    // button is a number with a running count for the
    // whole program.
    public Button counterButton()
    {
        Button btn = new Button();
        btn.setText(""+(count++));
        Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 18);
        btn.setFont(ff);
        return btn;
    }
    
    // returns a 6 digit code for a pastel color
    public String pastel()
    {
        String c = "";
        
        for( int i=0; i<3; i++ ) { c += pastel1(); }
       
        return c;
    }
    
    // return a string which is aa, bb, cc, dd, ee, or ff,
    public String pastel1()
    {
        char it = 'a';
        it += randy.nextInt(6);
        String s = ""+ it + it;
        return s;
    }
}
