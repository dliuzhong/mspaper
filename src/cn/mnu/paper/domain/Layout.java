package cn.mnu.paper.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * ������Ϣ��,Layouts��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="layouts")
public class Layout {
	// ��ʶ����
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// �ں�
	@ManyToOne(targetEntity=Paper.class)
	@JoinColumn(name="paper_id", nullable=false)
	private Paper paper;
	
	// ���
	@Column(name="layout_no")
	private int layout_no;
	
	// ͼƬ
	@Column(name="pic")
	private String pic;
	
	// pdf
	@Column(name="pdf")
	private String pdf;
	
	// ����
	@Column(name="zt")
	private String zt;
	
	// �༭
	@Column(name="bj")
	private String bj;
	
	// ����
	@Column(name="other")
	private String other;
	
	// �����������������
	@OneToMany(targetEntity=News.class, mappedBy="layout", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<News> layout_news = new HashSet<News>();
	
	public Layout() {
		// TODO Auto-generated constructor stub
	}
	
	public Layout(Integer id, Paper paper, int layout_no, String pic,
			String pdf, String zt, String bj, String other) {
		this.id = id;
		this.paper = paper;
		this.layout_no = layout_no;
		this.pic = pic;
		this.pdf = pdf;
		this.zt = zt;
		this.bj = bj;
		this.other = other;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public int getLayout_no() {
		return layout_no;
	}

	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Set<News> getAll_news() {
		return layout_news;
	}

	public void setAll_news(Set<News> layout_news) {
		this.layout_news = layout_news;
	}
}
