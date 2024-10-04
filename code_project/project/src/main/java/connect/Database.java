package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();

	public static Database getInstance() {
		return instance;
	}
	
	public static void connect() throws SQLException {
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNH";
		String user = "sa";
		String password = "123";
		con = DriverManager.getConnection(url, user, password);
	}
	
	public void disconnect() {
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static Connection getConnection() {
		try {
			connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}