/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@WebServlet(name = "CancelOrder", value = "/CancelOrder")
public class CancelOrder extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String oid = request.getParameter("oid");

		if (oid == null)
		{
			throw new NullPointerException("CancelOrder::doPost() -> Order ID is null!");
		}

		int orderID = Integer.parseInt(oid);
		
		String owner = request.getParameter("owner");
		
		if (owner == null)
		{
			throw new NullPointerException("CancelOrder::doPost() -> Owner Email is null!");
		}

		try
		{
			uDB.cancelOrder(orderID, owner);
			System.out.println("End Exec");
			response.sendRedirect("IoTCore/Orders.jsp?" + redirectParams("upd", "Cancelled Order!"));
		} catch (SQLException s)
		{
			throw new NullPointerException("CheckoutController::doPost() -> Failed to Place Order. " + s);
		}
	}
}
