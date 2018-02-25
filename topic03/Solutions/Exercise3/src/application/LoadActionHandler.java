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
import persistence.textfile.TextFileParseException;
import persistence.textfile.TextFilePersonDAO;

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
			try {
				persons.addAll(dao.load());
			} catch (TextFileParseException e) {
				String filePath = e.getFilePath();
				int lineNb = e.getLineNb();
				String line = e.getLine();
				String message = "Check line " + lineNb + " in file " + filePath + ":\n" + line;
				Alert alert = AlertBuilder.createErrorAlert("Load error", "Cannot parse data", message);
				alert.showAndWait();
			} catch (PersistenceException e) {
				Alert alert = AlertBuilder.createErrorAlert("Load error", "Cannot load from file", "Check file path");
				alert.showAndWait();
			}
		}
	}

}
