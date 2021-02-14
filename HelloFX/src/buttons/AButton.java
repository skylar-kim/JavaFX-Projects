package buttons;
// AButton.java
// 2018 Barrett Koster
// demo of pane with button in it.  
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AButton extends Application
{
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Quit button");
        HBox root = new HBox();
        Scene scene = new Scene(root, 200, 200);
	    stage.setScene(scene);
	    stage.show();
	    
	    stage.setOnCloseRequest(e->{Platform.exit(); System.exit(0); } );

        
        Button btn = new Button();
        btn.setText("Quit");
        // lamba expression that describes the handler
        btn.setOnAction(
           (ActionEvent event) -> {System.exit(0);} 
                       );
        
        //BackgroundFill[] bgf = new BackgroundFill[1];
        //bgf[0] = new BackgroundFill(  );
        //btn.setBackground( new Background(  ) );

        root.setPadding(new Insets(25));
        root.getChildren().add(btn);

   }
   
}
