package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.domain.Department;

/***
 * Department业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface DepartmentManager {
	/***
	 * 获取所有部门的总数量
	 * @return int 所有部门的总数量
	 * @throws PaperException
	 */
	int findAllDepartmentSum() throws PaperException;
	/***
	 * 获取所有部门
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有部门
	 * @throws PaperException
	 */
	List<Department> findAllDepartment(int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * 添加部门
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	int addDepartment(Department department) throws PaperException;
	/***
	 * 修改部门
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	int changeDepartment(Department department) throws PaperException; 
	/***
	 * 删除部门
	 * @param department
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeDepartment(Integer id) throws PaperException;
	/***
	 * 根据type获取Department
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	List<Department> findDepartmentByType(int type) throws PaperException;
	/***
	 * 根据type获取Department
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	List<Department> findDepartmentByType(int type, Integer id) throws PaperException;
	/***
	 * 获取所有department
	 * @return 所有department
	 * @throws PaperException
	 */
	List<Department> findAllDepartment() throws PaperException;

}
