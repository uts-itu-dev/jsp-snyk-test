/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Address;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Person.PaymentInformation;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Wu
 */
@WebServlet(name = "Update", value = "/Update")
public class UpdateController extends IoTWebpageBase implements IIoTWebpage {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String firstName = request.getParameter("First");
		String lastName = request.getParameter("Last");
		String email = request.getParameter("Email");
		String phoneNumber = request.getParameter("PhoneNumber");
		String currentPass = request.getParameter("Password");
		String pass1 = request.getParameter("Pass1");
		String pass2 = request.getParameter("Pass2");
		String addressNum = request.getParameter("addNum");
		String addressStreetName = request.getParameter("addStreetName");
		String addressSuburb = request.getParameter("addSuburb");
		String addressPostcode = request.getParameter("addPostcode");
		String addressCity = request.getParameter("addCity");
		String cardNo = request.getParameter("CardNo");
		String CVV = request.getParameter("CVV");
		String cardHolder = request.getParameter("CardHolder");

		boolean bIsCustomer = request.getParameter("bIsCustomer").equals("yes");
		String attribute = request.getParameter("Attribute");

		if (bIsCustomer) {
			try {
				HttpSession session = request.getSession();
				Customer current = uDB.findCustomer(((Customer) session.getAttribute("User")).getEmail());

				if (current != null) {
					switch (attribute) {
						case "Names":
							updateCustomerNames(current, firstName, lastName);
							session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", "Name Updated!"));

							break;
						case "Password":
							if (!currentPass.equals(current.getPassword())) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "The Current Password was incorrect!"));
							} else if (!pass1.equals(pass2)) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "Passwords did not match!"));
							} else {
								updateCustomerPassword(current, pass1);
								session.setAttribute("User", uDB.findCustomer(current.getEmail()));
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", attribute + " Updated!"));
							}

							break;
						case "Email":
							if (uDB.findCustomer(email) != null) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "An account with that E-Mail already exists!"));
							} else {
								updateCustomerEmail(current, email);
								session.setAttribute("User", uDB.findCustomer(email));
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", attribute + " Updated!"));
							}

							break;
						case "PhoneNumber":
							updateCustomerPhoneNumber(current, phoneNumber);
							session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", "Phone Number Updated!"));

							break;
						case "Address":
							updateCustomerAddress(current, addressNum, addressStreetName, addressSuburb, addressPostcode, addressCity);
							session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", attribute + " Updated!"));

							break;
						case "PaymentInformation":
							updateCustomerPaymentInformation(current, cardNo, CVV, cardHolder);
							session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", "Payment Information Updated!"));

							break;
					}
				}

			} catch (SQLException s) {
				System.out.println("UpdateController::doPost " + s);
			}
		}
	}

	void onSuccessfulUpdate(HttpSession session, HttpServletResponse response, String email) throws SQLException, IOException {
		session.setAttribute("User", uDB.findCustomer(email));
		response.sendRedirect("IoTCore/Landing.jsp");
	}

	void updateCustomerNames(Customer c, String fn, String ln) throws SQLException {
		try {
			Address a = c.getAddress();
			PaymentInformation p = c.getPayment();
			uDB.update(c, fn, ln, c.getPassword(), c.getEmail(), c.getPhoneNumber(),
				a.getNumber(), a.getStreetName(), a.getSuburb(), a.getPostcode(), a.getCity(),
				p.getCardNo(), p.getCVV(), p.getCardHolder());
		} catch (SQLException s) {
			System.out.println("Update Customer Names Failed: " + s);
		}
	}

	void updateCustomerPassword(Customer c, String password) throws SQLException {
		try {
			Address a = c.getAddress();
			PaymentInformation p = c.getPayment();
			uDB.update(c, c.getFirstName(), c.getLastName(), password, c.getEmail(), c.getPhoneNumber(),
				a.getNumber(), a.getStreetName(), a.getSuburb(), a.getPostcode(), a.getCity(),
				p.getCardNo(), p.getCVV(), p.getCardHolder());
		} catch (SQLException s) {
			System.out.println("Update Customer Password Failed: " + s);
		}
	}

	void updateCustomerEmail(Customer c, String email) throws SQLException {
		try {
			Address a = c.getAddress();
			PaymentInformation p = c.getPayment();
			uDB.update(c, c.getFirstName(), c.getLastName(), c.getPassword(), email, c.getPhoneNumber(),
				a.getNumber(), a.getStreetName(), a.getSuburb(), a.getPostcode(), a.getCity(),
				p.getCardNo(), p.getCVV(), p.getCardHolder());
		} catch (SQLException s) {
			System.out.println("Update Customer Email Failed: " + s);
		}
	}

	void updateCustomerPhoneNumber(Customer c, String phoneNumber) throws SQLException {
		try {
			Address a = c.getAddress();
			PaymentInformation p = c.getPayment();
			uDB.update(c, c.getFirstName(), c.getLastName(), c.getPassword(), c.getEmail(), phoneNumber,
				a.getNumber(), a.getStreetName(), a.getSuburb(), a.getPostcode(), a.getCity(),
				p.getCardNo(), p.getCVV(), p.getCardHolder());
		} catch (SQLException s) {
			System.out.println("Update Customer Phone Number Failed: " + s);
		}
	}

	void updateCustomerAddress(Customer c, String sNo, String sn, String sub, String pc, String city) throws SQLException {
		try {
			PaymentInformation p = c.getPayment();
			uDB.update(c, c.getFirstName(), c.getLastName(), c.getPassword(), c.getEmail(), c.getPhoneNumber(),
				sNo, sn, sub, pc, city,
				p.getCardNo(), p.getCVV(), p.getCardHolder());
		} catch (SQLException s) {
			System.out.println("Update Customer Address Failed: " + s);
		}
	}

	void updateCustomerPaymentInformation(Customer c, String cardNo, String cvv, String cardHolder) throws SQLException {
		try {
			Address a = c.getAddress();
			uDB.update(c, c.getFirstName(), c.getLastName(), c.getPassword(), c.getEmail(), c.getPhoneNumber(),
				a.getNumber(), a.getStreetName(), a.getSuburb(), a.getPostcode(), a.getCity(),
				cardNo, cvv, cardHolder);
		} catch (SQLException s) {
			System.out.println("Update Customer Payment Information Failed: " + s);
		}
	}
}
