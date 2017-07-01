package cn.mnu.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.AdminDao;
import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.AdminManager;
import cn.mnu.paper.tools.Md5;

/***
 * Loginҵ���߼����ʵ����
 * @author com.pie
 * @version 1.0
 * @date 2014-06-10
 */
public class AdminManagerImpl implements AdminManager {
	static Logger log = Logger.getLogger(
			AdminManagerImpl.class.getName());
	private AdminDao adminDao;
	
	/***
	 * ��������Ա�û�
	 * @param admin �����û���Adminʵ��
	 * @return �¾��û�������
	 */
	public int addAdmin(Admin admin) throws PaperException {
		try {
			if (adminDao.findAdminByName(admin.getUsername()) != null) {
				return -1;
			}
			if ((admin.getGrade() == 2 || admin.getGrade() == 3)
					&& (adminDao.getAdminSumByGrade(admin.getGrade()) >= 4)) {
				return -2;
			}
			admin.setOther("0");
			admin.setDatetime(new Date());
			adminDao.save(admin);
			return admin.getId();
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ӹ���Ա����ʧ�ܣ�������");
			
		}
		
	}

	/***
	 * ��֤����Ա�Ƿ���ã����ϵͳ�����иù���Ա���򲻿���
	 * @param name ��Ҫ��֤�Ĺ���Ա
	 * @return ����Ա�Ƿ����
	 */
	public boolean validateByName(Integer id, String name) throws PaperException {
		try {
			Admin a = adminDao.findAdminByName(name);
			if (a == null) {
				return true;
			} else if (id != 0 && a.getId() == id) {
				return true;
			} else {
				return false;
			}
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���ҹ���Ա�û���ʧ�ܣ�������");
			
		}
	}

	/***
	 * ɾ������Ա�û�
	 * @param admin ɾ���û���Adminʵ��
	 * @return ɾ���û�������
	 */
	public int deleteAdmin(Admin admin) throws PaperException {
		try {
			// ������Ա�˻��Ƿ����
			if (adminDao.findAdminByName(admin.getName()) != null) {
				return -1;		
			}
			adminDao.delete(admin);
			return admin.getId();
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("ɾ������Ա����ʧ�ܣ�������");
			
		}
	}

	/***
	 * ��֤����Ա�û���¼
	 * @param user ��Ҫ��֤��Adminʵ�� 
	 * @return �Ƿ��½�ɹ�
	 */
	public Admin loginValid(Admin admin) throws PaperException {
		try {
			Admin ad = null;
			ad = adminDao.findAdminByNameAndPass(admin.getUsername(), admin.getPassword());
			if (ad != null) {
				return ad;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����Ա�û�����������֤ʧ�ܣ�������");
		}
	}

	/***
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin Ҫ�ĳɵ�Adminʵ�� 
	 * @return �޸ĳɹ����Amdinʵ��ID
	 */
	public int updateAdmin(Admin admin) throws PaperException {
		try {
			if ((admin.getGrade() == 2 || admin.getGrade() == 3)
					&& (adminDao.getAdminSumByGrade(admin.getGrade()) >= 4)) {
				return -2;
			}
			Admin ad = null;
			ad = adminDao.findAdminById(admin.getId());
			if (ad != null) {
				ad.setUsername(admin.getUsername());
				ad.setName(admin.getName());
				ad.setGrade(admin.getGrade());
				ad.setOther(ad.getOther());
				if (admin.getPassword() != null && !admin.getPassword().trim().equals("")) {
					ad.setPasswordNoMD5(admin.getPassword());
				}
				adminDao.update(ad);
				return ad.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����Ա��Ϣ�޸�ʧ�ܣ�������");
		}
	}
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/***
	 * ��ȡ���й���Ա������
	 * @return int ����Ա������
	 * @throws PaperException
	 */
	public int findAllAdminSum() throws PaperException {
		try {
			return adminDao.getAllAdminSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�������й���Ա����������ʧ�ܣ�������");
		}
	}

	/***
	 * ��ȡ���й���Ա
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return ���й���Ա
	 * @throws PaperException
	 */
	public List<Admin> findAllAdmin(int start, int limit, String sort,
			String dir) throws PaperException {
		try {
			return adminDao.getAllAdmin(start, limit, sort, dir);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�������й���Ա����ʧ�ܣ�������");
		}
	}

	/***
	 * ����ID��֤����Ա����
	 * @param id ID
	 * @param password Ҫ��֤������
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean passwordValid(Integer id, String password)
			throws PaperException {
		try {
			Admin a = adminDao.get(Admin.class, id);
			if (a != null) {
				return Md5.validatePassword(a.getPassword(), password);
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��֤����Ա�������ʧ�ܣ�������");
		}
	}

	/***
	 * ����ID��ȡAdmin
	 * @param id
	 * @return Admin
	 * @throws PaperException
	 */
	public Admin findAdminById(Integer id) throws PaperException {
		try {
			Admin a = adminDao.get(Admin.class, id);
			if (a != null) {
				return a;
			} else {
				return null;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����ID��ȡ����Ա����ʧ�ܣ�������");
		}
	}

	/***
	 * ���ݵȼ���ȡ��Ӧ����Ա������
	 * @param grade
	 * @return ��Ӧ����Ա������
	 * @throws PaperException
	 */
	public int findAdminSumByGrade(int grade) throws PaperException {
		try {
			System.out.println("grade error");
			return adminDao.getAdminSumByGrade(grade);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���ݵȼ���ȡ��Ӧ����Ա����������ʧ�ܣ�������");
		}
	}

	

}
