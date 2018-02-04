package localobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diary {
	private int idDiary;
	private String filepath;
	private String content;
	private Date createTime;
	private Notebook notebook;
	private int idNotebook;
	private List<Comment> comments = new ArrayList<>();
	private Map<Integer, Comment> commentMap = new HashMap<>();
	
	
	public Diary(int idDiary, String content, Date createTime, Notebook notebook, int idNotebook) {
		this.idDiary = idDiary;
		this.filepath = "no file";
		this.content = content;
		this.createTime= createTime;
		this.notebook = notebook;
		this.idNotebook = idNotebook;
		
		this.notebook.addDiary(this);
	} 
	
	public void addComment(Comment comment) {
		comments.add(comment);
		commentMap.put(comment.getIdComment(), comment);
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
