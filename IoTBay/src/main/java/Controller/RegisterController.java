package Controller;

import Model.IoTBay.Core.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Register Controller for IoTBay. Controls the logic when registering a new
 * Customer for IoTBay.
 *
 * @author Michael Wu
 */
@WebServlet(name = "Register", value = "/Register")
public class RegisterController extends IoTWebpageBase implements IIoTWebpage {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		// Just write out the details for registration.
		PrintWriter out = response.getWriter();
		out.println("Register Controller:<br>First Name | Last Name: " + request.getParameter("First") + " " + request.getParameter("Last") + "<br>Email Address: "
			+ request.getParameter("Email") + "<br>Password | Confirm Password: " + request.getParameter("Pass1") + " " + request.getParameter("Pass2"));
	}

}
