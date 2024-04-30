package com.example.batomicaomber.view;

import com.example.batomicaomber.utils.ConstantsLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {
	static Stage stage;

	public static void setStage(Stage stage) {
		ViewLoader.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void newScene(String menuName) {
		FXMLLoader fxmlLoader = new FXMLLoader(StartMenu.class.getResource("/FXML/" + menuName + "-view.fxml"));
		Scene scene = null;
		try {
			scene = new Scene(fxmlLoader.load(), ConstantsLoader.getInstance().getWidth(),
				ConstantsLoader.getInstance().getHeight());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		scene.getStylesheets().add(StartMenu.class.getResource("/CSS/stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
