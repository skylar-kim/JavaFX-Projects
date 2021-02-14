package buttons;
// DotMaker.java
// 2018 Barrett Koster
// draw dots with a button
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;

public class DotMaker extends Application
{
	Random r;
	
	public static void main( String[] args )
	{ launch(args);}
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
    	r = new Random();
    	Group root = new Group();
    	
    	Button btn = new Button();
    	root.getChildren().add(btn);
    	btn.setText("add a dot");

    	btn.setOnAction(
    	   (ActionEvent a ) ->
    	   {
    	                  javafx.scene.shape.Circle ry 
        	   = new javafx.scene.shape.Circle(9);
            ry.setCenterX(r.nextInt(500));
            ry.setCenterY(r.nextInt(500));
           
            root.getChildren().add(ry);
 
    	   }
    	               );

        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("Dots");
	        stage.setScene(scene);
	        stage.show();
   }
}
