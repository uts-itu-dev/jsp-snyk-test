/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides the View for Editing a Product.
 *
 * @author Michael Wu
 */
@WebServlet(name = "ProductEditor", value = "/ProductEditor")
public class ProductEditorController extends IoTWebpageBase implements IIoTWebpage
{

	// For some reason, the Product Editor couldn't be made into a .jsp file, so here it is.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		super.doGet(request, response);

		PrintWriter out = response.getWriter();

		// Begin HTML.
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println(CSS_LINK);
		out.println("<title>Product Editor</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Product Editor</h1>");

		// Begin Logic.
		String pid = request.getParameter("productID");
		if (pid != null)
		{
			if (uDB != null)
			{
				int id = Integer.parseInt(pid);
				try
				{
					Product p = uDB.findProduct(id);

					if (p != null)
					{
						DecimalFormat df = new DecimalFormat("0.00");
						String price = df.format(p.getPrice());
						out.println("<div class=\"form-container\"><div class=\"form-inner\">"
							+ "<form action=\"Update\" class=\"login\" method=\"POST\">"
							//							
							+ "<label for=\"ProductName\">Product Name:</label><br>"
							//							
							+ "<div class=\"field\">"
							+ "<input name=\"ProductName\" type=\"text\" placeholder=\"Product Name\" value=\"" + p.getName() + "\">"
							+ "</div><br>"
							//								
							+ "<label for=\"ProductDesc\">Product Description:</label><br>"
							//								
							+ "<textarea name=\"ProductDesc\" rows=\"5\" cols=\"50\">"
							+ p.getDescription()
							+ "</textarea>"
							+ "<br>"
							//								
							+ "<label for=\"ProductPrice\">Product Price:</label><br>"
							+ "<div class=\"field\">"
							+ "<input name=\"ProductPrice\" type=\"number\" min =\".01\" max=\"9999\" step=\"0.01\" value=\"" + price + "\">"
							+ "</div><br>"
							//
							+ "<label for=\"ProductPrice\">Product Stock:</label><br>"
							+ "<div class=\"field\">"
							+ "<input name=\"ProductStock\" type=\"number\" min =\"0\" max=\"9999\" step=\"0.01\" value=\"" + p.getQuantity() + "\">"
							+ "</div><br>"
							//
							+ "<div class=\"field\">"
							+ "<input type=\"submit\" value=\"Update\">"
							+ "</div>"
							//								
							+ "<input type=\"hidden\" name=\"pid\" value=\"" + id + "\">"
							+ "<input type=\"hidden\" name=\"Attribute\" value=\"Product\">"
							+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"no\">"
							+ "</form>"
							+ "</div></div>");
					}
					else
					{
						out.println("<h1>No product selected!</h1>");
					}

				} catch (SQLException ex)
				{
					Logger.getLogger(ProductEditorController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		// End Logic.

		// End HTML.
		out.println("</body>");
		out.println("</html>");
	}
}
