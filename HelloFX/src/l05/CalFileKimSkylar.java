package l05;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// IF FILE IS NOT BEING FOUND, change loadData()'s filepath!
// I had to put the whole filepath because I am on a mac

public class CalFileKimSkylar extends Application
{
    Random randy = new Random(); // used for random colors
    int count=1; // numbers all of the Buttons
    
    // runs the program
    public static void main( String[] args )
    { launch(args);}
	
    @Override
    public void start(Stage stage) throws Exception
    {    
        // overall layout is a grid,
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Calendar Demo");
	    stage.setScene(scene);
	    stage.show();
	    
	    
	    
	    List<DayPane> dayList = new ArrayList<DayPane>();
	    // add days to the calendar
	    for ( int i=1; i<=31; i++ )
        {   int j = i + 2; // start position with offset 2
        	DayPane daypane = new DayPane();
        	root.add(daypane, j%7, j/7);
        	dayList.add(daypane);
        }
	    
	    try {
	    	loadData(dayList);
	    } catch(FileNotFoundException fnfe) {
	    	System.out.println("Exception: " + fnfe.getMessage());
	    }
	    
	    
	    /*
	     * File aFile = new File("events.txt");
	     * loadEvents(aFile)
	     * 
	     * Button chooseButton = new Button();
	     * root.getChildren().add(chooseButton);
	     * chooseButton.setText("open file");
	     * chooseButton.setOnAction(e -> {chooseAndLoad();});
	     */
    }
    /*
     public void chooseAndLoad() {
     	FileChooser fc = new FileChooser();
     	fc.setInitialDirectory(new File("."));
     	File theFile = fc.showOpenDialog(theStage);
     	loadEvents(theFile);
     	
     }
     */
    
    
    public void loadData(List<DayPane> dayList) throws Exception
    {
 	  try
 	  {
 		  // if file is not being found, change the filepath here
           Scanner scan = new Scanner(new File("events.txt"));
 	   	   while ( scan.hasNextLine() )
 	   	   {
 	   		   	int day = scan.nextInt();
 	   		   	System.out.println(day);
 	   		    String description = scan.nextLine();
 	   		    System.out.println(description);
 	   		    dayList.get(day-1).setLabel(description);
 
 	   	   }
 
 	  }
 	  catch(FileNotFoundException fe)
 	  { System.out.println("can't find the file."); }
       catch( Exception e )
 	  { System.out.println("loadData: exception="+e);}
 		  
 
    }
    
    // is one day of your calendar  text on the
    // button is a number with a running count for the
    // whole program, then some events.
    public class DayPane extends VBox
    {
    	Label description;
        public DayPane()
        {
	        Label bob = new Label(""+(count++));
	        Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
	        bob.setFont(ff);
	        getChildren().add(bob );
	        //getChildren().add(new Label("do something"));
	        //getChildren().add(new Label("do something"));
	        
	        setPrefSize(100,100);
            setPastel();
            
            
        }
        
        public void setLabel(String text) {
        	this.description = new Label(text);
        	getChildren().add(this.description);
        }
        
        
        
        // sets this Pane to background of random pastel
        public void setPastel()
        {
    		double red = 0.5 + 0.5 * Math.random();
    		double green = 0.5 + 0.5 * Math.random();
    		double blue = 0.5 + 0.5 * Math.random();
    		Color color =  new Color(red,green,blue,1.0);
    		setBackground(new Background(new BackgroundFill(
    				color, CornerRadii.EMPTY, Insets.EMPTY
    		)));
        }
    }
}