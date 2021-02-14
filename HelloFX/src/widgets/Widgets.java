// PDF demo

// **** THis does not work yet ***



package widgets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Widgets extends Application
{
	VBox root1;
	Scene scene1;
	Stage theStage;
	
   public static void main( String[] args )
   { launch(args); }
   
   @Override
   public void start( Stage stage )
   {
   	theStage = stage;
   	stage.setTitle("Widgets");
   	root1 = new VBox();
   	scene1 = new Scene( root1, 400, 300 );
   	stage.setScene( scene1 );
   	stage.show();
   	
   	// demo of ComboBox
      ComboBox<String> cb = new ComboBox();
      cb.getItems().add("bob");
      cb.getItems().add("jane");
      cb.getItems().add("alice");
      root1.getChildren().add(cb);
      cb.setValue("ted"); // weird
      cb.setOnAction( e->{ System.out.println("v="+cb.getValue()); } );
      
      // demo of DatePicker
      DatePicker dp = new DatePicker();
      root1.getChildren().add(dp);
      dp.setOnAction( e->{ System.out.println("v="+dp.getValue()); } );

      // demo of ListView
      ListView<String> lv = new ListView();
      root1.getChildren().add(lv);
      lv.getItems().add("red");
      lv.getItems().add("yellow");
      lv.getItems().add("green");
      lv.getItems().add("blue");

      lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      lv.addEventHandler // long form handler setting
      (MouseEvent.MOUSE_CLICKED, 
       m-> {System.out.println("lv?");} // short form for this part
      );
      //
      Button showLVButton = new Button("show selections");
      root1.getChildren().add(showLVButton);
      showLVButton.setOnAction
      ( a->
	      {
		      for ( String s : lv.getSelectionModel().getSelectedItems() )
		      {
		      	System.out.print(" selected="+s);
		      }
	      }
      );
      
      // slider
      Slider sv = new Slider();
      sv.setMin(1); sv.setMax(10);
      // adding to the window
      root1.getChildren().add(sv);
      // listener
      // you can add a listener on the value of the property
      // which generates an event
      // you can add a listener on anything tbh
      sv.valueProperty().addListener
      (e->
        {
      	  System.out.println("slide="+sv.getValue());
        }
      );
   }

}
