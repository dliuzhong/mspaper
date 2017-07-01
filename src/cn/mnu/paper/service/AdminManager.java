package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.exception.PaperException;

/***
 * Admin业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface AdminManager {
	
	/***
	 * 新增管理员用户
	 * @param admin 新增用户的Admin实例
	 * @return 新境用户的主键
	 */
	int addAdmin(Admin admin) throws PaperException;
	
	/***
	 * 验证管理员是否可用，如果系统中已有该管理员，则不可用
	 * @param name 需要验证的管理员
	 * @return 管理员是否可用
	 */
	boolean validateByName(Integer id, String name) throws PaperException;

	
	/***
	 * 删除管理员用户
	 * @param admin 删除用户的Admin实例
	 * @return 删除用户的主键
	 */

	int deleteAdmin(Admin admin) throws PaperException;
	
	/***
	 * 验证管理员用户登录
	 * @param admin 需要验证的Admin实例 
	 * @return 验证成功后的Amdin实例，否则为Null
	 */
	Admin loginValid(Admin admin) throws PaperException;
	
	/***
	 * 修改管理员信息
	 * @param admin 要改成的Admin实例 
	 * @return 修改成功后的Amdin实例，否则为Null
	 */
	int updateAdmin(Admin admin) throws PaperException;
	/***
	 * 获取所有管理员的总数
	 * @return int 管理员的总数
	 * @throws PaperException
	 */
	int findAllAdminSum() throws PaperException;
	/***
	 * 获取所有管理员
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return 所有管理员
	 *
	 */
	List<Admin> findAllAdmin(int start, int limit, String sort, String dir) 
			throws PaperException;
	/***
	 * 根据ID验证管理员密码
	 * @param id ID
	 * @param password 要验证的密码
	 * @return boolean
	 * @throws PaperException
	 */
	boolean passwordValid(Integer id, String password) throws PaperException;
	/***
	 * 根据ID获取Admin
	 * @param id
	 * @return Admin
	 * @throws PaperException
	 */
	Admin findAdminById(Integer id) throws PaperException;
	/***
	 * 根据等级获取相应管理员的数量
	 * @param grade
	 * @return 相应管理员的数量
	 * @throws PaperException
	 */
	int findAdminSumByGrade(int grade) throws PaperException;
}
