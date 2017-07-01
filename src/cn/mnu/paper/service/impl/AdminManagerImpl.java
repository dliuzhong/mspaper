package cn.mnu.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.AdminDao;
import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.AdminManager;
import cn.mnu.paper.tools.Md5;

/***
 * Login业务逻辑组件实现类
 * @author com.pie
 * @version 1.0
 * @date 2014-06-10
 */
public class AdminManagerImpl implements AdminManager {
	static Logger log = Logger.getLogger(
			AdminManagerImpl.class.getName());
	private AdminDao adminDao;
	
	/***
	 * 新增管理员用户
	 * @param admin 新增用户的Admin实例
	 * @return 新境用户的主键
	 */
	public int addAdmin(Admin admin) throws PaperException {
		try {
			if (adminDao.findAdminByName(admin.getUsername()) != null) {
				return -1;
			}
			if ((admin.getGrade() == 2 || admin.getGrade() == 3)
					&& (adminDao.getAdminSumByGrade(admin.getGrade()) >= 4)) {
				return -2;
			}
			admin.setOther("0");
			admin.setDatetime(new Date());
			adminDao.save(admin);
			return admin.getId();
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加管理员出现失败，请重试");
			
		}
		
	}

	/***
	 * 验证管理员是否可用，如果系统中已有该管理员，则不可用
	 * @param name 需要验证的管理员
	 * @return 管理员是否可用
	 */
	public boolean validateByName(Integer id, String name) throws PaperException {
		try {
			Admin a = adminDao.findAdminByName(name);
			if (a == null) {
				return true;
			} else if (id != 0 && a.getId() == id) {
				return true;
			} else {
				return false;
			}
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找管理员用户名失败，请重试");
			
		}
	}

	/***
	 * 删除管理员用户
	 * @param admin 删除用户的Admin实例
	 * @return 删除用户的主键
	 */
	public int deleteAdmin(Admin admin) throws PaperException {
		try {
			// 检查管理员账户是否存在
			if (adminDao.findAdminByName(admin.getName()) != null) {
				return -1;		
			}
			adminDao.delete(admin);
			return admin.getId();
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除管理员出现失败，请重试");
			
		}
	}

	/***
	 * 验证管理员用户登录
	 * @param user 需要验证的Admin实例 
	 * @return 是否登陆成功
	 */
	public Admin loginValid(Admin admin) throws PaperException {
		try {
			Admin ad = null;
			ad = adminDao.findAdminByNameAndPass(admin.getUsername(), admin.getPassword());
			if (ad != null) {
				return ad;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("管理员用户名，密码验证失败，请重试");
		}
	}

	/***
	 * 修改管理员信息
	 * @param admin 要改成的Admin实例 
	 * @return 修改成功后的Amdin实例ID
	 */
	public int updateAdmin(Admin admin) throws PaperException {
		try {
			if ((admin.getGrade() == 2 || admin.getGrade() == 3)
					&& (adminDao.getAdminSumByGrade(admin.getGrade()) >= 4)) {
				return -2;
			}
			Admin ad = null;
			ad = adminDao.findAdminById(admin.getId());
			if (ad != null) {
				ad.setUsername(admin.getUsername());
				ad.setName(admin.getName());
				ad.setGrade(admin.getGrade());
				ad.setOther(ad.getOther());
				if (admin.getPassword() != null && !admin.getPassword().trim().equals("")) {
					ad.setPasswordNoMD5(admin.getPassword());
				}
				adminDao.update(ad);
				return ad.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("管理员信息修改失败，请重试");
		}
	}
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/***
	 * 获取所有管理员的总数
	 * @return int 管理员的总数
	 * @throws PaperException
	 */
	public int findAllAdminSum() throws PaperException {
		try {
			return adminDao.getAllAdminSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有管理员总数量出现失败，请重试");
		}
	}

	/***
	 * 获取所有管理员
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return 所有管理员
	 * @throws PaperException
	 */
	public List<Admin> findAllAdmin(int start, int limit, String sort,
			String dir) throws PaperException {
		try {
			return adminDao.getAllAdmin(start, limit, sort, dir);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有管理员出现失败，请重试");
		}
	}

	/***
	 * 根据ID验证管理员密码
	 * @param id ID
	 * @param password 要验证的密码
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean passwordValid(Integer id, String password)
			throws PaperException {
		try {
			Admin a = adminDao.get(Admin.class, id);
			if (a != null) {
				return Md5.validatePassword(a.getPassword(), password);
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("验证管理员密码出现失败，请重试");
		}
	}

	/***
	 * 根据ID获取Admin
	 * @param id
	 * @return Admin
	 * @throws PaperException
	 */
	public Admin findAdminById(Integer id) throws PaperException {
		try {
			Admin a = adminDao.get(Admin.class, id);
			if (a != null) {
				return a;
			} else {
				return null;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID获取管理员出现失败，请重试");
		}
	}

	/***
	 * 根据等级获取相应管理员的数量
	 * @param grade
	 * @return 相应管理员的数量
	 * @throws PaperException
	 */
	public int findAdminSumByGrade(int grade) throws PaperException {
		try {
			System.out.println("grade error");
			return adminDao.getAdminSumByGrade(grade);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据等级获取相应管理员的数量出现失败，请重试");
		}
	}

	

}
