package cn.mnu.paper.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.AdminDao;
import cn.mnu.paper.domain.Admin;

/***
 * AdminDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class AdminDaoHibernate extends BaseDaoHibernate<Admin> 
	implements AdminDao {
	/***
	 * 根据用户名、密码查找管理员用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return 对应的用户
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
	 * 根据 用户名查找用户
	 * @param name 用户名
	 * @return 对应的用户
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
	 * 根据 用户编号
	 * @param name 用户名
	 * @return 对应的用户
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
	 * 根据 用户编号
	 * @param name 用户名
	 * @return 对应的用户
	 */
	public int getAllAdminSum() {
		return findAll(Admin.class).size();
	}

	/***
	 * 查询所有管理员
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return List<Admin> 管理员
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
	 * 根据等级grade查询出管理员的数量
	 * @param grade
	 * @return 管理员的数量
	 */
	public int getAdminSumByGrade(int grade) {
		return find("select a from Admin a where a.grade=?0", grade).size();
		
	}
	


}

