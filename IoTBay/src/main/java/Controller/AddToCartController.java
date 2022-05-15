package Controller;

import DAO.DBManager;
import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Customer;
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
@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCartController extends IoTWebpageBase implements IIoTWebpage
{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.doPost(request, response);

		String pid = request.getParameter("productID");

		if (pid != null)
		{
			// PRODUCTID of Product.
			int id = Integer.parseInt(pid);
			System.out.println("This is ID: " + id);
			try
			{
				// The Product with PRODUCTID.
				Product p = uDB.findProduct(id);

				if (p != null)
				{
					Customer owner = (Customer) request.getSession().getAttribute("User");

					// Registered Customer.
					if (owner != null)
					{

						uDB.injectOLI(owner.getEmail());
						uDB.addToCart(id, owner, 1);
						request.setAttribute("Cart", uDB.cart);

						response.sendRedirect("IoTCore/Cart.jsp");
					}
					// Anonymous Customer.
					else
					{
						// If the Anonymous Customer has no associated Cart, assign one.
						if (uDB.cart == null)
						{
							uDB.injectOLI(DBManager.AnonymousUserEmail);
						}

						uDB.addToCartAnonymous(id, 1);
						request.setAttribute("Cart", uDB.cart);
						response.sendRedirect("IoTCore/Cart.jsp");
					}
				}
				else
				{
					throw new NullPointerException("AddToCartController::doPost() -> Product is null!");
				}
			} catch (SQLException s)
			{
				throw new NullPointerException("AddToCartController::doPost() -> Something went wrong!\n" + s);
			}
		}
	}
}
