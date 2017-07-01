package cn.mnu.paper.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.AdminDao;
import cn.mnu.paper.domain.Admin;

/***
 * AdminDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class AdminDaoHibernate extends BaseDaoHibernate<Admin> 
	implements AdminDao {
	/***
	 * �����û�����������ҹ���Ա�û�
	 * @param name �û���
	 * @param pass ����
	 * @return ��Ӧ���û�
	 */
	@Override
	public Admin findAdminByNameAndPass(String name, String pass) {
		List<Admin> al = find("select a from Admin a where a.username=?0 and a.password=?1",
				name, pass);
		if (al != null && al.size() >= 1) {
			return (Admin) al.get(0);
		}
		return null;
	}
	
	/***
	 * ���� �û��������û�
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
	public Admin findAdminByName(String name) {
		// TODO Auto-generated method stub
		List<Admin> al = find("select a from Admin a where a.username=?0", name);
		if (al != null && al.size() > 0) {
			return (Admin) al.get(0);
		}
		return null;
	}
	/***
	 * ���� �û����
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
	public Admin findAdminById(Integer id) {
		// TODO Auto-generated method stub
		List<Admin> al = find("select a from Admin a where a.id=?0", id);
		if (al != null && al.size() >= 1) {
			return (Admin) al.get(0);
		}
		return null;
	}

	/***
	 * ���� �û����
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
	public int getAllAdminSum() {
		return findAll(Admin.class).size();
	}

	/***
	 * ��ѯ���й���Ա
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return List<Admin> ����Ա
	 */
	public List<Admin> getAllAdmin(int start, int limit, String sort, String dir) {
		String hql = "select a from Admin a ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			hql += "order by a." + sort + " " + dir;
		} else {
			hql += "order by a.datetime DESC";
		}
		List<Admin> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * ���ݵȼ�grade��ѯ������Ա������
	 * @param grade
	 * @return ����Ա������
	 */
	public int getAdminSumByGrade(int grade) {
		return find("select a from Admin a where a.grade=?0", grade).size();
		
	}
	


}

