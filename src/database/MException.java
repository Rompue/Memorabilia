package database;

public class MException extends Exception {
	private int code;
	private String message;
	
	public MException(int code, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}


// Exception list
/*
 * 1, "Username is empty."
 * 2, "Email invalid."
 * 3, "password cannot empty."
 * 4, "This email has already been registered."
 * 5, "Incorrect password for " + email
 * 6, "No user with email " + email + " exists"
 * 
 */
