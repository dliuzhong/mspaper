package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.DepartmentDao;
import cn.mnu.paper.domain.Department;
/***
 * DepartmentDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class DepartmentDaoHibernate extends BaseDaoHibernate<Department>
	implements DepartmentDao {

	/***
	 * �����û�����������ҹ���Ա�û�
	 * @param name �û���
	 * @param pass ����
	 * @return ��Ӧ���û�
	 */
	public int getAllDepartmentSum() {
		return findAll(Department.class).size();
	}

	/***
	 * ��ѯ���в���
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���в���
	 */
	public List<Department> getAllDepartment(int start, int limit, String sort,
			String dir) {
		String hql = "select d from Department d ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			hql += "order by d." + sort + " " + dir;
		} else {
			hql += "order by d.id DESC";
		}
		List<Department> dl = find(hql);
		if (start + limit <= dl.size())
			return dl.subList(start, start + limit);
		else 
			return dl.subList(start, dl.size());
	}

	/***
	 * ����type����Department
	 * @param type
	 * @return List<Department> 
	 */
	public List<Department> getDepartmentByType(int type) {
		return find("select d from Department d where d.type=?0", type);
	}

}
