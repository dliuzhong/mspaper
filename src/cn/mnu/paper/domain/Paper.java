package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * 报纸信息体,Papers类
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="papers")
public class Paper {
	// 标识属性
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// 期号
	@Column(name="paper")
	private String paper;
	
	// url
	@Column(name="paper_url")
	private String paper_url;
	
	// 版数
	@Column(name="lay_sum")
	private int lay_sum;
	
	// 主办
	@Column(name="zb")
	private String zb;
	
	// 出版
	@Column(name="cb")
	private String cb;
	
	// 总编
	@Column(name="zongb")
	private String zongb;
	
	// 出版日期
	@Column(name="cbtime")
	private Date cbtime;
	
	// email
	@Column(name="email")
	private String email;
	
	// 本期报纸的所有版面信息
	@OneToMany(targetEntity=Layout.class, mappedBy="paper", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<Layout> all_layouts = new HashSet<Layout>();
	
	// 本期报纸的所有新闻内容
	@OneToMany(targetEntity=News.class, mappedBy="paper", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<News> all_news = new HashSet<News>();
	
	
	
	public Paper() {
		// TODO Auto-generated constructor stub
	}
	public Paper(Integer id, String paper, String paper_url, int lay_sum,
			String zb, String cb, String zongb, Date cbtime, String email) {
		this.id = id;
		this.paper = paper;
		this.paper_url = paper_url;
		this.lay_sum = lay_sum;
		this.cb = cb;
		this.zb = zb;
		this.zongb = zongb;
		this.cbtime = cbtime;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getPaper_url() {
		return paper_url;
	}

	public void setPaper_url(String paper_url) {
		this.paper_url = paper_url;
	}

	public int getLay_sum() {
		return lay_sum;
	}

	public void setLay_sum(int lay_sum) {
		this.lay_sum = lay_sum;
	}

	public String getZb() {
		return zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	public String getCb() {
		return cb;
	}

	public void setCb(String cb) {
		this.cb = cb;
	}

	public String getZongb() {
		return zongb;
	}

	public void setZongb(String zongb) {
		this.zongb = zongb;
	}

	public Date getCbtime() {
		return cbtime;
	}

	public void setCbtime(Date cbtime) {
		this.cbtime = cbtime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Layout> getAll_layouts() {
		return all_layouts;
	}

	public void setAll_layouts(Set<Layout> all_layouts) {
		this.all_layouts = all_layouts;
	}

	public Set<News> getAll_news() {
		return all_news;
	}

	public void setAll_news(Set<News> all_news) {
		this.all_news = all_news;
	}
}
