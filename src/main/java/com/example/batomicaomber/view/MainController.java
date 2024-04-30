package com.example.batomicaomber.view;

import com.example.batomicaomber.service.AvatarService;
import com.example.batomicaomber.service.UserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {

	@FXML
	private ImageView avatarImageView;

	@FXML
	private Label usernameLabel;

	@FXML
	private Button newGameButton;

	@FXML
	private Button continueGameButton;

	@FXML
	private Button scoreboardButton;

	@FXML
	private Button settingsButton;

	@FXML
	private Button profileMenuButton;

	@FXML
	private Button exitButton;

	private UserService userService;

	public MainController() {
		userService = UserService.getInstance();
	}


	@FXML
	private void initialize() {
		usernameLabel.setText(userService.getLoggedInUser().getUsername());
		Image avatarImage = new Image(getClass().getResource("/images/images.jpg").toExternalForm());
		avatarImageView.setImage(AvatarService.getInstance().getAvatarForUser(userService.getLoggedInUser()));
	}

	@FXML
	private void newGameButtonAction() {
		// Add your logic here for the new game button
	}

	@FXML
	private void continueGameButtonAction() {
		// Add your logic here for the continue game button
	}

	@FXML
	private void scoreboardButtonAction() {
		// Add your logic here for the scoreboard button
	}

	@FXML
	private void settingsButtonAction() {
		// Add your logic here for the settings button
	}

	@FXML
	private void profileMenuButtonAction() {
		ViewLoader.newScene("profile");
	}

	@FXML
	private void exitButtonAction() {
		Platform.exit();
	}

}