package Controller.Administrator;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import static Model.IoTBay.Core.IoTWebpageBase.uDB;
import Model.IoTBay.Person.Customer;
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
@WebServlet(name = "AdminAllStaff", value = "/AdminAllStaff")
public class AdminAllStaff extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String em = request.getParameter("Email");

		boolean bHasCompletedEdits = request.getParameter("bEdited") != null;

		try
		{
			Staff s = uDB.findStaff(em);
			String fn = s.getFirstName();

			if (request.getParameter("Remove") != null)
			{
				uDB.remove("STAFF", em);

				response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Removed " + fn + " from IoTBay!"));
			}
			else if (request.getParameter("Update") != null)
			{
				response.sendRedirect("IoTCore/Administrator/StaffEditor.jsp?Email=" + em);
			}
			else if (bHasCompletedEdits)
			{
				String efn, eln, eem, epw;
				
				Staff es = uDB.findStaff(em);
				
				efn = request.getParameter("fn");
				eln = request.getParameter("ln");
				eem = request.getParameter("em");
				epw = request.getParameter("pw");
				epw = epw == null || epw.length() == 0 ? es.getPassword() : epw;
				
				uDB.update(es, efn, eln, epw, eem);
				
				response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Updated Staff Member!"));
			}

		} catch (SQLException s)
		{

		}
	}
}
