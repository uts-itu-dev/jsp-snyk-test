/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates any string.
 *
 * @author Michael Wu
 */
public class Validator {

	public static final String NAME = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
	public static final String EMAIL = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
	public static final String PASSWORD = "[a-z0-9]{4,}";

	public static boolean containsLetter(String s) {
		for (char c : s.toCharArray()) {
			if (Character.isLetter(c)) {
				return true;
			}
		}

		return false;
	}

	public static boolean containsNumber(String s) {
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}

		return false;
	}
	
	public static boolean validate(String string, String regularExpression) {
		Pattern rx = Pattern.compile(regularExpression);
		return rx.matcher(string).matches();
	}
}
