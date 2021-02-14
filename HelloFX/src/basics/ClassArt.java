package basics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import java.util.*;
import javafx.scene.paint.*;


public class ClassArt extends Application {
	// global root for this program
	// Pane is a superclass, let's you do geometry
	// Pane vs Group: group is a subclass of pane
	// Pane is needed for buttons, pane can be %used for most things
	// 
	Pane root;
	public static void main(String [] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		// must have these 5 things always!!!
		stage.setTitle("Sierpinkski Gasket");
		root = new Pane();
		Scene scene = new Scene(root, 600,600);
		stage.setScene(scene);
		stage.show();
		
		stage.setOnCloseRequest(e->{Platform.exit(); System.exit(0); } );
		
		Point[] corner = new Point[3];
		corner[0] = new Point(300,20);
		corner[1] = new Point(20, 250);
		corner[2] = new Point(450, 300);
		
		drawGasket(corner);
		
	}
	
	// draw the Sierpinski gasket
	public void drawGasket(Point[] corner ) {
	
		Point p = new Point(10,20);
		
		for (int i=0; i<10000; i++) {
			int which;
			
			which = (int)(Math.random()*3);
			p.halfWay(corner[which]);
			Circle c = new Circle();
			c.setCenterX(p.getX() );
			c.setCenterY(p.getY());
			c.setRadius(1);
			root.getChildren().add(c);
		}
	}
	
	public class Point{
		double x, y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public double getX() { return x;}
		public double getY() { return y;}
		
		public void halfWay (Point target) {
			x = (x+ target.x)/2;
			y = (y+ target.y)/2;
		}
	}
	
	
}
