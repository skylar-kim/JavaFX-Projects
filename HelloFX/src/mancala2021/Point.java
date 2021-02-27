package mancala2021;

public class Point
{
   private double x, y; // in screen coords
	    /**
	     * Constructor for objects of class Point
	     */
	    public Point( double x, double y )
	    {
	        this.x = x;
	        this.y = y;
	    }
	    
	    public Point()
	    { this(0,0); }

	    public double getX() { return x; }
	    public double getY() { return y; }
	    public void setX(double x1) { x = x1; }
	    public void setY(double y1) { y = y1; }
}

