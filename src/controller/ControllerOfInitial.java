package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ControllerOfInitial {

	public void exit(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.close();
		System.exit(0);

	}

}
