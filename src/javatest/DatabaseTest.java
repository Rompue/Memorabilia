package javatest;

import database.MConnection;
import database.MException;
import localobjects.User;

public class DatabaseTest {

	public static void main(String[] args) {
		
		// test connection
		MConnection.connect();
		
		
		// test create user
		try {
//			connection.createUser("WJL", "wjl@usc.edu", "wjl", "no file", "I love u.");
			User wjl = MConnection.authenticate("wjl@usc.edu", "wjl");
			System.out.println(wjl.getUsername());
		} catch (MException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	
	

}
