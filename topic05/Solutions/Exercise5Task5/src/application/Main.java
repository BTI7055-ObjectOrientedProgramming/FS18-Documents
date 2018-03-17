package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		PersonRegistryListSceneBuilder sceneBuilder = new PersonRegistryListSceneBuilder();
		Scene scene = sceneBuilder.createScene();
		primaryStage.setTitle("Person Registry");
		primaryStage.getIcons().add(new Image("/Icon.jpg"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
