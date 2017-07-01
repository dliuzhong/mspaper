package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.DepartmentDao;
import cn.mnu.paper.domain.Department;
/***
 * DepartmentDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class DepartmentDaoHibernate extends BaseDaoHibernate<Department>
	implements DepartmentDao {

	/***
	 * 根据用户名、密码查找管理员用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return 对应的用户
	 */
	public int getAllDepartmentSum() {
		return findAll(Department.class).size();
	}

	/***
	 * 查询所有部门
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有部门
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
	 * 根据type查找Department
	 * @param type
	 * @return List<Department> 
	 */
	public List<Department> getDepartmentByType(int type) {
		return find("select d from Department d where d.type=?0", type);
	}

}
