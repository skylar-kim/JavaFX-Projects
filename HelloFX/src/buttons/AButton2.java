package buttons;
// AButton2.java
// 2019 Barrett Koster
// demo of button with long form of handler  
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.*; ..........
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AButton2 extends Application
{
	protected Button btn;
	
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    {   
        stage.setTitle("needy button");
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 200);
	    stage.setScene(scene);
	    stage.show();
	    
	    // button that we did in class
	    // if the code gets any longer, just make a function
	    Button hiButton = new Button();
	    hiButton.setText("say hi");
	    root.getChildren().add(hiButton);
	    hiButton.setOnAction( (ActionEvent e)-> {System.out.println("hi"); });

        
        btn = new Button();
        btn.setText("click me");
        ButtonHandler bh = new ButtonHandler();
        btn.addEventHandler( ActionEvent.ANY, bh );
        root.getChildren().add(btn);
        
        Button btn4 = new Button();
        btn4.setText("4");
        ButtonHandler bh4 = new ButtonHandler("hi there",btn4);
        btn4.addEventHandler( ActionEvent.ANY, bh4);
        root.getChildren().add(btn4);

        root.setPadding(new Insets(25));
      
        // this is the long way
        // see below for the function implementation
        Button btn2 = new Button();
        btn2.setText("click me too");
        root.getChildren().add(btn2);
        
        // on the fly defn of a handler
        // the code can get nested so...be careful
        // at some point if its too complicated, just make a function
        btn2.addEventHandler( ActionEvent.ANY,
        		new EventHandler<ActionEvent>()
        		{ 
        	        @Override
        	        public void handle( ActionEvent e )
        	        { System.out.println("btn2 clicked"); }
        		}
        		);
        
        // this is the short version
        // implementation is on one line
        Button btn3 = new Button();
        root.getChildren().add(btn3);
        btn3.setText("this uses lambda");
        btn3.setOnAction( (ActionEvent e)->{ System.out.println("btn3 click"); } );
        
        stage.setOnCloseRequest( e->{Platform.exit(); System.exit(0); } );
        
    }
    
    public class ButtonHandler 
       implements EventHandler<ActionEvent>
    {
    	String sayThis;
    	Button b;
    	
    	public ButtonHandler()
    	{
    		sayThis = "thanks";
    		b = btn;
    	}
    	
    	public ButtonHandler(String s, Button target)
    	{
    		sayThis = s;
    		b = target;
    	}
    	
    	// the below is necessary
    	// handle button is changing the text of the button
		@Override
		public void handle(ActionEvent event)
		{
			b.setText(sayThis);	
		}
		
    }
}
