package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Subject;

/***
 * ר��DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-17
 */
public interface SubjectDao extends BaseDao<Subject> {
	/***
	 * ��ȡ����subjects��¼������
	 * @return
	 */
	int getAllRows();
	/***
	 * ��ȡ���л��subjects��¼������
	 * @return
	 */
	int getAllActiveRows();
	/***
	 * ��ȡ���е�subjects��¼
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return Subject��¼
	 */
	List<Subject> getAllSubjects(String sort, String dir);
	/***
	 * ��ȡ���л��subjects��¼
	 * @return Subject��¼
	 */
	List<Subject> getAllActiveSubjects();
	
	/***
	 * ��ȡquery���subjects��¼������
	 * @return
	 */
	int getActiveRows(String query);
	/***
	 * ��Name����queryƥ����һ�ȡ���л��subjects��¼
	 * @param query
	 * @param start ��ʼ
	 * @param limit ��С
	 * @return
	 */
	List<Subject> getActiveSubjectsByNameQurey(String query);
	
}
