package mousing;
// TicTacToe3.java
// 2020 Barrett Koster
// user makes Rectangles as buttons, attaches handler
// to each button.
// This also gets rid of the magic numbers (makes 
// them constants).
 
import javax.swing.JOptionPane;

//import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.input.*;


public class TicTacToe3 extends Application
{
    Pane root; // all the stuff in the window attaches here
    Square[][] board;
    int many;
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        stage.setTitle("TicTacToe2");
        root = new Pane();
        Scene scene = new Scene(root, 400, 400);
 	    stage.setScene(scene);
	    stage.show();
	    
	    String aString = JOptionPane.showInputDialog("How many? ");
	    many = Integer.parseInt(aString);
	    //many=4; // for debugging
	    board = new Square[many][many];
	    for ( int i=0; i<many; i++)
	    {
	    	for ( int j=0; j<many; j++ )
	    	{
	    		board[i][j] = new Square(i,j);
	    		root.getChildren().add(board[i][j]);
	    	}
	    }
   }

    
    public class Square extends Rectangle
    {
    	int myI;
    	int myJ;
    	double leftOffset = 60; // space on left of boxes
    	double topOffset = 20; // space above set of boxes
    	double hSpacing = 55; // horizontal spacing of boxes
    	double vSpacing = 85; // vertical spacing of boxes
    	
    	public Square( int i, int j )
    	{
    		super(50,50);
    		myI = i;
    		myJ = j;
    		setX( leftOffset + hSpacing*i );
    		setY( topOffset + vSpacing*j );
    		setPastel();
    		
    		// each square has an event handler 
    		addEventHandler(MouseEvent.MOUSE_CLICKED,
    				(MouseEvent m)->
    		{ System.out.println("hey, i="+myI+" j="+myJ );
    		}
    				);
    	}
        /**
         * Sets the Square color to a pastel
         */
        public void setPastel( )
        {
    		double red = 0.5 + 0.5 * Math.random();
    		double green = 0.5 + 0.5 * Math.random();
    		double blue = 0.5 + 0.5 * Math.random();
    		setFill( new Color(red,green,blue,1.0) );
        } 
    }
}
