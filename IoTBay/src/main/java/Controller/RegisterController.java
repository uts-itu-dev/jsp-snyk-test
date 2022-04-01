package Controller;

import Model.IoTBay.Core.*;
import Model.IoTBay.Person.User;
import Model.IoTBay.Person.Address;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Register Controller for IoTBay. Controls the logic when registering a new
 * Customer for IoTBay.
 *
 * @author Michael Wu
 */
@WebServlet(name = "Register", value = "/Register")
public class RegisterController extends IoTWebpageBase implements IIoTWebpage {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String firstName = request.getParameter("First");
		String lastName = request.getParameter("Last");
		String email = request.getParameter("Email");
		String pass1 = request.getParameter("Pass1");
		String pass2 = request.getParameter("Pass2");
		String addressNum = request.getParameter("addNum");
		String addressStreetName = request.getParameter("addStreetName");
		String addressSuburb = request.getParameter("addSuburb");
		String addressPostcode = request.getParameter("addPostcode");
		String addressCity = request.getParameter("addCity");

		if (pass1.equals(pass2)) {
			// Just write out the details for registration.
			PrintWriter out = response.getWriter();
			out.println("Register Controller:<br>First Name | Last Name: " + firstName + " " + lastName + "<br>Email Address: "
				+ email + "<br>Password | Confirm Password: " + pass1 + " " + pass2);

			// Mark this user as in-session.
			HttpSession session = request.getSession();
			User newUser = new User(firstName, lastName, pass1, email, new Address(addressNum, addressStreetName, addressSuburb, addressPostcode, addressCity));
			session.setAttribute("User", newUser);
			
			response.sendRedirect("IoTCore/Landing.jsp");
		} else {
			response.sendRedirect("IoTCore/Register.jsp?" + 
				redirectParams(
					"err", "Passwords did not match!",
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
		}
	}

}
