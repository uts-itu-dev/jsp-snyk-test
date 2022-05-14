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
 * @author Christian
 */
@WebServlet(name = "DeleteProduct", value = "/DeleteProduct")
public class DeleteProductController extends IoTWebpageBase implements IIoTWebpage{
 
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
            super.doPost(request, response);
            
            String productID = request.getParameter("ProductID");
            
            if(productID == null){
                throw new NullPointerException("DeleteProductController::doPost() -> Product ID is null!");
            }
            
            try{
                uDB.removeProduct(productID);
                request.getSession().invalidate();
                
                response.sendRedirect("IoTCore/Redirector.jsp?"
				+ redirectParams("HeadingMessage", "Deleted " + productID,
					"Message", "Please wait while we redirect you.")
			);
            } catch (SQLException s)
		{
			System.out.println("DeleteController::doPost() -> Failed to Delete: " + s);
		}
        }
    
}
