package layouts;
// ScrollPaneDemo.java
// 2020 Barrett Koster
// trying to see if I can make one work  
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ScrollPaneDemo extends Application
{
	Button btn;
	
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("scroll pane demo");
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, 200, 200);
	    stage.setScene(scene);
	    stage.show();
	    
	    //HBox hb = new HBox();
	    VBox vb = new VBox();
	    root.setContent(vb);

	    for ( int i=0; i<20; i++ )
	    {
            Label lab = new Label("a bunch of stuff");
            vb.getChildren().add(lab);
	    }
    }
}
