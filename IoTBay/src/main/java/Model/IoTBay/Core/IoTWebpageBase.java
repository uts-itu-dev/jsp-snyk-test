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
				connectToDB();
				
				session.setAttribute("UDatabase", uDB);
				session.setAttribute("Customers", uDB.customers);
				session.setAttribute("Staff", uDB.staff);
				session.setAttribute("Products", uDB.products);

				System.out.println("Database Set");
			} catch (SQLException s) {
				throw new NullPointerException("IoTWebpageBase::doPost. SQL Exception. " + s);
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

	public static final void connectToDB() throws SQLException {
		try {
			System.out.println("Connecting to DB...");
			
			connector = new DBConnector();
			uDB = new DBUsers(connector.openConnection());
			
			System.out.println("DB Connected.");
		} catch (ClassNotFoundException c) {
			throw new NullPointerException("IoTWebpageBase::connectToDB. Class Not Found Exception.");
		} catch (SQLException s) {
			throw new NullPointerException("IoTWebpageBase::connectToDB. SQL Exception. " + s);
		}
	}
}
