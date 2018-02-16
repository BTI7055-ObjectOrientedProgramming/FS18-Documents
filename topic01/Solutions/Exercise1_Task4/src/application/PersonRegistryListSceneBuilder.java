package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public final class PersonRegistryListSceneBuilder {
	Scene createScene(List<Person> persons) {
		BorderPane root = new BorderPane();
		root.setMinWidth(600);
		root.setPadding(new Insets(10));
		Insets insets = new Insets(10);
		
		Label listViewLabel = new Label("Registered Persons:");
		root.setTop(listViewLabel);
		BorderPane.setMargin(listViewLabel, insets);
		
		ObservableList<Person> observablePersons = FXCollections.observableList(persons);
		ListView<Person> personListView = new ListView<>(observablePersons);
		root.setCenter(personListView);
		BorderPane.setMargin(personListView, insets);
		
		Button addPersonButton = new Button("Add person ...");
		addPersonButton.setOnAction(new AddActionHandler(observablePersons));
		
		root.setBottom(addPersonButton);
		BorderPane.setMargin(addPersonButton, insets);
		BorderPane.setAlignment(addPersonButton, Pos.BOTTOM_RIGHT);
		
		Scene scene = new Scene(root);
		return scene;
	}
}
