package cn.mnu.paper.service;
import java.util.List;

import cn.mnu.paper.domain.*;
import cn.mnu.paper.exception.PaperException;

public interface SubjectManager {
	/***
	 * 获取所有活动的Subject
	 * @return 活动的Subject
	 * @throws PaperException 
	 */
	List<Subject> findActiveSubjects() throws PaperException;
	/***
	 * 获取所有的Subject
	 * @return 活动的Subject
	 * @throws PaperException 
	 */
	List<Subject> findAllSubjects(int start, int limit, String sort, String dir) throws PaperException;
	/***
	 * 对Name进行query匹配查找获取所有活动的Subject
	 * @param query
	 * @param start 开始
	 * @param limit 大小
	 * @return 满足条件的Subject
	 * @throws PaperException
	 */
	List<Subject> findActiveSubjectsByNameQuery(String query, int start, int limit) throws PaperException;
	
	/***
	 * 获取所有活动的Subject总数量
	 * @return 总数量
	 */
	int getAllAciveSubjectSum() throws PaperException;
	/***
	 * 获取所有的Subject总数量
	 * @return 总数量
	 */
	int getAllSubjectSum() throws PaperException;
	
	/***
	 * 获取query所有的Subject总数量
	 * @return 总数量
	 */
	int getQuerySubjectSum(String query) throws PaperException;
	/***
	 * 添加专题内容
	 * @param subject 所添加的新专题
	 * @return subjet id
	 * @throws PaperException
	 */
	Integer addSubject(Subject subject) throws PaperException;
	/***
	 * 修改专题
	 * @param subject 要修改的专题
	 * @return 修改后的subject id
	 * @throws PaperException
	 */
	Integer changeSubject(Subject subject) throws PaperException;
	/***
	 * 根据ID删除专题
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeSubject(Integer id) throws PaperException;
	/***
	 * 根据ID修改专题的状态，修改为1
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeSubjectStTo1(Integer id) throws PaperException;
	/***
	 * 根据ID修改专题的状态，修改为0
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeSubjectStTo0(Integer id) throws PaperException;
}
