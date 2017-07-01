package cn.mnu.paper.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * ����������,ArticleType��
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
@Entity
@Table(name="article_type")
public class ArticleType {
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
	
	// ��������������
	@OneToMany(targetEntity=Article.class, mappedBy="type", cascade=CascadeType.PERSIST)
	private Set<Article> type_articles = new HashSet<Article>();
	
	public ArticleType() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticleType(Integer id, String name, String de) {
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

	public Set<Article> getType_articles() {
		return type_articles;
	}

	public void setType_articles(Set<Article> type_articles) {
		this.type_articles = type_articles;
	}
}
