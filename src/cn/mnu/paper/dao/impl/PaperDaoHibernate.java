package cn.mnu.paper.dao.impl;



import java.sql.Date;
import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.domain.*;
import cn.mnu.paper.dao.PaperDao;
/***
 * PaperDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public class PaperDaoHibernate extends BaseDaoHibernate<Paper> 
	implements PaperDao {

	/***
	 * �����ںŲ��ұ�ֽ
	 * @param paper �ں�
	 * @return ��Ӧ�ı�ֽ
	 */
	public Paper findPaperByPaper(String paper) {
		
		List<Paper> pl = find("select p from Paper p where p.paper=?0", paper);
		if (pl != null && pl.size() >= 1) {
			return (Paper) pl.get(0);
		}
		return null;
	}

	/***
	 * �����ںź�����ұ�ֽList<Paper>
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
	 * ��ȡ����papers��¼������
	 * @return int
	 */
	public int getAllRows() {
		return findAll(Paper.class).size();
	}

	/***
	 * ��ȡ����papers��¼������
	 * @param query �ؼ���
	 * @return int
	 */
	public int getAllRows(String query) {
		String hql = "select p from Paper p where p.paper like '%" + query + "%'";
		
		return find(hql).size();
	}

	/***
	 * ��paper����queryƥ����һ�ȡ���л��papers��¼
	 * @param query
	 * @return
	 */
	public List<Paper> getPaperByPaperQuery(String query) {
		String hql = "select p from Paper p where p.paper like '%" + query + "%' order by p.cbtime DESC, p.id DESC";
		
		return find(hql);
	}

	/***
	 * ���� ����ʱ��cbtime��ѯ����paper
	 * @return List<Paper>
	 */
	public List<Paper> getAllPaperOrderByCbtime() {
		return find("select p from Paper p where p.paper_url != null and p.paper_url=p.paper" +
				" order by p.cbtime DESC, p.id DESC");
	}

	/***
	 * ��ѯ���������Paper
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
	 * ��ѯ��һ�ڱ�ֽ
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
