package com.example.batomicaomber.view;

import com.example.batomicaomber.model.User;
import com.example.batomicaomber.service.AvatarService;
import com.example.batomicaomber.service.UserService;
import com.example.batomicaomber.utils.Patterns;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupController {
	private final UserService userService;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Label errorLabel;

	public SignupController() {
		userService = UserService.getInstance();
	}


	@FXML
	private void signupButtonPress() {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();


		if (!Patterns.getMatcher(username, Patterns.USERNAME).matches()) {
			errorLabel.setText("Invalid username");
			return;
		}

		if (!Patterns.getMatcher(password, Patterns.PASSWORD).matches()) {
			errorLabel.setText("Invalid password");
			return;
		}

		if (userService.isUsernameTaken(username)) {
			errorLabel.setText("Username is taken");
			return;
		}

		User user = new User(username, password);
		userService.addUser(user);
		AvatarService.getInstance().setAvatarForUser(user, AvatarService.getInstance().randomAvatar());

		AlertCreator.informativeAlert("Success", "User created", "User created successfully",
			"start");
	}

	@FXML
	private void backButtonPress() {
		ViewLoader.newScene("start");
	}
}
