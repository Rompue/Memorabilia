package javatest;

import java.util.Date;

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
			User kun = MConnection.authenticate("kunpeng@usc.edu", "pengkunn");
			System.out.println(kun.getUsername());
			
			Date createDate = new Date();
			@SuppressWarnings("deprecation")
			Date expireDate = new Date(2018, 8, 9);
			MConnection.createNotebook("Dear Er", createDate, expireDate, true, kun);
			
		} catch (MException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	
	

}
