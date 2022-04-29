/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Michael Wu
 */
public class Validator {

	public static boolean containsLetter(String s) {
		for (char c : s.toCharArray())
			if (Character.isLetter(c))
				return true;

		return false;
	}

	public static boolean containsNumber(String s) {
		for (char c : s.toCharArray())
			if (Character.isDigit(c))
				return true;
				
		return false;
	}
}
