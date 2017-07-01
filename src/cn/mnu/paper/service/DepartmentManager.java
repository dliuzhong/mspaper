package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.domain.Department;

/***
 * Departmentҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface DepartmentManager {
	/***
	 * ��ȡ���в��ŵ�������
	 * @return int ���в��ŵ�������
	 * @throws PaperException
	 */
	int findAllDepartmentSum() throws PaperException;
	/***
	 * ��ȡ���в���
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���в���
	 * @throws PaperException
	 */
	List<Department> findAllDepartment(int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * ��Ӳ���
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	int addDepartment(Department department) throws PaperException;
	/***
	 * �޸Ĳ���
	 * @param department
	 * @return int id
	 * @throws PaperException
	 */
	int changeDepartment(Department department) throws PaperException; 
	/***
	 * ɾ������
	 * @param department
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeDepartment(Integer id) throws PaperException;
	/***
	 * ����type��ȡDepartment
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	List<Department> findDepartmentByType(int type) throws PaperException;
	/***
	 * ����type��ȡDepartment
	 * @param type
	 * @return List<Department>
	 * @throws PaperException
	 */
	List<Department> findDepartmentByType(int type, Integer id) throws PaperException;
	/***
	 * ��ȡ����department
	 * @return ����department
	 * @throws PaperException
	 */
	List<Department> findAllDepartment() throws PaperException;

}
