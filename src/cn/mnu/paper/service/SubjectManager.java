package cn.mnu.paper.service;
import java.util.List;

import cn.mnu.paper.domain.*;
import cn.mnu.paper.exception.PaperException;

public interface SubjectManager {
	/***
	 * ��ȡ���л��Subject
	 * @return ���Subject
	 * @throws PaperException 
	 */
	List<Subject> findActiveSubjects() throws PaperException;
	/***
	 * ��ȡ���е�Subject
	 * @return ���Subject
	 * @throws PaperException 
	 */
	List<Subject> findAllSubjects(int start, int limit, String sort, String dir) throws PaperException;
	/***
	 * ��Name����queryƥ����һ�ȡ���л��Subject
	 * @param query
	 * @param start ��ʼ
	 * @param limit ��С
	 * @return ����������Subject
	 * @throws PaperException
	 */
	List<Subject> findActiveSubjectsByNameQuery(String query, int start, int limit) throws PaperException;
	
	/***
	 * ��ȡ���л��Subject������
	 * @return ������
	 */
	int getAllAciveSubjectSum() throws PaperException;
	/***
	 * ��ȡ���е�Subject������
	 * @return ������
	 */
	int getAllSubjectSum() throws PaperException;
	
	/***
	 * ��ȡquery���е�Subject������
	 * @return ������
	 */
	int getQuerySubjectSum(String query) throws PaperException;
	/***
	 * ���ר������
	 * @param subject ����ӵ���ר��
	 * @return subjet id
	 * @throws PaperException
	 */
	Integer addSubject(Subject subject) throws PaperException;
	/***
	 * �޸�ר��
	 * @param subject Ҫ�޸ĵ�ר��
	 * @return �޸ĺ��subject id
	 * @throws PaperException
	 */
	Integer changeSubject(Subject subject) throws PaperException;
	/***
	 * ����IDɾ��ר��
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeSubject(Integer id) throws PaperException;
	/***
	 * ����ID�޸�ר���״̬���޸�Ϊ1
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeSubjectStTo1(Integer id) throws PaperException;
	/***
	 * ����ID�޸�ר���״̬���޸�Ϊ0
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeSubjectStTo0(Integer id) throws PaperException;
}
