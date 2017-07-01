package cn.mnu.paper.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;

import cn.mnu.paper.dao.PaperDao;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.PaperManager;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.NewFiles;

/***
 * Paperҵ���߼����ʵ����
 * @author com
 * @version 1.0
 * @date 2014-07-14
 */
public class PaperManagerImpl implements PaperManager {
	static Logger log = Logger.getLogger(
			PaperManagerImpl.class.getName());
	private PaperDao paperDao;
	/***
	 * ������ֽ��Ϣ
	 * @param paper ������ֽ��Paperʵ��
	 * @return ������ֽ
	 */
	public Paper addPaper(Paper paper) throws PaperException {
		try {
			Paper p = findPaperByName(paper.getPaper());
			if (p != null) {
				p.setZb(paper.getZb());
				p.setCb(paper.getCb());
				p.setZongb(paper.getZongb());
				p.setCbtime(paper.getCbtime());
				p.setEmail(paper.getEmail());
				p.setLay_sum(paper.getLay_sum());
				paperDao.update(p);
				return p;
			} else {
				paperDao.save(paper);
				return paper;
			}



		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����±�����ʧ�ܣ�������");
		}
	}

	/***
	 * ��֤ϵͳ���Ƿ����иñ�ֽ
	 * @param paper ��Ҫ��֤���ں�
	 * @return ��ֽ�Ƿ����
	 * @throws PaperException
	 */
	public boolean validatePaperByName(String paper) throws PaperException {
		try {
			if (paperDao.findPaperByPaper(paper) != null) {
				return true;
			} else {
				return false;
			}


		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���ں��Ҳ鱨ֽ����ʧ�ܣ�������");

		}
	}
	/***
	 * ���ݱ�ֽ�ں��Ҳ�
	 * @param paper ��Ҫ�Ҳ���ں�
	 * @return ��ֽ
	 * @throws PaperException
	 */
	public Paper findPaperByName(String paper) throws PaperException {
		try {
			Paper p = paperDao.findPaperByPaper(paper);
			if (p != null) {
				return p;
			} else {
				return null;
			}


		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���ں��Ҳ鱨ֽ����ʧ�ܣ�������");

		}
	}

	public PaperDao getPaperDao() {
		return paperDao;
	}
	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	/***
	 * �����ںź�����ұ�ֽ
	 * @param paper
	 * @param year
	 * @return
	 * @throws PaperException
	 */
	public List<Paper> getPaperByPaperAndYear(String paper, String year)
			throws PaperException {
		try {
			List<Paper> ps = paperDao.findPaperByPaperAndYear(paper, year);
			return ps;

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�������б�ֽ������Ϣ����ʧ�ܣ�������");
		}
	}

	/***
	 * ���� ID���ұ�ֽ������Ϣ
	 * @param id ID
	 * @return Paper
	 * @throws PaperException
	 */
	public Paper getPaperById(Integer id) throws PaperException {
		try {
			return paperDao.get(Paper.class, id);
		}  catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����ID���ұ�ֽ������Ϣ����ʧ�ܣ�������");
		}
	}

	/***
	 * ����paperID���İ����� + 1
	 * @param paperid
	 * @return int
	 * @throws PaperException
	 */
	public int changePaperLay_num(Integer paperid) throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, paperid);
			if (p != null) {
				p.setLay_sum(p.getLay_sum() + 1);
				paperDao.update(p);
				return p.getLay_sum();
			}
			return 0;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���İ���������ʧ�ܣ�������");
		}
	}

	/***
	 * ����IDɾ����ֽ
	 * @param id ID
	 * @param request �û�ɾ���ļ�
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removePaperById(Integer id, HttpServletRequest request) throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, id);
			paperDao.delete(p);
			FileDo fd = new FileDo(request);
			String filepath = fd.getDir() + "paper\\" + p.getPaper();
			//System.out.println(filepath);
			if (fd.isFileExist(filepath)) {
				if (fd.deleteFolder("paper\\" + p.getPaper())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����IDɾ����ֽ����ʧ�ܣ�������");
		}

	}
	/***
	 * �޸�Paper
	 * @param paper �޸ĵ�����
	 * @param request �����޸�index.jsp
	 * @return paper-id
	 * @throws PaperException
	 */
	public int changePaper(Paper paper, HttpServletRequest request)
			throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, paper.getId());
			if (p != null) {
				p.setZb(paper.getZb());
				p.setCb(paper.getCb());
				p.setZongb(paper.getZongb());
				p.setCbtime(paper.getCbtime());
				p.setEmail(paper.getEmail());
				if (paper.getPaper_url() != null) {
					p.setPaper_url(paper.getPaper_url());
				}
				paperDao.update(p);
				NewFiles nf = new NewFiles();
				if (nf.newIndex(p, request)) {
					return p.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ֽ�޸ĳ���ʧ�ܣ�������");
		}
	}

	/***
	 * ���� query�ؼ��ֻ�ȡ��ص�����
	 * @param query �ؼ���
	 * @return int
	 * @throws PaperException
	 */
	public int findQueryPaperSum(String query) throws PaperException {
		try {
			int sum = paperDao.getAllRows(query);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�������еı�ֽ����������ʧ�ܣ�������");
		}
	}

	/***
	 * ��paper����queryƥ����һ�ȡ����papers
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 * @throws PaperException
	 */
	public List<Paper> findPaperByPaperQuery(String query, int start, int limit)
			throws PaperException {
		try {
			List<Paper> pl = paperDao.getPaperByPaperQuery(query);
			List<Paper> p = new ArrayList<Paper>();
			Paper pa;
			for (int i = 0;i < pl.size(); i++) {
				pa = pl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {

					p.add(pa);
				}
			}
			return p;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("�������б�ֽ����ʧ�ܣ�������");
		}
	}

	/***
	 * ���ݳ���ʱ��cbtime��ȡ����Paper
	 * @return List<Paper>
	 * @throws PapaerException
	 */
	public List<Paper> findAllPaperOrderByCbtime() throws PaperException {
		try {
			return paperDao.getAllPaperOrderByCbtime();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("����cbtime�������еı�ֽ����ʧ�ܣ�������");
		}
	}

	/***
	 * ��ȡ����һ��Paper
	 * @return Paper
	 * @throws PaperException
	 */
	public Paper findLastPaper() throws PaperException {
		try {
			return paperDao.getLastPaper();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ȡ����һ�ڱ�ֽ����ʧ�ܣ�������");
		}
	}

	/***
	 * ��ȡID���ڵ�ǰһ��
	 * @param id
	 * @return �����ǰΪ��ǰһ�ڣ�����0�����򷵻�ǰһ�ڵ��ں�
	 * @throws PaperException
	 */
	public String findPrevPaper(Integer id) throws PaperException {
		try {
			// ȡ�ð�����ʱ�併�����еı�ֽ��ϢList
			List<Paper> paperList = paperDao.getAllPaperOrderByCbtime();

			int no;
			// ��ÿһ����ʼ������0��ʼ��
			for (no = 0;no < paperList.size(); no++) {
				// �����ǰID��id��ȣ���break
				if (Integer.parseInt(paperList.get(no).getId().toString()) == id ||
						paperList.get(no).getId().toString().equals(id.toString()))
					break;

			}
			// ���IDΪid����һ��Ϊ���ݵ����һ������û��ǰһ�ڣ�����0
			if (no == (paperList.size() - 1) || no == paperList.size()) {
				return "0";
			} else {
				// ����++no;
				++no;
			}
//			����ǰһ�ڵ�URL
			return paperList.get(no).getPaper_url();

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ȡ��ǰһ�ڱ�ֽ����ʧ�ܣ�������");
		}
	}

	/***
	 * ��ȡID���ڵĺ�һ��
	 * @param id
	 * @return �����ǰΪ���һ�ڣ�����0�����򷵻غ�һ�ڵ��ں�
	 * @throws PaperException
	 */
	public String findNextPaper(Integer id) throws PaperException {
		try {
			// ȡ�ð�����ʱ�併�����еı�ֽ��ϢList
			List<Paper> paperList = paperDao.getAllPaperOrderByCbtime();

			int no;
			// �ӵ�һ����ʼ������0��ʼ��
			for (no = 0;no < paperList.size(); no++) {
				// �����ǰID��id��ȣ���break
				if (Integer.parseInt(paperList.get(no).getId().toString()) == id ||
						paperList.get(no).getId().toString().equals(id.toString()))
					break;

			}
			// ���IDΪid����һ��Ϊ���ݵĵ�һ������û�к�һ�ڣ�����0
			if (no == 0 || no == paperList.size()) {
				return "0";
			} else {
//				����--no
				--no;
			}
//			���غ�һ�ڵ�URL
			return paperList.get(no).getPaper_url();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("��ȡ���һ�ڱ�ֽ����ʧ�ܣ�������");
		}
	}


}
