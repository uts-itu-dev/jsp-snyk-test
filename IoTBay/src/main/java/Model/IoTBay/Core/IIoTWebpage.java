package Model.IoTBay.Core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface that all web pages of IoTBay must implement.
 *
 * @author Michael Wu
 */
public interface IIoTWebpage {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
