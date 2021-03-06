package L07;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ElfKimS extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
    public void start(Stage stage) {
		Pane root = new Pane();
		stage.setTitle("Elf Lab");
		Scene scene = new Scene(root, 700,700);
		stage.setScene(scene);
		stage.show();
		
		
		int initialX = 10;
		int initialY = 10;
		
		for (int i = 0; i < 5; i++) {
			ElfPane newElfPane = new ElfPane();
			newElfPane.setLayoutX(initialX);
			newElfPane.setLayoutY(initialY);
			
			root.getChildren().add(newElfPane);
			
			initialX += 150;
		}
    
		
	}
	
	public class ElfPane extends Pane {
		Rectangle r;
		Circle c;
		private double x = 0;
		private double y = 0;
		private double mousex = 0;
		private double mousey = 0;
		
		
		public ElfPane() {
			setPrefSize(100,100);
			setStyle("-fx-background-color: pink;");
			
			// add face
			c = new Circle();
			c.setRadius(20);
			c.setCenterX(50);
			c.setCenterY(30);
			
			// add body
			r = new Rectangle(50,50);
			r.setX(25);
			r.setY(50);
			
			// add the shapes to the pane
			getChildren().add(c);
			getChildren().add(r);
			
			
			
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
		
		
	}
}
