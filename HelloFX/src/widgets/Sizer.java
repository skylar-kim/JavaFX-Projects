package widgets;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sizer extends Application
{
	VBox root1;
	VBox root2;
	Scene scene1;
	Scene scene2;
	Stage theStage;
	
   public static void main( String[] args )
   { launch(args); }
   
   @Override
   public void start( Stage stage )
   {
   	// get the whole screen width
   	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
   			.getDefaultScreenDevice();
   	int width = gd.getDisplayMode().getWidth();
   	int height = gd.getDisplayMode().getHeight();
   	System.out.println("w="+width+" h="+height);
   	
   	theStage = stage;
   	stage.setTitle("Sizer");
   	root1 = new VBox();
   	root2 = new VBox();
   	scene1 = new Scene( root1, width, 500 );
   	scene2 = new Scene( root2, 300, 500 );
   	stage.setScene( scene1 );
   	stage.show();

   	// rest to normal width 
   	Button tog = new Button("normal width");
   	root1.getChildren().add(tog);
   	tog.setOnAction
   	( e->{ stage.setScene(scene2); } );
   	
   	// listener to know when the user changes the screen size
     stage.widthProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
             System.out.println("Width changed to"+newValue);
         }
     });

     stage.heightProperty().addListener(new ChangeListener<Number>() {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
             System.out.println("Height changed to "+newValue);
         }
     });

   }

}
