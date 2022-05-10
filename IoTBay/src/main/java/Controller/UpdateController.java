package Controller;

import Model.IoTBay.Core.IIoTWebpage;
import Model.IoTBay.Core.IoTWebpageBase;
import Model.IoTBay.Person.Address;
import Model.IoTBay.Person.Customer;
import Model.IoTBay.Person.PaymentInformation;
import Model.IoTBay.Person.Staff;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Functions involving the updating of the Database as it relates to the people
 * within IoTBay.
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

		String updateCallFromStaff = request.getParameter("Update");
		if (updateCallFromStaff != null) {
			Log("Update");
			response.sendRedirect("IoTCore/StaffControlPanel/CustomerProfileUpdator.jsp?Email=" + email);
			return;
		}

		if (request.getParameter("Remove") != null) {
			Log("Remove");
			try {
				uDB.remove(email);

				response.sendRedirect("IoTCore/StaffControlPanel/SeeEditCustomers.jsp?" + redirectParams("upd", "Customer Removed!"));
			} catch (SQLException s) {
				throw new NullPointerException("UpdateController::doPost() -> Failed to remove Customer with E-Mail: " + email);
			}
			return;
		}

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

		boolean bCalledFromStaff = request.getParameter("CalledFromStaff") != null;
		String redirectLink = bCalledFromStaff
			? "IoTCore/StaffControlPanel/SeeEditCustomers.jsp?"
			: "IoTCore/Profile.jsp?";

		String attribute = request.getParameter("Attribute");

		HttpSession session = request.getSession();

		if (bIsCustomer) {
			try {
				Customer current;
				if (!bCalledFromStaff) {
					current = uDB.findCustomer(((Customer) session.getAttribute("User")).getEmail());
				}
				else {
					String uEmail = request.getParameter("originalEmail");

					if (uEmail == null) {
						throw new NullPointerException("UpdateController::doPost() -> Email is null -> Trying to edit a Customer with no parameter 'Email'!");
					}

					current = uDB.findCustomer(uEmail);
				}

				if (current != null) {
					switch (attribute) {
						case "Names":
							updateCustomerNames(current, firstName, lastName);
							if (!bCalledFromStaff) {
								session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							}

							response.sendRedirect(redirectLink + redirectParams("upd", "Name Updated!"));

							break;
						case "Password":
							if (!bCalledFromStaff && !currentPass.equals(current.getPassword())) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "The Current Password was incorrect!"));
							}
							else if (!bCalledFromStaff && !pass1.equals(pass2)) {
								response.sendRedirect(redirectLink + redirectParams("err", "Passwords did not match!"));
							}
							else {
								updateCustomerPassword(current, pass1);
								if (!bCalledFromStaff) {
									session.setAttribute("User", uDB.findCustomer(current.getEmail()));
								}
								response.sendRedirect(redirectLink + redirectParams("upd", attribute + " Updated!"));
							}

							break;
						case "Email":
							if (uDB.findCustomer(email) != null) {
								response.sendRedirect(redirectLink + redirectParams("err", "An account with that E-Mail already exists!"));
							}
							else {
								updateCustomerEmail(current, email);
								if (!bCalledFromStaff) {
									session.setAttribute("User", uDB.findCustomer(email));
								}
								response.sendRedirect(redirectLink + redirectParams("upd", attribute + " Updated!"));
							}

							break;
						case "PhoneNumber":
							updateCustomerPhoneNumber(current, phoneNumber);
							if (!bCalledFromStaff) {
								session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							}
							response.sendRedirect(redirectLink + redirectParams("upd", "Phone Number Updated!"));

							break;
						case "Address":
							updateCustomerAddress(current, addressNum, addressStreetName, addressSuburb, addressPostcode, addressCity);
							if (!bCalledFromStaff) {
								session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							}
							response.sendRedirect(redirectLink + redirectParams("upd", attribute + " Updated!"));

							break;
						case "PaymentInformation":
							updateCustomerPaymentInformation(current, cardNo, CVV, cardHolder);
							if (!bCalledFromStaff) {
								session.setAttribute("User", uDB.findCustomer(current.getEmail()));
							}
							response.sendRedirect(redirectLink + redirectParams("upd", "Payment Information Updated!"));

							break;
					}
				}

			} catch (SQLException s) {
				System.out.println("UpdateController::doPost::Logged in as a Customer " + s);
			}
		}
		else { // The User is Staff.
			try {
				Staff current = uDB.findStaff(((Staff) session.getAttribute("User")).getEmail());

				if (current != null) {
					switch (attribute) {
						case "Names":
							updateStaffNames(current, firstName, lastName);
							session.setAttribute("User", uDB.findStaff(current.getEmail()));
							response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", "Name Updated!"));
							break;
						case "Password":
							if (!currentPass.equals(current.getPassword())) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "The Current Password was incorrect!"));
							}
							else if (!pass1.equals(pass2)) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "Passwords did not match!"));
							}
							else {
								updateStaffPassword(current, pass1);
								session.setAttribute("User", uDB.findStaff(current.getEmail()));
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", attribute + " Updated!"));
							}
							break;
						case "Email":
							if (uDB.findCustomer(email) != null) {
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("err", "An account with that E-Mail already exists!"));
							}
							else {
								updateStaffEmail(current, email);
								session.setAttribute("User", uDB.findStaff(email));
								response.sendRedirect("IoTCore/Profile.jsp?" + redirectParams("upd", attribute + " Updated!"));
							}
							break;
						case "Product":
							String pName = request.getParameter("ProductName");
							String pDesc = request.getParameter("ProductDesc");
							String pPrice = request.getParameter("ProductPrice");

							String pid = request.getParameter("pid");
							int id = Integer.parseInt(pid);

							uDB.updateProduct(id, pName, pDesc, pPrice);

							response.sendRedirect("IoTCore/Redirector.jsp?"
								+ redirectParams(
									"HeadingMessage", pName + " Updated!",
									"Message", "Please wait while we redirect you...")
							);

							break;
					}
				}
			} catch (SQLException s) {
				System.out.println("UpdateController::doPost::Logged in as STAFF " + s);
			}
		}
	}

	void onSuccessfulCustomerUpdate(HttpSession session, HttpServletResponse response, String email) throws SQLException, IOException {
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

	void updateStaffNames(Staff s, String fn, String ln) throws SQLException {
		uDB.update(s, fn, ln, s.getPassword(), s.getEmail());
	}

	void updateStaffPassword(Staff s, String password) throws SQLException {
		uDB.update(s, s.getFirstName(), s.getLastName(), password, s.getEmail());
	}

	void updateStaffEmail(Staff s, String email) throws SQLException {
		uDB.update(s, s.getFirstName(), s.getLastName(), s.getPassword(), email);
	}
}
