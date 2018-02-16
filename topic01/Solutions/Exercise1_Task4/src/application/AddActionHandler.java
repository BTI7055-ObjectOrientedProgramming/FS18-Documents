package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class AddActionHandler implements EventHandler<ActionEvent> {
	private List<Person> persons;

	public AddActionHandler(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Stage addDialog = new Stage();
		addDialog.setTitle("Add person");
		addDialog.getIcons().add(new Image("/icon.jpg"));
		addDialog.initModality(Modality.APPLICATION_MODAL);
		
		Scene addPersonScene = AddPersonSceneBuilder.createScene(persons);
		addDialog.setScene(addPersonScene);
		
		addDialog.showAndWait();
	}

}
