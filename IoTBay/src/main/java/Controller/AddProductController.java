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
@WebServlet(name = "AddProduct", value = "/AddProduct")
public class AddProductController extends IoTWebpageBase implements IIoTWebpage{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String pName = request.getParameter("ProductName");
		String pDesc = request.getParameter("ProductDesc");
		String price = request.getParameter("ProductPrice");
		String quant = request.getParameter("ProductQuant");
		
		try {
			if (uDB.products != null && !uDB.products.isEmpty()) {
				for (Product p : uDB.products){
					if (p.getName().toLowerCase().equals(pName.toLowerCase())){
						response.sendRedirect("IoTCore/StaffControlPanel/AddProduct.jsp?" + 
							redirectParams("err", "A Product with that name already exists!"));
						return;
					}
				}
			}
			
			uDB.add(new Product(pName, pDesc, Float.parseFloat(price), Integer.parseInt(quant)));
			
			response.sendRedirect("IoTCore/StaffControlPanel/AddProduct.jsp?" + 
						redirectParams("add", pName + " has been added to IoTBay!"));
		} catch (SQLException s) {
			System.out.println("AddProductController::doPost Failed to add new Product! " + s);
		}
	}
}
