package cn.mnu.paper.dao.impl;


import java.sql.Date;
import java.util.List;

import cn.mnu.paper.dao.NewsDao;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.common.dao.impl.*;
/***
 * NewsDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public class NewsDaoHibernate extends BaseDaoHibernate<News> 
	implements NewsDao {

	/***
	 * 根据期号和版面号查询所有News
	 * @param paperid 期号
	 * @param layoutid 版面号
	 * @return 所有News
	 */
	public List<News> findNewsByPaperAndLayout(Integer paperid, Integer layoutid) {
	
		return (List<News>) find("select n from News n where n.paper.id=?0 and n.layout.id=?1",
					paperid, layoutid);
	}

	/***
	 * 获取所有news记录的总数
	 * @return int
	 */
	public int getAllRows() {
		return findAll(News.class).size();
	}

	/***
	 * 根据搜索字段和关键字获取所有news记录的总数
	 * @param work 搜索字段
	 * @param query 关键字
	 * @return int
	 */
	public int getAllRowsByWorkAndQuery(String work, String query) {
		if (work.equals("word")) {
			String hql = "select n from News n where n.title like '%" + query + "%'" +
					" or n.author like '%" + query + "%' or n.content like '%" +
					query + "%' or n.subject.name like '%" + query + "%'";
			return find(hql).size();
		} else if (work.equals("title")) {
			String hql = "select n from News n where n.title like '%" + query + "%'";
			return find(hql).size();
		} else if (work.equals("author")) {
			String hql = "select n from News n where n.author like '%" + query + "%'";
			return find(hql).size();
		} else if (work.equals("subject")) {
			String hql = "select n from News n where n.subject.name like '%" + query + "%'";
			return find(hql).size();
		} else {
			return findAll(News.class).size();
		}
	}

	/***
	 * 根据搜索字段和关键字获取所有news记录
	 * @param work
	 * @param query
	 * @return
	 */
	public List<News> getNewsByWorkQuery(String work, String query) {
		if (work.equals("word")) {
			String hql = "select n from News n where n.title like '%" + query + "%'" +
					" or n.author like '%" + query + "%' or n.content like '%" +
					query + "%' or n.subject.name like '%" + query + "%' order by n.id DESC";
			return find(hql);
		} else if (work.equals("title")) {
			String hql = "select n from News n where n.title like '%" + query + "%' order by n.id DESC";
			return find(hql);
		} else if (work.equals("author")) {
			String hql = "select n from News n where n.author like '%" + query + "%' order by n.id DESC";
			return find(hql);
		} else if (work.equals("subject")) {
			String hql = "select n from News n where n.subject.name like '%" + query + "%' order by n.id DESC";
			return find(hql);
		} else {
			return find("select n from News n order by n.id DESC");
		}
	}

	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return List<News>
	 */
	public List<News> getNewsByFind(Integer paperid, Integer subjectid,
			String year, String author) {
		int i = 0;
		String hql = "select n from News n where";
		
		if (author != null && !author.trim().equals("")) {
			
			hql += " n.author like '%" + author + "%'";
			i++;
		}
		if (paperid != null && paperid != 0) {
			if (i >= 1) {
				hql += " and n.paper.id=" + paperid;
			} else {
				hql += " n.paper.id=" + paperid;
			}
			i++;
			
		}
		if (subjectid != null && subjectid != 0) {
			if (i >= 1) {
				hql += " and n.subject.id=" + subjectid;
			} else {
				hql += " n.subject.id=" + subjectid;
			}
			i++;
		}
		if (year != null && !year.trim().equals("")) {
			Date beginDate = Date.valueOf(year + "-1-1");
			Date endDate = Date.valueOf(year + "-12-31");
			if (i >= 1) {
				hql += " and n.paper.cbtime>=?0 and n.paper.cbtime<=?1 order by n.paper.cbtime DESC, n.id DESC";
			} else {
				hql += " n.paper.cbtime>=?0 and n.paper.cbtime<=?1 order by n.paper.cbtime DESC, n.id DESC";
			}
			i++;
			
			//System.out.println(hql);
			return (List<News>) find(hql, beginDate, endDate);
		} else {
			if (i == 0) {
				return (List<News>) findAll(News.class);
			} else {
				//System.out.println(hql);
				return (List<News>) find(hql +  " order by n.paper.cbtime DESC, n.id DESC");
			}
			
		}
		
	}

	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索总数
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return int
	 */
	public int getNewsByFindSum(Integer paperid, Integer subjectid,
			String year, String author) {
		int i = 0;
		String hql = "select n from News n where";
		
		if (author != null && !author.trim().equals("")) {
			
			hql += " n.author like '%" + author + "%'";
			i++;
		}
		if (paperid != null && paperid != 0) {
			if (i >= 1) {
				hql += " and n.paper.id=" + paperid;
			} else {
				hql += " n.paper.id=" + paperid;
			}
			i++;
			
		}
		if (subjectid != null && subjectid != 0) {
			if (i >= 1) {
				hql += " and n.subject.id=" + subjectid;
			} else {
				hql += " n.subject.id=" + subjectid;
			}
			i++;
		}
		if (year != null && !year.trim().equals("")) {
			Date beginDate = Date.valueOf(year + "-1-1");
			Date endDate = Date.valueOf(year + "-12-31");
			if (i >= 1) {
				hql += " and n.paper.cbtime>=?0 and n.paper.cbtime<=?1";
			} else {
				hql += " n.paper.cbtime>=?0 and n.paper.cbtime<=?1";
			}
			i++;
			
			
			return find(hql, beginDate, endDate).size();
		} else {
			if (i == 0) {
				return findAll(News.class).size();
			} else {
				return find(hql).size();
			}
			
		}
		
	}

	/***
	 * 根据subjectid查询出相应News的数量;
	 * @param subjectid
	 * @return
	 */
	public int getNewsSumBySubject(Integer subjectid) {
		return find("select n from News n where n.subject.id=?0", subjectid).size();
	}


	/***
	 * 根据搜索字段和关键字获取所有news记录
	 * @param start
	 * @param limit
	 * @param select
	 * @param words
	 * @return List<News>
	 */
	public List<News> getNewsBySelectWords(int start, int limit, String select,
			String words) {
		String hql;
		if (select.equals("word")) {
			hql = "select n from News n where n.title like '%" + words + "%'" +
					" or n.author like '%" + words + "%' or n.content like '%" +
					words + "%' or n.subject.name like '%" + words + "%'";
			
		} else if (select.equals("title")) {
			hql = "select n from News n where n.title like '%" + words + "%'";
			
		} else if (select.equals("author")) {
			hql = "select n from News n where n.author like '%" + words + "%'";
			
		} else if (select.equals("subject")) {
			hql = "select n from News n where n.subject.name like '%" + words + "%'";
			
		} else {
			return null;
		}
		hql += " order by n.id DESC, n.paper.cbtime DESC";
		List<News> nl = find(hql);
		if (start + limit <= nl.size())
			return nl.subList(start, start + limit);
		else 
			return nl.subList(start, nl.size());
	}

	/***
	 * 根据搜索字段和关键字获取所有news记录数量
	 * @param select
	 * @param words
	 * @return int
	 */
	public int getNewsBySelectWordsSum(String select, String words) {
		String hql;
		if (select.equals("word")) {
			hql = "select n from News n where n.title like '%" + words + "%'" +
					" or n.author like '%" + words + "%' or n.content like '%" +
					words + "%' or n.subject.name like '%" + words + "%'";
			
		} else if (select.equals("title")) {
			hql = "select n from News n where n.title like '%" + words + "%'";
			
		} else if (select.equals("author")) {
			hql = "select n from News n where n.author like '%" + words + "%'";
			
		} else if (select.equals("subject")) {
			hql = "select n from News n where n.subject.name like '%" + words + "%'";
			
		} else {
			return 0;
		}
		
		return find(hql).size();
		
	}

	
}
