package cn.mnu.paper.domain;

import java.util.Date;

import javax.persistence.*;

/***
 * ������,Article��
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
@Entity
@Table(name="articles")
public class Article {
	// ��ʶ����
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// ����
	@Column(name="title")
	private String title;
	
	// ������
	@ManyToOne(targetEntity=Msuser.class)
	@JoinColumn(name="user_id", nullable=false)
	private Msuser msuser;
	
	// �ؼ���
	@Column(name="keyword")
	private String keyword;
	
	// ����
	@Column(name="content")
	private String content;
	
	// ��������
	@ManyToOne(targetEntity=ArticleType.class)
	@JoinColumn(name="type_id", nullable=true)
	private ArticleType type;
	
	// ����״̬
	@ManyToOne(targetEntity=ArticleStatus.class)
	@JoinColumn(name="status_id", nullable=true)
	private ArticleStatus status;
	
	// ��������ʱ��
	@Column(name="time")
	private Date time;
	
	// ������Ϣ
	@Column(name="other")
	private String other;
	
	// ���ʱ��
	@Column(name="checktime")
	private Date checktime;
	
	// �޸����
	@Column(name="changeinfo")
	private String changeinfo;
	
	// �Ƿ񷢱�
	@Column(name="publish")
	private int publish;
	
	// ��˵Ĺ���Ա
	@ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="admin_id", nullable=true)
	private Admin admin;
	
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public Article(Integer id, String title, Msuser msuser, String keyword, String content,
			ArticleType type, ArticleStatus status, Date time, String other,
			Date checktime, String changeinfo, Admin admin, int publish) {
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.msuser = msuser;
		this.content = content;
		this.type = type;
		this.status = status;
		this.time = time;
		this.other = other;
		this.checktime = checktime;
		this.changeinfo = changeinfo;
		this.admin = admin;
		this.publish = publish;
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

	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleType getType() {
		return type;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public ArticleStatus getStatus() {
		return status;
	}

	public void setStatus(ArticleStatus status) {
		this.status = status;
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

	public Msuser getMsuser() {
		return msuser;
	}

	public void setMsuser(Msuser msuser) {
		this.msuser = msuser;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public String getChangeinfo() {
		return changeinfo;
	}

	public void setChangeinfo(String changeinfo) {
		this.changeinfo = changeinfo;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getPublish() {
		return publish;
	}

	public void setPublish(int publish) {
		this.publish = publish;
	}
}
