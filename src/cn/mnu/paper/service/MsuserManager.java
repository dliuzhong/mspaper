package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;

/***
 * MsUserҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-12
 */
public interface MsuserManager {
	/***
	 * ��ȡ�����û���������
	 * @return int �����û���������
	 * @throws PaperException
	 */
	int findAllMsuserSum() throws PaperException;
	/***
	 * ��ȡ�����û�
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ��ȡ�����û�
	 * @throws PaperException
	 */
	List<Msuser> findAllMsuser(int start, int limit, String sort, String dir)
		throws PaperException;
	/***
	 * ����Ͷ���û�
	 * @param msuer
	 * @return Integer
	 * @throws PaperException
	 */
	int addMsuser(Msuser msuser) throws PaperException;
	/***
	 * �޸�Ͷ���û�
	 * @param msuser
	 * @return
	 * @throws PaperException
	 */
	int changeUNPWMsuser(Msuser msuser) throws PaperException;
	/***
	 * ����IDɾ��Ͷ���û�
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean deleteMsuser(Integer id) throws PaperException;
	/***
	 * ����departmentid��ȡ��Ӧ�û�������
	 * @param departmentid
	 * @return ��Ӧ�û�������
	 * @throws PaperException
	 */
	int findMsuserSumByDepartment(Integer departmentid) throws PaperException;
	/***
	 * ����ID��ȡmsuser
	 * @param id
	 * @return msuser
	 * @throws PaperException
	 */
	Msuser findMsuserById(Integer id) throws PaperException;
	/***
	 * �޸�Msuser
	 * @param msuser
	 * @param departmentid
	 * @return msuser id
	 * @throws PaperException
	 */
	int changeMsuser(Msuser msuser, Integer departmentid) throws PaperException;
	/***
	 * �޸�Msuser������
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	int changeMsuserType(Msuser msuser) throws PaperException;
	/***
	 * ��֤email�Ƿ������û�
	 * @param email
	 * @return boolean
	 * @throws PaperException
	 */
	boolean validateMsuserByEmail(Integer id, String email) throws PaperException;
	
	/***
	 * ��֤Ͷ���û���¼
	 * @param admin ��Ҫ��֤��Msuserʵ�� 
	 * @return ��֤�ɹ����Msuserʵ��������ΪNull
	 */
	Msuser loginValid(Msuser msuser) throws PaperException;
	/***
	 * ��ȡ����Msuser
	 * @return ����Msuser
	 * @throws PaperException
	 */
	List<Msuser> findAllMsuser() throws PaperException;
}
