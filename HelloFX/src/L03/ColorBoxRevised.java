package L03;
// ColorBox.java

import java.util.Random;

import javafx.event.*;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ColorBoxRevised extends FlowPane
{
	protected Button colorButton; // click to change color
	protected Random randy;
	//protected static ColorBoxDemo theDemo;
	protected Color color;
	protected static ColorBoxRevised lastOne; // the last one you clicked on
	
	// constructor
    public ColorBoxRevised( String date )
    {

    	//theDemo = td;
        randy = new Random();
     	setPrefSize(100,100);
     	
    	
        colorButton = new Button();
        colorButton.setText(date);
        colorButton.setOnAction( new ColorButtonHandler() );
        
        //colorButton.setOnMousePressed( new ColorButtonHandler() );
        //colorButton.addEventHandler( MouseEvent.MOUSE_PRESSED, eventHandler);
        
        getChildren().add(colorButton);
      
        setPastel();
    }
    
    /**
     * This handler sets this box to a random color (pastel)
     * AND sets the main program's background to the same thing.
     * @author Barry-Standard
     *
     */
    public class ColorButtonHandler implements EventHandler<ActionEvent>
    {
    	@Override
    	public void handle(ActionEvent e)
    	{
    		setPastel();
    		//theDemo.setColor(color);
    		lastOne = ColorBoxRevised.this;
    	}
    }
    
    /**
     * Sets the 'color' variable to a pastel
     */
    public void setPastel()
    {
		double red = 0.5 + 0.5 * Math.random();
		double green = 0.5 + 0.5 * Math.random();
		double blue = 0.5 + 0.5 * Math.random();
		color =  new Color(red,green,blue,1.0);
		setBackground(new Background(new BackgroundFill(
				color, CornerRadii.EMPTY, Insets.EMPTY
		)));
    }
    
    // access
    public static ColorBoxRevised getLastOne() { return lastOne; }
    public Color getColor() { return color; }
}
