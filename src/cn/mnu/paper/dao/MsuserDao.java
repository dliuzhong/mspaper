package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Msuser;

/***
 * �û�DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface MsuserDao extends BaseDao<Msuser>{
	/***
	 * �����û�������������û�
	 * @param name �û���
	 * @param pass ����
	 * @return ��Ӧ���û�
	 */
//	User findUserByNameAndPass(String name, String pass);
	/***
	 * ���� �û��������û�
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
//	User findUserByName(String name);
	/***
	 * ��֤email�Ƿ������û�
	 * @param email
	 * @return Msuser
	 */
	Msuser getMsuserByEmail(String email);
	/***
	 * ��ѯ�����û����� 
	 * @return int �û����� 
	 */
	int getAllMsuserSum();
	/***
	 * ��ѯ�����û�
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return �����û�
	 */
	List<Msuser> getAllMsuser(int start, int limit, String sort, String dir);
	/***
	 * ����departmentid��ѯ��Ӧ�û�������
	 * @param departmentid
	 * @return ��Ӧ�û�������
	 */
	int getMsuserSumByDepartment(Integer departmentid);
	/***
	 * �����û����������ѯMsuser
	 * @param username �û���
	 * @param password ����
	 * @return ��Ӧ�û�
	 */
	Msuser getMsuserByUsernameAndPassword(String username, String password);
}

