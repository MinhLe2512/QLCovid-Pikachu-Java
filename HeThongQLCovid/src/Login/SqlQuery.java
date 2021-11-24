package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQuery {
	private static 	String dbURL = "jdbc:sqlserver://NIERA-ASUS\\SQLEXPRESS; databaseName=HeThongQuanLyCovid",
			user = "sa",
			pass = "12345678";

	public static ResultSet checkLogin (Account account) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(dbURL, user, pass);
		Statement st = conn.createStatement();
		return st.executeQuery("SELECT * FROM ql_user WHERE username= '" + account.getUsername()
		+ "'AND user_password='" + account.getPassword()+"'");
	}
}