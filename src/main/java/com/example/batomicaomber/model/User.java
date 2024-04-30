package com.example.batomicaomber.model;

import com.example.batomicaomber.utils.ConstantsLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class User {
	private String username;
	private String password;
	private String id;

	private static String getRandomId() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			builder.append((char) (Math.random() * 26 + (int)('a')));
		}

		return builder.toString();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.id = getRandomId();
	}

	private User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public static User loadUser(Path path) {
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String username = reader.readLine();
			String password = reader.readLine();
			String id = path.getFileName().toString();
			return new User(id, username, password);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void saveUser(User user) {
		Path path = ConstantsLoader.getInstance().getPath("setting.users.path").resolve(user.getId());
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(user.getUsername());
			writer.newLine();
			writer.write(user.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String newUsername) {
		username = newUsername;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}
}
