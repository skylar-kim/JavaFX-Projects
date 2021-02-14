package basics;
// DrawStuff.java
// 2018 Barrett Koster
// demo of some basic drawing features of JavaFX
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class DrawStuff extends Application
{
	protected Group root;
    public static void main( String[] args )
    { launch(args); } // Application.launch()
	
    @Override
    public void start(Stage stage)
    {  
        stage.setTitle("DrawStuff");
    	root = new Group(); // allows absolute position
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
        // closes the program when the window is closed
        stage.setOnCloseRequest(e->{Platform.exit(); System.exit(0); } );
        
        thickLine();
	    someRectangle();
	    someDot();
        demoText(); 
        fence(root); // draw a bunch of lines
        polly(root);
        
        
    }
    
    
    public void thickLine() {
    	// declare a line, coords are xy of each end, add to root
        Line liney = new Line(30,180, 150, 70 );
	    root.getChildren().add(liney);
	    liney.setStrokeWidth(4);
    }
    
    public void someRectangle() {
    	// make a rectangle (width,height), color it, add to root
 	   Rectangle ry = new Rectangle(18,40);
         ry.setX(50); // upper left from upper left
         ry.setY(100);
         ry.setFill( new Color ( 0.9, 0.4, 0.4, 1)  ); // dark pink
         //ry.setFill( new Color ( 0.9, 0.0, 0.0, 1)  ); // red red
 	   root.getChildren().add(ry);
    }
    
    public void someDot() {
    	// add a colored disc to root
    	Circle c   = new Circle(20);
    	c.setCenterX(200);
    	c.setCenterY(200);
    	c.setFill( new Color ( .4, .6, .9, 1)  );// light blue
    	root.getChildren().add(c);
    	        
    	c.toFront(); // note: dot on top of lines despite being drawn before
    }
    
    public void demoText()
    {
        // put some text in the scene
        Text say = new Text();
        say.setText("Carlota");
        say.setX(400);
        say.setY(100);
        root.getChildren().add(say);
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        say.setFont(font);
    }

    /**
     * draws a triangle in red
     * @param root
     */
    public void polly( Group root )
    {
    	double[] points = new double[6];
    	points[0] = 200; points[1] = 450;
    	points[2] = 100; points[3] = 400;
    	points[4] = 400; points[5] = 200;
    	
    	Polygon p = new Polygon(points);
    	p.setFill(Color.RED);
    	root.getChildren().add(p);
    }
    
    /**
     * make a bunch of lines
     * @param root is the group to put the lines in
     */
    public void fence(Group root)
    {
        for ( int x=100; x<400; x += 20 )
        {
            Line liney = new Line(x, 200, 400, x );
            root.getChildren().add(liney);
        }
    }
}
