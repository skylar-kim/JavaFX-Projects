package basics;

 
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

public class Flower extends Application
{
	Random r;
	
	public static void main( String[] args )
	{ launch(args);}
	
    @Override
    public void start(Stage stage)
    { initUI(stage); }

    private void initUI(Stage stage)
    {   
    	r = new Random();
    	Group root = new Group();

    	// loop for flowers, so ... 50 flowers
        for ( int j=0; j<50; j++ )
        {
	            double[] xy = new double[6];
	     double rbase, gbase, bbase;
	     rbase = Math.random()* 0.8;
	     gbase = Math.random()* 0.8;
	     bbase = Math.random()* 0.8;
	            
	     // flower center
	     xy[0] = r.nextInt(500);  xy[1] = r.nextInt(500);
	            
        	for ( double angle=0; angle<6.28; angle += 0.3 ) // loops on petals
        	{
	            Polygon p = new Polygon();
	        	
	            xy[2] = xy[0] + 50 * Math.cos( angle );
	            xy[3] = xy[1] + 50 * Math.sin(angle);
	            xy[4] = xy[0] + 50 * Math.cos( angle + 0.18);
	            xy[5] = xy[1] + 50 * Math.sin(angle + 0.18);
	            
	            Double[] d = new Double[6];
	            for ( int i=0; i<6; i++ )
	            { d[i] = new Double(xy[i]);}
	            
	            p.getPoints().addAll(d);
	        
	           p.setFill( new Color ( 
	              rbase + Math.random()* 0.2, 
	              gbase + Math.random()* 0.2, 
	              bbase + Math.random()* 0.2, 
	                              1)  );
	        	
	        	
	            root.getChildren().add(p);
        	}
        }

        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("flower");
	        stage.setScene(scene);
	        stage.show();
   }
}
