package PaintKim;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import mousing.Point;

public class DraggableRectangle extends Rectangle{
	Point imAt;
	private double x = 0;
	private double y = 0;
	private double mousex = 0;
	private double mousey = 0;
	
	public DraggableRectangle(double xCoord, double yCoord) {
		super(xCoord, yCoord);
		
		onMousePressedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				
				// record the current mouse X and Y position
				mousex = m.getSceneX();
				mousey = m.getSceneY();
				
				x = getLayoutX();
				y = getLayoutY();
				

			}
		});
		
		onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				double distx = m.getSceneX() - mousex;
				double disty = m.getSceneY() - mousey;
				
				x += distx;
				y += disty;
				
				setLayoutX(x);
				setLayoutY(y);
				
				
				mousex = m.getSceneX();
				mousey = m.getSceneY();
				
				m.consume();
			}
		});
		
		onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// not really do anything...
			}
		});
	}
	public void setimAt(double x, double y) {
		imAt = new Point(x, y);
	}
	
	public void dragged( Point p )
    {
 	   setLayoutX( p.getX() );
 	   setLayoutY( p.getY() );
 	   imAt = p;
 	   System.out.println("imAt x: " + imAt.getX());
 	   System.out.println("imAt Y: " + imAt.getY());
    }
	
	public boolean zatYou( Point m )
    {
    	System.out.println("zatYou x="+m.getX()+" y="+m.getY());
    	double d = Math.abs(m.getX()-imAt.getX())
    			  +Math.abs(m.getY()-imAt.getY());
    	System.out.println("d: " + d);
    	boolean ret = (d<50);
    	System.out.println("zatYou ret="+ret);
    	return ret;
    }
	
	
}
