package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.*;
/***
 * 报纸内容DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public interface NewsDao extends BaseDao<News> {
	/***
	 * 根据期号和版面号查询所有News
	 * @param paperid 期号
	 * @param layoutid 版面号
	 * @return 所有News
	 */
	List<News> findNewsByPaperAndLayout(Integer paperid, Integer layoutid);
	
	/***
	 * 获取所有news记录的总数
	 * @return int
	 */
	int getAllRows();
	/***
	 * 根据搜索字段和关键字获取所有news记录的总数
	 * @param work 搜索字段
	 * @param query 关键字
	 * @return int
	 */
	int getAllRowsByWorkAndQuery(String work, String query);
	/***
	 * 根据搜索字段和关键字获取所有news记录
	 * @param work
	 * @param query
	 * @return
	 */
	List<News> getNewsByWorkQuery(String work, String query);
	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return List<News>
	 */
	List<News> getNewsByFind(Integer paperid, Integer subjectid, String year, String author);
	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索总数
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return int
	 */
	int getNewsByFindSum(Integer paperid, Integer subjectid, String year, String author);
	/***
	 * 根据subjectid查询出相应News的数量;
	 * @param subjectid
	 * @return int
	 */
	int getNewsSumBySubject(Integer subjectid);
	
	/***
	 * 根据搜索字段和关键字获取所有news记录
	 * @param start
	 * @param limit
	 * @param select
	 * @param words
	 * @return List<News>
	 */
	List<News> getNewsBySelectWords(int start, int limit, String select, String words);
	/***
	 * 根据搜索字段和关键字获取所有news记录数量
	 * @param select
	 * @param words
	 * @return int
	 */
	int getNewsBySelectWordsSum(String select, String words);
}
