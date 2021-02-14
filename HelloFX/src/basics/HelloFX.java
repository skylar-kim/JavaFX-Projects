package basics;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloFX extends Application
{
   @Override
   public void start( Stage stage )
   {
	  stage.setTitle("Hello World in JavaFX"); 
	  stage.show();
   }
   public static void main( String[] args )
   {
	   launch(args );
   }
}
