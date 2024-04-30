package com.example.batomicaomber.service;

import com.example.batomicaomber.model.User;
import com.example.batomicaomber.utils.ConstantsLoader;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AvatarService {
	private static AvatarService instance = null;

	private AvatarService() {
	}

	public static AvatarService getInstance() {
		if (instance == null) {
			instance = new AvatarService();
		}
		return instance;
	}

	public InputStream randomAvatar() {
		int random = (int) (Math.random() * 3) + 1;
		return AvatarService.class.getResourceAsStream("/images/avatars/avatar" + random + ".png");
	}

	public void setAvatarForUser(User user, InputStream avatarStream) {
		String id = user.getId();
		Path path = ConstantsLoader.getInstance().getPath("setting.users.avatars").resolve(id + ".png");
		try {
			System.err.println(avatarStream);
			Files.copy(avatarStream, path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Image getAvatarForUser(User user) {
		String id = user.getId();
		Path path = ConstantsLoader.getInstance().getPath("setting.users.avatars").resolve(id + ".png");
		return new Image(path.toUri().toString());
	}
}
