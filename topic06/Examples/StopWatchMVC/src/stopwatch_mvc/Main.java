package stopwatch_mvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {

		// Create model
		Timer timer = new Timer(500);
		// Create first view
		new Stopwatch(timer, "yellow");
		
//		Timer timer2 = new Timer(100);
		// Create second view
		new Stopwatch(timer, "blue");
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}