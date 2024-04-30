package com.example.batomicaomber.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
	public static final String USERNAME = "[a-zA-Z0-9]{3,20}";
	public static final String PASSWORD = "\\S+{8,20}$";

	public static Matcher getMatcher(String input, String pattern) {
		return Pattern.compile(pattern).matcher(input);
	}
}
