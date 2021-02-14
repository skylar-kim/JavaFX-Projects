package widgets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeScene extends Application
{
	VBox root1;
	HBox root2;
	Scene scene1; // has root1
	Scene scene2; // has root 2
	int which = 1; // 1=scene1 is visible, 2=scene2
	Stage theStage;
	
   public static void main( String[] args )
   { launch(args); }
   
   @Override
   public void start( Stage stage )
   {
   	theStage = stage;
   	stage.setTitle("change scene");
   	root1 = new VBox();
   	scene1 = new Scene( root1, 300, 500 );
   	stage.setScene( scene1 );
   	stage.show();
   	
   	root2 = new HBox();
   	scene2 = new Scene( root2, 600, 400);
   	
   	for ( int i=0; i<10; i++ )
   	{
	   	Button tog = new Button("toggle");
	   	root1.getChildren().add(tog);
	   	tog.setOnAction( e->{ 
	   		// scene1.setRoot(root2); not happy
	   	 toggle();  } );
   	}
   	for ( int i=0; i<10; i++ )
   	{
	   	Button tog = new Button("toggle");
	   	root2.getChildren().add(tog);
	   	tog.setOnAction( e->{ toggle(); } );
   	}
   }
   
   // toggle between scene1 and scene2
   public void toggle()
   {
   	if ( which==1 ) { theStage.setScene(scene2); which=2; }
   	else            { theStage.setScene(scene1); which=1; }
   }
}
