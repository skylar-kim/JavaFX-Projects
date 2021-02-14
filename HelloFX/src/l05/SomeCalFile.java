package l05;
// LalyoutDemo.java
// 2018 Barrett Koster
// shows some of the layouts for JavaFX
 
import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SomeCalFile extends Application
{
    Random randy = new Random(); // used for random colors
    int count=1; // numbers all of the Buttons
    LinkedList<DayPane> dpList;
    Stage theStage;
    
    // runs the program
    public static void main( String[] args )
    { launch(args);}
	
    @Override
    public void start(Stage stage)
    {    
    	theStage = stage;
        // overall layout is a grid,
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Calendar Demo");
	    stage.setScene(scene);
	    stage.show();
	    
	    dpList = new LinkedList<DayPane>();
	 
	    // add days to the calendar
	    for ( int i=1; i<=31; i++ )
        {   int j = i + 2; // start position with offset 2
           DayPane dp = new DayPane();
           dpList.add(dp);
        	root.add(dp, j%7, j/7);
        }
	    
	    File aFile = new File("events.txt");
	    loadEvents(aFile);
	    
	    Button chooseButton = new Button();
	    root.getChildren().add(chooseButton);
	    chooseButton.setText("open file ...");
	    chooseButton.setOnAction(e->{chooseAndLoad();});
    }
    
    public void chooseAndLoad()
    {
    	FileChooser fc = new FileChooser(); 
    	fc.setInitialDirectory( new File(".") );
    	File theFile = fc.showOpenDialog(theStage);
    	loadEvents(theFile);
    }
    
    // returns the DayPane object given the date as an int
    public DayPane findDay(int date )
    {
    	DayPane found = null;
    	for ( DayPane dp : dpList )
    	{
    		if ( dp.getDate()==date )
    		{
    			found = dp; break;
    		}
    	}
    	return found;
    }
    
    public void loadEvents(File theFile)
    {
    	try
    	{
    		Scanner scan = new Scanner( theFile );
    		while ( scan.hasNextLine() )
    		{
    			//String s = scan.nextLine();
    			//System.out.println("read: "+s);
    			int d = scan.nextInt();
    			String s = scan.nextLine();
    			System.out.println("d="+d+" s="+s);
    			DayPane dp = findDay( d );
    			dp.getChildren().add( new Label(s));
    		}
    	}
    	catch(Exception e){ System.out.println("oops"); }
    }
    
    // is one day of your calendar  text on the
    // button is a number with a running count for the
    // whole program, then some events.
    public class DayPane extends VBox
    {
    	private int date;
    	public int getDate() { return date; }
    	
        public DayPane()
        {
        	date = count++;
	        Label bob = new Label(""+date);
	
	        Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
	        bob.setFont(ff);
	        getChildren().add(bob );

	        setPrefSize(100,100);
            setPastel();
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
