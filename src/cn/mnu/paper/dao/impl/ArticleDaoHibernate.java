package cn.mnu.paper.dao.impl;

import java.util.Date;
import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.ArticleDao;
import cn.mnu.paper.domain.Article;
/***
 * ArticleDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public class ArticleDaoHibernate extends BaseDaoHibernate<Article>
	implements ArticleDao {

	/***
	 * 查询所有稿件总数量
	 * @return 所有稿件总数量
	 */
	public int getAllArticleSum() {
		return findAll(Article.class).size();
	}

	/***
	 * 查询所有稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有稿件
	 */
	public List<Article> getAllArticle(int start, int limit, String sort,
			String dir) {
		String hql = "select a from Article a ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.equals("msuser")) {
				hql += "order by a.msuser.name " + dir;
			} else if (sort.equals("type")) {
				hql += "order by a.type.name " + dir;
			}  else if (sort.equals("status")) {
				hql += "order by a.status.name " + dir;
			} else {
				hql += "order by a." + sort + " " + dir;
			}
		} else {
			hql += "order by a.checktime DESC, a.time DESC";
		}
		List<Article> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * 查询相应状态的稿件总数量
	 * @param 要查询的状态
	 * @return 相应状态的稿件总数量
	 */
	public int getStatusArticleSum(int status) {
		if (status == 1) {
			return find("select a from Article a where a.status.id=1 or a.status.id=2").size();
		}
		return find("select a from Article a where a.status.id=?0", status).size();
	}

	/***
	 * 查询相应状态的稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 相应状态的稿件
	 */
	public List<Article> getStatusArticle(int status, int start,
			int limit, String sort, String dir) {
		String hql;
		if (status == 0 || status == 1) {
			hql = "select a from Article a where a.status.id=1 or a.status.id=2";
		} else {
			hql = "select a from Article a where a.status.id=" + status + " ";
		}
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.equals("msuser")) {
				hql += "order by a.msuser.name " + dir;
			} else if (sort.equals("type")) {
				hql += "order by a.type.name " + dir;
			} else {
				hql += "order by a." + sort + " " + dir;
			}
		} else {
			if (status == 0 || status == 1) {
				hql += "order by a.time DESC";
			} else {
				hql += "order by a.checktime DESC, a.time DESC";
			}
			
		}
		List<Article> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * 根据用户id查询所有相应稿件
	 * @param msuserid 用户id
	 * @param start 开始
	 * @param limit 数量
	 * @return List<Article>
	 */
	public List<Article> getArticleByMsuserid(Integer msuserid, int start,
			int limit) {
		List<Article> al = find("select a from Article a where a.msuser.id=?0 order by a.time DESC",
				msuserid);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * 根据用户id查询所有相应稿件数量
	 * @param msuserid 用户id
	 * @return int
	 */
	public int getArticleByMsuseridSum(Integer msuserid) {
		return find("select a from Article a where a.msuser.id=?0 order by a.time DESC",
				msuserid).size();
	}

	/***
	 * 根据关键字sort和id查询数量
	 * @param sort
	 * @param id
	 * @return 数量
	 */
	public int getArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2) {
		String hql = "select a from Article a where";
		if (sort != null && !sort.trim().equals("")) {
			if (sort.trim().equals("depart")) {
				hql += " a.msuser.department.id=" + id;
			}
			if (sort.trim().equals("msuser")) {
				hql += " a.msuser.id=" + id;
			}
			if (sort.trim().equals("type")) {
				hql += " a.type.id=" + id;
				
			}
			if (contain != null && contain.trim().equals("pass")) {
				hql += " and a.status=4";
			}
			if (contain != null && contain.trim().equals("publish")) {
				hql += " and a.publish=1";
			}
			if (date1 != null && date2 != null) {
				hql += " and a.time>=?0 and a.time<=?1";
				return find(hql, date1, date2).size();
			}
			return find(hql).size();
		} else {
			return 0;
		}
	}

	/***
	 * 根据方式，范围，时间范围查询数量
	 * @param sort 方式
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return
	 */
	public int getArticleAllSum(String sortMain, String sort, String contain, Date date1,
			Date date2) {
		String hql = "select a from Article a where";
		if (sort != null && !sort.trim().equals("")) {
			// 部门
			if (sortMain.trim().equals("depart")) {
				hql += " a.msuser.department.";
				// 内，外，所有
				if (sort.trim().equals("in")) {
					hql += "type=1";
				} else if(sort.trim().equals("out")) {
					hql += "type=2";
				} else {
					hql += "type!=null";
				}
				// 用户
			} else if (sortMain.trim().equals("msuser")) {
				hql += " a.msuser.";
				// 内，外，所有
				if (sort.trim().equals("in")) {
					hql += "type like '内部用户'";
				} else if (sort.trim().equals("out")){
					hql += "type like '外部用户'";
				} else {
					hql += "type!=null";
				}
				// 稿件类型
			} else if (sortMain.trim().equals("type")) {
				hql += " a.id != null";
				
			}
			// 稿件状态
			if (contain != null && contain.trim().equals("pass")) {
				hql += " and a.status=4";
			}
			// 稿件是否发表
			if (contain != null && contain.trim().equals("publish")) {
				hql += " and a.publish=1";
			}
			// 日期范围
			if (date1 != null && date2 != null) {
				hql += " and a.time>=?0 and a.time<=?1";
				//System.out.println(hql);
				return find(hql, date1, date2).size();
			}
			//System.out.println(hql);
			return find(hql).size();
		} else {
			return 0;
		}
	}

}
