package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.SubjectDao;
import cn.mnu.paper.domain.Subject;
/***
 * SubjectDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-07-17
 */
public class SubjectDaoHibernate extends BaseDaoHibernate<Subject>
	implements SubjectDao {
	/***
	 * 获取所有subjects记录的总数
	 * @return 总数
	 */
	public int getAllRows() {
		
		return findAll(Subject.class).size();
	}

	/***
	 * 获取所有活动的subjects记录的总数
	 * @return
	 */
	public int getAllActiveRows() {
		return find("select s from Subject s where s.st=1").size();
	}
	/***
	 * 获取所有的subjects记录
	 * @return Subject记录
	 */
	public List<Subject> getAllSubjects(String sort, String dir) {
		String hql;
		//System.out.println("sort:"+ sort +","+ dir);
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			hql = "select s from Subject s order by s." + sort + " " + dir;
		} else {
			hql = "select s from Subject s order by s.time DESC";
		}
		return (List<Subject>) find(hql);

	}
	/***
	 * 获取所有活动的subjects记录
	 * @return Subject记录
	 */
	public List<Subject> getAllActiveSubjects() {
		
		return (List<Subject>) find("select s from Subject s where s.st=1 order by s.time DESC, s.id DESC");
	}
	/***
	 * 获取query活动的subjects记录的总数
	 * @return
	 */
	public int getActiveRows(String query) {
		String hql = "select s from Subject s where s.st=1 and s.name like '%" + query + "%'";
	
		return find(hql).size();
	}
	/***
	 * 对Name进行query匹配查找获取所有活动的subjects记录
	 * @param query
	 * @return
	 */
	public List<Subject> getActiveSubjectsByNameQurey(String query) {
		String hql = "select s from Subject s where s.st=1 and s.name like '%" + query + "%' order by s.time DESC, s.id DESC";
		
		return find(hql);
	}

	

}
