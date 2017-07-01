package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.*;
/***
 * ��ֽ����DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public interface NewsDao extends BaseDao<News> {
	/***
	 * �����ںźͰ���Ų�ѯ����News
	 * @param paperid �ں�
	 * @param layoutid �����
	 * @return ����News
	 */
	List<News> findNewsByPaperAndLayout(Integer paperid, Integer layoutid);
	
	/***
	 * ��ȡ����news��¼������
	 * @return int
	 */
	int getAllRows();
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼������
	 * @param work �����ֶ�
	 * @param query �ؼ���
	 * @return int
	 */
	int getAllRowsByWorkAndQuery(String work, String query);
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼
	 * @param work
	 * @param query
	 * @return
	 */
	List<News> getNewsByWorkQuery(String work, String query);
	/***
	 * ���ݱ�ֽID��ר��ID����ݡ������������
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return List<News>
	 */
	List<News> getNewsByFind(Integer paperid, Integer subjectid, String year, String author);
	/***
	 * ���ݱ�ֽID��ר��ID����ݡ����������������
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return int
	 */
	int getNewsByFindSum(Integer paperid, Integer subjectid, String year, String author);
	/***
	 * ����subjectid��ѯ����ӦNews������;
	 * @param subjectid
	 * @return int
	 */
	int getNewsSumBySubject(Integer subjectid);
	
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼
	 * @param start
	 * @param limit
	 * @param select
	 * @param words
	 * @return List<News>
	 */
	List<News> getNewsBySelectWords(int start, int limit, String select, String words);
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼����
	 * @param select
	 * @param words
	 * @return int
	 */
	int getNewsBySelectWordsSum(String select, String words);
}
