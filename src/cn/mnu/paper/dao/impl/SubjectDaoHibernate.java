package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.SubjectDao;
import cn.mnu.paper.domain.Subject;
/***
 * SubjectDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-07-17
 */
public class SubjectDaoHibernate extends BaseDaoHibernate<Subject>
	implements SubjectDao {
	/***
	 * ��ȡ����subjects��¼������
	 * @return ����
	 */
	public int getAllRows() {
		
		return findAll(Subject.class).size();
	}

	/***
	 * ��ȡ���л��subjects��¼������
	 * @return
	 */
	public int getAllActiveRows() {
		return find("select s from Subject s where s.st=1").size();
	}
	/***
	 * ��ȡ���е�subjects��¼
	 * @return Subject��¼
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
	 * ��ȡ���л��subjects��¼
	 * @return Subject��¼
	 */
	public List<Subject> getAllActiveSubjects() {
		
		return (List<Subject>) find("select s from Subject s where s.st=1 order by s.time DESC, s.id DESC");
	}
	/***
	 * ��ȡquery���subjects��¼������
	 * @return
	 */
	public int getActiveRows(String query) {
		String hql = "select s from Subject s where s.st=1 and s.name like '%" + query + "%'";
	
		return find(hql).size();
	}
	/***
	 * ��Name����queryƥ����һ�ȡ���л��subjects��¼
	 * @param query
	 * @return
	 */
	public List<Subject> getActiveSubjectsByNameQurey(String query) {
		String hql = "select s from Subject s where s.st=1 and s.name like '%" + query + "%' order by s.time DESC, s.id DESC";
		
		return find(hql);
	}

	

}
