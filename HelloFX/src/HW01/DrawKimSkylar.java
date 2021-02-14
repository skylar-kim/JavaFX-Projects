package HW01;
import javafx.application.Application;
import java.util.ArrayList;
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
import javafx.stage.Stage;

// Name: Skylar Kim
// Class: ITP 368
// HW01: Art
// Description: draws a randomized mandala each time the program is run.

public class DrawKimSkylar extends Application{
	Pane root;
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		stage.setTitle("Skylar's Mandala Art");
		root = new Pane();
		Scene scene = new Scene (root, 1024, 1024);
		stage.setScene(scene);
		stage.show();
		
		stage.setOnCloseRequest(e->{Platform.exit(); System.exit(0); } );
		
		drawMandala();
	}
	
	
	
	public void drawMandala() {
		// calls drawTriangle with randomized values 7 times
		for (int i = 0; i < 7; i++) {
			double randomTriangleRadius = Math.random() * 1000;
			double randomCircleRadius = Math.random() * 1000;
			double randomRColor = Math.random();
			double randomGColor = Math.random();
			double randomBColor = Math.random();
			
			drawTriangle(512, 512, randomTriangleRadius, randomCircleRadius, 
					randomRColor, randomGColor, randomBColor);
			
		}
		

	}
	public void drawTriangle(double xCenter, double yCenter, double triangleR, double circR, 
			double rColorConst, double gColorConst, double bColorConst) {
		
		double x = xCenter;
		double y = yCenter;
		double r = triangleR;
		double circleR = circR;
		
		double rColor = Math.random() * rColorConst;
		double gColor = Math.random() * gColorConst;
		double bColor = Math.random() * gColorConst;
		
		// outer for loop draws triangles in a circle 
		for (double startingCircAngle = 0; startingCircAngle < 6.28319; startingCircAngle +=1.0472) {
			double xCenterTri = x + circleR * Math.cos(startingCircAngle);
			double yCenterTri = y + circleR * Math.sin(startingCircAngle);
			// drawing equilateral triangles
			for (double startingAngle = 0; startingAngle < 2.0944; startingAngle+= 0.0872665) {
				
				for (double angle = 0; angle < 6.2831; angle += 2.0944) {
					Line line = new Line();
					line.setStartX(xCenterTri + r * Math.cos(angle + startingAngle));
					line.setStartY(yCenterTri + r * Math.sin(angle + startingAngle));
					line.setEndX(xCenterTri+ r * Math.cos(angle + startingAngle + 2.0944));
					line.setEndY(yCenterTri + r * Math.sin(angle + startingAngle + 2.0944));
					
					line.setStroke( new Color ( rColor, gColor, bColor, 1));
					
					root.getChildren().add(line);
					
				
				}
				
			}
		}
		
	}
}
