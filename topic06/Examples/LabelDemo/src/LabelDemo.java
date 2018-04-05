import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class LabelDemo extends Application {

   public void start(Stage primaryStage) throws Exception {
      final TilePane pane = new TilePane();
      pane.getChildren().add(new Label("I'm a Label"));
      pane.getChildren().add(new Label("I'm another Label"));
      primaryStage.setScene(new Scene(pane, 400, 50));
      primaryStage.setTitle("Label Demo");
      primaryStage.setResizable(true);
      primaryStage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
