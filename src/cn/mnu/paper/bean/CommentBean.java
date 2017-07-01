package cn.mnu.paper.bean;

import java.util.Date;



import cn.mnu.paper.domain.News;

public class CommentBean {
	private Integer id;
	
	// ���ź�

	private News news;
	
	// �ǳ�
	private String name;
	
	// ��������
	private String comment;
	
	// ��������ʱ��
	private Date time;
	
	// ������IP
	private String ip;
	
	// ���
	private int pass;
	
	public CommentBean(Integer id, News news, String name, String comment,
			Date time, String ip, int pass) {
		this.id = id;
		this.news = news;
		this.name = name;
		this.comment = comment;
		this.time = time;
		this.ip = ip;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}
}
