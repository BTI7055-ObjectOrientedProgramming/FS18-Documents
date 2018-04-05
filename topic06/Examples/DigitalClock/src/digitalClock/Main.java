package digitalClock;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main program Digital clock
 * @author lua1
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox pane = new VBox(5);
		pane.setPadding(new Insets(5));
		pane.setAlignment(Pos.CENTER);

		Time time = new Time();
		DigitalClock clock = new DigitalClock(time);

		pane.getChildren().add(clock);

		primaryStage.setScene(new Scene(pane, 225, 100));

		// set properties title and resizeable
		primaryStage.setTitle("DigitalClock (Pull)");
		primaryStage.setResizable(true);

		// Position and show
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}