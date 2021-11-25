package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getJDBC() {

//MySQL
        Connection connection = null;
        try {
            // Load the JDBC driver//org.gjt.mm.mysql.Driver
            String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
            //Class.forName(driverName);
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            String serverName = "localhost:3306";
            String mydatabase = "student";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
            String username = "root";
            String password = "28022001jkem454";
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }
        return null;

    }

//    Connection x = new Connection();
//    public static void main(String[] args) {
//        if(x!=null){
//            System.out.print("ok");
//        }
//        else{
//            System.out.print("11");
//        }
//    }
}
