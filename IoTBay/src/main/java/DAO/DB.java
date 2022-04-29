package DAO;

import java.sql.*;

/**
 *
 * @author Michael Wu
 */
public abstract class DB {
	protected String URL = "jdbc:derby://localhost:1527/";
	protected String db = "IoTBay";
	protected String dbuser = "IoTBay";
	protected String dbpass = "password";
	protected String driver = "org.apache.derby.jdbc.ClientDriver";
	protected Connection conn;
}
