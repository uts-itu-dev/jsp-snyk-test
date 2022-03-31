package Controller;

import Model.IoTBay.Core.*;
import java.io.IOException;
import java.io.PrintWriter;
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/type");

		// Just write out the login details.
		PrintWriter out = response.getWriter();
		out.println("Login Controller: " + request.getParameter("Email") + " " + request.getParameter("Password"));
		
		/* Not implemented yet.
		
		HttpSession session = request.getSession();
		session.setAttribute("User", new User());
		
		response.sendRedirect("IoTCore/Landing.jsp");
		
		*/
	}
}
