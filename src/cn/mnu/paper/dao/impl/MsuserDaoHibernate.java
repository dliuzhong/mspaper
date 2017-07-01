package cn.mnu.paper.dao.impl;


import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.MsuserDao;
import cn.mnu.paper.domain.Msuser;
/***
 * UserDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-08-12
 */
public class MsuserDaoHibernate extends BaseDaoHibernate<Msuser>
	implements MsuserDao {

	/***
	 * ��ѯ�����û����� 
	 * @return int �û����� 
	 */
	public int getAllMsuserSum() {
		
		return findAll(Msuser.class).size();
	}

	/***
	 * ��ѯ�����û�
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return �����û�
	 */
	public List<Msuser> getAllMsuser(int start, int limit, String sort, String dir) {
		String hql = "select u from Msuser u ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.equals("department")) {
				hql += "order by u.department.name " + dir;
			} else {
				hql += "order by u." + sort + " " + dir;
			}
			
		} else {
			hql += "order by u.datetime DESC";
		}
		List<Msuser> ul = find(hql);
		if (start + limit <= ul.size())
			return ul.subList(start, start + limit);
		else 
			return ul.subList(start, ul.size());
	}

	/***
	 * ����departmentid��ѯ��Ӧ�û�������
	 * @param departmentid
	 * @return ��Ӧ�û�������
	 */
	public int getMsuserSumByDepartment(Integer departmentid) {
		return find("select m from Msuser m where m.department.id=?0", 
				departmentid).size();
	}

	/***
	 * ��֤email�Ƿ������û�
	 * @param email
	 * @return Msuser
	 */
	public Msuser getMsuserByEmail(String email) {
		List<Msuser> ml = find("select m from Msuser m where m.email=?0", email);
		if (ml != null && ml.size() > 0) {
			return ml.get(0);
		} else {
			return null;
		}
	}

	/***
	 * �����û����������ѯMsuser
	 * @param username �û���
	 * @param password ����
	 * @return ��Ӧ�û�
	 */
	public Msuser getMsuserByUsernameAndPassword(String username,
			String password) {
		List<Msuser> ml = find("select m from Msuser m where m.username=?0 and m.password=?1",
				username, password);
		if (ml != null && ml.size() >= 1) {
			return (Msuser) ml.get(0);
		}
		return null;
	}

	
}
