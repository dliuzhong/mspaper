package cn.mnu.paper.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import cn.mnu.paper.domain.News;
import cn.mnu.paper.exception.PaperException;

/***
 * Newsҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public interface NewsManager {
	/***
	 * ��ӱ�ֽ��������
	 * @param news
	 * @return
	 * @throws Exception
	 */
	Integer addNewsRefresh(News news, Integer paperid, Integer layoutid, Integer subjectid, HttpServletRequest request) throws PaperException;
	/***
	 * ��ӱ�ֽ��������,����Page.html��Main.html
	 * @param news
	 * @return
	 * @throws Exception
	 */
	Integer addNews(News news, Integer paperid, Integer layoutid, Integer subjectid, HttpServletRequest request) throws PaperException;
	/***
	 * �����ںźͰ���Ż�ȡ������Ӧ��News
	 * @param paperid �ں�id
	 * @param layoutid �����id
	 * @return ��Ӧ��News
	 */
	List<News> getNewsByPaperAndLayout(Integer paperid, Integer layoutid) throws PaperException;
	/***
	 * ���� ID��ȡNews
	 * @param id
	 * @return News
	 */
	News findNewsById(Integer id) throws PaperException;
	/***
	 * ���ݸ��ĺ��News�޸�News
	 * @param news Ҫ�޸ĵ�News
	 * @param subjectid �޸ĵ�ר��id
	 * @param request ���ڸ���Page.html��Main.html
	 * @return News.id
	 * @throws PaperException
	 */
	Integer changeNews(News news, Integer subjectid, HttpServletRequest request) throws PaperException;
	
	/***
	 * Ҫ��IDɾ����Ӧ����������
	 * @param id[] ID����
	 * @param request ���ڸ���Page.html��Main.html
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeNewss(Integer[] id, HttpServletRequest request) throws PaperException;
	/***
	 * ��ȡ����News������
	 * @return
	 * @throws PaperException
	 */
	int findAllNewsSum() throws PaperException;
	/***
	 * ����������ʽ�͹ؼ��ֻ�ȡNewsr������
	 * @param work ������ʽ
	 * @param query �ؼ���
	 * @return int
	 * @throws PaperException
	 */
	int findNewsSumByWorkAndQuerySum(String work, String query) throws PaperException;
	/***
	 * ����������ʽ�͹ؼ��ֻ�ȡNews
	 * @param work ������ʽ
	 * @param query �ؼ���
	 * @param start
	 * @param limit
	 * @return List<News>
	 * @throws PaperException
	 */
	List<News> findNewsByWorkAndQurey(String work, String query, int start, int limit) throws PaperException;
	/***
	 * ���ݱ�ֽID��ר��ID����ݡ����������������
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	int findNewsSumFind(Integer paperid, Integer subjectid, String year, String author) throws PaperException;
	/***
	 * ���ݱ�ֽID��ר��ID����ݡ������������
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @param start
	 * @param limit
	 * @return
	 * @throws PaperException
	 */
	List<News> findNewsFind(Integer paperid, Integer subjectid, String year, String author, int start, int limit) throws PaperException;
	/***
	 * ����������ʽ�͹ؼ��ֻ�ȡNews
	 * @param work
	 * @param query
	 * @return List<News>
	 * @throws PaperException
	 */
	List<News> findNewsByWorkAndQurey(String work, String query) throws PaperException;
	
	/***
	 * ���ݱ�ֽID��ר��ID����ݡ������������
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	List<News> findNewsFind(Integer paperid, Integer subjectid, String year, String author) throws PaperException;
	/***
	 * POI����InputStream->EXCEL
	 * @param newss
	 * @return
	 * @throws PaperException
	 */
	InputStream findInputStreamNews(List<News> newss) throws PaperException;
	/***
	 * ����subjectid��ȡ��Ӧ���ŵ�����
	 * @param subjectid
	 * @return int
	 * @throws PaperException
	 */
	int findNewsSumBySubject(Integer subjectid) throws PaperException;
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼����
	 * @param select
	 * @param words
	 * @return int
	 * @throws PaperException
	 */
	int findNewsBySelectWordsSum(String select, String words) throws PaperException;
	/***
	 * ���������ֶκ͹ؼ��ֻ�ȡ����news��¼
	 * @param start
	 * @param limit
	 * @param select
	 * @param words
	 * @return List<News>
	 * @throws PaperException
	 */
	List<News> findNewsBySelectWords(int start, int limit, String select,
			String words) throws PaperException;
}

