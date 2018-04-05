package digitalClock;

import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * UI element (subclass of Label) to represent the DigitalClock
 * Acts as Observer
 * 
 * @author lua1
 */
public class DigitalClock extends Label implements Observer {
	final private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");

	public DigitalClock(final Time time) {
		time.addObserver(this);

		this.setStyle("-fx-font-family: \"Dialog\";" + " -fx-font-size: 24pt;" + "-fx-font-weight: bold;"
				+ "-fx-effect: dropshadow( three-pass-box, black, 5, 0.2, 2, 3);"
				+ "-fx-text-fill: linear-gradient(to left, darkviolet 15%, yellow 45%, red 75%, firebrick 85%);"
				+ "-fx-background-color: linear-gradient(darkblue 10%, #ABCDEF 65%, dodgerblue 90%);");
	}

	public void update(Observable o, Object arg) {
		// Make sure, the GUI is updated in the JavaFX Application Thread!
		Time time = (Time) o;
		Platform.runLater(() -> {
			this.setText(sdf.format(time.getTime()));
		});
	}
}
