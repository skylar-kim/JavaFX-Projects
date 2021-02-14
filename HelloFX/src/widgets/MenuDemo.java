package widgets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuDemo extends Application
{
	VBox root1;
	Scene scene1;
	Stage theStage;
	
   public static void main( String[] args )
   { launch(args); }
   
   @Override
   public void start( Stage stage )
   {
   	theStage = stage;
   	stage.setTitle("Menu Demo");
   	root1 = new VBox();
   	scene1 = new Scene( root1, 400, 300 );
   	stage.setScene( scene1 );
   	stage.show();

      MenuBar mb = new MenuBar();
      root1.getChildren().add(mb);
      Menu fm = new Menu("file");
      mb.getMenus().add(fm);
      fm.getItems().add(new MenuItem("open"));
      fm.getItems().add(new MenuItem("close"));
      //fm.getItems().add(new MenuItem("quit"));
        MenuItem qmi = new MenuItem("quit");
        fm.getItems().add(qmi);
      Menu em = new Menu("edit");
      mb.getMenus().add(em);
      em.getItems().add(new MenuItem("copy"));
      em.getItems().add(new MenuItem("paste"));
      em.getItems().add(new MenuItem("delete"));
      em.getItems().add(new MenuItem("conceptualize"));
      
      // attach an action to something
      qmi.setOnAction( g->{ System.out.println("quitter!");} );
      
      // add a submenu
      Menu howMenu = new Menu("save as ...");
      howMenu.getItems().add(new MenuItem("txt"));
      howMenu.getItems().add(new MenuItem("pdf"));
      fm.getItems().add(howMenu);
   }

}
