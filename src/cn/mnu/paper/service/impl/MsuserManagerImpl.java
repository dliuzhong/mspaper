package cn.mnu.paper.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.DepartmentDao;
import cn.mnu.paper.dao.MsuserDao;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.MsuserManager;

/***
 * Loginҵ���߼����ʵ����
 * @author com.pie
 * @version 1.0
 * @date 2014-06-10
 */
public class MsuserManagerImpl implements MsuserManager {
	static Logger log = Logger.getLogger(
			MsuserManagerImpl.class.getName());
	private MsuserDao msuserDao;
	private DepartmentDao departmentDao;
	
	
	public MsuserDao getMsuserDao() {
		return msuserDao;
	}
	public void setMsuserDao(MsuserDao msuserDao) {
		this.msuserDao = msuserDao;
	}
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	/***
	 * ��ȡ�����û���������
	 * @return int �����û���������
	 * @throws PaperException
	 */
	public int findAllMsuserSum() throws PaperException {
		try {
			return msuserDao.getAllMsuserSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���������û�����������ʧ�ܣ�������" + e.getMessage());
		}
	}
	/***
	 * ��ȡ���й���Ա
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ 
	 * @return ���й���Ա
	 *
	 */
	public List<Msuser> findAllMsuser(int start, int limit, String sort, String dir)
			throws PaperException {
		try {
			return msuserDao.getAllMsuser(start, limit, sort, dir);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���������û�����ʧ�ܣ�������");
		}
	}
	
	/***
	 * ����Ͷ���û�
	 * @param msuer
	 * @return Integer
	 * @throws PaperException
	 */
	public int addMsuser(Msuser msuser) throws PaperException {
		try {
			msuser.setStatus(0);
			msuser.setDatetime(new Date());
			msuser.setDepartment(null);
			msuser.setUsername(msuser.getEmail());
			msuserDao.save(msuser);
			return msuser.getId();
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����û�����ʧ�ܣ�������");
		}
	}
	/***
	 * �޸�Ͷ���û����û���������
	 * @param msuser
	 * @return
	 * @throws PaperException
	 */
	public int changeUNPWMsuser(Msuser msuser) throws PaperException {
		try {
			Msuser u = msuserDao.get(Msuser.class, msuser.getId());
			if (u != null) {
				u.setUsername(msuser.getEmail());
				u.setEmail(msuser.getEmail());
				if (msuser.getPassword() != null && !msuser.getPassword().trim().equals("")) {
					u.setPasswordNoMD5(msuser.getPassword());
				}
				msuserDao.update(u);
			}
			return u.getId();
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�޸��û�����ʧ�ܣ�������");
		}
	}
	/***
	 * ����IDɾ��Ͷ���û�
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean deleteMsuser(Integer id) throws PaperException {
		try {
			Msuser u = msuserDao.get(Msuser.class, id);
			if (u != null) {
				msuserDao.delete(u);
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("ɾ���û�����ʧ�ܣ�������");
		}
	}
	/***
	 * ����departmentid��ȡ��Ӧ�û�������
	 * @param departmentid
	 * @return ��Ӧ�û�������
	 * @throws PaperException
	 */
	public int findMsuserSumByDepartment(Integer departmentid)
			throws PaperException {
		try {
			return msuserDao.getMsuserSumByDepartment(departmentid);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����departmentid���������û�����ʧ�ܣ�������");
		}
	}
	/***
	 * ����ID��ȡmsuser
	 * @param id
	 * @return msuser
	 * @throws PaperException
	 */
	public Msuser findMsuserById(Integer id) throws PaperException {
		try {
			return msuserDao.get(Msuser.class, id);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����ID���������û�����ʧ�ܣ�������");
		}
	}
	/***
	 * �޸�Msuser
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	public int changeMsuser(Msuser msuser, Integer departmentid) throws PaperException {
		try {
			Msuser m = msuserDao.get(Msuser.class, msuser.getId());
			if (m != null) {
				m.setUsername(msuser.getEmail());
				m.setName(msuser.getName());
				m.setEmail(msuser.getEmail());
				m.setTelephone(msuser.getTelephone());
				m.setQq(msuser.getQq());
				Department d = null;
				if (departmentid != 0) {
					d = departmentDao.get(Department.class, departmentid);
				}
				
				m.setDepartment(d);
				if (m.getStatus() == 0) {
					m.setStatus(1);
				}
				msuserDao.update(m);
				return m.getId();
			} else {
				return 0;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�޸��û�����ʧ�ܣ�������");
		}
	}
	/***
	 * �޸�Msuser������
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	public int changeMsuserType(Msuser msuser) throws PaperException {
		try {
			Msuser m = msuserDao.get(Msuser.class, msuser.getId());
			if (m != null) {
				
				m.setType(msuser.getType());
				
				msuserDao.update(m);
				return m.getId();
			} else {
				return 0;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�޸��û����ͳ���ʧ�ܣ�������");
		}
	}
	/***
	 * ��֤email�Ƿ������û�
	 * @param email
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean validateMsuserByEmail(Integer id, String email) throws PaperException {
		try {
			Msuser m = msuserDao.getMsuserByEmail(email);
			if (m == null) {
				return true;
			} else if (id != 0 && m.getId() == id) {
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��֤email����ʧ�ܣ�������");
		}
	}
	/***
	 * ��֤Ͷ���û���¼
	 * @param admin ��Ҫ��֤��Msuserʵ�� 
	 * @return ��֤�ɹ����Msuserʵ��������ΪNull
	 */
	public Msuser loginValid(Msuser msuser) throws PaperException {
		try {
			Msuser mu = null;
			mu = msuserDao.getMsuserByUsernameAndPassword(msuser.getUsername(), 
					msuser.getPassword());
			if (mu != null) {
				return mu;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("Ͷ���û�����������֤ʧ�ܣ�������");
		}
	}
	/***
	 * ��ȡ����Msuser
	 * @return ����Msuser
	 * @throws PaperException
	 */
	public List<Msuser> findAllMsuser() throws PaperException {
		try {
			return msuserDao.findAll(Msuser.class);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ȡ����Ͷ���û�����ʧ�ܣ�������");
		}
	}
	
	
}
