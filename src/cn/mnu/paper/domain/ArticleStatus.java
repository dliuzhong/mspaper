package cn.mnu.paper.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * ����״̬��,ArticleStatus��
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
@Entity
@Table(name="article_status")
public class ArticleStatus {
	// ��ʶ����
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// ����
	@Column(name = "name")
	private String name;

	// ����
	@Column(name = "de")
	private String de;

	// ��������������
	@OneToMany(targetEntity = Article.class, mappedBy = "status", cascade=CascadeType.PERSIST)
	private Set<Article> status_articles = new HashSet<Article>();
	
	public ArticleStatus() {
		// TODO Auto-generated constructor stub
	}
	public ArticleStatus(Integer id, String name, String de) {
		this.id = id;
		this.name = name;
		this.de = de;
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

	public Set<Article> getStatus_articles() {
		return status_articles;
	}

	public void setStatus_articles(Set<Article> status_articles) {
		this.status_articles = status_articles;
	}
}
