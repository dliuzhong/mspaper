package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.ArticleTypeDao;
import cn.mnu.paper.domain.ArticleType;
/***
 * ArticleTypeDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class ArticleTypeDaoHibernate extends BaseDaoHibernate<ArticleType> 
	implements ArticleTypeDao {

	/***
	 * 查询所有稿件类型的总数量
	 * @return int 总数量
	 */
	public int getAllArticleTypeSum() {
		return findAll(ArticleType.class).size();
	}

	/***
	 * 查询所有稿件类型
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return所有稿件类型
	 */
	public List<ArticleType> getAllArticleType(int start, int limit,
			String sort, String dir) {
		String hql = "select at from ArticleType at ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			hql += "order by at." + sort + " " + dir;
		} else {
			hql += "order by at.id DESC";
		}
		List<ArticleType> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

}
