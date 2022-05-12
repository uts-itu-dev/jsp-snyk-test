package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Product;
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
@WebServlet(name = "UpdateOrder", value = "/UpdateOrder")
public class UpdateOrder extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String owner = request.getParameter("owner");

		// The Product ID.
		String pid = request.getParameter("pid");
		int id = -1;
		if (pid != null)
		{
			id = Integer.parseInt(pid);
		}

		// The edited quantity of this Order.
		String nQ = request.getParameter("EditedQuantity");
		if (nQ == null)
		{
			throw new NullPointerException("Edited Quantity is NaN!!!!!");
		}

		int newQuantity = Integer.parseInt(nQ);

		// Other error checking.
		if (id == -1)
		{
			throw new NullPointerException("UpdateOrder::doPost() -> Failed to read ID of Product from PID!");
		}

		if (owner == null)
		{
			throw new NullPointerException("UpdateOrder::doPost() -> No Owner!");
		}

		// If the Customer made the call to Update the Order's Quantity.
		if (request.getParameter("Update") != null)
		{
			try
			{
				// If the new Quantity is greater than zero, try to update.
				if (newQuantity > 0)
				{
					Product editingProduct = uDB.findProduct(id);

					// If the Product has enough Stock.
					if (editingProduct.getQuantity() >= newQuantity)
					{
						// Update this Product.
						uDB.updateCart(id, owner, newQuantity);
						response.sendRedirect("IoTCore/Cart.jsp?" + redirectParams("upd", "Updated Order!"));
					}
					// Otherwise, the Product does not have enough Stock.
					else
					{
						// Do not Update this Product and tell the Customer it failed.
						response.sendRedirect("IoTCore/Cart.jsp?"
							+ redirectParams("err", "There is not enough stock!\nWe only have " + editingProduct.getQuantity() + " left!")
						);
					}
				}
				// Otherwise, if the new Quantity is zero, remove it.
				else
				{
					uDB.removeFromCart(id, owner);
					response.sendRedirect("IoTCore/Cart.jsp?" + redirectParams("upd", "Removed Order!"));
				}

			} catch (SQLException s)
			{
				throw new RuntimeException("UpdateOrder::doPost() -> Update -> SQLException -> " + s);
			}
		}
		// Otherwise, if the Customer made the call to Remove this Product from their Order.
		else if (request.getParameter("Remove") != null)
		{
			try
			{
				// Remove from the Cart.
				uDB.removeFromCart(id, owner);

				response.sendRedirect("IoTCore/Cart.jsp?" + redirectParams("upd", "Removed Order!"));
			} catch (SQLException s)
			{
				throw new RuntimeException("UpdateOrder::doPost() -> Remove -> SQLException -> " + s);
			}
		}
		// Otherwise, if the Customer wishes to proceed with their Order. (Checkout)
		else if (request.getParameter("Checkout") != null)
		{
			
		}
	}
}
