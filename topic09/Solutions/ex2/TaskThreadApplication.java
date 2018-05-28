package topic09.ex2;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskThreadApplication extends Application {

	private TextArea textArea;
	private Button btn1a;
	private Button btn1b;
	private Button btn2;
	private Task<Void> task;	
	
	public TaskThreadApplication() {
		this.textArea = new TextArea();
		this.textArea.setMinHeight(200);
		this.btn1a = new Button("Wait 5 seconds");
		this.btn1b = new Button("Cancel waiting");
		this.btn1b.setDisable(true);
		this.btn2 = new Button("Say \'Hi\'");

		this.btn1a.setOnAction((ActionEvent event) -> {
			this.task = new Task<Void>(){

				@Override
				protected Void call() throws Exception {
					Thread.sleep(5000);
					return null;
				}
				
				@Override
				protected void running() { 
					btn1a.setDisable(true); 
					btn1b.setDisable(false);
					textArea.appendText("Waiting...\n"); 
				}
				
				@Override
				protected void succeeded() {
					textArea.appendText("5 seconds are over!\n"); 
					btn1a.setDisable(false); 
					btn1b.setDisable(true);
				}
				
				@Override
				protected void cancelled() {
					textArea.appendText("Wating cancelled!\n"); 
					btn1a.setDisable(false); 
					btn1b.setDisable(true);
				}

			};
			new Thread(this.task).start();
		});

		this.btn1b.setOnAction((ActionEvent event) -> {
			this.task.cancel();
		});
		
		this.btn2.setOnAction((ActionEvent event) -> {
			this.textArea.appendText("Hi!\n");
		});
	}


	@Override
	public void start(Stage primaryStage) {

		Parent root = new VBox(this.textArea, new HBox(this.btn1a, this.btn1b, this.btn2));
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}