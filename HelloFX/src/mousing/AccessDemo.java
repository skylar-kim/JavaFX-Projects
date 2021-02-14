

package mousing;
// AccessDemo.java
// 2020 Barrett Koster
// get access to button  
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AccessDemo extends Application
{
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("Access Demo");
        HBox root = new HBox();
        Scene scene = new Scene(root, 500, 200);
	    stage.setScene(scene);
	    stage.show();

        Button hiButton = new Button("say hi");
        hiButton.setOnAction(
           (ActionEvent event) -> {System.out.println("hi");} 
                       );
        
        Button byeButton = new Button("say bye");
        byeButton.setOnAction
        ( (ActionEvent e)->
          { System.out.println("bye"); 
            
          }
        );
        
        Label aLabel = new Label("This is just some text.");
        root.getChildren().add(aLabel);
        aLabel.setFocusTraversable(true);

        root.setPadding(new Insets(25));
        root.getChildren().add(hiButton);
        root.getChildren().add(byeButton);
        
        System.out.println("byeButton.role="+byeButton.getAccessibleRole());
        hiButton.setAccessibleText("this button writes hi to the console");
        System.out.println("hiButton.text="+hiButton.getAccessibleText());
   }
   
}
