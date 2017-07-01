package cn.mnu.paper.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import cn.mnu.paper.domain.News;
import cn.mnu.paper.exception.PaperException;

/***
 * News业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public interface NewsManager {
	/***
	 * 添加报纸新闻内容
	 * @param news
	 * @return
	 * @throws Exception
	 */
	Integer addNewsRefresh(News news, Integer paperid, Integer layoutid, Integer subjectid, HttpServletRequest request) throws PaperException;
	/***
	 * 添加报纸新闻内容,更新Page.html和Main.html
	 * @param news
	 * @return
	 * @throws Exception
	 */
	Integer addNews(News news, Integer paperid, Integer layoutid, Integer subjectid, HttpServletRequest request) throws PaperException;
	/***
	 * 根据期号和版面号获取所有相应的News
	 * @param paperid 期号id
	 * @param layoutid 版面号id
	 * @return 相应的News
	 */
	List<News> getNewsByPaperAndLayout(Integer paperid, Integer layoutid) throws PaperException;
	/***
	 * 根据 ID获取News
	 * @param id
	 * @return News
	 */
	News findNewsById(Integer id) throws PaperException;
	/***
	 * 根据更改后的News修改News
	 * @param news 要修改的News
	 * @param subjectid 修改的专题id
	 * @param request 用于更新Page.html和Main.html
	 * @return News.id
	 * @throws PaperException
	 */
	Integer changeNews(News news, Integer subjectid, HttpServletRequest request) throws PaperException;
	
	/***
	 * 要所ID删除相应的新闻内容
	 * @param id[] ID数组
	 * @param request 用于更新Page.html和Main.html
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeNewss(Integer[] id, HttpServletRequest request) throws PaperException;
	/***
	 * 获取所有News的总数
	 * @return
	 * @throws PaperException
	 */
	int findAllNewsSum() throws PaperException;
	/***
	 * 根据搜索方式和关键字获取Newsr的总数
	 * @param work 搜索方式
	 * @param query 关键字
	 * @return int
	 * @throws PaperException
	 */
	int findNewsSumByWorkAndQuerySum(String work, String query) throws PaperException;
	/***
	 * 根据搜索方式和关键字获取News
	 * @param work 搜索方式
	 * @param query 关键字
	 * @param start
	 * @param limit
	 * @return List<News>
	 * @throws PaperException
	 */
	List<News> findNewsByWorkAndQurey(String work, String query, int start, int limit) throws PaperException;
	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索总数
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	int findNewsSumFind(Integer paperid, Integer subjectid, String year, String author) throws PaperException;
	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索
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
	 * 根据搜索方式和关键字获取News
	 * @param work
	 * @param query
	 * @return List<News>
	 * @throws PaperException
	 */
	List<News> findNewsByWorkAndQurey(String work, String query) throws PaperException;
	
	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	List<News> findNewsFind(Integer paperid, Integer subjectid, String year, String author) throws PaperException;
	/***
	 * POI导出InputStream->EXCEL
	 * @param newss
	 * @return
	 * @throws PaperException
	 */
	InputStream findInputStreamNews(List<News> newss) throws PaperException;
	/***
	 * 根据subjectid获取相应新闻的数量
	 * @param subjectid
	 * @return int
	 * @throws PaperException
	 */
	int findNewsSumBySubject(Integer subjectid) throws PaperException;
	/***
	 * 根据搜索字段和关键字获取所有news记录数量
	 * @param select
	 * @param words
	 * @return int
	 * @throws PaperException
	 */
	int findNewsBySelectWordsSum(String select, String words) throws PaperException;
	/***
	 * 根据搜索字段和关键字获取所有news记录
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

