package PaintKim;
/* Name: Skylar Kim
 * USCID: 2353559966
 * Course: ITP 368
 * Semester: Spring 2021
 * Instructor: Professor Barrett Koster
 * */
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class GeneralShape extends Group {
	double mouseX;
	double mouseY;
	abstract void setColor(Color c);
	abstract void setHighlight(boolean bool);
	abstract void dragged(Point p, MouseEvent m);
	protected abstract boolean zatYou(Point p);
	abstract void setMouseX(double x);
	abstract void setMouseY(double y);
	public void addTextLabel(String textFieldInput) {
		// TODO Auto-generated method stub
		
	}
//	abstract String convertToString();
	public abstract String convertToString();
	
}
