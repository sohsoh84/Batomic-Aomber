package com.example.batomicaomber.view;

import com.example.batomicaomber.service.AvatarService;
import com.example.batomicaomber.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AvatarController {

	@FXML
	private ImageView mainImageView;

	@FXML
	private ImageView smallImageView1;

	@FXML
	private ImageView smallImageView2;

	@FXML
	private ImageView smallImageView3;

	@FXML
	private Button chooseFileButton;

	private final ImageView[] allImageViews = new ImageView[3];

	private AvatarService avatarService;
	private InputStream newAvatarStream;


	public AvatarController() {
		avatarService = AvatarService.getInstance();
		newAvatarStream = null;
	}

	@FXML
	private void initialize() {
		mainImageView.setImage(avatarService.getAvatarForUser(UserService.getInstance().getLoggedInUser()));
		allImageViews[0] = smallImageView1;
		allImageViews[1] = smallImageView2;
		allImageViews[2] = smallImageView3;

		for (int i = 0; i < 3; i++) {
			allImageViews[i].setImage(new Image(getClass().getResource("/images/avatars/avatar" + (i + 1) + ".png")
				.toExternalForm()));
		}
	}

	@FXML
	private void chooseFileButtonAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			String newAvatarPath = file.getAbsolutePath();
			try {
				newAvatarStream = Files.newInputStream(Paths.get(newAvatarPath));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			Image avatar = new Image("file:" + newAvatarPath);
			mainImageView.setImage(avatar);
		}
	}

	@FXML
	private void sampleAvatarClick(MouseEvent mouseEvent) {
		ImageView imageView = (ImageView) mouseEvent.getSource();
		newAvatarStream = getClass().getResourceAsStream("/images/avatars/avatar" +
			imageView.getId().charAt(imageView.getId().length() - 1) + ".png");
		mainImageView.setImage(new Image(imageView.getImage().getUrl()));
	}

	@FXML
	private void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			File file = event.getDragboard().getFiles().get(0);
			if (file.getName().endsWith(".png")) {
				event.acceptTransferModes(TransferMode.COPY);
			}
		} else {
			event.consume();
		}
	}

	@FXML
	private void handleDragDropped(DragEvent event) {
		File file = event.getDragboard().getFiles().get(0);
		String newAvatarPath = file.getAbsolutePath();
		try {
			newAvatarStream = Files.newInputStream(Paths.get(newAvatarPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Image avatar = new Image("file:" + newAvatarPath);
		mainImageView.setImage(avatar);
	}

	public void apply(MouseEvent mouseEvent) {
		avatarService.setAvatarForUser(UserService.getInstance().getLoggedInUser(), newAvatarStream);
		ViewLoader.newScene("profile");
	}

	public void cancel(MouseEvent mouseEvent) {
		ViewLoader.newScene("profile");
	}
}