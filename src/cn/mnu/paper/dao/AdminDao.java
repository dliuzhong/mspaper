package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Admin;

/***
 * ����ԱDAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface AdminDao extends BaseDao<Admin> {
	/***
	 * �����û�����������ҹ���Ա�û�
	 * @param name �û���
	 * @param pass ����
	 * @return ��Ӧ���û�
	 */
	Admin findAdminByNameAndPass(String name, String pass);
	
	/***
	 * ���� �û��������û�
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
	Admin findAdminByName(String name);
	
	/***
	 * ���� �û����
	 * @param name �û���
	 * @return ��Ӧ���û�
	 */
	Admin findAdminById(Integer id);
	/***
	 * ��ѯ�����й���Ա������
	 * @return int ����Ա������
	 */
	int getAllAdminSum();
	/***
	 * ��ѯ���й���Ա
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return List<Admin> ����Ա
	 */
	List<Admin> getAllAdmin(int start, int limit, String sort, String dir);
	/***
	 * ���ݵȼ�grade��ѯ������Ա������
	 * @param grade
	 * @return ����Ա������
	 */
	int getAdminSumByGrade(int grade);
	
}

