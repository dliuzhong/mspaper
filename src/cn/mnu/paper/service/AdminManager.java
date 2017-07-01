package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.exception.PaperException;

/***
 * Adminҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface AdminManager {
	
	/***
	 * ��������Ա�û�
	 * @param admin �����û���Adminʵ��
	 * @return �¾��û�������
	 */
	int addAdmin(Admin admin) throws PaperException;
	
	/***
	 * ��֤����Ա�Ƿ���ã����ϵͳ�����иù���Ա���򲻿���
	 * @param name ��Ҫ��֤�Ĺ���Ա
	 * @return ����Ա�Ƿ����
	 */
	boolean validateByName(Integer id, String name) throws PaperException;

	
	/***
	 * ɾ������Ա�û�
	 * @param admin ɾ���û���Adminʵ��
	 * @return ɾ���û�������
	 */

	int deleteAdmin(Admin admin) throws PaperException;
	
	/***
	 * ��֤����Ա�û���¼
	 * @param admin ��Ҫ��֤��Adminʵ�� 
	 * @return ��֤�ɹ����Amdinʵ��������ΪNull
	 */
	Admin loginValid(Admin admin) throws PaperException;
	
	/***
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin Ҫ�ĳɵ�Adminʵ�� 
	 * @return �޸ĳɹ����Amdinʵ��������ΪNull
	 */
	int updateAdmin(Admin admin) throws PaperException;
	/***
	 * ��ȡ���й���Ա������
	 * @return int ����Ա������
	 * @throws PaperException
	 */
	int findAllAdminSum() throws PaperException;
	/***
	 * ��ȡ���й���Ա
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return ���й���Ա
	 *
	 */
	List<Admin> findAllAdmin(int start, int limit, String sort, String dir) 
			throws PaperException;
	/***
	 * ����ID��֤����Ա����
	 * @param id ID
	 * @param password Ҫ��֤������
	 * @return boolean
	 * @throws PaperException
	 */
	boolean passwordValid(Integer id, String password) throws PaperException;
	/***
	 * ����ID��ȡAdmin
	 * @param id
	 * @return Admin
	 * @throws PaperException
	 */
	Admin findAdminById(Integer id) throws PaperException;
	/***
	 * ���ݵȼ���ȡ��Ӧ����Ա������
	 * @param grade
	 * @return ��Ӧ����Ա������
	 * @throws PaperException
	 */
	int findAdminSumByGrade(int grade) throws PaperException;
}
