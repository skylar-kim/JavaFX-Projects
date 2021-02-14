package layouts;
// MoreButtons.java
// 2018 Barrett Koster
// making buttons in a loop, demo and HBox
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;

public class MoreButtons extends Application
{
	public static void main( String[] args )
	{ launch(args);}
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        HBox root = new HBox();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("more buttons ");
	    stage.setScene(scene);
	    stage.show();
        
        root.setPadding(new Insets(25));
        
        Button btn;

        for ( double y=100; y<1000; y += 100 )
        {
           btn = new Button();
           btn.setText("B"+y);
           root.getChildren().add(btn);
           if ( y>850 )
           {
        	   btn.setOnAction((ActionEvent event) -> 
	           {stage.setWidth( 800 );} // Platform.exit();
	                       );
        	   
           }
           else
           {
	           btn.setOnAction((ActionEvent event) -> 
	           {System.exit(0);} // Platform.exit();
	                       );
           }
        }

   }
}
