package Controller;

import Model.IoTBay.Core.*;
import Model.IoTBay.Person.*;
import java.io.IOException;
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
		String phoneNumber = request.getParameter("PhoneNumber");
		String pass1 = request.getParameter("Pass1");
		String pass2 = request.getParameter("Pass2");
		String addressNum = request.getParameter("addNum");
		String addressStreetName = request.getParameter("addStreetName");
		String addressSuburb = request.getParameter("addSuburb");
		String addressPostcode = request.getParameter("addPostcode");
		String addressCity = request.getParameter("addCity");

		try {
			// If an account with email already exists, don't Register.
			if (uDB.findCustomer(email) != null) {
				response.sendRedirect("IoTCore/Register.jsp?"
					+ redirectParams(
						"err", "An account with that E-Mail already exists!",
						"First", firstName,
						"Last", lastName,
						"PhoneNumber", phoneNumber,
						"addNum", addressNum,
						"addStreetName", addressStreetName,
						"addSuburb", addressSuburb,
						"addPostcode", addressPostcode,
						"addCity", addressCity
					)
				);

				// Make sure to exit Registration.
				return;
			}
		} catch (SQLException s) {
		}
		
		if (!pass1.equals(pass2)) {
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
		} else if (Validator.containsNumber(firstName + lastName)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "Names do not have numbers!",
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (Validator.containsLetter(phoneNumber)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "Phone Numbers don't have letters!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (addressNum.length() >= 10) {
			// A street number should never be a VARCHAR greater than 10 in length.

			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "The Street Number was too long!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (Validator.containsLetter(addressNum)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "A Street Number cannot contain letters!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (Validator.containsNumber(addressStreetName)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "Street Names cannot contain numbers!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (Validator.containsNumber(addressSuburb)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "The name of a Suburb cannot have a number!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addPostcode", addressPostcode,
					"addCity", addressCity
				)
			);
		} else if (Validator.containsNumber(addressCity)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "The name of a City cannot contain a number!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addPostcode", addressPostcode
				)
			);
		} else if (Validator.containsLetter(addressPostcode)) {
			response.sendRedirect("IoTCore/Register.jsp?"
				+ redirectParams(
					"err", "Australian Postcodes do not contain letters!",
					"First", firstName,
					"Last", lastName,
					"Email", email,
					"PhoneNumber", phoneNumber,
					"addNum", addressNum,
					"addStreetName", addressStreetName,
					"addSuburb", addressSuburb,
					"addCity", addressCity
				)
			);
		} else {

			// Otherwise, officially Register these details.
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
		}
	}
}
