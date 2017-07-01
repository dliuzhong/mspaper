package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***
 * ר����,Subject��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="subjects")
public class Subject {
	// ��ʶ����
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// ����
	@Column(name="name")
	private String name;
	
	// ����
	@Column(name="de")
	private String de;
	
	// ����
	@Column(name="st")
	private int st;
	
	// ��������ʱ��
	@Column(name="time")
	private Date time;
	
	// ר����ص�������������
	@OneToMany(targetEntity=News.class, mappedBy="subject", cascade=CascadeType.PERSIST)
	private Set<News> subject_news = new HashSet<News>();
	
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	public Subject(Integer id, String name, String de, int st, Date time) {
		this.id = id;
		this.name = name;
		this.de = de;
		this.st = st;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}



	public Set<News> getSubject_news() {
		return subject_news;
	}

	public void setSubject_news(Set<News> subject_news) {
		this.subject_news = subject_news;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}
}
