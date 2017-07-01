package cn.mnu.paper.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * ������,Department��
 * @author mdl
 * @version 1.0
 * @date 2014-07-11
 */
@Entity
@Table(name="departments")
public class Department {
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
	@Column(name="type")
	private int type;
	
	// �������ŵ������û�
	@OneToMany(targetEntity=Msuser.class, mappedBy="department", cascade=CascadeType.PERSIST)
	private Set<Msuser> dpt_user = new HashSet<Msuser>();
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(Integer id, String name, String de, int type) {
		this.id = id;
		this.name = name;
		this.de = de;
		this.type = type;
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

	public Set<Msuser> getDpt_user() {
		return dpt_user;
	}

	public void setDpt_user(Set<Msuser> dpt_user) {
		this.dpt_user = dpt_user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
