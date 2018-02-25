package application;
	
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Person;
import persistence.PersonDAO;
import persistence.serialized.SerializingPersonDao;
import persistence.textfile.TextFilePersonDAO;


public final class Main extends Application {
//	private PersonDAO dao = new TextFilePersonDAO("persons.txt");
	private PersonDAO dao = new SerializingPersonDao("persons.bin");
	private List<Person> persons;
	
	@Override
	public void start(Stage primaryStage) {
		PersonRegistryListSceneBuilder sceneBuilder = new PersonRegistryListSceneBuilder();
		Scene scene = sceneBuilder.createScene(persons);
		primaryStage.setTitle("Person Registry");
		primaryStage.getIcons().add(new Image("/Icon.jpg"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		persons = dao.load();
	}

	@Override
	public void stop() throws Exception {
		dao.save(persons);
	}
	
}
