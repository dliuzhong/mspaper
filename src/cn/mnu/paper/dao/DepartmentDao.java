package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Department;
/***
 * 部门DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface DepartmentDao extends BaseDao<Department> {
	/***
	 * 查询所有部门的总数量
	 * @return int 所有部门的总数量
	 */
	int getAllDepartmentSum();
	/***
	 * 查询所有部门
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有部门
	 */
	List<Department> getAllDepartment(int start, int limit, String sort, String dir);
	/***
	 * 根据type查找Department
	 * @param type
	 * @return List<Department> 
	 */
	List<Department> getDepartmentByType(int type);
}
