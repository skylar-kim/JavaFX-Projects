package ListsFiles;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DiceStuff extends Application
{
	Dice[] theDice;
	int numDice = 5;
	
   public static void main( String[] args )
   { launch(args); }
   
   public void start( Stage stage )
   {
	   stage.setTitle("dice");
	   FlowPane root = new FlowPane();
	   Scene scene = new Scene( root,500,500 );
	   stage.setScene(scene);
	   stage.show();
	   stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );
	   
	   theDice = new Dice[numDice];
	   for ( int i=0; i<numDice; i++ )
	   {
		   root.getChildren().add( theDice[i] = new Dice() );
	   }
	   
	   Button rollAll = new Button();
	   rollAll.setText("roll all");
	   root.getChildren().add(rollAll);
	   // calls roll() on each Dice
	   rollAll.setOnAction
	   ( e-> { for(int i=0;i<numDice;i++){ theDice[i].roll(); }});
   }
   
   // one Dice object
   public class Dice extends VBox
   {
	   int face; // 1-6 value when rolled
	   Color color; // random pastel background
	   HBox buttonPane; // for "roll" and "hold"
	   Pane dotPane; // for the dice's dots
	   boolean hold = false; // can't roll when hold==tru
	   
      public Dice()
	  {
		   buttonPane = new HBox();
		   getChildren().add(buttonPane);
		   
			   Button rollButton = new Button();
			   rollButton.setText("roll");
			   buttonPane.getChildren().add(rollButton);
			   rollButton.setOnAction
			   ( e->{roll();});
			   
			   Button holdButton = new Button();
			   holdButton.setText("hold");
			   buttonPane.getChildren().add(holdButton);
			   holdButton.setOnAction
			   (e->{ hold = !hold; rollButton.setDisable(hold); });
		   
		   dotPane = new Pane();
		   getChildren().add(dotPane);
		   dotPane.setPrefSize( 50,50 );
		   
		   setPastel();
		   roll();
      }
	   
	  // if not on hold, new random face and display it
	  public void roll()
	  {
		   if ( !hold )
		   {
			   face = (int)(Math.random()*6+1);
			   showFace();
		   }
	  }
	   
	  // put the dots for the face value
	  public void showFace()
	  {
		   dotPane.getChildren().clear();
		   if ( face>1    ) { makeDot( 10,10 ); makeDot(40,40); }
		   if ( face==6   ) { makeDot( 10,25 ); makeDot(40,25); }
		   if ( face>3    ) { makeDot( 10,40 ); makeDot( 40,10 );}
		   if ( face%2==1 ) { makeDot( 25,25 ); }
	  }
	   
	  // makes a single dot at these coordinates
	  public void makeDot( double x, double y )
	  {
		   Circle c = new Circle( x, y, 3 );
		   dotPane.getChildren().add(c);
	  }
	   
	  /**
	   * Sets the 'color' variable to a pastel
	   */
      public void setPastel()
	  {
			double red = 0.5 + 0.5 * Math.random();
			double green = 0.5 + 0.5 * Math.random();
			double blue = 0.5 + 0.5 * Math.random();
			color =  new Color(red,green,blue,1.0);
			setBackground(new Background(new BackgroundFill(
					color, CornerRadii.EMPTY, Insets.EMPTY
			)));
      }
   }
}
