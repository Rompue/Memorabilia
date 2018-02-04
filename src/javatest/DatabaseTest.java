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
//			MConnection.createUser("Rompue", "kunpeng@usc.edu", "pengkunn", "no file", "I love u.");
			User kun = MConnection.authenticate("kunpeng@usc.edu", "pengkunn");
			System.out.println(kun.getUsername());
			
			
//			for (int i = 0; i < 3; i++) {
//				Date createDate = new Date();
//				@SuppressWarnings("deprecation")
//				Date expireDate = new Date(2018-1900, 8, 9);
//				Notebook newNotebook = MConnection.createNotebook("Notebook No."+i, createDate, expireDate, true, kun);
//				for (int j = 0; j < 3; j ++) {
//					Date diaryCreateDate = new Date();
//					Diary newDiary = MConnection.createDiary("Diary No." + i + "." + j, diaryCreateDate, newNotebook);
//				}
//			}
			
			MConnection.pullUserInfoByID(kun.getIdUser());
			System.out.println();
			
			for(Notebook notebook : kun.getNotebooks()) {
				
			}
			
		} catch (MException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	
	

}
