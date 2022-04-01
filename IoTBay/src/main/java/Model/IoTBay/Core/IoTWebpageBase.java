package Model.IoTBay.Core;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import java.lang.IllegalArgumentException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The base class for any Java-controlled webpage in IoTBay.
 *
 * @author Michael Wu
 */
public class IoTWebpageBase extends HttpServlet implements IIoTWebpage {

	public final String CSS_LINK = "<link rel=\"stylesheet\"href=\"IoTCore/IoTBayStyles.css\">";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	}

	public final String redirectParams(String... params) throws IllegalArgumentException {
		if (params.length % 2 == 1) {
			throw new IllegalArgumentException("Input redirection params must have an even length! Input range: " + params.length + "\n" + Thread.currentThread().getStackTrace()[2]);
		}

		String result = "";

		for (int i = 0; i < params.length - 1; i += 2) {
			result += params[i] + "=" + params[i + 1];
			
			if (i != params.length - 2) {
				result += "&";
			}
		}

		return result;
	}
	
	final String convertStringToHTMLRequest(String s) {
		String result = "";
		
		for (char r : s.toCharArray()) {
			if (r == ' ') {
				result += "%20";
			} else {
				result += r;
			}
		}
		
		return result;
	}
}
