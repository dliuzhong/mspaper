package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Department;
/***
 * ����DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface DepartmentDao extends BaseDao<Department> {
	/***
	 * ��ѯ���в��ŵ�������
	 * @return int ���в��ŵ�������
	 */
	int getAllDepartmentSum();
	/***
	 * ��ѯ���в���
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���в���
	 */
	List<Department> getAllDepartment(int start, int limit, String sort, String dir);
	/***
	 * ����type����Department
	 * @param type
	 * @return List<Department> 
	 */
	List<Department> getDepartmentByType(int type);
}
