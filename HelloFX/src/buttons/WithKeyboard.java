package buttons;
// WithKeyboard.java
// 2018 Barrett Koster
// demo a home-made on screen keyboard  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
//import java.awt.*;
//import java.awt.Dimension;

public class WithKeyboard extends Application
{
    String message = ""; // this gets added to when you click on a letter
    Label theMessage; // Label to hold/display the above
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        FlowPane root = new FlowPane();
        
        char c ;
        for (  c='A'; c<='Z'; c++ )
        {
                Button btn = new Button();
                //btn.setStyle("-fx-font-size: 30;");
                //btn.setPrefSize( 100,100 );
                btn.setText(""+c);
                
                btn.setOnAction(
                   (ActionEvent event) -> 
                   {
                       Button b = (Button) (event.getTarget());
                       message += b.getText(); // add this button's letter to message
                       theMessage.setText(message); // refresh display
                    } 
                               );
                root.getChildren().add( btn );
                
                theMessage = new Label();
                root.getChildren().add(theMessage);
            
        }
        Scene scene = new Scene(root, 300, 300);

        stage.setTitle("Quit button");
	        stage.setScene(scene);
	        stage.show();
   }
   

}
