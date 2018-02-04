package localobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.MConnection;

public class User {
	private int idUser;
	private String username;
	private String email;
	private String filepath;
	private boolean alive;
	private Date joinTime;
	private String intro;
	private List<Notebook> notebooks = new ArrayList<>();
	private Map<Integer, Notebook> notebookMap = new HashMap<>();

	public User(int id, 
			String username, 
			String email, 
			boolean alive, 
			Date joinTime, 
			String intro) {
		this.idUser = id;
		this.username = username;
		this.email = email;
		this.alive = alive;
		this.joinTime = joinTime;
		this.intro = intro;
		this.filepath = "no image"; // TODO: fix the filepath
	}
	
	public void addNotebook(Notebook notebook) {
		notebooks.add(notebook);
		notebookMap.put(notebook.getIdNotebook(), notebook);
		if(MConnection.debug) {
			System.out.println("added one new notebook in user class");
		}
	}

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath
	 *            the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the joinTime
	 */
	public Date getJoinTime() {
		return joinTime;
	}

	/**
	 * @param joinTime
	 *            the joinTime to set
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @param intro
	 *            the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * @return the notebooks
	 */
	public List<Notebook> getNotebooks() {
		return notebooks;
	}

	/**
	 * @param notebooks the notebooks to set
	 */
	public void setNotebooks(List<Notebook> notebooks) {
		this.notebooks = notebooks;
	}

	/**
	 * @return the notebookMap
	 */
	public Map<Integer, Notebook> getNotebookMap() {
		return notebookMap;
	}

	/**
	 * @param notebookMap the notebookMap to set
	 */
	public void setNotebookMap(Map<Integer, Notebook> notebookMap) {
		this.notebookMap = notebookMap;
	}

}
