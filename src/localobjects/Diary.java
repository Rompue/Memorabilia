package localobjects;

import java.util.Date;

public class Diary {
	private int idDiary;
	private String filepath;
	private String content;
	private Date createTime;
	private Notebook notebook;
	private int idNotebook;
	
	
	public Diary() {
		// TODO Auto-generated constructor stub
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


	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}


	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}


	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * @return the notebook
	 */
	public Notebook getNotebook() {
		return notebook;
	}


	/**
	 * @param notebook the notebook to set
	 */
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
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
}
