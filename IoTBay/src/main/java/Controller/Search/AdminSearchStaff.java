/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Search;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Staff;
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
@WebServlet(name = "AdminSearchStaff", value = "/AdminSearchStaff")
public class AdminSearchStaff extends IoTWebpageBase implements IIoTWebpage
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String params = request.getParameter("Params");

		if (params == null)
		{
			response.sendRedirect("IoTCore/Administrator/ViewAllSearchedStaff.jsp?" + redirectParams("err", "Something went wrong! Please try again."));
			return;
		}

		try
		{
			ArrayList<Staff> searched = uDB.searchStaff(params);
			HttpSession s = request.getSession();

			s.setAttribute("SearchedStaff", searched);
			
			response.sendRedirect("IoTCore/Administrator/ViewAllSearchedStaff.jsp");

		} catch (SQLException s)
		{
			throw new NullPointerException("SearchStaff::doPost() -> Unable to Search! " + s);
		}
	}
}
