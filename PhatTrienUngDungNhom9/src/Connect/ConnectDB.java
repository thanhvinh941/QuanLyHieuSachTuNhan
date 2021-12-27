package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = QuanLyHieuSachTuNhan", "sa", "sapassword");
//		if (conn != null) {
//		    System.out.println("Connected succes!!!<3<3<3");
//		}
		return conn; 
	}
}
