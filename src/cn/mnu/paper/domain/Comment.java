package cn.mnu.paper.domain;

import java.util.Date;

import javax.persistence.*;

/***
 * 评论体,Comment类
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="comments")
public class Comment {
	// 标识属性
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 新闻号
	@ManyToOne(targetEntity=News.class)
	@JoinColumn(name="news_id", nullable=false)
	private News news;
	
	// 昵称
	@Column(name="name")
	private String name;
	
	// 评论内容
	@Column(name="comment")
	private String comment;
	
	// 评论日期时间
	@Column(name="time")
	private Date time;
	
	// 评论者IP
	@Column(name="ip")
	private String ip;
	
	// 审查
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
