package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import cn.mnu.paper.tools.Md5;


/***
 * ����Ա��,Admin��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="admin")
public class Admin {
	// ��ʶ����
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// �û���
	@Column(name="username")
	private String username;
	
	// ����
	@Column(name="password")
	private String password;
	
	// ����
	@Column(name="name")
	private String name;
	
	// �ȼ�
	@Column(name="grade")
	private int grade;
	
	// ����
	@Column(name="other")
	private String other;
	
	// ��������ʱ��
	@Column(name="datatime")
	private Date datetime;
	
	// ���û���������
	@OneToMany(targetEntity=Article.class, mappedBy="admin", cascade=CascadeType.PERSIST)
	private Set<Article> all_articles = new HashSet<Article>();
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer id, String username, String password, String name, 
			int grade, String other, Date datetime) {
		this.id = id;
		this.username = username;
		// ����
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
		// ����
		this.password = Md5.generatePassword(password);
	}
	
	public void setPasswordNoMD5(String password) {
		// ����
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
