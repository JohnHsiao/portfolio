package dao.orm;

import java.util.Date;

/**
 * EpsFeedback entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EpsFeedback implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer page;
	private String author;
	private String content;
	private Date postime;

	// Constructors

	/** default constructor */
	public EpsFeedback() {
	}

	/** minimal constructor */
	public EpsFeedback(Date postime) {
		this.postime = postime;
	}

	/** full constructor */
	public EpsFeedback(Integer page, String author, String content, Date postime) {
		this.page = page;
		this.author = author;
		this.content = content;
		this.postime = postime;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostime() {
		return this.postime;
	}

	public void setPostime(Date postime) {
		this.postime = postime;
	}

}