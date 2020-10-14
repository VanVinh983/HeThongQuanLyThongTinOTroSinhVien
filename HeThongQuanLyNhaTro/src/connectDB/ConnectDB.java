package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static Connection con = null;
	private static ConnectDB instance = new ConnectDB();

	public static Connection getConnecction() {
		return con;
	}

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException {

		String url = "jdbc:sqlserver://localhost:1433;databasename=";
		String user = "sa";
		String password = "sapassword";
		con = DriverManager.getConnection(url, user, password);
	}

	public void disconnect() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
