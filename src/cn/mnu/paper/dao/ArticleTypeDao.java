package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.ArticleType;

/***
 * 稿件类型DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface ArticleTypeDao extends BaseDao<ArticleType> {
	/***
	 * 查询所有稿件类型的总数量
	 * @return int 总数量
	 */
	int getAllArticleTypeSum();
	/***
	 * 查询所有稿件类型
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return所有稿件类型
	 */
	List<ArticleType> getAllArticleType(int start, int limit, String sort, String dir);
}
