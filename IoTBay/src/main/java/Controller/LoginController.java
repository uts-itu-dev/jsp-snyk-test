package Controller;

import Model.IoTBay.Core.*;
import Model.IoTBay.Person.User;
import java.io.IOException;
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
		
		HttpSession session = request.getSession();
		session.setAttribute("User", new User(null, null, password, email));
		
		response.sendRedirect("IoTCore/Landing.jsp");
	}
}
