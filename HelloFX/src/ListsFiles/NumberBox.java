// NumberBox.java
// Barrett Koster 2019
// used with ListPlay ... demos list objects that
// can be ordered in different ways.

package ListsFiles;


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


public class NumberBox extends Rectangle
   //implements Comparable
{
   protected Text t;
   protected static int maxCount=0;
   protected int count =0;
   
   public NumberBox()
   {
	   super(18,18);
	   t = new Text();
	   count = ++maxCount;
	   t.setText(""+count);
   }
   
   /*
   @Override
   public int compareTo( Object other )
   {
	   NumberBox other1 = (NumberBox)other;
	   return (int)(getX() - other1.getX());
   }
   */
   
   public void setNBX( double x )
   {
	   super.setX(x);
	   t.setX(x);
   }
   
   public void setNBY( double y )
   {
	   super.setY(y);
	   t.setY(y);
   }
   
   // make the text match the position of the Rectangle
   public void match()
   {
	   t.setX(getX());
	   t.setY(getY());
   }
   
   public void setText( String s ) { t.setText(s); }
   public void setCount( int c ) { count = c; t.setText(""+c);}
   
   public Text getT() { return t; }
}
