package cn.mnu.paper.bean;

import java.util.Date;

public class NewsBean {
	private Integer id;
	private String title;
	private String paper;
	private int layout_no;
	private String author;
	private String subject;
	private Date cbtime;
	private Date lastedittime;
	
	public NewsBean(Integer id, String paper, int layout_no, String author,
			String subject, Date cbtime, Date lastedittime) {
		this.id = id;
		this.paper = paper;
		this.layout_no = layout_no;
		this.author = author;
		this.subject = subject;
		this.cbtime = cbtime;
		this.lastedittime = lastedittime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public int getLayout_no() {
		return layout_no;
	}

	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCbtime() {
		return cbtime;
	}

	public void setCbtime(Date cbtime) {
		this.cbtime = cbtime;
	}

	public Date getLastedittime() {
		return lastedittime;
	}

	public void setLastedittime(Date lastedittime) {
		this.lastedittime = lastedittime;
	}
}
