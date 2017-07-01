package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import cn.mnu.paper.tools.Md5;


/***
 * 管理员体,Admin类
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="admin")
public class Admin {
	// 标识属性
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 用户名
	@Column(name="username")
	private String username;
	
	// 密码
	@Column(name="password")
	private String password;
	
	// 姓名
	@Column(name="name")
	private String name;
	
	// 等级
	@Column(name="grade")
	private int grade;
	
	// 其它
	@Column(name="other")
	private String other;
	
	// 创建日期时间
	@Column(name="datatime")
	private Date datetime;
	
	// 本用户所以来稿
	@OneToMany(targetEntity=Article.class, mappedBy="admin", cascade=CascadeType.PERSIST)
	private Set<Article> all_articles = new HashSet<Article>();
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer id, String username, String password, String name, 
			int grade, String other, Date datetime) {
		this.id = id;
		this.username = username;
		// 加密
		this.password = Md5.generatePassword(password);
		this.name = name;
		this.grade = grade;
		this.other = other;
		this.datetime = datetime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// 加密
		this.password = Md5.generatePassword(password);
	}
	
	public void setPasswordNoMD5(String password) {
		// 加密
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Set<Article> getAll_articles() {
		return all_articles;
	}
	public void setAll_articles(Set<Article> all_articles) {
		this.all_articles = all_articles;
	}


}
