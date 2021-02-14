package mousing;
// TicTacToe2.java
// 2019 Barrett Koster
// In this version, you click in the window and the
// program computes which Rectangle your click is on.
 
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



public class TicTacToe2 extends Application
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
	    
	    //String aString = JOptionPane.showInputDialog("How many? ");
	    //many = Integer.parseInt(aString);
	    many=4; // for debugging
	    board = new Square[many][many];
	    for ( int i=0; i<many; i++)
	    {
	    	for ( int j=0; j<many; j++ )
	    	{
	    		board[i][j] = new Square(i,j);
	    		root.getChildren().add(board[i][j]);
	    	}
	    }
	    
	    // one listener for the whole window
        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, 
           (MouseEvent m)->
           { clickAt( m.getX(), m.getY() ); }
                            );
   }
    
    public void clickAt ( double x, double y )
    {
    		// figure out which box is clicked
    		// this is computationally fast
     		double id = (x-20) / 55;
    		double jd = (y-20) / 55;
    		int i = (int)id;
    		int j = (int)jd;
    		System.out.println("coords=" + x + " "+y);
    		System.out.println("i="+i+"j="+j);
    		if ( 0<=i && i<many )
    		{
    		}
    }
    
    public class Square extends Rectangle
    {
    	public Square( int i, int j )
    	
    	{
    		// no actual registeration between what i'm clicking on,
    		// and what i think im clicking on
    		super(50,50);
    		setX( 20 + 55*i );
    		setY( 20 + 55*j );
    		setPastel();
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
