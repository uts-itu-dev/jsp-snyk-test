package Controller.Administrator;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Address;
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
@WebServlet(name = "AdminAddCustomer", value = "/AdminAddCustomer")
public class AdminAddCustomer extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String fn, ln, em, pw, pn, sNo, sNa, sub, pc, ct;
		fn = request.getParameter("fn");
		ln = request.getParameter("ln");
		em = request.getParameter("em");
		pw = request.getParameter("pw");
		pn = request.getParameter("pn");
		sNo = request.getParameter("sNo");
		sNa = request.getParameter("sNa");
		sub = request.getParameter("sub");
		pc = request.getParameter("pc");
		ct = request.getParameter("ct");

		try
		{
			Address a = new Address(sNo, sNa, sub, pc, ct);
			Customer c = new Customer(fn, ln, pw, em, a, pn);
			
			uDB.add(c);
			
			response.sendRedirect("IoTCore/Administrator/Admin.jsp?" + redirectParams("msg", "Added " + fn + " as a Customer of IoTBay!"));
			
		} catch (SQLException s)
		{
			
		}
	}
}
