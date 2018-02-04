package localobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notebook {
	
	private int idNotebook;
	private String name;
	private Date createDate;
	private Date expireDate;
	private boolean isPublic;
	private User user;
	private int idUser;
	
	private List<Diary> diaries = new ArrayList<>();
	private Map<Integer, Diary> diaryMap = new HashMap<>();
	
	public Notebook(int id, String name, Date createDate, Date expireDate, boolean isPublic,
					User user, int idUser) {
		this.idNotebook = id;
		this.name = name;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.isPublic = isPublic;
		this.user = user;
		this.idUser = idUser;
		
		this.user.addNotebook(this);
	}
	
	public void addDiary(Diary diary) {
		diaries.add(diary);
		diaryMap.put(diary.getIdDiary(), diary);
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
	public boolean getIsPublic() {
		return isPublic;
	}
	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(boolean isPublic) {
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
