package Controller;

import Model.IoTBay.Core.*;
import Model.IoTBay.Person.Customer;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login Controller for IoTBay. Controls whether a Login attempt was successful.
 *
 * @author Michael Wu
 */
@WebServlet(name = "Login", value = "/Login")
public class LoginController extends IoTWebpageBase implements IIoTWebpage {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String email = request.getParameter("Email");
		String password = request.getParameter("Password");

		try {
			Customer tryLoginCredentials = uDB.findCustomer(email, password);
			if (tryLoginCredentials != null) {
				HttpSession session = request.getSession();
				session.setAttribute("User", tryLoginCredentials);
				
				response.sendRedirect("IoTCore/Landing.jsp");
			} else {
				response.sendRedirect("IoTCore/Login.jsp?"
					+ redirectParams("err", "Incorrect E-Mail or Password!", "Email", email)
				);
			}

		} catch (SQLException e) {
			System.out.println("Unable to find Email: " + email + "\n" + e);
		}
	}
}
