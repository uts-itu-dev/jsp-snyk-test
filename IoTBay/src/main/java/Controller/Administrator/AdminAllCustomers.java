package Controller.Administrator;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import static Model.IoTBay.Core.IoTWebpageBase.uDB;
import Model.IoTBay.Person.Customer;
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
@WebServlet(name = "AdminAllCustomers", value = "/AdminAllCustomers")
public class AdminAllCustomers extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String em = request.getParameter("Email");

		boolean bHasCompletedEdits = request.getParameter("bEdited") != null;

		try
		{
			Customer c = uDB.findCustomer(em);
			String fn = c.getFirstName();

			if (request.getParameter("Remove") != null)
			{
				uDB.remove("CUSTOMERS", em);

				response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Removed " + fn + " from IoTBay!"));
			}
			else if (request.getParameter("Update") != null)
			{
				response.sendRedirect("IoTCore/Administrator/CustomerEditor.jsp?Email=" + em);
			}
			else if (bHasCompletedEdits)
			{
				String efn, eln, eem, epw, epn, esNo, esNa, esub, epc, ect, cno, cvv, cho;
				
				Customer ec = uDB.findCustomer(em);
				
				efn = request.getParameter("fn");
				eln = request.getParameter("ln");
				eem = request.getParameter("em");
				epw = request.getParameter("pw");
				epw = epw == null || epw.length() == 0 ? ec.getPassword() : epw;
				epn = request.getParameter("pn");
				esNo = request.getParameter("sNo");
				esNa = request.getParameter("sNa");
				esub = request.getParameter("sub");
				epc = request.getParameter("pc");
				ect = request.getParameter("ct");
				cno = request.getParameter("cno");
				cvv = request.getParameter("cvv");
				cho = request.getParameter("cho");
				
				uDB.update(ec, efn, eln, epw, eem, epn, esNo, esNa, esub, epc, ect, cno, cvv, cho);
				
				response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Updated Customer!"));
			}

		} catch (SQLException s)
		{

		}
	}
}
