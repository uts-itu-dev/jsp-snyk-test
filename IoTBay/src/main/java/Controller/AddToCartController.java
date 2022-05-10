package Controller;

import DAO.DBManager;
import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.OrderLineItem;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Wu
 */
@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCartController extends IoTWebpageBase implements IIoTWebpage {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		// Add to Cart Logic here...
		String pid = request.getParameter("productID");
		
		if (pid != null) {
			int id = Integer.parseInt(pid);
			try {
				Product p = uDB.findProduct(id);
				Customer owner = (Customer) request.getSession().getAttribute("User");

				// Registered Customer.
				if (p != null && owner != null) {

					uDB.injectOLI(owner.getEmail());
					uDB.addToCart(id, owner, 1);
					request.setAttribute("Cart", uDB.cart);
					
					response.sendRedirect("IoTCore/Cart.jsp");
				}
				// Anonymous Customer.
				else if (p != null) {
					if (uDB.cart == null) {
						uDB.injectOLI(DBManager.AnonymousUserEmail);
					}

					//uDB.cart.add(new OrderLineItem(p, null, 1));
					uDB.addToCartAnonymous(id, 1);
					request.setAttribute("Cart", uDB.cart);
					response.sendRedirect("IoTCore/Cart.jsp");
				}
			} catch (SQLException s) {
				throw new NullPointerException("AddToCartController::doPost() -> Something went wrong!\n" + s);
			}
		}
	}
}
