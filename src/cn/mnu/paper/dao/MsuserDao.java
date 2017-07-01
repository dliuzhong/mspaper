package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Msuser;

/***
 * 用户DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface MsuserDao extends BaseDao<Msuser>{
	/***
	 * 根据用户名、密码查找用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return 对应的用户
	 */
//	User findUserByNameAndPass(String name, String pass);
	/***
	 * 根据 用户名查找用户
	 * @param name 用户名
	 * @return 对应的用户
	 */
//	User findUserByName(String name);
	/***
	 * 验证email是否已用用户
	 * @param email
	 * @return Msuser
	 */
	Msuser getMsuserByEmail(String email);
	/***
	 * 查询所有用户总数 
	 * @return int 用户总数 
	 */
	int getAllMsuserSum();
	/***
	 * 查询所有用户
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return 所有用户
	 */
	List<Msuser> getAllMsuser(int start, int limit, String sort, String dir);
	/***
	 * 根据departmentid查询相应用户的数量
	 * @param departmentid
	 * @return 相应用户的数量
	 */
	int getMsuserSumByDepartment(Integer departmentid);
	/***
	 * 根据用户名和密码查询Msuser
	 * @param username 用户名
	 * @param password 密码
	 * @return 相应用户
	 */
	Msuser getMsuserByUsernameAndPassword(String username, String password);
}

