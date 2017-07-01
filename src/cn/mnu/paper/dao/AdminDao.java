package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Admin;

/***
 * 管理员DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface AdminDao extends BaseDao<Admin> {
	/***
	 * 根据用户名、密码查找管理员用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return 对应的用户
	 */
	Admin findAdminByNameAndPass(String name, String pass);
	
	/***
	 * 根据 用户名查找用户
	 * @param name 用户名
	 * @return 对应的用户
	 */
	Admin findAdminByName(String name);
	
	/***
	 * 根据 用户编号
	 * @param name 用户名
	 * @return 对应的用户
	 */
	Admin findAdminById(Integer id);
	/***
	 * 查询出所有管理员的总数
	 * @return int 管理员的总数
	 */
	int getAllAdminSum();
	/***
	 * 查询所有管理员
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return List<Admin> 管理员
	 */
	List<Admin> getAllAdmin(int start, int limit, String sort, String dir);
	/***
	 * 根据等级grade查询出管理员的数量
	 * @param grade
	 * @return 管理员的数量
	 */
	int getAdminSumByGrade(int grade);
	
}

