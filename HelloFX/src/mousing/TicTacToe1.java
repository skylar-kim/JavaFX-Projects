package mousing;
// TIcTacToe1.java
// 2018 Barrett Koster
// the GUI shell of a tic tac toe game.  
// In this version, each cell has a button you can
// click on.
 
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
//import java.awt.*;
//import java.awt.Dimension;

public class TicTacToe1 extends Application
{
    private int gameSize = 3;
    private char whoseTurn = 'X'; // other possible is 'O'
    
	public static void main( String[] args )
	{ launch(args); }
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
        GridPane root = new GridPane();
        
        for ( int c=0; c<gameSize; c++ ) // nexted loop, make squre grid
        {
            for ( int r=0; r<gameSize; r++ )
            {
                Button btn = new Button();
                btn.setStyle("-fx-font-size: 30;");
                btn.setPrefSize( 100,100 );
                
                // if button is clicked, change text on button to X or O,
                // for whoever's turn it is.
                btn.setOnAction(
                   (ActionEvent event) -> 
                   {
                       Button b = (Button) (event.getTarget());
                       b.setText(""+takeTurn());
                    } 
                               );
                root.add( btn, r, c ); // add button to scene
            }
        }
        Scene scene = new Scene(root, 300, 300);

        stage.setTitle("Quit button");
	        stage.setScene(scene);
	        stage.show();
   }
   
   // takeTurn returns the current whoseTurn and 
   // then toggles it
   private char takeTurn()
   {
       char w = whoseTurn;
       whoseTurn = (whoseTurn=='X')? 'O' : 'X' ;
       return w;
   }
   
}
