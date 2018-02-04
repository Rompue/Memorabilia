package javatest;

import java.util.Date;

import database.MConnection;
import database.MException;
import localobjects.Diary;
import localobjects.Notebook;
import localobjects.User;

public class DatabaseTest {

	public static void main(String[] args) {
		
		// test connection
		MConnection.connect();
		
		
		// test create user
		try {
			MConnection.createUser("Rompue", "kunpeng@usc.edu", "pengkunn", "no file", "I love u.");
			User kun = MConnection.authenticate("kunpeng@usc.edu", "pengkunn");
			System.out.println(kun.getUsername());
			
			Date createDate = new Date();
			@SuppressWarnings("deprecation")
			Date expireDate = new Date(2018-1900, 8, 9);
			Notebook newNotebook = MConnection.createNotebook("Dear Er", createDate, expireDate, true, kun);
			Date diaryCreateDate = new Date();
			Diary newDiary = MConnection.createDiary("this is my day", diaryCreateDate, newNotebook);
			
		} catch (MException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	
	

}
