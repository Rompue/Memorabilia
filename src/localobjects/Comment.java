package localobjects;

public class Comment {
	private int idComment;
	private String content;
	private User user;
	private int idUser;
	private Diary diary;
	private int idDiary;
	
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the idComment
	 */
	public int getIdComment() {
		return idComment;
	}


	/**
	 * @param idComment the idComment to set
	 */
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}


	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	/**
	 * @return the diary
	 */
	public Diary getDiary() {
		return diary;
	}


	/**
	 * @param diary the diary to set
	 */
	public void setDiary(Diary diary) {
		this.diary = diary;
	}


	/**
	 * @return the idDiary
	 */
	public int getIdDiary() {
		return idDiary;
	}


	/**
	 * @param idDiary the idDiary to set
	 */
	public void setIdDiary(int idDiary) {
		this.idDiary = idDiary;
	}
	
	
	
	
}
