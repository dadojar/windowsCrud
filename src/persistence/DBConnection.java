package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * singleton class to have one connection in all the system
 * **/
public class DBConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if(connection == null) {
			connection = createConnection();
			return connection;
		}
		return connection;
	}
	
	
	public static Connection createConnection() {
		Connection connection = null;
		try {
//			System.out.println("Start create connection to database");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_test", "admin", "admin");
//			System.out.println("End create connection to database");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	
	
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Exception in closing connection : "+e);
		}
	}

}
