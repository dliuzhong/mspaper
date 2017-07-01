package cn.mnu.paper.dao.impl;


import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.MsuserDao;
import cn.mnu.paper.domain.Msuser;
/***
 * UserDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-12
 */
public class MsuserDaoHibernate extends BaseDaoHibernate<Msuser>
	implements MsuserDao {

	/***
	 * 查询所有用户总数 
	 * @return int 用户总数 
	 */
	public int getAllMsuserSum() {
		
		return findAll(Msuser.class).size();
	}

	/***
	 * 查询所有用户
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return 所有用户
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
	 * 根据departmentid查询相应用户的数量
	 * @param departmentid
	 * @return 相应用户的数量
	 */
	public int getMsuserSumByDepartment(Integer departmentid) {
		return find("select m from Msuser m where m.department.id=?0", 
				departmentid).size();
	}

	/***
	 * 验证email是否已用用户
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
	 * 根据用户名和密码查询Msuser
	 * @param username 用户名
	 * @param password 密码
	 * @return 相应用户
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
