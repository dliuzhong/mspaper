package cn.mnu.paper.domain;

import java.util.Date;

import javax.persistence.*;

/***
 * ������,Comment��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="comments")
public class Comment {
	// ��ʶ����
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// ���ź�
	@ManyToOne(targetEntity=News.class)
	@JoinColumn(name="news_id", nullable=false)
	private News news;
	
	// �ǳ�
	@Column(name="name")
	private String name;
	
	// ��������
	@Column(name="comment")
	private String comment;
	
	// ��������ʱ��
	@Column(name="time")
	private Date time;
	
	// ������IP
	@Column(name="ip")
	private String ip;
	
	// ���
	@Column(name="pass")
	private int pass;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(Integer id, String name, String comment, 
			Date time, String ip, int pass, News news) {
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
