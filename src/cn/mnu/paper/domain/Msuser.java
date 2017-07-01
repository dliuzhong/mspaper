package cn.mnu.paper.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import cn.mnu.paper.tools.Md5;


/***
 * �û���,User��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="msuser")
public class Msuser {
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
	
	// �绰
	@Column(name="telephone")
	private String telephone;
	
	// qq
	@Column(name="qq")
	private String qq;
	
	// ����
	@Column(name="type")
	private String type;
	
	// email
	@Column(name="email")
	private String email;
	
	// ����
	@ManyToOne(targetEntity=Department.class)
	@JoinColumn(name="department_id", nullable=true)
	private Department department;
	
	// ״̬
	@Column(name="status")
	private int status;
	
	// ��������ʱ��
	@Column(name="datetime")
	private Date datetime;
	
	
	// ���û���������
	@OneToMany(targetEntity=Article.class, mappedBy="msuser", cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<Article> all_articles = new HashSet<Article>();
	
	public Msuser() {
	}
	
	public Msuser(Integer id, String username, String password, String name, 
			String telephone, String qq, String type, Department department,
			int status, Date datetime, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.telephone = telephone;
		this.qq = qq;
		this.type = type;
		this.department = department;
		this.status = status;
		this.datetime = datetime;
		this.email = email;
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
		this.password = Md5.generatePassword(password);
	}
	public void setPasswordNoMD5(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public Set<Article> getAll_articles() {
		return all_articles;
	}

	public void setAll_articles(Set<Article> all_articles) {
		this.all_articles = all_articles;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
