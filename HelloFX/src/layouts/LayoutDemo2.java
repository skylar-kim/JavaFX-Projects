package layouts;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LayoutDemo2 extends Application
{
   GridPane root;
	
   public static void main(String[] args )
   { launch(args); }
   
   public void start( Stage stage )
   {
	   // the usual 5 lines (now 6 lines)
	   root = new GridPane();
	   Scene scene = new Scene( root, 900, 700 );
	   stage.setScene(scene);
	   stage.show();
	   stage.setTitle("Layout Demo 2");
	   stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );
	   
	   makeFlowPane();
	   makeHBox();
	   makeVBox();
   }
   
   // put a flow pane in the upper left of the grid 2x2
   public void makeFlowPane()
   {
	   FlowPane fp = new FlowPane();
	   root.add(fp,0,0);
	   for ( int i=0; i<40; i++ )
	   {
		   Button b = new Button();
		   fp.getChildren().add(b);
		   b.setText("button"+i);
	   }
   }
   
   // put an HBox in the upper right corner with some buttons
   public void makeHBox()
   {
	   HBox hb = new HBox();
	   root.add(hb,1,0);
	   for ( int i=0; i<10; i++ )
	   {
		   Button b = new Button();
		   hb.getChildren().add(b);
		   b.setText("hb" + i );
		   
	   }
   }
   
   // put a VBox in the lower left corner of the grid
   public void makeVBox()
   {
	  
	   VBox vb = new VBox();
	   root.add( vb, 0,1 );
	   for ( int i=0; i<10; i++ )
	   {
		   Button b = new Button();
		   vb.getChildren().add(b);
		   b.setText("vb"+i);
	   }
   }
}
