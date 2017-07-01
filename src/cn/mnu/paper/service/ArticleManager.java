package cn.mnu.paper.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.bean.SortBean;
import cn.mnu.paper.domain.Article;
/***
 * Article业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public interface ArticleManager {

	/***
	 * 获取所有稿件的总数量
	 * @return 所有稿件的总数量
	 * @throws PaperException
	 */
	int findAllArticleSum() throws PaperException;
	/***
	 * 获取所有稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有稿件
	 * @throws PaperException
	 */
	List<Article> findAllArticle(int start, int limit, String sort, 
			String dir) throws PaperException;
	/***
	 * 获取相应状态的稿件的总数量
	 * @return 相应状态的稿件的总数量
	 * @throws PaperException
	 */
	int findStatusArticleSum(int status) throws PaperException;
	/***
	 * 获取相应状态的稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 相应状态的稿件
	 * @throws PaperException
	 */
	List<Article> findStatusArticle(int status, int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * 把相应ID的状态从status_f改为status_t
	 * @param article 
	 * @param status_f 原状态
	 * @param status_t 目标状态
	 * @param adminid 审核的管理员ID
	 * @return articleid
	 * @throws PaperException
	 */
	int changeArticleStatus(Article article, int status_f, int status_t,
			Integer adminid) throws PaperException;
	
	/***
	 * 根据ID获取Article
	 * @param id
	 * @return 相应ID的Article
	 * @throws PaperException
	 */
	Article findArticleById(Integer id) throws PaperException;
	/***
	 * 管理员修改article
	 * @param article 要修改的article
	 * @param adminid
	 * @return article id
	 * @throws PaperException
	 */
	int adminChangeEditArticle(Article article, Integer adminid) throws PaperException;
	/***
	 * 根据ID导出Word
	 * @param id
	 * @return InputStream
	 * @throws PaperException
	 */
	InputStream findArticleToExport(Integer id) throws PaperException;
	/***
	 * 根据ID删除article
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleById(Integer id) throws PaperException;
	/***
	 * 根据用户ID获取所有相应的稿件数量
	 * @param id 用户ID
	 * @return 相应的稿件数量
	 * @throws PaperExcetion
	 */
	int findArticleByMsuserSum(Integer msuserid) throws PaperException;
	/***
	 * 根据用户ID获取所有相应的稿件
	 * @param id 用户ID
	 * @param start 开始
	 * @param limit 数量
	 * @return List<Article>
	 * @throws PaperException
	 */
	List<Article> findArticleByMsuser(Integer msuserid, int start, int limit)
		throws PaperException;
	/***
	 * 用户投稿，添加稿件
	 * @param article 添加稿件
	 * @param Msuserid 用户ID
	 * @param typeid 稿件类型ID
	 * @return 添加稿件的ID
	 * @throws PaperException
	 */
	Integer addArticle(Article article, Integer msuserid, Integer typeid)
		throws PaperException;
	
	/***
	 * 根据ID删除article状态为1的记录
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleStatus1ById(Integer id) throws PaperException;
	/***
	 * 用户修改article
	 * @param article
	 * @param typeid
	 * @return article id
	 * @throws PaperException
	 */
	Integer changeMyArticle(Article article, Integer typeid) throws PaperException;
	/***
	 * 根据关键字sort和id查询数量
	 * @param sort 方式
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return 数量
	 */
	int findArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2) throws PaperException;
	/***
	 * 根据方式，范围，时间范围查询数量
	 * @param sort 方式
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return
	 * @throws PaperException
	 */
	int findAllArticleBySortSum(String sortMain, String sort, String contain, Date date1, Date date2) throws PaperException;
	/***
	 * 在aritlcle.other里添加信息
	 * @param data 添加的信息
	 * @param article
	 * @return article id
	 * @throws PaperException
	 */
	int changeArticleAddDataToOther(String data, Article article) throws PaperException;
	/***
	 * 导出Article统计SortBean
	 * @param sbList
	 * @param allArticle
	 * @return InputStream
	 * @throws PaperException
	 */
	InputStream findInputStreamArticles(List<SortBean> sbList, int allArticle,
			String sortMain, String sort, String contain, Date date1, Date date2, String other) throws PaperException;
}
