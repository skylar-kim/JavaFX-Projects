package CSSStuff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CSS1 extends Application
{
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    {    
    	Pane root = new FlowPane();
      Scene scene = new Scene(root, 600, 400);
      stage.setTitle("CSS1");
	   stage.setScene(scene);
	   stage.show();
      scene.getStylesheets().add("CSSStuff/CSS1.css");
	   
      
	   int nubmer = 10;
	   Label[] lab = new Label[nubmer];
	   for ( int i=0; i<nubmer; i++ )
	   {
	   	lab[i] = new Label("lab"+i);
	   	root.getChildren().add(lab[i]);
	   }
	   
	   Button[] bb = new Button[nubmer];
	   for ( int i=0; i<nubmer; i++ )
	   {
	   	bb[i] = new Button("bb"+i);
	   	root.getChildren().add(bb[i]);
	   }

	   root.getStyleClass().add("wholePane");
	   
	   for ( int i=0; i<nubmer/2; i++ )
	   {
	   	lab[i].getStyleClass().add("lab");
	   	bb[i].getStyleClass().add("but");
	   }
	   
    }
}