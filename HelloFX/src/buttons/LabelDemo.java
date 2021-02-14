package buttons;
// LabelDemo.java
// 2018 Barrett Koster
// demo of some basic drawing features of JavaFX
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import java.io.*;

public class LabelDemo extends Application
{
    public static void main( String[] args )
    { launch(args); } // Application.launch()
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
    	Group root = new Group(); // allows absolute position
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("LabelDemo");
	    stage.setScene(scene);
	    stage.show();
	    
	    
	    File file = new File("bob.jpg");
	    Image image = new Image(file.toURI().toString());
	    //ImageView iv = new ImageView(image);
	    ImageView iv = new ImageView();
	    iv.setImage(image);
	    iv.setFitHeight(100);
	    iv.setFitWidth(100);
	    Label withpic = new Label("pic-", iv);
	    root.getChildren().add(withpic);
	    iv.setX(100);
	    iv.setY(100);
	    root.getChildren().add(iv);
	    
	 
      
	    Label splat = new Label("see me");
	    splat.setLayoutX(200);
	    splat.setLayoutY(200);
	    root.getChildren().add(splat);
	    
 
    }
}
