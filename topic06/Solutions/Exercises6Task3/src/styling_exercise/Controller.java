package styling_exercise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;

public class Controller {

	@FXML
	private Label label1;
	@FXML
	private Button button1;
	
	@FXML
	protected void handleButton(ActionEvent event) {
		if (this.button1.getEffect() != null) {
			this.button1.setEffect(null);
			this.label1.setEffect(null);
			this.button1.setText("Add Reflection");
		} else {
			this.label1.setEffect(new Reflection());
			this.button1.setEffect(new Reflection());
			this.button1.setText("Hide Reflection");			
		}
	}
}
