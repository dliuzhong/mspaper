package cn.mnu.paper.dao;

import java.util.Date;
import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Article;

/***
 * 稿件DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public interface ArticleDao extends BaseDao<Article> {
	/***
	 * 查询所有稿件总数量
	 * @return 所有稿件总数量
	 */
	int getAllArticleSum();
	/***
	 * 查询所有稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有稿件
	 */
	List<Article> getAllArticle(int start, int limit, String sort, String dir);
	/***
	 * 查询相应状态的稿件总数量
	 * @param 要查询的状态
	 * @return 相应状态的稿件总数量
	 */
	int getStatusArticleSum(int status);
	/***
	 * 查询相应状态的稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 相应状态的稿件
	 */
	List<Article> getStatusArticle(int status, int start, int limit, 
			String sort, String dir);
	/***
	 * 根据用户id查询所有相应稿件
	 * @param msuserid 用户id
	 * @param start 开始
	 * @param limit 数量
	 * @return List<Article>
	 */
	List<Article> getArticleByMsuserid(Integer msuserid, int start, int limit);
	/***
	 * 根据用户id查询所有相应稿件数量
	 * @param msuserid 用户id
	 * @return int
	 */
	int getArticleByMsuseridSum(Integer msuserid);
	/***
	 * 根据关键字sort和id查询数量
	 * @param sort 方式
	 * @param id
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return 数量
	 */
	int getArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2);
	/***
	 * 根据方式，范围，时间范围查询数量
	 * @param sort 方式
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return
	 */
	int getArticleAllSum(String sortMain, String sort, String contain, Date date1, Date date2);
}
