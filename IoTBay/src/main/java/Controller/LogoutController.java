/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Core.IIoTWebpage;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Wu
 */
@WebServlet(name = "Logout", value = "/Logout")
public class LogoutController extends IoTWebpageBase implements IIoTWebpage {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.doGet(request, response);
		
		request.getSession().invalidate();
		
		response.sendRedirect("index.jsp");
	}	
}
