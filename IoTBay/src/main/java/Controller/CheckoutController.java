package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Address;
import Model.IoTBay.Person.PaymentInformation;
import Model.IoTBay.OrderLineItem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Marks a User's Cart (Unregistered or Registered) as Checked Out.
 *
 * @author Michael Wu
 */
@WebServlet(name = "Checkout", value = "/Checkout")
public class CheckoutController extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String email = request.getParameter("email");
		String isCustomer = request.getParameter("isCustomer");

		if (isCustomer == null)
		{
			throw new NullPointerException("CheckoutController::doPost() -> isCustomer attribute is null!");
		}

		String addressNum = request.getParameter("addNum");
		String addressStreetName = request.getParameter("addStreetName");
		String addressSuburb = request.getParameter("addSuburb");
		String addressPostcode = request.getParameter("addPostcode");
		String addressCity = request.getParameter("addCity");
		String cardNo = request.getParameter("CardNo");
		String CVV = request.getParameter("CVV");
		String cardHolder = request.getParameter("CardHolder");

		Address shippingAddress = new Address(addressNum, addressStreetName, addressSuburb, addressPostcode, addressCity);
		PaymentInformation pi = new PaymentInformation(cardNo, CVV, cardHolder);

		try
		{
			ArrayList<OrderLineItem> cart = (ArrayList<OrderLineItem>) request.getSession().getAttribute("Cart");

			if (!cart.isEmpty())
			{
				uDB.makeOrder(email, cart, shippingAddress, pi);

				response.sendRedirect("IoTCore/Orders.jsp");
			}
			else
			{
				response.sendRedirect("IoTCore/Cart.jsp?" + redirectParams("err", "There is nothing in your Cart!"));
			}

		} catch (SQLException s)
		{
			throw new NullPointerException("CheckoutController::doPost() -> Failed to Place Order. " + s);
		}
	}
}
