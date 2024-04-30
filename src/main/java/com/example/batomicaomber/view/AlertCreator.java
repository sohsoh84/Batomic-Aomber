package com.example.batomicaomber.view;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertCreator {
	public static void informativeAlert(String title, String header, String content, String nextMenuName) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.getDialogPane().getStylesheets().add(AlertCreator.class
			.getResource("/CSS/alert.css").toExternalForm());
		alert.initOwner(ViewLoader.getStage());
		alert.initModality(Modality.APPLICATION_MODAL);
		if (nextMenuName != null) alert.setOnCloseRequest(event -> ViewLoader.newScene(nextMenuName));
		alert.setGraphic(null);
		alert.show();
	}
}
