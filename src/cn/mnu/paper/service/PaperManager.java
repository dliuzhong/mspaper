package cn.mnu.paper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.exception.PaperException;

/***
 * Paperҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public interface PaperManager {
	/***
	 * ������ֽ��Ϣ
	 * @param paper ������ֽ��Paperʵ��
	 * @return ������ֽ
	 */
	Paper addPaper(Paper paper) throws PaperException;
	/***
	 * ��֤ϵͳ���Ƿ����иñ�ֽ
	 * @param paper ��Ҫ��֤���ں�
	 * @return ��ֽ�Ƿ����
	 */
	boolean validatePaperByName(String paper) throws PaperException;
	/***
	 * ���ݱ�ֽ�ں��Ҳ�
	 * @param paper ��Ҫ�Ҳ���ں�
	 * @return ��ֽ
	 * @throws PaperException 
	 */
	Paper findPaperByName(String paper) throws PaperException;
	/***
	 * �����ںź�����ұ�ֽ
	 * @param paper
	 * @param year
	 * @return
	 * @throws PaperException
	 */
	List<Paper> getPaperByPaperAndYear(String paper, String year) throws PaperException;

	/***
	 * ���� ID���ұ�ֽ������Ϣ
	 * @param id ID
	 * @return Paper
	 * @throws PaperException
	 */
	Paper getPaperById(Integer id) throws PaperException;
	
	/***
	 * ����paperID���İ����� + 1
	 * @param paperid
	 * @return int
	 * @throws PaperException
	 */
	int changePaperLay_num(Integer paperid) throws PaperException;
	/***
	 * ����IDɾ����ֽ
	 * @param id ID
	 * @param request �û�ɾ���ļ�
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removePaperById(Integer id, HttpServletRequest request) throws PaperException;
	/***
	 * �޸�Paper
	 * @param paper �޸ĵ�����
	 * @param request �����޸�index.jsp
	 * @return paper-id
	 * @throws PaperException
	 */
	int changePaper(Paper paper, HttpServletRequest request) throws PaperException;
	/***
	 * ���� query�ؼ��ֻ�ȡ��ص�����
	 * @param query �ؼ���
	 * @return int
	 * @throws PaperException
	 */
	int findQueryPaperSum(String query) throws PaperException;
	/***
	 * ��paper����queryƥ����һ�ȡ����papers
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 * @throws PaperException
	 */
	List<Paper> findPaperByPaperQuery(String query, int start, int limit) throws PaperException;
	/***
	 * ���ݳ���ʱ��cbtime��ȡ����Paper
	 * @return List<Paper>
	 * @throws PapaerException
	 */
	List<Paper> findAllPaperOrderByCbtime() throws PaperException;
	/***
	 * ��ȡ����һ��Paper
	 * @return Paper
	 * @throws PaperException
	 */
	Paper findLastPaper() throws PaperException;
	
	/***
	 * ��ȡID���ڵ�ǰһ��
	 * @param id
	 * @return �����ǰΪ��ǰһ�ڣ�����0�����򷵻�ǰһ�ڵ��ں�
	 * @throws PaperException
	 */
	String findPrevPaper(Integer id) throws PaperException;  
	/***
	 * ��ȡID���ڵĺ�һ��
	 * @param id
	 * @return �����ǰΪ���һ�ڣ�����0�����򷵻غ�һ�ڵ��ں�
	 * @throws PaperException
	 */
	String findNextPaper(Integer id) throws PaperException;
}
