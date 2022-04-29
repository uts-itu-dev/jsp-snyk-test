package Model.IoTBay.Core;

import DAO.*;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * The base class for any Java-controlled webpage in IoTBay.
 *
 * @author Michael Wu
 */
public class IoTWebpageBase extends HttpServlet implements IIoTWebpage {

	public final String CSS_LINK = "<link rel=\"stylesheet\"href=\"IoTCore/IoTBayStyles.css\">";

	public static DBConnector connector;
	public static DBUsers uDB;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		
		if (session.getAttribute("UDatabase") == null) {
			try {
				connector = new DBConnector();
				uDB = new DBUsers(connector.openConnection());
				session.setAttribute("UDatabase", uDB);
				
				System.out.println("Database Set");
			} catch (ClassNotFoundException c) {
				throw new NullPointerException("Unable to make a new DBConnector. Class Not Found Exception.");
			} catch (SQLException s) {
				throw new NullPointerException("Unable to make a new DBConnector. SQL Exception.");
			}
		}
	}

	public final String redirectParams(String... params) throws IllegalArgumentException {
		if (params.length % 2 == 1) {
			throw new IllegalArgumentException("Input redirection params must have an even length! Input range: " + params.length + "\n" + Thread.currentThread().getStackTrace()[2]);
		}

		String result = "";

		for (int i = 0; i < params.length - 1; i += 2) {
			result += params[i] + "=" + params[i + 1];

			if (i != params.length - 2) {
				result += "&";
			}
		}

		return result;
	}

	final String convertStringToHTMLRequest(String s) {
		String result = "";

		for (char r : s.toCharArray()) {
			if (r == ' ') {
				result += "%20";
			} else {
				result += r;
			}
		}

		return result;
	}
}
