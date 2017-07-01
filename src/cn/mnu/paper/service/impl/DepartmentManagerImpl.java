package cn.mnu.paper.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.DepartmentDao;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.DepartmentManager;

/***
 * Deparment业务逻辑组件实现类
 * @author com.pie
 * @version 1.0
 * @date 2014-08-13
 */
public class DepartmentManagerImpl implements DepartmentManager {
	static Logger log = Logger.getLogger(
			DepartmentManagerImpl.class.getName());
	private DepartmentDao departmentDao;
	
	
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	
	
	/***
	 * 获取所有部门的总数量
	 * @return int 所有部门的总数量
	 * @throws PaperException
	 */
	public int findAllDepartmentSum() throws PaperException {
		try {
			return departmentDao.getAllDepartmentSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有部门总数量出现失败，请重试");
		}
	}

	/***
	 * 获取所有部门
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有部门
	 * @throws PaperException
	 */
	public List<Department> findAllDepartment(int start, int limit,
			String sort, String dir) throws PaperException {
		try {
			return departmentDao.getAllDepartment(start, limit, sort, dir);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有部门出现失败，请重试");
		}
	}

	/***
	 * 添加部门
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	public int addDepartment(Department department) throws PaperException {
		try {
			if (department != null) {
				departmentDao.save(department);
				return department.getId();
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加部门出现失败，请重试");
		}
	}

	/***
	 * 修改部门
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	public int changeDepartment(Department department) throws PaperException {
		try {
			if (department != null) {
				Department d = departmentDao.get(Department.class, department.getId());
				if (d != null) {
					d.setName(department.getName());
					d.setDe(department.getDe());
					d.setType(department.getType());
					
					departmentDao.update(d);
					return d.getId();
				} else {
					return 0;
				}
				
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改部门出现失败，请重试");
		}
	}

	/***
	 * 删除部门
	 * @param department
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeDepartment(Integer id)
			throws PaperException {
		try {
			
			Department d = departmentDao.get(Department.class, id);
			if (d != null) {
				Set<Msuser> ml = d.getDpt_user();
				for (Msuser m : ml) {
					m.setDepartment(null);
				}
				departmentDao.delete(d);
				return true;
			} else {
				return false;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除部门出现失败，请重试");
		}
	}

	/***
	 * 根据type获取Department
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	public List<Department> findDepartmentByType(int type, Integer id)
			throws PaperException {
		try {
			Department d = departmentDao.get(Department.class, id);
			List<Department> dl = new ArrayList<Department>();;
			
			
			if (type == 3) {
				if (d != null && (d.getType() == type || d.getType() == 2)) {
					dl.add(d);
				}
				List<Department> dl1 = departmentDao.getDepartmentByType(type);
				List<Department> dl2 = departmentDao.getDepartmentByType(2);
				for (int i = 0; i < dl1.size(); i++) {
					Department de = dl1.get(i);
					if (id != de.getId()) {
						dl.add(de);
					}
				}
				for (int i = 0; i < dl2.size(); i++) {
					Department de = dl2.get(i);
					if (id != de.getId()) {
						dl.add(de);
					}
				}
			} else {
				if (d != null && d.getType() == type) {
					dl.add(d);
				}
				List<Department> dl1 = departmentDao.getDepartmentByType(type);
				for (int i = 0; i < dl1.size(); i++) {
					Department de = dl1.get(i);
					if (id != de.getId()) {
						dl.add(de);
					}
				}
			}
			return dl;
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据type获取部门出现失败，请重试");
		}
	}

	/***
	 * 获取所有department
	 * @return 所有department
	 * @throws PaperException
	 */
	public List<Department> findAllDepartment() throws PaperException {
		try {
			return departmentDao.findAll(Department.class);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有部门出现失败，请重试");
		}
	}

	/***
	 * 根据type获取Department
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	public List<Department> findDepartmentByType(int type)
			throws PaperException {
		try {
			
			List<Department> dl = departmentDao.getDepartmentByType(type);
			
			return dl;
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据type获取部门出现失败，请重试");
		}
	}

	

	
}
