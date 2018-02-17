package application;

import java.io.File;

import io.PersonDAO;
import io.TextFilePersonDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Person;

public final class LoadActionHandler implements EventHandler<ActionEvent> {
	private ObservableList<Person> persons;
	private Scene scene;

	public LoadActionHandler(ObservableList<Person> persons, Scene scene) {
		this.persons = persons;
		this.scene = scene;
	}

	@Override
	public void handle(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Person Data");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(scene.getWindow());
		if (selectedFile != null) {
			PersonDAO dao = new TextFilePersonDAO(selectedFile);
			persons.clear();
			persons.addAll(dao.load());
		}
	}

}
