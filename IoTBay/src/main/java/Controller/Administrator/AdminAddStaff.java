package Controller.Administrator;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import static Model.IoTBay.Core.IoTWebpageBase.uDB;
import Model.IoTBay.Person.Staff;
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
@WebServlet(name = "AdminAddStaff", value = "/AdminAddStaff")
public class AdminAddStaff extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String fn, ln, em, pw;
		fn = request.getParameter("fn");
		ln = request.getParameter("ln");
		em = request.getParameter("em");
		pw = request.getParameter("pw");

		try
		{
			Staff s = new Staff(fn, ln, pw, em);
			
			uDB.add(s);
			
			response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Added " + fn + " as a Staff Member of IoTBay!"));
			
		} catch (SQLException s)
		{
			
		}
	}
}