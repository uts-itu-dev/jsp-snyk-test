package Controller;

import Model.IoTBay.Core.*;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Person.Staff;
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
			if (tryLoginCredentials != null) { // Found Customer.
				HttpSession session = request.getSession();
				session.setAttribute("User", tryLoginCredentials);

				response.sendRedirect("IoTCore/Redirector.jsp?"
					+ redirectParams(
						"HeadingMessage", "Welcome, " + tryLoginCredentials.getFirstName() + "!",
						"Message", "Please wait while we redirect you...")
				);

			} else { // If no Customer was found, try Staff.

				Staff tryStaffCredentials = uDB.findStaff(email, password);

				if (tryStaffCredentials != null) { // Found Staff.
					HttpSession session = request.getSession();
					session.setAttribute("User", tryStaffCredentials);

					response.sendRedirect("IoTCore/Redirector.jsp?"
						+ redirectParams(
							"HeadingMessage", "Welcome, " + tryStaffCredentials.getFirstName() + "!",
							"Message", "Please wait while we redirect you...")
					);
					
				} else { // Neither Customer or Staff exists with this Email.

					response.sendRedirect("IoTCore/Login.jsp?"
						+ redirectParams("err", "Incorrect E-Mail or Password!", "Email", email)
					);
				}
			}

		} catch (SQLException e) {
			System.out.println("Unable to find Email: " + email + "\n" + e);
		}
	}
}
