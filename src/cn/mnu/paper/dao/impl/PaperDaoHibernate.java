package cn.mnu.paper.dao.impl;



import java.sql.Date;
import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.domain.*;
import cn.mnu.paper.dao.PaperDao;
/***
 * PaperDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public class PaperDaoHibernate extends BaseDaoHibernate<Paper> 
	implements PaperDao {

	/***
	 * 根据期号查找报纸
	 * @param paper 期号
	 * @return 对应的报纸
	 */
	public Paper findPaperByPaper(String paper) {
		
		List<Paper> pl = find("select p from Paper p where p.paper=?0", paper);
		if (pl != null && pl.size() >= 1) {
			return (Paper) pl.get(0);
		}
		return null;
	}

	/***
	 * 根据期号和年查找报纸List<Paper>
	 * @param paper
	 * @param year
	 * @return List<Paper>
	 */
	public List<Paper> findPaperByPaperAndYear(String paper, String year) {
		if (year.trim().equals("")) {
			String hql = "select p from Paper p where p.paper like '%" + paper + "%' order by p.cbtime DESC";
			return (List<Paper>) find(hql);
		} else {
			Date beginDate = Date.valueOf(year + "-1-1");
			Date endDate = Date.valueOf(year + "-12-31"); 
			String hql = "select p from Paper p where p.paper like '%" + paper + "%' " +
					"and p.cbtime>=?0 and p.cbtime<=?1 order by p.cbtime DESC";
			return (List<Paper>) find(hql, beginDate, endDate);
		}
		
		
		
		
	}

	/***
	 * 获取所有papers记录的总数
	 * @return int
	 */
	public int getAllRows() {
		return findAll(Paper.class).size();
	}

	/***
	 * 获取所有papers记录的总数
	 * @param query 关键字
	 * @return int
	 */
	public int getAllRows(String query) {
		String hql = "select p from Paper p where p.paper like '%" + query + "%'";
		
		return find(hql).size();
	}

	/***
	 * 对paper进行query匹配查找获取所有活动的papers记录
	 * @param query
	 * @return
	 */
	public List<Paper> getPaperByPaperQuery(String query) {
		String hql = "select p from Paper p where p.paper like '%" + query + "%' order by p.cbtime DESC, p.id DESC";
		
		return find(hql);
	}

	/***
	 * 根据 出版时间cbtime查询所有paper
	 * @return List<Paper>
	 */
	public List<Paper> getAllPaperOrderByCbtime() {
		return find("select p from Paper p where p.paper_url != null and p.paper_url=p.paper" +
				" order by p.cbtime DESC, p.id DESC");
	}

	/***
	 * 查询日期最近的Paper
	 * @return Paper
	 */
	public Paper getLastPaper() {
		List<Paper> pl = find("select p from Paper p where p.paper_url != null and p.paper_url=p.paper" +
				" order by p.cbtime DESC, p.id DESC");
		
		if (pl != null && pl.size() > 0) {
			return pl.get(0);
		} else {
			return null;
		}
	}

	/***
	 * 查询第一期报纸
	 * @return Paper
	 */
	public Paper getFirstPaper() {
		List<Paper> pl = find("select p from Paper p where p.paper_url != null and p.paper_url=p.paper" +
				" order by p.cbtime, p.id");
		if (pl != null && pl.size() > 0) {
			return pl.get(0);
		} else {
			return null;
		}
	}


}
