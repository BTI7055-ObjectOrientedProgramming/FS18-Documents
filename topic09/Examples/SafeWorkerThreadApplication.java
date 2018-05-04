package topic09;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SafeWorkerThreadApplication extends Application {

	@Override
	public void start(Stage primaryStage) {

		TextArea textArea = new TextArea();
		textArea.setMinHeight(200);
		Button btn1 = new Button("Wait 5 seconds");
		Button btn2 = new Button("Say \'Hi\'");
		VBox root = new VBox(textArea, new HBox(btn1, btn2));

		btn1.setOnAction((ActionEvent event) -> {
			new Thread(() -> {
				Platform.runLater(() -> { 
					btn1.setDisable(true); // THREAD-SAFE!
					textArea.appendText("Waiting...\n"); // THREAD-SAFE!
				});
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				}
				Platform.runLater(() -> {
					textArea.appendText("5 seconds are over!\n"); // THREAD-SAFE!
					btn1.setDisable(false); // THREAD-SAFE!
				});
			}).start();
		});

		btn2.setOnAction((ActionEvent event) -> {
			textArea.appendText("Hi!\n");
		});

		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}