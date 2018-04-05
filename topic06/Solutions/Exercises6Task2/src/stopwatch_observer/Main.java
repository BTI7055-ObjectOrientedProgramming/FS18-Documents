package stopwatch_observer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {

		Timer timer = new Timer(500);
		new Stopwatch(timer);
		new Stopwatch(timer);
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}