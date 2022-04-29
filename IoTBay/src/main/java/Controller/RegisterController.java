package Controller;

import DAO.*;
import Model.IoTBay.Core.*;
import Model.IoTBay.Person.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Register Controller for IoTBay. Controls the logic when registering a new
 * Customer for IoTBay.
 *
 * @author Michael Wu
 */
@WebServlet(name = "Register", value = "/Register")
public class RegisterController extends IoTWebpageBase implements IIoTWebpage {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String firstName = request.getParameter("First");
		String lastName = request.getParameter("Last");
		String email = request.getParameter("Email");
		String phoneNumber = request.getParameter ("PhoneNumber");
		String pass1 = request.getParameter("Pass1");
		String pass2 = request.getParameter("Pass2");
		String addressNum = request.getParameter("addNum");
		String addressStreetName = request.getParameter("addStreetName");
		String addressSuburb = request.getParameter("addSuburb");
		String addressPostcode = request.getParameter("addPostcode");
		String addressCity = request.getParameter("addCity");

		if (pass1.equals(pass2)) {

			System.out.println("EXEC");
			// Mark this user as in-session.
			HttpSession session = request.getSession();
			Customer newCustomer = new Customer(firstName, lastName, pass1, email, new Address(addressNum, addressStreetName, addressSuburb, addressPostcode, addressCity), phoneNumber);
			
			try {
				uDB.add(newCustomer);
			} catch (SQLException e) {
				System.out.println("SQL\n" + e);
			}

			session.setAttribute("User", newCustomer);

			response.sendRedirect("IoTCore/Landing.jsp");
		} else {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "Passwords did not match!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		}
	}

}
