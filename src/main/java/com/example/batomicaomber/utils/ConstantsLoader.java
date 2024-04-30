package com.example.batomicaomber.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConstantsLoader {
	private static ConstantsLoader instance = null;
	private Properties properties;

	private ConstantsLoader() {
		properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/constants.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConstantsLoader getInstance() {
		if (instance == null) {
			instance = new ConstantsLoader();
		}
		return instance;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getWidth() {
		return Integer.parseInt(properties.getProperty("app.width"));
	}

	public int getHeight() {
		return Integer.parseInt(properties.getProperty("app.height"));
	}

	public String getSettingPath() {
		return System.getProperty("user.home");
	}

	public Path getPath(String key) {
		return Paths.get(getSettingPath() + "/" + properties.getProperty(key));
	}
}
