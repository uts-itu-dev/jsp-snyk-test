package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddToCartController extends IoTWebpageBase implements IIoTWebpage {

	@Override // Temporary GET. Replace with .jsp
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.doGet(request, response);

		boolean bIsAnonymous = request.getParameter("bAnonymous").equals("true");

		try (PrintWriter out = response.getWriter()) {
			String userType = bIsAnonymous ? "an Anonymous" : "a Registered";

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println(CSS_LINK);
			out.println("<title>Servlet AddToCartController</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet AddToCartController at " + request.getContextPath() + "</h1>");
			out.println("<h2>Viewing this page as: " + userType + " Customer</h2>");
			out.println("<br><br>");
			out.println("<h2>NOTE THAT THIS IS JUST A BASIC PAGE TO SHOW THAT IT WORKS.<br>IN REALITY, ADDING SOMETHING TO "
				+ "THE CART SHOULDN'T HAVE A PAGE, JUST A NOTIFICATION.</h2>");
			out.println("<h3>See Controller/AddToCartController.java for source code.<br>"
				+ "Implement Add to Cart in AddToCartController::doPost(request, response)</h3>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		// Add to Cart Logic here...
		
		String pid = request.getParameter("productID");
		if (pid != null) {
			int id = Integer.parseInt(pid);
			try {
				Product p = uDB.findProduct(id);
				if (p != null) {
					
					// ...
					
					
				}
			} catch (SQLException s) {
				throw new NullPointerException("AddToCartController::doPost() -> Something went wrong!\n" + s);
			}
		}
	}
}
