package buttons;
// ButtonDetail.java
// 2018 Barrett Koster
// demo to show how a button works  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.*;

public class LambdaDetail extends Application
{
    Pane root; // all the stuff in the window attaches here
    Random randy;
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        randy = new Random();
        root = new FlowPane();
        
        // put in a button to quit
        Button btn = new Button();
        btn.setText("quit");
        btn.setOnAction(
           (ActionEvent event) -> {System.exit(0);} 
                       );
        root.getChildren().add(btn);

        // make a button that adds more buttons.
        // This one calls a defined EH class object
        Button b1 = new Button();
        b1.setText("add1");
        root.getChildren().add(b1);
        EH myHandler = new EH();  // EH defined below
        b1.setOnAction( myHandler );
        
        // This one uses a lambda expression to bypass all
        // of the obvious stuff.  
        Button b2 = new Button();
        b2.setText("change color");
        root.getChildren().add(b2);
        b2.setOnAction( 
           (ActionEvent ae ) -> 
           {
               root.setStyle("-fx-background-color: #"+pastel()+";");
           }
                      );

        Scene scene = new Scene(root, 200, 200);

        stage.setTitle("Quit button");
	        stage.setScene(scene);
	        stage.show();
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
   
   // this handler makes a new button in the window (when called)
   public class EH implements EventHandler<ActionEvent>
   {
       @Override
       public void handle( ActionEvent ae )
       {
           Button b = new Button();
           b.setText("x");
           root.getChildren().add(b);
       }
   }
   

}
