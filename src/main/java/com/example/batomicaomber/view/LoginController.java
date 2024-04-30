package com.example.batomicaomber.view;

import com.example.batomicaomber.model.User;
import com.example.batomicaomber.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;
	@FXML
	private Label errorLabel;

	private final UserService userService;

	public LoginController() {
		userService = UserService.getInstance();
	}

	@FXML
	private void loginButtonPress() {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();

		User user = userService.getUserByUsername(username);
		if (user == null) {
			errorLabel.setText("User not found");
			return;
		}

		if (!user.getPassword().equals(password)) {
			errorLabel.setText("Incorrect password");
			return;
		}

		userService.setLoggedInUser(user);
		ViewLoader.newScene("profile");
	}

	@FXML
	private void backButtonPress() {
		ViewLoader.newScene("start");
	}
}
