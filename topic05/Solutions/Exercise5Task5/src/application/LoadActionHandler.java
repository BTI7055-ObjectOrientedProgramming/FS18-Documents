package application;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;
import persistence.stax.StAXPersonDAO;

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
		fileChooser.getExtensionFilters().add(new ExtensionFilter("XML files", "*.xml"));
		File selectedFile = fileChooser.showOpenDialog(scene.getWindow());
		if (selectedFile != null) {
			persons.clear();
			try {
				PersonDAO dao = new StAXPersonDAO(selectedFile);
				persons.addAll(dao.load());
			} catch (PersistenceException e) {
				e.printStackTrace();
				Alert alert = AlertBuilder.createErrorAlert("Load error", "Cannot load from file", e.getMessage());
				alert.showAndWait();
			}
		}
	}

}
