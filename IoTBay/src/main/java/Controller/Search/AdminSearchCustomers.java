package Controller.Search;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Customer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Wu
 */
@WebServlet(name = "AdminSearchCustomers", value = "/AdminSearchCustomers")
public class AdminSearchCustomers extends IoTWebpageBase implements IIoTWebpage
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String params = request.getParameter("Params");

		if (params == null)
		{
			response.sendRedirect("IoTCore/Administrator/ViewAllSearchedCustomers.jsp?" + redirectParams("err", "Something went wrong! Please try again."));
			return;
		}

		try
		{
			ArrayList<Customer> searched = uDB.searchCustomers(params);
			HttpSession s = request.getSession();

			s.setAttribute("SearchedCustomers", searched);
			
			response.sendRedirect("IoTCore/Administrator/ViewAllSearchedCustomers.jsp");

		} catch (SQLException s)
		{
			throw new NullPointerException("SearchCustomers::doPost() -> Unable to Search! " + s);
		}
	}
}
