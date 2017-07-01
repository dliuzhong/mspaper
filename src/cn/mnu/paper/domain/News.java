package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * ��������,News��
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
@Entity
@Table(name="news")
public class News {
	// ��ʶ����
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// ����
	@Column(name = "title")
	private String title;
	
	// ����
	@Column(name = "author")
	private String author;
	
	// ����
	@Column(name = "content")
	private String content;
	
	// ��
	@Column(name = "zuo")
	private int zuo;
	
	// ��
	@Column(name = "shang")
	private int shang;
	
	// ��
	@Column(name = "kuan")
	private int kuan;
	
	// ��
	@Column(name = "gao")
	private int gao;
	
	// �ļ�·�� 
	@Column(name = "file_path")
	private String file_path;
	
	// ��������ʱ��
	@Column(name = "time")
	private Date time;
	
	// ����
	@Column(name = "other")
	private String other;
		
	// �ں�
	@ManyToOne(targetEntity=Paper.class)
	@JoinColumn(name="paper_id", nullable=false)
	private Paper paper;
	
	// �����
	@ManyToOne(targetEntity = Layout.class)
	@JoinColumn(name = "layout_id", nullable = false)
	private Layout layout;
	// ר���
	@ManyToOne(targetEntity = Subject.class)
	@JoinColumn(name = "subject_id", nullable = true)
	private Subject subject;
	
	
	// �����ŵ�������������
	@OneToMany(targetEntity=Comment.class, mappedBy="news", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<Comment> new_comments = new HashSet<Comment>();
	
	public News() {
		
	}
	
	public News(Integer id, String title, String author, String content, 
			int zuo, int shang, int kuan, int gao, String file_path, Date time,
			String other, Paper paper, Layout layout, Subject subject) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.zuo = zuo;
		this.shang = shang;
		this.kuan = kuan;
		
		this.gao = gao;
		
		this.file_path = file_path;
		this.time = time;
		this.other = other;
		this.paper = paper;
		this.layout = layout;
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getZuo() {
		return zuo;
	}

	public void setZuo(int zuo) {
		this.zuo = zuo;
	}

	public int getShang() {
		return shang;
	}

	public void setShang(int shang) {
		this.shang = shang;
	}

	public int getKuan() {
		return kuan;
	}

	public void setKuan(int kuan) {
		this.kuan = kuan;
	}

	public int getGao() {
		return gao;
	}

	public void setGao(int gao) {
		this.gao = gao;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<Comment> getNew_comments() {
		return new_comments;
	}

	public void setNew_comments(Set<Comment> new_comments) {
		this.new_comments = new_comments;
	}

	
	
}
