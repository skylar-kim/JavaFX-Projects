package buttons;
// KeyDemo.java
// 2019 Barrett Koster
// demo of pane with button in it.  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*; // <----!!!!!!
import javafx.stage.*;

public class KeyDemo extends Application
{
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Key Demo");
        HBox root = new HBox();
        Scene scene = new Scene(root, 200, 200);
	    stage.setScene(scene);
	    stage.show();
              
        scene.setOnKeyPressed
        (  (KeyEvent ke) -> 
           {
           	   System.out.println("text:"+ke.getText());
           	   System.out.println("key code:"+ke.getCode());
           } 
        );
   }
}
