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
 * Handles http POST logic that Cancels a Customer's or Anonymous Customer's
 * Order from IoTBay.
 *
 * Handles restoring Stock, Cancellation of Order, and the updating of the
 * relevant information in the Database.
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

		// The ID of the Order to mark as Cancelled.
		int orderID = Integer.parseInt(oid);

		// The Owning Email of the Customer that holds this Order.
		// If the Owner is an Anonymous Customer, it will use DBManager.AnonymousUserEmail instead.
		// See Orders.jsp under the // Get Owner comment for logic.
		String owner = request.getParameter("owner");

		if (owner == null)
		{
			throw new NullPointerException("CancelOrder::doPost() -> Owner Email is null!");
		}

		try
		{
			// The logic and implementation to completely mark an Order as Cancelled is here.
			uDB.cancelOrder(orderID, owner);

			response.sendRedirect("IoTCore/Orders.jsp?" + redirectParams("upd", "Cancelled Order!"));
		} catch (SQLException s)
		{
			throw new NullPointerException("CheckoutController::doPost() -> Failed to Place Order. " + s);
		}
	}
}
