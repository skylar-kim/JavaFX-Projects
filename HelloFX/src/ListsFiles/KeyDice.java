package ListsFiles;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class KeyDice extends Application
{
   protected HBox root;
   Random randy = new Random();
   DicePane[] di;
	
   public static void main( String[] args )
   { launch(args); }
   
   public void start( Stage stage )
   {
	   root = new HBox();
	   Scene scene = new Scene( root, 600, 600 );
	   stage.setScene(scene);
	   stage.show();
	   stage.setTitle("dice with keys");
	   
	   // make 5 dice and a button to roll each
	   di = new DicePane[5];
	   for ( int i=0; i<5; i++ )
	   {
		   Button b = new Button();
		   b.setText(""+i);
		   root.getChildren().add(b);
		   root.getChildren().add( di[i] = new DicePane() );
		   final int j = i;
		   b.setOnAction(e->{ di[j].roll(); });
	   }
	   
	    // add a keyboard handler to roll on number keys
	   // javafx automatically has the space bar as clicking
	   // also, digit0, etc is being CAUGHT when its pressed
	   // code that catches the space bar isn't in here because it's automatic
	   
       scene.setOnKeyPressed 
       (  (KeyEvent ke) -> 
          {
        	     if ( ke.getCode()==KeyCode.DIGIT0 ) { di[0].roll(); }
           	     if ( ke.getCode()==KeyCode.DIGIT1 ) { di[1].roll(); }
           	     if ( ke.getCode()==KeyCode.DIGIT2 ) { di[2].roll(); }
           	     if ( ke.getCode()==KeyCode.DIGIT3 ) { di[3].roll(); }
           	     if ( ke.getCode()==KeyCode.DIGIT4 ) { di[4].roll(); }

          } 
       );
   }
   
   public class DicePane extends Pane
   {
	   protected int face;
	   
	   public DicePane()
	   {
		   setMinWidth(100);
		   setMinHeight(100);
		   roll();
	   }
	   
	   // clear pane and put in face dots
	   public void drawDots()
	   {
		   getChildren().clear();
		   if ( face==1 || face==3 || face==5 ) { dot( 50,50); }
		   if ( face>1 )  { dot(30,30); dot(70,70); }
		   if ( face>3 ) { dot(30,70); dot(70,30); }
		   if ( face==6 ) { dot( 30, 50 );  dot( 70,50 ); }
	   }
	   
	   // add one dot to pane, centered at x,y
	   public void dot( double x, double y )
	   {
		   Circle c = new Circle(5);
		   c.setFill( Color.BLACK );
		   c.setCenterX(x);
		   c.setCenterY(y);
		   getChildren().add(c);
	   }
	   
	   // randomly set face and redraw dots
	   public void roll()
	   {
		   face = randy.nextInt(6)+1;
		   drawDots();
	   }
   }
}
