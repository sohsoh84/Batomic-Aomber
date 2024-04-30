package com.example.batomicaomber.view;

import com.example.batomicaomber.service.UserService;
import com.example.batomicaomber.utils.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class ProfileController {
	private final UserService userService;

	@FXML
	private TextField newUsernameTextField;

	@FXML
	private TextField newPasswordTextField;

	@FXML
	private Label errorLabel;;

	public ProfileController() {
		userService = UserService.getInstance();
	}

	@FXML
	private void resetUsernameAndPassword() {
		String newUsername = newUsernameTextField.getText();
		String newPassword = newPasswordTextField.getText();

		if (!Patterns.getMatcher(newUsername, Patterns.USERNAME).matches()) {
			errorLabel.setText("Invalid username");
			return;
		}

		if (!Patterns.getMatcher(newPassword, Patterns.PASSWORD).matches()) {
			errorLabel.setText("Invalid password");
			return;
		}

		if (userService.isUsernameTaken(newUsername)) {
			errorLabel.setText("Username is taken");
			return;
		}

		userService.resetUsernameAndPassword(newUsername, newPassword);
		AlertCreator.informativeAlert("Success", "Username and password reset",
			"Username and password reset successfully", null);
	}

	@FXML
	private void deleteAccount() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Delete Account");
		alert.setContentText("Are you sure you want to delete your account?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			userService.deleteUser(userService.getLoggedInUser());
			ViewLoader.newScene("start");
		}
	}

	@FXML
	private void logout(ActionEvent actionEvent) {
		userService.setLoggedInUser(null);
		ViewLoader.newScene("start");
	}

	@FXML
	private void goToMainMenu(ActionEvent actionEvent) {
		ViewLoader.newScene("main");
	}

	@FXML
	private void goToAvatarMenu(MouseEvent actionEvent) {
		ViewLoader.newScene("avatar");
	}
}