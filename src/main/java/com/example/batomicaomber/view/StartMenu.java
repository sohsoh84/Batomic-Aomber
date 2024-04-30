package com.example.batomicaomber.view;

import com.example.batomicaomber.utils.ConstantsLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;

public class StartMenu extends Application {
	static ConstantsLoader constantsLoader = ConstantsLoader.getInstance();

	@Override
	public void start(Stage stage) throws IOException {
		ViewLoader.setStage(stage);
		stage.setTitle(constantsLoader.getProperty("app.title"));
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
		stage.setAlwaysOnTop(false);

		ViewLoader.newScene("start");
	}

	public static void main(String[] args) {
		initSettings();
		launch();
	}

	private static void initSettings() {
		if (!Files.exists(constantsLoader.getPath("setting.path"))) {
			try {
				Files.createDirectories(constantsLoader.getPath("setting.path"));
				Files.createDirectories(constantsLoader.getPath("setting.users.path"));
				Files.createDirectories(constantsLoader.getPath("setting.users.avatars"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}