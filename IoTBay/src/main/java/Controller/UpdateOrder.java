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
 * @author Michael Wu
 */
@WebServlet(name = "UpdateOrder", value = "/UpdateOrder")
public class UpdateOrder extends IoTWebpageBase implements IIoTWebpage {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String owner = request.getParameter("owner");
		
		String pid = request.getParameter("pid");
		int id = -1;
		if (pid != null) {
			id = Integer.parseInt(pid);
		}
		
		String nQ = request.getParameter("EditedQuantity");
		if (nQ == null)
			throw new NullPointerException("Edited Quantity is NaN!!!!!");
		
		int eq = Integer.parseInt(nQ);

		if (id == -1) {
			throw new NullPointerException("UpdateOrder::doPost() -> Failed to read ID of Product from PID!");
		}

		if (owner == null) {
			throw new NullPointerException("UpdateOrder::doPost() -> No Owner!");
		}

		try {
			uDB.updateCart(id, owner, eq);
			
			response.sendRedirect("IoTCore/Cart.jsp?" + redirectParams("upd", "Updated Order!"));
		} catch (SQLException s) {
			throw new RuntimeException("UpdateOrder::doPost() -> SQLException -> " + s);
		}
	}
}
