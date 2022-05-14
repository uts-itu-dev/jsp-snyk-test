package Model.IoTBay.Core;

import DAO.*;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * The base class for any Java-controlled webpage in IoTBay.
 *
 * @author Michael Wu
 */
public class IoTWebpageBase extends HttpServlet implements IIoTWebpage
{

	public final String CSS_LINK = "<link rel=\"stylesheet\"href=\"IoTCore/IoTBayStyles.css\">";

	public static DBConnector connector;
	public static DBManager uDB;

	/**
	 * Base HTTP GET method that ensures a connection to the IoTBay Database
	 * is made.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");

		HttpSession session = request.getSession();

		if (session.getAttribute("UDatabase") == null)
		{
			try
			{
				connectToDB();

				session.setAttribute("UDatabase", uDB);
				session.setAttribute("Customers", uDB.customers);
				session.setAttribute("Staff", uDB.staff);
				session.setAttribute("Products", uDB.products);

				System.out.println("Database Set");
			} catch (SQLException s)
			{
				throw new NullPointerException("IoTWebpageBase::doPost. SQL Exception. " + s);
			}
		}
	}

	/**
	 * Base HTTP POST method that ensures a connection to the IoTBay
	 * Database is made.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");

		HttpSession session = request.getSession();

		if (session.getAttribute("UDatabase") == null)
		{
			try
			{
				connectToDB();

				session.setAttribute("UDatabase", uDB);
				session.setAttribute("Customers", uDB.customers);
				session.setAttribute("Staff", uDB.staff);
				session.setAttribute("Products", uDB.products);

				System.out.println("Database Set");
			} catch (SQLException s)
			{
				throw new NullPointerException("IoTWebpageBase::doPost. SQL Exception. " + s);
			}
		}
	}

	/**
	 * Utility function to join URL Parameter Attributes.
	 *
	 * @param params { AttributeName, AttributeValue, AttributeName,
	 * AttributeValue, ... }. Must be even in length/contents.
	 * @return A String formatted and organised in a way that can be passed at the end of a base URL.
	 * @throws IllegalArgumentException
	 */
	public final String redirectParams(String... params) throws IllegalArgumentException
	{
		if (params.length % 2 == 1)
		{
			throw new IllegalArgumentException("Input redirection params must have an even length! Input range: " + params.length + "\n" + Thread.currentThread().getStackTrace()[2]);
		}

		String result = "";

		for (int i = 0; i < params.length - 1; i += 2)
		{
			result += params[i] + "=" + params[i + 1];

			if (i != params.length - 2)
			{
				result += "&";
			}
		}

		return result;
	}

	@Deprecated // Google Chrome does this for automatically.
	final String convertStringToHTMLRequest(String s)
	{
		String result = "";

		for (char r : s.toCharArray())
		{
			if (r == ' ')
			{
				result += "%20";
			}
			else
			{
				result += r;
			}
		}

		return result;
	}

	public static final String makeTag(String tag, String content, String... params) throws IllegalArgumentException
	{
		if (params.length % 2 == 1)
		{
			throw new IllegalArgumentException("Input redirection params must have an even length! Input range: " + params.length + "\n" + Thread.currentThread().getStackTrace()[2]);
		}

		String result = "<" + tag;

		for (int i = 0; i < params.length - 1; i += 2)
		{
			result += " " + params[i] + "=\"" + params[i + 1] + "\"";
		}

		result += ">" + content + "</" + tag + ">";

		return result;
	}

	public static final String makeTagNoClose(String tag, String... params) throws IllegalArgumentException
	{
		if (params.length % 2 == 1)
		{
			throw new IllegalArgumentException("Input redirection params must have an even length! Input range: " + params.length + "\n" + Thread.currentThread().getStackTrace()[2]);
		}

		String result = "<" + tag;

		for (int i = 0; i < params.length - 1; i += 2)
		{
			result += " " + params[i] + "=\"" + params[i + 1] + "\"";
		}

		return result + ">";
	}

	public static final void connectToDB() throws SQLException
	{
		try
		{
			System.out.println("Connecting to DB...");

			connector = new DBConnector();
			uDB = new DBManager(connector.openConnection());

			System.out.println("DB Connected.");
		} catch (ClassNotFoundException c)
		{
			throw new NullPointerException("IoTWebpageBase::connectToDB. Class Not Found Exception.");
		} catch (SQLException s)
		{
			throw new NullPointerException("IoTWebpageBase::connectToDB. SQL Exception. " + s);
		}
	}

	public static final void Log(Object o)
	{
		System.out.println(o);
	}
}
