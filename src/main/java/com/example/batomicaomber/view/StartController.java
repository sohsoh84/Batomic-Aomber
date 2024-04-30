package com.example.batomicaomber.view;

import javafx.fxml.FXML;

public class StartController {
	@FXML
	void signupButtonClickListener() {
		ViewLoader.newScene("signup");
	}

	@FXML
	void loginButtonClickListener() {
		ViewLoader.newScene("login");
	}
}
