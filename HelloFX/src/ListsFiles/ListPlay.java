package ListsFiles;

// ListPlay.java
//2019 Barrett Koster
//demo of some basic list things

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;

//import inclass.NumberBox;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class ListPlay extends Application
{
	protected LinkedList<Rectangle> rectangles;
	protected LinkedList<Circle> circles;
	protected LinkedList<NumberBox> boxes;
	protected Random randy;
	
   public static void main( String[] args )
   { launch(args); } // Application.launch()
	
   @Override
   public void start(Stage stage)
   { initUI(stage); }

   private void initUI(Stage stage)
   {   
	    stage.setTitle("ListPlay");
	 	FlowPane root = new FlowPane();
	    Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		stage.show();
		
		rectangles = new LinkedList<Rectangle>();
		circles    = new LinkedList<Circle>();
		boxes      = new LinkedList<NumberBox>();
		randy = new Random();
		
		// add to root a Pane for the buttons and one for
		// the shapes
	    FlowPane buttonPanel = new FlowPane();
	    root.getChildren().add(buttonPanel);
        Group drawHere = new Group(); // allows absolute position
	    root.getChildren().add(drawHere);
	    
	
		// make some random rectangles, add to window AND the list
		for ( int i=0; i<20; i++ )
		{
		   Rectangle ry = new Rectangle(18,18);
	       ry.setX(randy.nextInt(400));
	       ry.setY(randy.nextInt(400));
	       ry.setFill( new Color ( 0.9, 0.4, 0.4, 1)  ); // dark pink
		   drawHere.getChildren().add(ry);
		   rectangles.add(ry);
		}
		
		// make some random circles, add to window and list
		for ( int i=0; i<20; i++ )
		{
			// add a colored disc to root
			Circle c   = new Circle(10);
			c.setCenterX(randy.nextInt(400));
			c.setCenterY(randy.nextInt(400));
			c.setFill( new Color ( .4, .6, .9, 1)  );// light blue
	        drawHere.getChildren().add(c);
	        circles.add(c);
		}
		
		// make some NumberBox items, add to window and list
		for ( int i=0; i<20; i++ )
		{
			NumberBox nb = new NumberBox();
			nb.setX(randy.nextInt(400));
			nb.setY(randy.nextInt(400));
			nb.setFill( new Color ( .8, .8, .3, 1)  );// light yelow
			drawHere.getChildren().add(nb);
			drawHere.getChildren().add(nb.getT());
			nb.match();
			boxes.add(nb);
		}
     
		// button to move Rectangles a little
		Button moveR = new Button("move rectangles");
		buttonPanel.getChildren().add(moveR);
		moveR.setOnAction
		( (ActionEvent e ) -> { moveAllR(); }
    	);
		
		// button to move Circles a little
		Button moveC = new Button("move circles");
		buttonPanel.getChildren().add(moveC);
		moveC.setOnAction
		( (ActionEvent e ) -> { moveAllC(); }
    	);
		
		// button to renumber boxes top to bottom
		Button sortNSButton = new Button("number ns");
		buttonPanel.getChildren().add(sortNSButton);
		sortNSButton.setOnAction
		( (ActionEvent e ) -> { sortNS(); }
        );
		
		// button to renumber boxes left to right
		Button sortEWButton = new Button("number ew");
		buttonPanel.getChildren().add(sortEWButton);
		sortEWButton.setOnAction
		( (ActionEvent e) -> { sortEW(); }
		);
	}
   
   // move all of the Rectangles a little, use Iterator
   public void moveAllR()
   {
	   	System.out.println("ListPlay.moveAllR: entering ...");
	   	// iterator 
	   	Iterator<Rectangle> it = rectangles.iterator();
	   	while ( it.hasNext() )
	    {
	   		Rectangle r = it.next();
	   		r.setX(r.getX()+10);
	   	}
   }
   
   // move all of the cirlces a little, use for(:) notation
   public void moveAllC()
   {
	   	System.out.println("ListPlay.moveAllC: entering ...");
	   	// for each loop
	   	for ( Circle c : circles )
	   	{
	   		c.setCenterY(c.getCenterY()+10);
	   	}
   }
   
   // sort with the left to right (East-West) Comparator
   // and renumber
   public void sortEW()
   {
	   Collections.sort( boxes, new CompEW() );
	   // lambda expression
	   //Collections.sort(boxes, (a,b) -> {return (int)(b.getX()-a.getX());});
	   numberTheBoxes();
   }
   
   // sort with the top to bottom (North-South) comparator
   // and renumber
   public void sortNS()
   {
	   Collections.sort( boxes, new CompNS() );
	   // lambda function:
	   //Collections.sort(boxes, (a,b) ->{return (int)(a.getY()-b.getY());});
	   numberTheBoxes();
   }
   
   // numbers the boxes in the current order in the list
   public void numberTheBoxes()
   {
	   int i=0;
	   for ( NumberBox nb : boxes )
	   {
		   nb.setText(""+i); 
		   i++; 
	   }
   }
   
   // NumberBox Comparator class that puts top of the 
   // screen items first (lowest)
   public class CompNS implements Comparator<NumberBox>
   {
	   public int compare(NumberBox a, NumberBox b )
	   {
		   return (int)(a.getY()-b.getY());
	   }
   }
   // NumberBox Comparator class that puts left of
   // the screen items first (lowest)
   public class CompEW implements Comparator<NumberBox>
   {
	   public int compare(NumberBox a, NumberBox b )
	   {
		   return (int)(b.getX()-a.getX());
	   }
   }
}
