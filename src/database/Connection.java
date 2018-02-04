package database;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Connection {
	
	public static java.sql.Connection connection;
	private static boolean connected; 
	public static boolean debug = true;
	
	public static void connect() {
		if (!connected) {
			// Initial SQL and database connections
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Connects to database
				connection = DriverManager.getConnection("jdbc:mysql://localhost/Memorabilia?user=root&password=root&useSSL=false");
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			} catch (ClassNotFoundException cnfe) {
				System.out.println("cnfe: " + cnfe.getMessage());
			}
			connected = true;
			
			if (debug) {
				System.out.println("Successfully connected to Memorabilia database.");
			}
		}
	}
}
