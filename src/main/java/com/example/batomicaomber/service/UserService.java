package com.example.batomicaomber.service;

import com.example.batomicaomber.model.User;
import com.example.batomicaomber.utils.ConstantsLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class UserService {
	private final ArrayList<User> allUsers;
	private User loggedInUser;

	private static UserService instance;

	private UserService() {
		allUsers = new ArrayList<>();
		Path usersPath = ConstantsLoader.getInstance().getPath("setting.users.path");
		try {
			Files.list(usersPath).forEach(path -> allUsers.add(User.loadUser(path)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public User getUserByUsername(String username) {
		return allUsers.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
	}

	public void addUser(User user) {
		allUsers.add(user);
		User.saveUser(user);
	}

	public boolean isUsernameTaken(String username) {
		return allUsers.stream().anyMatch(user -> user.getUsername().equals(username));
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void deleteUser(User user) {
		allUsers.remove(user);
		File file = new File(ConstantsLoader.getInstance()
			.getPath("setting.users.path").resolve(user.getId()).toString());
		file.delete();
	}


	public void setLoggedInUser(User user) {
		loggedInUser = user;
	}

	public void resetUsernameAndPassword(String newUsername, String newPassword) {
		loggedInUser.setUsername(newUsername);
		loggedInUser.setPassword(newPassword);
		User.saveUser(loggedInUser);
	}
}
