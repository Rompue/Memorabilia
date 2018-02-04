package localobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notebook {
	
	private int idNotebook;
	private String name;
	private Date createDate;
	private Date expireDate;
	private boolean isPublic;
	private User user;
	private int idUser;
	
	private List<Diary> diaries = new ArrayList<>();
	
	public Notebook() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the idNotebook
	 */
	public int getIdNotebook() {
		return idNotebook;
	}
	/**
	 * @param idNotebook the idNotebook to set
	 */
	public void setIdNotebook(int idNotebook) {
		this.idNotebook = idNotebook;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}
	/**
	 * @param isPublic the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
	
	
}
