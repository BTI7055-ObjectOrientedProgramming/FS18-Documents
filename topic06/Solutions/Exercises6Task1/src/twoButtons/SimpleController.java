package twoButtons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimpleController {
	private int countA;
	private int countB;

	@FXML
	private Label labelA;
	
	@FXML
	private Label labelB;
	
	@FXML
	protected void handleClickA(ActionEvent event) {
		countA++;
		labelA.setText("A = " + countA);
	}
	
	@FXML
	protected void handleClickB(ActionEvent event) {
		countB++;
		labelB.setText("B = " + countB);
	}
}
