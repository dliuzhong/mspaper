package cn.mnu.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.DepartmentDao;
import cn.mnu.paper.dao.MsuserDao;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.MsuserManager;

/***
 * Login业务逻辑组件实现类
 * @author com.pie
 * @version 1.0
 * @date 2014-06-10
 */
public class MsuserManagerImpl implements MsuserManager {
	static Logger log = Logger.getLogger(
			MsuserManagerImpl.class.getName());
	private MsuserDao msuserDao;
	private DepartmentDao departmentDao;
	
	
	public MsuserDao getMsuserDao() {
		return msuserDao;
	}
	public void setMsuserDao(MsuserDao msuserDao) {
		this.msuserDao = msuserDao;
	}
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	/***
	 * 获取所有用户的总数量
	 * @return int 所有用户的总数量
	 * @throws PaperException
	 */
	public int findAllMsuserSum() throws PaperException {
		try {
			return msuserDao.getAllMsuserSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有用户总数量出现失败，请重试" + e.getMessage());
		}
	}
	/***
	 * 获取所有管理员
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式 
	 * @return 所有管理员
	 *
	 */
	public List<Msuser> findAllMsuser(int start, int limit, String sort, String dir)
			throws PaperException {
		try {
			return msuserDao.getAllMsuser(start, limit, sort, dir);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有用户出现失败，请重试");
		}
	}
	
	/***
	 * 增加投稿用户
	 * @param msuer
	 * @return Integer
	 * @throws PaperException
	 */
	public int addMsuser(Msuser msuser) throws PaperException {
		try {
			msuser.setStatus(0);
			msuser.setDatetime(new Date());
			msuser.setDepartment(null);
			msuser.setUsername(msuser.getEmail());
			msuserDao.save(msuser);
			return msuser.getId();
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加用户出现失败，请重试");
		}
	}
	/***
	 * 修改投稿用户的用户名和密码
	 * @param msuser
	 * @return
	 * @throws PaperException
	 */
	public int changeUNPWMsuser(Msuser msuser) throws PaperException {
		try {
			Msuser u = msuserDao.get(Msuser.class, msuser.getId());
			if (u != null) {
				u.setUsername(msuser.getEmail());
				u.setEmail(msuser.getEmail());
				if (msuser.getPassword() != null && !msuser.getPassword().trim().equals("")) {
					u.setPasswordNoMD5(msuser.getPassword());
				}
				msuserDao.update(u);
			}
			return u.getId();
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改用户出现失败，请重试");
		}
	}
	/***
	 * 根据ID删除投稿用户
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean deleteMsuser(Integer id) throws PaperException {
		try {
			Msuser u = msuserDao.get(Msuser.class, id);
			if (u != null) {
				msuserDao.delete(u);
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除用户出现失败，请重试");
		}
	}
	/***
	 * 根据departmentid获取相应用户的数量
	 * @param departmentid
	 * @return 相应用户的数量
	 * @throws PaperException
	 */
	public int findMsuserSumByDepartment(Integer departmentid)
			throws PaperException {
		try {
			return msuserDao.getMsuserSumByDepartment(departmentid);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据departmentid查找所有用户出现失败，请重试");
		}
	}
	/***
	 * 根据ID获取msuser
	 * @param id
	 * @return msuser
	 * @throws PaperException
	 */
	public Msuser findMsuserById(Integer id) throws PaperException {
		try {
			return msuserDao.get(Msuser.class, id);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID查找所有用户出现失败，请重试");
		}
	}
	/***
	 * 修改Msuser
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	public int changeMsuser(Msuser msuser, Integer departmentid) throws PaperException {
		try {
			Msuser m = msuserDao.get(Msuser.class, msuser.getId());
			if (m != null) {
				m.setUsername(msuser.getEmail());
				m.setName(msuser.getName());
				m.setEmail(msuser.getEmail());
				m.setTelephone(msuser.getTelephone());
				m.setQq(msuser.getQq());
				Department d = null;
				if (departmentid != 0) {
					d = departmentDao.get(Department.class, departmentid);
				}
				
				m.setDepartment(d);
				if (m.getStatus() == 0) {
					m.setStatus(1);
				}
				msuserDao.update(m);
				return m.getId();
			} else {
				return 0;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改用户出现失败，请重试");
		}
	}
	/***
	 * 修改Msuser的类型
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	public int changeMsuserType(Msuser msuser) throws PaperException {
		try {
			Msuser m = msuserDao.get(Msuser.class, msuser.getId());
			if (m != null) {
				
				m.setType(msuser.getType());
				
				msuserDao.update(m);
				return m.getId();
			} else {
				return 0;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改用户类型出现失败，请重试");
		}
	}
	/***
	 * 验证email是否已有用户
	 * @param email
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean validateMsuserByEmail(Integer id, String email) throws PaperException {
		try {
			Msuser m = msuserDao.getMsuserByEmail(email);
			if (m == null) {
				return true;
			} else if (id != 0 && m.getId() == id) {
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("验证email出现失败，请重试");
		}
	}
	/***
	 * 验证投稿用户登录
	 * @param admin 需要验证的Msuser实例 
	 * @return 验证成功后的Msuser实例，否则为Null
	 */
	public Msuser loginValid(Msuser msuser) throws PaperException {
		try {
			Msuser mu = null;
			mu = msuserDao.getMsuserByUsernameAndPassword(msuser.getUsername(), 
					msuser.getPassword());
			if (mu != null) {
				return mu;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("投稿用户名，密码验证失败，请重试");
		}
	}
	/***
	 * 获取所有Msuser
	 * @return 所有Msuser
	 * @throws PaperException
	 */
	public List<Msuser> findAllMsuser() throws PaperException {
		try {
			return msuserDao.findAll(Msuser.class);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("获取所有投稿用户出现失败，请重试");
		}
	}
	
	
}
