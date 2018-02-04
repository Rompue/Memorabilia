package database;

import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import database.PasswordStorage.CannotPerformOperationException;
import database.PasswordStorage.InvalidHashException;
import localobjects.User;



public class MConnection {
	
	public static java.sql.Connection connection;
	private static boolean connected = false; 
	public static boolean debug = true;
	
	private static PreparedStatement preparedStatement= null;
	private static ResultSet resultSet = null;
	
	
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
	
	
	// TODO: test this function
	public static void createUser(String username, String email, String password, String filepath, String intro) throws MException{
		// validation
		if (username == null || username.trim().equals("")) throw new MException(1, "Username is empty.");
		if (email == null || email.trim().equals("")) throw new MException(2, "Email invalid.");
		if (password == null || password.trim().equals("")) throw new MException(3, "password cannot empty.");
		if (filepath == null) filepath = "";
		if (intro == null) intro = "";
		
		// check duplicate
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE email=?;");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) { // There is already a user with this email
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				throw new MException(4, "This email has already been registered.");
			}
		} catch (SQLException e) {
			System.out.println("sqle: " + e.getMessage());
		}
		
		
		// if not, create the new user
		
		// hash password
		PasswordStorage passwordStorage = new PasswordStorage();
		Date date = new Date();
				
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO User (username, email, filepath, alive, joinTime, intro, hash) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, filepath);
			preparedStatement.setBoolean(4, true);;
			preparedStatement.setObject(5, date.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toLocalDate());
			preparedStatement.setString(6, intro);
			preparedStatement.setString(7, passwordStorage.createHash(password));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sqle: " + e.getMessage());
		} catch (CannotPerformOperationException e) {
			e.getMessage();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (resultSet != null) resultSet.close();
			} catch (SQLException e2) {
				e2.getMessage();
			}
		}

	}
	
	public static User authenticate(String email, String password) throws MException {
		if (email == null || email.trim().equals("")) throw new MException(2, "Email invalid.");
		if (password == null || password.trim().equals("")) throw new MException(3, "password cannot empty.");
		
		// Attempt to log in
				try {
					preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE email=?;");
					preparedStatement.setString(1, email);
					resultSet = preparedStatement.executeQuery();
					
					PasswordStorage passwordStorage = new PasswordStorage();
					if (resultSet.next()) { // Found a user, now check if the password matches
						if (passwordStorage.verifyPassword(password, resultSet.getString("hash"))) { // Password matches
							// Gets all necessary information and puts it in a user object
							int idUser = resultSet.getInt("idUser");
							String username = resultSet.getString("username");
//							String email = resultSet.getString("email");
							boolean alive = resultSet.getBoolean("alive");
							Date joinTime = resultSet.getDate("joinTime");
							String intro = resultSet.getString("intro");
							
							
							User user = new User(idUser, username, email, alive, joinTime, intro);
							return user;
						}
						else { // Email and password were incorrect
							throw new MException(5, "Incorrect password for " + email);
						}
					}
					else { // No user with this email exists
						throw new MException(6, "No user with email " + email + " exists");
					}
				} catch (SQLException sqle) {
					System.out.println("sqle: " + sqle.getMessage());
				} catch (CannotPerformOperationException e) {
					e.getMessage();
				} catch (InvalidHashException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (resultSet != null) resultSet.close();
						if (preparedStatement != null) preparedStatement.close();
					} catch (SQLException sqle) {
						System.out.println("sqle closing stuff: " + sqle.getMessage());
					}
				}
		
		
		return null;
	}
	
	public static User pullUserInfoByID(int id) throws MException {
		
		// Retrieve user's basic info and create the user first
		User user = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE idUesr=?;");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) { // Found a user, now check if the password matches
					// Gets all necessary information and puts it in a user object
					int idUser = resultSet.getInt("idUser");
					String username = resultSet.getString("username");
					String email = resultSet.getString("email");
					boolean alive = resultSet.getBoolean("alive");
					Date joinTime = resultSet.getDate("joinTime");
					String intro = resultSet.getString("intro");
					
					
					user = new User(idUser, username, email, alive, joinTime, intro);
			}
			else { // No user with this email exists
				throw new MException(7, "No user with id " + id + " exists");
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException sqle) {
				System.out.println("sqle closing stuff: " + sqle.getMessage());
			}
		}
		
		// fill in uesr's notebooks
		
		
		
		
		return null;
	}
	
	public static User fillCurrentUser() {
		return null;
	}
	
	
	
	
}
