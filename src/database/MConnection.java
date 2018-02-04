package database;

import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import database.PasswordStorage.CannotPerformOperationException;
import database.PasswordStorage.InvalidHashException;
import localobjects.Comment;
import localobjects.Diary;
import localobjects.Notebook;
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
							if (debug) {
								System.out.println("wrong password");
							}
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
			preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE idUser=?;");
			preparedStatement.setInt(1, id);
			
			if(debug) {
				System.out.println(preparedStatement.toString());
			}
			
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
		
		// fill in user's notebooks
		
		if (user != null) {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM Notebook WHERE User_idUser=?;");
				preparedStatement.setInt(1, user.getIdUser());
				
				if(debug) {
					System.out.println(preparedStatement.toString());
				}
				
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) { // Found a user, now check if the password matches
						// Gets all necessary information and puts it in a user object
						int idNotebook = resultSet.getInt("idNotebook");
						String name = resultSet.getString("name");
						boolean isPublic = resultSet.getBoolean("isPublic");
						Date createDate = resultSet.getDate("createDate");
						Date expireDate = resultSet.getDate("expireDate");
						
						Notebook notebook = new Notebook(idNotebook, name, createDate, expireDate, isPublic, user, user.getIdUser());
						
						// retrieve diaries and add into the current notebook
						try {
							PreparedStatement diaryPS = connection.prepareStatement("SELECT * FROM Diary WHERE Notebook_idNotebook=?;");
							diaryPS.setInt(1, idNotebook);
							
							if(debug) {
								System.out.println(diaryPS.toString());
							}
							
							ResultSet diaryRS = diaryPS.executeQuery();
							
							while (diaryRS.next()) {
								int idDiary = diaryRS.getInt("idDiary");
								String content = diaryRS.getString("content");
								Date createTime = diaryRS.getTime("createTime");
						
								
								Diary diary = new Diary(idDiary, content, createTime, notebook, idNotebook);
								
								
								PreparedStatement commentPS = connection.prepareStatement("SELECT * FROM Comment WHERE Diary_idDiary=?;");
								commentPS.setInt(1, idDiary);
								
								if(debug) {
									System.out.println(commentPS.toString());
								}
								
								ResultSet commentRS = commentPS.executeQuery();
								
								while (commentRS.next()) {
									int idComment = commentRS.getInt("idComment");
									String commentContent = commentRS.getString("content");
									Date commentCreateDate = commentRS.getDate("createDate");
									int idUser = commentRS.getInt("idUser");
									
									Comment comment = new Comment(idComment, commentContent, commentCreateDate, idUser, diary, idDiary);
								}
							}
							
						} catch (SQLException e) {
							System.out.println("sqle: " + e.getMessage());
						} 
						
						
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
		}
		
		
		return null;
	}
	
	public static User fillCurrentUser(User user) throws MException {
		return pullUserInfoByID(user.getIdUser());
	}
	
	
	public static Notebook createNotebook(Notebook notebook) throws MException {
		return createNotebook(notebook.getName(), notebook.getCreateDate(), notebook.getExpireDate(), notebook.getIsPublic(), notebook.getUser());	
	}
	
	public static Notebook createNotebook(String name, Date createDate, Date expireDate,
										boolean isPublic, User user) throws MException {
		// check null
		if (name == null || name.trim().equals("")) throw new MException(8, "Notebook name cannot be empty.");
		if (createDate == null) throw new MException(9, "Notebook createDate cannot be empty.");
		if (expireDate == null) throw new MException(10, "Notebook expireDate cannot be empty.");
		if (user == null) throw new MException(11, "Notebook must belong to a user.");
		
		Notebook newNotebook = null;
		
		// TODO: check duplicate by user and name(notebook name)
		
		
		// create new notebook if not exist
		try {
			
			preparedStatement = connection.prepareStatement("INSERT INTO Notebook (name, createDate, expireDate, isPublic, User_idUser) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);
			preparedStatement.setObject(2, createDate.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toLocalDate());
			preparedStatement.setObject(3, expireDate.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toLocalDate());
			preparedStatement.setBoolean(4, isPublic);
			preparedStatement.setInt(5, user.getIdUser());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				int idNotebook = resultSet.getInt(1);
				
				newNotebook = new Notebook(idNotebook, name, createDate, expireDate, isPublic, user, user.getIdUser());
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
		
		return newNotebook;
	}
	
	
	public static Diary createDiary(Diary diary) throws MException {
		return createDiary(diary.getContent(), diary.getCreateTime(), diary.getNotebook());
	}
	
	
	public static Diary createDiary(String content, Date createTime, Notebook notebook) throws MException {
		
		// check null
		if (content == null || content.trim().equals("")) throw new MException(12, "Dairy content cannot be empty.");
		if (createTime == null) throw new MException(13, "Diary createTime cannot be empty.");
		if (notebook == null) throw new MException(14, "Diary must belong to a notebook.");
		
		
		// no need to chack duplicate
		
		// create new diary
		
		Diary newDiary = null;
		try {
			
			preparedStatement = connection.prepareStatement("INSERT INTO Diary (content, createTime, Notebook_idNotebook) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, content);
			preparedStatement.setObject(2, createTime.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toLocalDateTime());
			preparedStatement.setInt(3, notebook.getIdNotebook());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				int idDiary = resultSet.getInt(1);
				newDiary = new Diary(idDiary, content, createTime, notebook, notebook.getIdNotebook());

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
		
		
		return newDiary;
	}
	
	
}
