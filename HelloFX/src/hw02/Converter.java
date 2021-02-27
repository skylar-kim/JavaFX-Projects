package hw02;
// Calculator.java
// 2019 Barrett Koster
// HW02?
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Converter extends Application
{
    public static void main( String[] args )
    { launch(args); } // Application.launch()

    protected VBox root; // tow parts, display row and all of the buttons
    protected Button inputBox;
    protected Button outputBox;
    protected HBox controls; // 2 sections, 1. number grid and 2. vertical list of op buttons
    protected GridPane g; // number pad
    protected VBox ops; // the operations
    protected boolean clearMe = true; // next clear does a clear
    Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
	
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Calculator");
    	root = new VBox(); // allows absolute position
        Scene scene = new Scene(root,500,260);
	    stage.setScene(scene);
	    stage.show();
	    
	    makeDisplayRow();
        controls = new HBox();
        root.getChildren().add(controls);
        makeDigitGrid();
        makeOps();
    }
    
    /**
     * Fills in the 4 operations buttons on the right side of the screen
     */
    public void makeOps()
    {
        ops = new VBox();
        addOp( "lb to kg",e->{ convert(0, 0.454); });
        addOp( "kg to lb",e->{ convert(0,2.2026); });
        addOp( "F to C",e->{ convert(-32,0.555); });
        addOp( "C to F",e->{ convert(17.77,1.8); });
        controls.getChildren().add(ops);
    }
    
    /**
     * Adds one operation button to the vertical set.
     * @param s text on the button.
     * @param oal what to do when you click on the button
     */
    public void addOp(String s, EventHandler<ActionEvent> oal )
    {
    	Button b = new Button();
    	b.setFont(font);
    	b.setMinWidth(180);
    	b.setText(s);
    	ops.getChildren().add(b);
    	b.setOnAction(oal);
    }
    
    /**
     * Takes the inputBox, parses it as a number, adds 'off',
     * multiplies by 'd', and then puts answer in outputBox.
     * @param off   formula is output = (input+off) * d
     * @param d
     * note: whole thing is in try-catch, so if it screws up, just
     * reset both inputBox and outputBox
     */
    public void convert( double off, double d )
    {
    	try
    	{
	    	String s = inputBox.getText().toString();
	    	double x = Double.parseDouble(s);
	    	double c = (x+off) *d;
	    	String cs = String.format("%.1f",c);
	    	outputBox.setText(""+cs);
	    	//System.out.println("setting output to "+cs);
	    	clearMe = true; // next click clears the inputs
    	}
    	catch(Exception e) { clearNumbers(); }
    }
    
    /**
     * Make a fill in the number pad (also has '.' and '-').
     * All of these things just add themselves to the 
     * inputBox.
     */
    public void makeDigitGrid()
    {
    	//num = new Button[10];
    	g = new GridPane();
        controls.getChildren().add(g);
    	for ( int i=9; i>=0; i-- )
    	{
    		//num[i] = makeButton(""+i);
    		g.add( makeButton(""+i), (i+2)%3, (9-i)/3 );
    	}

    	g.add( makeButton("-"), 0, 3 );
    	g.add( makeButton(".") , 1, 3);
    }
    
    /**
     * creates a single button.  When you click on this button, it
     * add s to the text in inputBox.
     * @param s text on the button.
     * @return the button 
     */
    public Button makeButton( String s )
    {
    	Button b = new Button();
    	b.setText(s);
    	b.setMinWidth(90);
    	b.setFont(font);
    	b.setOnAction((ActionEvent e)->{ addDigit(s); } );
    	
    	return b;
    }
    
    /**
     * Adds the given string to the end of the string already in
     * inputBox.  
     * @param i what to put on the end of inputBox
     * note: if clearMe is true, clear the inputBox first (and unset
     * clearMe so this doesn't happen again until a convesion is done)
     */
    public void addDigit(  String i )
    {
    	if(clearMe){ clearNumbers(); }
    	String s = inputBox.getText().toString();
    	s += i;
    	inputBox.setText(s);
    }
    
    /**
     * blank out inputBox and outputBox
     */
    public void clearNumbers()
    {
    	inputBox.setText("");
    	outputBox.setText("");
    	clearMe = false;
    }
    
    /**
     * Put into root the HBox which has two places for numbers,
     * inputBox and outputBox.  
     */
    public void makeDisplayRow()
    {
        HBox displayRow = new HBox();
        
        inputBox = new Button(); inputBox.setMinWidth(225);
        inputBox.setFont(font);
        //inputBox.setText("input"); debugging
        displayRow.getChildren().add(inputBox);
        
        outputBox = new Button(); outputBox.setMinWidth(225);
        outputBox.setFont(font);
        //outputBox.setText("output"); debugging
        displayRow.getChildren().add(outputBox);
        
        root.getChildren().add(displayRow);
    }
}
