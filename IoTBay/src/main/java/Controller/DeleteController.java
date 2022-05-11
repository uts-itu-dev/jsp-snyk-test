package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Wu
 */
@WebServlet(name = "Delete", value = "/Delete")
public class DeleteController extends IoTWebpageBase implements IIoTWebpage {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		System.out.println("Deleting");

		String email = request.getParameter("Email");
		String type = request.getParameter("Type");
		
		if (email == null)
			throw new NullPointerException("DeleteController::doPost() -> Email is null!");
		if (type == null)
			throw new NullPointerException("DeleteController::doPost() -> Type is null!");

		try {
			uDB.remove(type, email);
			
			request.getSession().invalidate();
			
			response.sendRedirect("IoTCore/Redirector.jsp?" +
				redirectParams("HeadingMessage", "Deleted " + type,
					"Message", "Please wait while we redirect you.")
			);
				
		} catch (SQLException s) {
			System.out.println("DeleteController::doPost() -> Failed to Delete: " + s);
		}

	}

}
