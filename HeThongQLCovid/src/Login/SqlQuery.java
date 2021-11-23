package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQuery {
	public static ResultSet checkLogin (Account account) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid","root","hieunghia3107");
		Statement st = conn.createStatement();
		return st.executeQuery("SELECT * FROM patient WHERE ID= '"+account.getUsername()+"'AND Password='"+account.getPassword()+"'");
	}
}
