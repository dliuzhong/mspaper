package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Paper;

/***
 * ��ֽ������ϢDAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public interface PaperDao extends BaseDao<Paper> {
	/***
	 * �����ںŲ��ұ�ֽ
	 * @param paper �ں�
	 * @return ��Ӧ�ı�ֽ
	 */
	Paper findPaperByPaper(String paper);
	/***
	 * �����ںź�����ұ�ֽ
	 * @param paper
	 * @param year
	 * @return List<Paper>
	 */
	List<Paper> findPaperByPaperAndYear(String paper, String year);
	/***
	 * ��ȡ����papers��¼������
	 * @return int
	 */
	int getAllRows();
	/***
	 * ��ȡ����papers��¼������
	 * @param query �ؼ���
	 * @return int
	 */
	int getAllRows(String query);
	/***
	 * ��paper����queryƥ����һ�ȡ���л��papers��¼
	 * @param query
	 * @return
	 */
	List<Paper> getPaperByPaperQuery(String query);
	/***
	 * ���� ����ʱ��cbtime��ѯ����paper
	 * @return List<Paper>
	 */
	List<Paper> getAllPaperOrderByCbtime();
	/***
	 * ��ѯ���������Paper
	 * @return Paper
	 */
	Paper getLastPaper();
	/***
	 * ��ѯ��һ�ڱ�ֽ
	 * @return Paper
	 */
	Paper getFirstPaper();
}
