package ListsFiles;
// OvalFiles.java
// 2020 Barrett Koster
// demo of Ovals, file chooser, and PDF dump
 
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OvalFiles extends Application
{
    Random r;
	
	public static void main( String[] args )
	{ launch(args);}
	
	// is called once when object is created
	public void init()
	{
        r = new Random();
	}
	
    @Override
    public void start(Stage stage)
    {  
        stage.setTitle("Dots");
        HBox root = new HBox();
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.show();

        VBox controls = new VBox();
        Group drawing = new Group();
        root.getChildren().add(controls);
        root.getChildren().add(drawing);
        
        Button loadButton = new Button("load");
        controls.getChildren().add(loadButton);
        loadButton.setOnAction((ActionEvent e)->
        {
        	FileChooser fc = new FileChooser();
        	fc.setInitialDirectory( new File(".") );
        	fc.setTitle("choose file to load");
        	File fi = fc.showOpenDialog(stage);
        	try
        	{
        		Scanner scan = new Scanner(fi);
        		String s;
        		while ( scan.hasNextLine() )
        		{
        			s = scan.nextLine();
        			System.out.println("read: "+s);
        		}
        		scan.close();
        	}
        	catch(Exception e2 ) { System.out.println("file read error"); }
        }
                              );
        Button saveButton = new Button("save");
        controls.getChildren().add(saveButton);
        saveButton.setOnAction
        (   (ActionEvent e)->
	        {
	        	FileChooser fc = new FileChooser();
	        	fc.setTitle("choose file to save");
	        	File fi = fc.showSaveDialog(stage);
	        	try
	        	{
		        	FileWriter outy = new FileWriter(fi);
		        	outy.write("now hear this!\nnow hear this!");
		        	outy.close();
	        	}
	        	catch(Exception e3) { System.out.println("file write error"); }
	        }
        );
        
        // make a bunch of ellipses
        for ( int i=0; i<20; i++ )
        {      	
            Ellipse dotty = new Ellipse();
            int cx = r.nextInt(500);
            int cy = r.nextInt(500);
            
            dotty.setCenterX(cx);
            dotty.setCenterY(cy);
            dotty.setRadiusX( r.nextInt(20)+5);
            dotty.setRadiusY( r.nextInt(20)+5);
            {drawing.getChildren().add(dotty);}
        }
    }
}
